package org.ircenter.serv.dataserver.images.impl.resize;

import org.ircenter.fileserver.impl.ImageUtils;
import org.ircenter.serv.dataserver.images.ResizeService;
import org.ircenter.serv.dataserver.images.impl.ImageSize;
import org.ircenter.serv.dataserver.images.resize.RConf;
import org.ircenter.serv.dataserver.images.resize.RResult;
import org.ircenter.serv.dataserver.images.resize.ResizeException;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:13
 */
public class ResizeServiceImpl extends UnicastRemoteObject implements ResizeService {

    public ResizeServiceImpl() throws RemoteException {
    }

    @Override
    public String testConnection() throws RemoteException {
        return "OK";
    }

    @Override
    public RResult resize(RConf conf, byte[] originalImage) throws ResizeException {
        try {
            BufferedImage original = ImageUtils.createBufferedImage(originalImage);
            BufferedImage normalized = ImageUtils.normalizeImageSize(original, conf.getNormalizedSize());
            return processImage(conf, normalized, 0);
        } catch (Exception ex) {
            throw new ResizeException("cant resize image", ex);
        }
    }

    @Override
    public RResult rotate(RConf conf, byte[] normalizedImage, int angle) throws ResizeException {
        try {
            return processImage(conf, ImageUtils.createBufferedImage(normalizedImage), angle);
        } catch (Exception ex) {
            throw new ResizeException("cant rotate image", ex);
        }
    }

    private RResult processImage(RConf conf, BufferedImage normalizedImage, int angle) throws IOException, ResizeException {
        normalizedImage = ImageUtils.rotateImage(normalizedImage, angle);

        BufferedImage image_176_preview = null;
        BufferedImage image_240_preview = null;
        BufferedImage image_320_preview = null;
        BufferedImage image_480_preview = null;
        BufferedImage image_176 = null;
        BufferedImage image_240 = null;
        BufferedImage image_320 = null;
        BufferedImage image_480 = null;

        if (conf.isProcess240()) {
            if (conf.isOnBackground()) {
                image_176_preview = ImageUtils.createPreview(normalizedImage, ImageSize.size176small.getWidth(), ImageSize.size176small.getHeight(), conf.getBorderColor());
                image_240_preview = ImageUtils.createPreview(normalizedImage, ImageSize.size240small.getWidth(), ImageSize.size240small.getHeight(), conf.getBorderColor());
            } else {
                image_176_preview = ImageUtils.createCroppedThumbnail(normalizedImage, ImageSize.size176small.getWidth(), ImageSize.size176small.getHeight());
                image_240_preview = ImageUtils.createCroppedThumbnail(normalizedImage, ImageSize.size240small.getWidth(), ImageSize.size240small.getHeight());
            }
            image_176 = ImageUtils.createThumbnail(normalizedImage, ImageSize.size176big.getWidth(), ImageSize.size176big.getHeight());
            image_240 = ImageUtils.createThumbnail(normalizedImage, ImageSize.size240big.getWidth(), ImageSize.size240big.getHeight());
        }

        if (conf.isProcess480()) {
            if (conf.isOnBackground()) {
                image_320_preview = ImageUtils.createPreview(normalizedImage, ImageSize.size320small.getWidth(), ImageSize.size320small.getHeight(), conf.getBorderColor());
                image_480_preview = ImageUtils.createPreview(normalizedImage, ImageSize.size480small.getWidth(), ImageSize.size480small.getHeight(), conf.getBorderColor());
            } else {
                image_320_preview = ImageUtils.createCroppedThumbnail(normalizedImage, ImageSize.size320small.getWidth(), ImageSize.size320small.getHeight());
                image_480_preview = ImageUtils.createCroppedThumbnail(normalizedImage, ImageSize.size480small.getWidth(), ImageSize.size480small.getHeight());
            }
            image_320 = ImageUtils.createThumbnail(normalizedImage, ImageSize.size320big.getWidth(), ImageSize.size320big.getHeight());
            image_480 = ImageUtils.createThumbnail(normalizedImage, ImageSize.size480big.getWidth(), ImageSize.size480big.getHeight());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        RResult result = new RResult();

        if (conf.isProcessNormalized()) {
            result.normalizedOriginal = ImageUtils.writeJPEG(baos, normalizedImage).toByteArray();
        }

        if (conf.isProcess480()) {
            baos.reset();
            result.data_480 = ImageUtils.writeJPEG(baos, image_480).toByteArray();

            baos.reset();
            result.data_320 = ImageUtils.writeJPEG(baos, image_320).toByteArray();

            baos.reset();
            result.data_480_small = ImageUtils.writeJPEG(baos, image_480_preview).toByteArray();

            baos.reset();
            result.data_320_small = ImageUtils.writeJPEG(baos, image_320_preview).toByteArray();
        }

        if (conf.isProcess240()) {
            baos.reset();
            result.data_240 = ImageUtils.writeJPEG(baos, image_240).toByteArray();

            baos.reset();
            result.data_176 = ImageUtils.writeJPEG(baos, image_176).toByteArray();

            baos.reset();
            result.data_240_small = ImageUtils.writeJPEG(baos, image_240_preview).toByteArray();

            baos.reset();
            result.data_176_small = ImageUtils.writeJPEG(baos, image_176_preview).toByteArray();
        }
        return result;
    }
}
