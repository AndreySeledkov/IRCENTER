package org.ircenter.serv.dataserver.images;

import org.ircenter.serv.dataserver.images.resize.RConf;
import org.ircenter.serv.dataserver.images.resize.RResult;
import org.ircenter.serv.dataserver.images.resize.ResizeException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 8:01
 */
public interface ResizeService extends Remote {

    public String testConnection() throws RemoteException;

    public RResult resize(RConf conf, byte[] image) throws RemoteException, ResizeException;

    public RResult rotate(RConf conf, byte[] image, int angle) throws RemoteException, ResizeException;
}
