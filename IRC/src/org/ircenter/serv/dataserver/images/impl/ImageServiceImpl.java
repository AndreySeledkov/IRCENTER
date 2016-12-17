package org.ircenter.serv.dataserver.images.impl;

import org.ircenter.filestorage.FileStorage;
import org.ircenter.filestorage.selectors.ManyHddDirectorySelector;
import org.ircenter.filestorage.selectors.ModSelector;
import org.ircenter.serv.dataserver.ImageService;
import org.ircenter.serv.dataserver.images.defaultimages.InitDefaultImages;
import org.ircenter.serv.dataserver.images.impl.resize.ListResizeService;
import org.ircenter.serv.dataserver.images.resize.RConf;
import org.ircenter.serv.dataserver.images.resize.RResult;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:11
 */
public class ImageServiceImpl extends UnicastRemoteObject implements ImageService {

    private static final Logger LOGGER = Logger.getLogger(ImageServiceImpl.class.getName());

    private LastAccessManager accessManager;

    private FileStorage storagePreviewsSmall;
    private FileStorage storagePreviewsLarge;

    private MirrorStorage storageOriginals;
    private FileStorage storageOriginalsOld;

    private ImageCache cacheImages240Preview;
    private ImageCache cacheImages240Full;
    private ImageCache cacheImages480Preview;
    private ImageCache cacheImages480Full;

    private long startTime = System.currentTimeMillis();

    private AtomicLong bytesSent = new AtomicLong();
    private AtomicLong functionGetBytes = new AtomicLong();
    private AtomicLong functionGetBytesQuiet = new AtomicLong();
    private AtomicLong functionRemoveImage = new AtomicLong();
    private AtomicLong functionAddImage = new AtomicLong();
    private AtomicLong functionAddImageSuccess = new AtomicLong();
    private AtomicLong functionRotate = new AtomicLong();
    private AtomicLong functionRotateSuccess = new AtomicLong();
    private AtomicLong functionExists = new AtomicLong();
    private AtomicLong functionCheckImage = new AtomicLong();
    private AtomicLong functionScale = new AtomicLong();
    private AtomicLong functionScaleSuccess = new AtomicLong();

    private ImageCache[] caches;

    private ListResizeService resizeService;

    private int normalizedImageSize;
    private boolean handleSmallSizes;
    private boolean whiteBackground;

    private AtomicInteger movedFiles = new AtomicInteger();
    private static final String SEPARATOR = System.getProperty("file.separator");

    private static final String CATALINA_HOME = System.getProperty("catalina.home");

    public ImageServiceImpl() throws RemoteException {
        //default is russia options
        handleSmallSizes = true;
        whiteBackground = true;
        normalizedImageSize = 480;

        System.out.println("ImageServerImpl.handleSmallSizes = " + handleSmallSizes);
        System.out.println("ImageServerImpl.whiteBackground = " + whiteBackground);
        System.out.println("ImageServerImpl.normalizedImageSize = " + normalizedImageSize);

        resizeService = new ListResizeService();

        accessManager = new LastAccessManager(this);

        int cacheSizePreviewSmall = 5000;
        int cacheSizePreviewLarge = 4000;
        int cacheSizeFullSmall = 3000;
        int cacheSizeFullLarge = 200000;

        FileStorage storageOriginals1 = new FileStorage(false, new ModSelector(CATALINA_HOME + SEPARATOR + "webapps" + SEPARATOR + "images" + SEPARATOR + "filestorage-o/", 2, 256));
        FileStorage storageOriginals2 = new MockStorage(true);
        storageOriginals = new MirrorStorage(storageOriginals1, storageOriginals2);

        storageOriginalsOld = new MockStorage(true);

        int rootCount = 2;
        String rootsSmall[] = new String[rootCount];
        String rootsLarge[] = new String[rootCount];
        LOGGER.info("rootCount: " + rootCount);
        for (int i = 0; i < rootCount; i++) {
            rootsSmall[i] = CATALINA_HOME + SEPARATOR + "webapps" + SEPARATOR + "images" + SEPARATOR + "ssd" + (i + 1);
            rootsLarge[i] = CATALINA_HOME + SEPARATOR + "webapps" + SEPARATOR + "images" + SEPARATOR + "ssdLarge" + (i + 1);
        }
        for (int i = 0; i < rootCount; i++) {
            LOGGER.info("rootSmall" + (i + 1) + ": " + rootsSmall[i]);
        }
        for (int i = 0; i < rootCount; i++) {
            LOGGER.info("rootLarge" + (i + 1) + ": " + rootsLarge[i]);
        }

        if (handleSmallSizes) {
            storagePreviewsSmall = new FileStorage(true, new ManyHddDirectorySelector(rootsSmall));
        } else {
            storagePreviewsSmall = new MockStorage(false);
        }
        storagePreviewsLarge = new FileStorage(true, new ManyHddDirectorySelector(rootsLarge));

        ArrayList<ImageCache> cacheList = new ArrayList<ImageCache>();
        if (handleSmallSizes) {
            cacheList.add(cacheImages240Preview = new ImageCache(this, "cache240-preview", cacheSizePreviewSmall, EnumSet.of(ImageSize.size176small, ImageSize.size240small)));
            cacheList.add(cacheImages240Full = new ImageCache(this, "cache240-full", cacheSizeFullSmall, EnumSet.of(ImageSize.size176big, ImageSize.size240big)));
        }
        cacheList.add(cacheImages480Preview = new LargeImageCache(this, "cache480-preview", cacheSizePreviewLarge, EnumSet.of(ImageSize.size320small, ImageSize.size480small)));
        cacheList.add(cacheImages480Full = new LargeImageCache(this, "cache480-full", cacheSizeFullLarge, EnumSet.of(ImageSize.size320big, ImageSize.size480big)));

        caches = cacheList.toArray(new ImageCache[0]);
        for (ImageCache cache : caches) {
            cache.init();
        }

        InitDefaultImages.copyToSmallPreview(storagePreviewsSmall);
        InitDefaultImages.copyToLargePreview(storagePreviewsLarge);

        accessManager.init();

    }

