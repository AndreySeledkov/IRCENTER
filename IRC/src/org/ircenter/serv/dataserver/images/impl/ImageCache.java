package org.ircenter.serv.dataserver.images.impl;

import net.sf.ehcache.constructs.blocking.CacheEntryFactory;
import org.ircenter.filestorage.BadFileException;
import org.ircenter.filestorage.FileStorage;
import org.ircenter.filestorage.NoPartException;
import org.ircenter.serv.dataserver.images.impl.cache.JavaCache;
import org.ircenter.serv.dataserver.images.impl.cache.SPCache;
import org.ircenter.serv.dataserver.images.impl.cache.SimpleCache;
import org.ircenter.serv.dataserver.images.resize.RConf;
import org.ircenter.serv.dataserver.images.resize.RResult;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:13
 */
public class ImageCache {
    protected static final Logger LOGGER = Logger.getLogger(ImageCache.class.getName());
    protected final String name;
    protected Set<ImageSize> sizes;
    protected ImageServiceImpl service;
    protected FileStorage storage;
    protected int maxSize;
    private volatile SPCache cache;
    private ImageLoader loader;
    public static final AtomicLong recoverImageCount = new AtomicLong();
    public static final AtomicLong successRecoverImageCount = new AtomicLong();

    public ImageCache(ImageServiceImpl service, String name, int maxSize, Set<ImageSize> sizes) {
        this.service = service;
        this.name = name;
        this.maxSize = maxSize;
        this.sizes = sizes;
        this.storage = service.getFileStorageSmallPreviews();
        this.loader = new ImageLoader();
    }

    public String getName() {
        return name;
    }

    public FileStorage getStorage() {
        return storage;
    }

    public void init() {
        changeCache();
    }

    public void changeCache() {
        SPCache old = cache;

        SimpleCache baseCache = new JavaCache(maxSize);
        this.cache = new SPCache(baseCache, loader);
        if (old != null) {
            old.shutdown();
        }

        System.out.println("ImageCache."+ getName() + ".engine=" + cache.getEngine());
    }

    private static Object getKey(long imageId, ImageSize size) {
        return new Key(imageId, (byte) size.getId());
    }

    public byte[] getBytes(long imageId, ImageSize size) {
        if (sizes.contains(size)) {
            return (byte[]) cache.get(getKey(imageId, size));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public byte[] getBytesQuiet(long imageId, ImageSize size) {
        if (sizes.contains(size)) {
            return (byte[])cache.getQuiet(getKey(imageId, size));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public SPCache getBackedCache() {
        return cache;
    }

    public boolean acceptSize(ImageSize size) {
        return sizes.contains(size);
    }

    public void remove(long imageId) {
        for (ImageSize size : sizes) {
            cache.remove(getKey(imageId, size));
        }
    }

    protected RConf createResizeConf() {
        return service.createResizeConf()
                .setProcessNormalized(false)
                .setProcess480(false);
    }

    protected List<byte[]> compose(RResult result) {
        return ImageServiceImpl.composeSmallPreviewBytes(result);
    }

    protected byte[] recoverImage(long imageId, int index) {
        try {
            byte[] originalBytes = service.getOriginalBytes(imageId);
            if (originalBytes != null) {
                int angle = 0;//service.getImgDAO().getAngle(imageId);
                RResult result = service.getResizeService().rotate(createResizeConf(), originalBytes, angle);
                List<byte[]> list = compose(result);
                storage.saveFile(imageId, list);
//                LOGGER.log(Level.INFO, "recover/transmit file " + imageId);
                return list.get(index);
            } else {
                LOGGER.log(Level.WARNING, "cant recover/transmit file because original not found. imageId=" + imageId);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "cant recover/transmit file " + imageId, ex);
        }
        return null;
    }

    private byte[] recoverOrGetStub(long imageId, int size, int index) {
        recoverImageCount.incrementAndGet();

        byte[] bytes = recoverImage(imageId, index);
        if (bytes == null) {
            bytes = getStubImageDeleted(size);
        } else {
            successRecoverImageCount.incrementAndGet();
        }
        return bytes;
    }

    protected byte[] load(long imageId, int size) {
        int index = ImageServiceImpl.getImageSizeIndex(size);

        synchronized (storage.getFileLock(imageId)) {
            byte bytes[];
            try {
                bytes = storage.getFile(imageId, index);
            } catch (FileNotFoundException ex) {
                bytes = recoverOrGetStub(imageId, size, index);
            } catch (BadFileException ex) {
                bytes = recoverOrGetStub(imageId, size, index);
            } catch (NoPartException ex) {
                bytes = recoverOrGetStub(imageId, size, index);
            }
            return bytes;
        }
    }

    public byte[] getStubImageDeleted(int size) {
        size = ImageServiceImpl.getImageStubDeletedSize(size);

        int index = ImageServiceImpl.getImageSizeIndex(size);
        byte bytes[] = null;
        try {
            bytes = storage.getFile(ImageServiceImpl.IMAGE_ID_REMOVED, index);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "cant load stub image deleted", ex);
        }

        return bytes;
    }

    private class ImageLoader implements CacheEntryFactory {

        public Object createEntry(Object key) throws Exception {
            Key k = (Key) key;
            return load(k.imageId, k.size);
        }
    }

    private static final class Key {

        private final long imageId;
        private final byte size;

        public Key(long imageId, byte size) {
            this.imageId = imageId;
            this.size = size;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Key other = (Key) obj;
            if (this.imageId != other.imageId) {
                return false;
            }
            if (this.size != other.size) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 67 * hash + (int) (this.imageId ^ (this.imageId >>> 32));
            hash = 67 * hash + this.size;
            return hash;
        }

        @Override
        public String toString() {
            return imageId + ":" + size;
        }
    }
}
