package org.ircenter.serv.dataserver.images.impl;

import org.ircenter.serv.dataserver.images.resize.RConf;
import org.ircenter.serv.dataserver.images.resize.RResult;

import java.util.List;
import java.util.Set;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 7:54
 */
public class LargeImageCache extends ImageCache {

    public LargeImageCache(ImageServiceImpl service, String name, int maxSize, Set<ImageSize> sizes) {
        super(service, name, maxSize, sizes);
        this.storage = service.getFileStorageLargePreviews();
    }

    @Override
    protected RConf createResizeConf() {
        return service.createResizeConf()
                .setProcessNormalized(false)
                .setProcess240(false);
    }

    @Override
    protected List<byte[]> compose(RResult result) {
        return ImageServiceImpl.composeLargePreviewBytes(result);
    }

    @Override
    protected byte[] recoverImage(long imageId, int index) {
        byte[] r = super.recoverImage(imageId, index);
        if (r != null) {    //if recovered
            //service.getImgDAO().setDeletedBigSize(imageId, false);
        }
        return r;
    }

    @Override
    protected byte[] load(long imageId, int size) {
        service.getAccessManager().onLoad(imageId);
        return super.load(imageId, size);
    }

}
