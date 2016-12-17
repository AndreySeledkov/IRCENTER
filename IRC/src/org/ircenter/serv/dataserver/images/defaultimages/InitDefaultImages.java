package org.ircenter.serv.dataserver.images.defaultimages;

import org.ircenter.filestorage.FileStorage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:07
 */
public class InitDefaultImages {
    public static final int MAX_ID = 4;

    private static byte[] readImage(String filename) {
        String packageName = InitDefaultImages.class.getPackage().getName().replace('.', '/') + "/";
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            InputStream stream = InitDefaultImages.class.getClassLoader().getResourceAsStream(packageName + filename);
            int b;
            while ((b = stream.read()) != -1) {
                bout.write(b);
            }
            stream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bout.toByteArray();
    }

    public static void copyToSmallPreview(FileStorage storage) {
        for (int i = 0; i <= MAX_ID; i++) {
            byte[][] bytes = new byte[4][];
            bytes[0] = readImage("image-" + i + "-0.png");
            bytes[1] = readImage("image-" + i + "-1.png");
            bytes[2] = new byte[0];
            bytes[3] = new byte[0];

            storage.saveFile(i, Arrays.asList(bytes[0], bytes[1], bytes[2], bytes[3]));
        }
    }

    public static void copyToLargePreview(FileStorage storage) {
        for (int i = 0; i <= MAX_ID; i++) {
            byte[][] bytes = new byte[4][];
            bytes[0] = readImage("image-" + i + "-2.png");
            bytes[1] = readImage("image-" + i + "-3.png");
            bytes[2] = new byte[0];
            bytes[3] = new byte[0];

            storage.saveFile(i, Arrays.asList(bytes[0], bytes[1], bytes[2], bytes[3]));
        }
    }

}
