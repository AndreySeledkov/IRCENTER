package org.ircenter.serv.dataserver.images.impl.resize;

import org.ircenter.serv.dataserver.images.ResizeService;
import org.ircenter.serv.dataserver.images.resize.RConf;
import org.ircenter.serv.dataserver.images.resize.RResult;
import org.ircenter.serv.dataserver.images.resize.ResizeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:12
 */
public class ListResizeService implements ResizeService {

    private ResizeServiceImpl localResizer;
    private volatile Server[] servers;
    private AtomicInteger counter = new AtomicInteger();
    private AtomicLong useLocalResizer = new AtomicLong();
    private AtomicLong useResizer = new AtomicLong();

    public ListResizeService() {
        try {
            localResizer = new ResizeServiceImpl();
        } catch (Exception ex) {
            throw new Error(ex);
        }
        reloadServers();
    }

    private Server[] loadServers() {
        List<Server> result = new ArrayList<Server>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("resize_servers.list")));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Server s = parse(line);
                if (s != null) {
                    result.add(s);
                }
            }
            reader.close();
        } catch (Exception ex) {
        }

        return result.toArray(new Server[0]);
    }

    public final void reloadServers() {
        servers = loadServers();

        System.out.println("ResizeServer count = " + servers.length);
        for (int i = 0; i < servers.length; ++i) {
            System.out.println("ResizeServer" + i + ".url = " + servers[i].getUrl());
        }
    }

    @Override
    public String testConnection() {
        return "OK";
    }

    public long getUseLocalResizer() {
        return useLocalResizer.longValue();
    }

    public long getUseRemoteResizer() {
        return useResizer.intValue() - useLocalResizer.longValue();
    }

    public Server[] getServers() {
        return servers;
    }

    private int getOffset() {
        int offset = counter.incrementAndGet();
        if (offset > Integer.MAX_VALUE / 2) {
            offset = 0;
            counter.set(0);
        }
        return offset;
    }

    @Override
    public RResult resize(RConf conf, byte[] image) throws ResizeException {
        useResizer.incrementAndGet();

        Server[] list = servers;
        int offset = getOffset();
        for (int i = 0; i < list.length; ++i) {
            Server server = list[(i + offset) % list.length];
            boolean acquired = server.tryAcquire();
            if (acquired) {
                long T0 = System.nanoTime();
                try {
                    RResult r = server.service.resize(conf, image);
                    return r;
                } catch (ResizeException ex) {
                    throw ex;
                } catch (RemoteException ex) {
                    server.errCounter.incrementAndGet();
                } finally {
                    server.release();
                }
            }
        }

        useLocalResizer.incrementAndGet();
        return localResizer.resize(conf, image);
    }

    @Override
    public RResult rotate(RConf conf, byte[] image, int angle) throws ResizeException {
        useResizer.incrementAndGet();

        Server[] list = servers;
        int offset = getOffset();
        for (int i = 0; i < list.length; ++i) {
            Server server = list[(i + offset) % list.length];

            boolean acquired = server.tryAcquire();
            if (acquired) {
                long T0 = System.nanoTime();
                try {
                    RResult r = server.service.rotate(conf, image, angle);
                    return r;
                } catch (ResizeException ex) {
                    throw ex;
                } catch (RemoteException ex) {
                    server.errCounter.incrementAndGet();
                } finally {
                    server.release();
                }
            }
        }

        useLocalResizer.incrementAndGet();
        return localResizer.rotate(conf, image, angle);
    }

    private static Server parse(String text) {
        try {
            String temp[] = text.split(" ");
            int maxThreads = Integer.parseInt(temp[1]);
            text = temp[0];
            String name = text.substring(text.indexOf("/") + 1);
            text = text.substring(0, text.indexOf("/"));
            int port = Integer.parseInt(text.substring(text.indexOf(':') + 1));
            String host = text.substring(0, text.indexOf(':'));
            return new Server(host, port, name, maxThreads);
        } catch (Exception ex) {
            return null;
        }
    }

    public static class Server {

        private String host;
        private String name;
        private ResizeService service;
        private int maxThreads;
        private Semaphore semaphore;
        private AtomicInteger errCounter = new AtomicInteger();

        public Server(String host, int port, String name, int maxThreads) {
            this.name = name;
            this.host = host;
            //this.service =  RemoteService.createService(ResizeService.class, host, port, name, "testConnection");
            this.maxThreads = maxThreads;
            this.semaphore = new Semaphore(maxThreads);
        }

        public String getUrl() {
            return host + "/" + name;
        }

        private boolean tryAcquire() {
            return semaphore.tryAcquire();
        }

        private void release() {
            semaphore.release();
        }

    }
}
