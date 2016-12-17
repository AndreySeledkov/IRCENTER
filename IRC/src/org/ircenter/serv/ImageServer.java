package org.ircenter.serv;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.serv.dataserver.ImageService;
import org.ircenter.serv.dataserver.images.impl.ImageServiceImpl;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 8:48
 */
@Service
public class ImageServer implements ImageService {

    private static final Log LOGGER = LogFactory.getLog(ImageServer.class.getClass());
    private ImageService service;
    private volatile Semaphore semaphoreGet;
    private volatile Semaphore semaphoreGetQuiet;

    private volatile int getThreads;
    private volatile int getQuietThreads;
    private volatile boolean disabled;
    private volatile boolean getQuietFirst;

    public ImageServer() {
        setMaxLoadingThreads(50, 350, true);
        create();
    }

    public final void setMaxLoadingThreads(int countGet, int countGetQuiet, boolean getQuietFirst) {
        this.getThreads = countGet;
        this.getQuietThreads = countGetQuiet;
        this.getQuietFirst = getQuietFirst;
        this.semaphoreGet = new Semaphore(countGet, true);
        this.semaphoreGetQuiet = new Semaphore(countGetQuiet, true);
    }

    private void create() {
        try {
            service = new ImageServiceImpl();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public byte[] getBytes(long imageId, int imageSize) throws RemoteException {
        if (disabled) {
            return null;
        }
        return service.getBytes(imageId, imageSize);
    }

    @Override
    public byte[] getBytesQuiet(long imageId, int imageSize) throws RemoteException {
        if (disabled) {
            return null;
        }
        return service.getBytesQuiet(imageId, imageSize);
    }

    @Override
    public void removeImage(long imageId) {
        if (disabled) {
            return;
        }
        try {
            service.removeImage(imageId);
        } catch (Exception ex) {
            LOGGER.error("", ex);
        }
    }

    @Override
    public boolean addImage(long imageId, byte[] bytes) {
        if (disabled) {
            return false;
        }
        try {
            return service.addImage(imageId, bytes);
        } catch (Exception ex) {
            LOGGER.error("", ex);
        }
        return false;
    }

    @Override
    public byte[] scale(byte[] bytes, int imageSize) {
        if (disabled) {
            return null;
        }
        try {
            return service.scale(bytes, imageSize);
        } catch (Exception ex) {
            LOGGER.error("", ex);
        }
        return null;
    }
}
