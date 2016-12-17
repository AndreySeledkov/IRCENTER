package org.ircenter.serv.dataserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:09
 */
public interface ImageService extends Remote {

    public static final int IMG_SIZE_ORIGINAL = 0;
    public static final int IMG_SIZE_176_220_SMALL = 1;
    public static final int IMG_SIZE_330_220 = 2;
    public static final int IMG_SIZE_240_320_SMALL = 3;
    public static final int IMG_SIZE_120_67 = 4;

    public static final int IMG_SIZE_320_480_SMALL = 5;
    public static final int IMG_SIZE_170_121 = 6;
    public static final int IMG_SIZE_480_640_SMALL = 7;
    public static final int IMG_SIZE_800_600 = 8;

    public static final String SERVICE_NAME = "ImageService";

    public byte[] getBytes(long imageId, int imageSize) throws RemoteException;

    public byte[] getBytesQuiet(long imageId, int imageSize) throws RemoteException;

    public void removeImage(long imageId) throws RemoteException;

    public byte[] scale(byte[] data, int imageSize) throws RemoteException;

    public boolean addImage(long imageId, byte[] bytes) throws RemoteException;
}
