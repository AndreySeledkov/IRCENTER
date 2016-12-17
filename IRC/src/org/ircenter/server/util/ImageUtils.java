package org.ircenter.server.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

public class ImageUtils {

    private ImageUtils() {
    }
    
    public static BufferedImage createPreview(BufferedImage image, int thumbWidth, int thumbHeight, BufferedImage imgtop) {
        BufferedImage imageBack = createThumbnail(image, thumbWidth, thumbHeight);
        BufferedImage imageResult = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_3BYTE_BGR);

        Graphics g = imageResult.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, thumbWidth, thumbHeight);

        g.drawImage(imageBack, (thumbWidth - imageBack.getWidth()) / 2, (thumbHeight - imageBack.getHeight()) / 2, null);

        if (imgtop != null) {
            g.drawImage(imgtop, 0, 0, null);
        }
        g.dispose();
        return imageResult;
    }

    public static BufferedImage createPreview(BufferedImage image, int thumbWidth, int thumbHeight, int borderColor) {
        BufferedImage imageBack = createThumbnail(image, thumbWidth, thumbHeight);
        BufferedImage imageResult = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_3BYTE_BGR);

        Graphics g = imageResult.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, thumbWidth, thumbHeight);

        g.drawImage(imageBack, (thumbWidth - imageBack.getWidth()) / 2, (thumbHeight - imageBack.getHeight()) / 2, null);

        g.setColor(new Color(borderColor));
        g.drawRect(0, 0, thumbWidth-1, thumbHeight-1);

        g.dispose();
        
        return imageResult;
    }

    private static BufferedImage getScaled(BufferedImage image, int width, int height, int hints) {
        java.awt.Image scaled = image.getScaledInstance(width, height, hints);
        BufferedImage img = new BufferedImage(width, height, image.getType());
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.drawImage(scaled, 0, 0, Color.white, null);
        g.dispose();
        return img;
    }

    public static BufferedImage createThumbnail(BufferedImage image, int thumbMaxWidth, int thumbMaxHeight) {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        int thumbWidth = thumbMaxWidth;
        int thumbHeight = thumbWidth * sourceHeight / sourceWidth;

        if (thumbHeight > thumbMaxHeight) {
            thumbHeight = thumbMaxHeight;
            thumbWidth = thumbHeight * sourceWidth / sourceHeight;
        }

        if (sourceHeight < thumbHeight && sourceWidth < thumbWidth) {
            thumbHeight = sourceHeight;
            thumbWidth = sourceWidth;
        }
        
        return getScaled(image, thumbWidth, thumbHeight, java.awt.Image.SCALE_SMOOTH);
    }

    /**
     * промасштабировать. уменьшить МЕНЬШУЮ из сторон до max_len
     * @param image
     * @param max_len (newwidth==max_len || newheight==maxlen) == true
     * @return returns image(parameter) if smaller or cant resize, or rescaled image otherwise
     */
    public static BufferedImage normalizeImageSize(BufferedImage image, int max_len) {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        int thumbWidth;
        int thumbHeight;

        if (sourceHeight < sourceWidth) {
            if (sourceHeight <= max_len) {
                return image;
            }

            thumbHeight = max_len;
            thumbWidth = thumbHeight * sourceWidth / sourceHeight;
        } else {
            if (sourceWidth <= max_len) {
                return image;
            }

            thumbWidth = max_len;
            thumbHeight = thumbWidth * sourceHeight / sourceWidth;
        }
        
        return getScaled(image, thumbWidth, thumbHeight, java.awt.Image.SCALE_SMOOTH);
    }

    public static <O extends OutputStream> O writeJPEG(O out, BufferedImage image) throws IOException {
        ImageIO.write(image, "jpeg", out);
        return out;
    }
    
    public static <O extends OutputStream> O writeJPEG(O out, BufferedImage image, float quality) throws IOException {
        ImageOutputStream stream = ImageIO.createImageOutputStream(out);

        try {
            ImageWriter writer = null;
            Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("JPG");
            if (iter.hasNext()) {
                writer = iter.next();
            }
            if (writer == null) {
                throw new IOException("cant find writer");
            }

            ImageWriteParam iwp = writer.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwp.setCompressionQuality(quality);

            writer.setOutput(stream);

            IIOImage ioImage = new IIOImage(image, null, null);
            
            try {
                writer.write(null, ioImage, iwp);
            } finally {
                stream.flush();
                writer.dispose();
            }
        } finally {
            stream.close();
        }
        return out;
    }
    
    /**
     *
     * @param original
     * @param angle
     * @return if angle==0 => return same object
     */
    public static BufferedImage rotateImage(BufferedImage original, int angle) {
        if ((angle != 0) && (angle != 90) && (angle != 180) && (angle != 270)) {
            throw new IllegalArgumentException("(angle != 0) && (angle != 90) && (angle != 180) && (angle != 270)");
        }

        if (angle == 0) {
            return original;
        }

        int width = original.getWidth();
        int height = original.getHeight();
        int w = (angle == 180) ? width : height;
        int h = (angle == 180) ? height : width;

        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);

        double theta = angle * Math.PI / 180;

        Graphics2D g2 = result.createGraphics();

        double x = w / 2;
        double y = h / 2;

        AffineTransform at = AffineTransform.getRotateInstance(theta, x, y);

        x = (w - width) / 2 - 0;
        y = (h - height) / 2 - 0;

        at.translate(x, y);

        g2.drawRenderedImage(original, at);
        g2.dispose();

        return result;
    }

    private static BufferedImage convert(ImageIcon icon) throws IOException {

        try {
            java.awt.Image image = icon.getImage();

            int width = image.getWidth(null);
            int height = image.getHeight(null);

            if (image instanceof sun.awt.image.ToolkitImage) {
                sun.awt.image.ToolkitImage ti = (sun.awt.image.ToolkitImage) image;

                width = ti.getWidth(null);
                height = ti.getHeight(null);

                BufferedImage jpgImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

                Graphics g = jpgImage.getGraphics();
                g.drawImage(ti.getBufferedImage(), 0, 0, Color.white, null);
                g.dispose();

                return jpgImage;
            } else {
                BufferedImage jpgImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics g = jpgImage.getGraphics();

                g.drawImage(image, 0, 0, Color.white, null);
                g.dispose();

                return jpgImage;
            }
        } catch (Exception ex) {
            throw new IOException(ex);
        }
    }

    private static BufferedImage createCopy(BufferedImage img) {
        BufferedImage copy = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = copy.getGraphics();
        g.drawImage(img, 0, 0, Color.white, null);
        g.dispose();
        return copy;
    }
    
    private static boolean isBMP(byte[] bytes) {
        return (bytes.length > 2) && (bytes[0] == 0x42) && (bytes[1] == 0x4d);
    }
    private static boolean isGIF(byte[] bytes) {
        return (bytes.length > 3) && (bytes[0] == 71) && (bytes[1] == 73) && (bytes[2] == 70); //'G' 'I' 'F'
    }
    
    public static BufferedImage createBufferedImage(byte[] bytes) throws IOException {
        if (isBMP(bytes)) {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } else if (isGIF(bytes)) {
            return createCopy(ImageIO.read(new ByteArrayInputStream(bytes)));
        }
        return convert(new ImageIcon(bytes));
    }

    public static BufferedImage createBufferedImage(URL url) throws IOException {
        //TODO check BMP
        //TODO check GIF
        ImageIcon icon = new ImageIcon(url);

        return convert(icon);
    }

    public static byte[] getResizedImage(byte [] bytes, int width, int height) throws IOException {
        BufferedImage img = createBufferedImage(bytes);
        img.flush();

        img = createThumbnail(img, width, height);
        img.flush();

        return writeJPEG(new ByteArrayOutputStream(), img).toByteArray();
    }
}