    List<ImageCache> getCaches() {
        return Arrays.asList(caches);
    }

    public LastAccessManager getAccessManager() {
        return accessManager;
    }

    public byte[] getOriginalBytes(long imageId) {
        synchronized (storageOriginals.getFileLock(imageId)) {
            try {
                return storageOriginals.getFile(imageId, 0);
            } catch (FileNotFoundException ex) {
                try {
                    byte[] data = storageOriginalsOld.getFile(imageId, 0);

                    if (data != null) {
                        boolean saveOk = storageOriginals.saveFile(imageId, data);
                        if (saveOk) {
                            storageOriginalsOld.removeFile(imageId);
                            movedFiles.incrementAndGet();
                        }
                    }
                    return data;
                } catch (FileNotFoundException ex1) {
                    LOGGER.log(Level.WARNING, "file not found: " + imageId);
                } catch (Exception ex1) {
                    LOGGER.log(Level.WARNING, "", ex);
                }
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "", ex);
            }
        }
        return null;
    }

    private ImageCache chooseCache(ImageSize imageSize) {
        for (ImageCache cache : caches) {
            if (cache.acceptSize(imageSize)) {
                return cache;
            }
        }
        throw new IllegalArgumentException("wrong imageSize: " + imageSize);
    }

    @Override
    public byte[] getBytes(long imageId, int imageSize) {
        functionGetBytes.incrementAndGet();

        if (imageSize == IMG_SIZE_ORIGINAL) {
            byte[] bytes = getOriginalBytes(imageId);
            if (bytes == null) {
                ImageSize size = ImageSize.valueOfId(getImageStubDeletedSize(imageSize));
                bytes = chooseCache(size).getStubImageDeleted(imageSize);
            }
            return bytes;
        }

        ImageSize size = ImageSize.valueOfId(imageSize);
        byte[] bytes = chooseCache(size).getBytes(imageId, size);
        if (bytes != null) {
            bytesSent.addAndGet(bytes.length);
        }
        return bytes;
    }

    @Override
    public byte[] getBytesQuiet(long imageId, int imageSize) {
        functionGetBytesQuiet.incrementAndGet();

        if (imageSize == IMG_SIZE_ORIGINAL) {
            return getOriginalBytes(imageId);
        }

        ImageSize size = ImageSize.valueOfId(imageSize);
        byte[] bytes = chooseCache(size).getBytesQuiet(imageId, size);
        if (bytes != null) {
            bytesSent.addAndGet(bytes.length);
        }
        return bytes;
    }

    private void removeFromCache(long imageId) {
        for (ImageCache cache : caches) {
            cache.remove(imageId);
        }
    }

    @Override
    public void removeImage(long imageId) {
        functionRemoveImage.incrementAndGet();

        storagePreviewsSmall.removeFile(imageId);
        storagePreviewsLarge.removeFile(imageId);
        storageOriginals.removeFile(imageId);
        storageOriginalsOld.removeFile(imageId);

        removeFromCache(imageId);

        //imageDAO.delete(imageId);
    }

    public static final int IMAGE_ID_DEFAULT = 0;
    public static final int IMAGE_ID_REMOVED = 1;

    public static int getImageStubDeletedSize(int imageSize) {
        if (imageSize == ImageService.IMG_SIZE_ORIGINAL) {
            imageSize = ImageService.IMG_SIZE_480_640_SMALL;
        }
        if (imageSize == ImageService.IMG_SIZE_330_220) {
            imageSize = ImageService.IMG_SIZE_176_220_SMALL;
        }
        if (imageSize == ImageService.IMG_SIZE_120_67) {
            imageSize = ImageService.IMG_SIZE_240_320_SMALL;
        }
        if (imageSize == ImageService.IMG_SIZE_170_121) {
            imageSize = ImageService.IMG_SIZE_320_480_SMALL;
        }
        if (imageSize == ImageService.IMG_SIZE_800_600) {
            imageSize = ImageService.IMG_SIZE_480_640_SMALL;
        }
        return imageSize;
    }

    public static int getImageSizeIndex(int size) {
        if (size == ImageService.IMG_SIZE_240_320_SMALL) return 0;
        if (size == ImageService.IMG_SIZE_176_220_SMALL) return 1;
        if (size == ImageService.IMG_SIZE_120_67) return 2;
        if (size == ImageService.IMG_SIZE_330_220) return 3;

        if (size == ImageService.IMG_SIZE_320_480_SMALL) return 0;
        if (size == ImageService.IMG_SIZE_480_640_SMALL) return 1;
        if (size == ImageService.IMG_SIZE_170_121) return 2;
        if (size == ImageService.IMG_SIZE_800_600) return 3;

        return 100;
    }

    public static List<byte[]> composeSmallPreviewBytes(RResult r) {
        return Arrays.asList(r.data_240_small, r.data_176_small, r.data_240, r.data_176);
    }

    public static List<byte[]> composeLargePreviewBytes(RResult r) {
        return Arrays.asList(r.data_320_small, r.data_480_small, r.data_320, r.data_480);
    }

    RConf createResizeConf() {
        return new RConf(whiteBackground, normalizedImageSize);
    }

    public ListResizeService getResizeService() {
        return resizeService;
    }

    public byte[] scale(byte[] bytes, int imageSize) {
        functionScale.incrementAndGet();
        try {
            RConf conf = createResizeConf();
            if (imageSize == IMG_SIZE_ORIGINAL) {
                conf.setProcess240(false);
                conf.setProcess480(false);
            }

            RResult result = getResizeService().resize(conf, bytes);
            functionScaleSuccess.incrementAndGet();
            if (imageSize == IMG_SIZE_ORIGINAL) {
                return result.normalizedOriginal;
            } else if (imageSize == IMG_SIZE_330_220) {
                return result.data_176;
            } else if (imageSize == IMG_SIZE_120_67) {
                return result.data_240;
            } else if (imageSize == IMG_SIZE_170_121) {
                return result.data_320;
            } else if (imageSize == IMG_SIZE_800_600) {
                return result.data_480;
            } else {
                return null;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "cant scale image", ex);
            return null;
        }
    }

    @Override
    public boolean addImage(long imageId, byte[] bytes) {
        functionAddImage.incrementAndGet();

        boolean ok = false;
        try {
            RResult result = getResizeService().resize(createResizeConf(), bytes);

            ok = storageOriginals.saveFile(imageId, result.normalizedOriginal);                //test here
            if (ok) {
                ok = storagePreviewsSmall.saveFile(imageId, composeSmallPreviewBytes(result));
            }
            if (ok) {
                ok = storagePreviewsLarge.saveFile(imageId, composeLargePreviewBytes(result));
            }
            if (ok) {
                //ok = imageDAO.add(imageId);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "cant add image: " + imageId, ex);
            ok = false;
        } finally {
            if (!ok) {
                storageOriginals.removeFile(imageId);
                storageOriginalsOld.removeFile(imageId);
                storagePreviewsSmall.removeFile(imageId);
                storagePreviewsLarge.removeFile(imageId);
                //imageDAO.delete(imageId);
            } else {
                functionAddImageSuccess.incrementAndGet();
                removeFromCache(imageId);   //if stubImage was in cache -> remove it
            }
        }
        return ok;
    }


    public FileStorage getFileStorageSmallPreviews() {
        return storagePreviewsSmall;
    }

    public FileStorage getFileStorageLargePreviews() {
        return storagePreviewsLarge;
    }

    public MirrorStorage getFileStorageOriginals() {
        return storageOriginals;
    }

}
