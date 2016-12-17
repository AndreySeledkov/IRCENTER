package org.ircenter.server.service.media;

import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.util.ImageUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

public class MediaHelper {

    private static final String SEPARATOR = System.getProperty("file.separator");

    private static final String CATALINA_HOME = System.getProperty("catalina.home");


    public static void saveDefaultImages() {
        String pathDefaultBigAvatar = CATALINA_HOME + SEPARATOR + "webapps" + SEPARATOR + "ROOT" + SEPARATOR + "WEB-INF" + SEPARATOR + "img" + SEPARATOR + "medium_photo.jpg";
        String pathDefaultSmallAvatar = CATALINA_HOME + SEPARATOR + "webapps" + SEPARATOR + "ROOT" + SEPARATOR + "WEB-INF" + SEPARATOR + "img" + SEPARATOR + "small_photo.jpg";

        String path = CATALINA_HOME + SEPARATOR + "webapps" + SEPARATOR + "ROOT";
        String imgDir = path + SEPARATOR + "img";
        createDir(imgDir);
        String userDir = imgDir + SEPARATOR + UserHelper.getUserId();
        createDir(userDir);

        try {
            File file = new File(pathDefaultBigAvatar);
            FileInputStream fin = new FileInputStream(file);

            byte fileContent[] = new byte[(int) file.length()];
            fin.read(fileContent);

            File fileDefaultBigAvatar = new File(userDir + SEPARATOR + UserHelper.getUserId() + ".jpg");
            OutputStream out = new FileOutputStream(fileDefaultBigAvatar);

            byte[] resizedImage = ImageUtils.getResizedImage(fileContent, 180, 278);
            out.write(resizedImage, 0, resizedImage.length);
            out.close();


            File fileDefaultSmallAvatar = new File(userDir + SEPARATOR + "th_" + UserHelper.getUserId() + ".jpg");
            out = new FileOutputStream(fileDefaultSmallAvatar);

            file = new File(pathDefaultSmallAvatar);
            fin = new FileInputStream(file);

            byte fileContent1[] = new byte[(int) file.length()];
            fin.read(fileContent1);


            resizedImage = ImageUtils.getResizedImage(fileContent1, 88, 88);
            out.write(resizedImage, 0, resizedImage.length);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сохранить приложенные изображения
     *
     * @param pictures изображения
     * @param userId   id пользователя
     * @throws java.io.IOException эксепшын
     */
    public static void saveImages(List<MultipartFile> pictures, Long userId)
            throws IOException {
        if (!(pictures.size() == 1 && pictures.get(0).isEmpty())) {
            String path = CATALINA_HOME + SEPARATOR + "webapps" + SEPARATOR + "ROOT";
            String imgDir = path + SEPARATOR + "img";
            createDir(imgDir);
            String userDir = imgDir + SEPARATOR + userId;
            createDir(userDir);

//            String postDir = userDir + SEPARATOR + elementName;
//            createDir(postDir);
//            String postIdDir = postDir + SEPARATOR + id;
//            createDir(postIdDir);
            for (MultipartFile picture : pictures) {
                String originalName = picture.getOriginalFilename();
                File f = new File(userDir + SEPARATOR + userId + ".jpg");
                InputStream inputStream = picture.getInputStream();
                OutputStream out = new FileOutputStream(f);

                byte[] resizedImage = ImageUtils.getResizedImage(picture.getBytes(), 180, 278);
                out.write(resizedImage, 0, resizedImage.length);
                out.close();
                inputStream.close();


//                byte buf[] = new byte[1024];
//                int len;
//                while ((len = inputStream.read(buf)) > 0) {
//                    out.write(buf, 0, len);
//                }
//                out.close();
//                inputStream.close();
                f = new File(userDir + SEPARATOR + "th_" + userId + ".jpg");
                out = new FileOutputStream(f);
                resizedImage = ImageUtils.getResizedImage(picture.getBytes(), 100, 100);
                out.write(resizedImage, 0, resizedImage.length);
                out.close();
                inputStream.close();
            }
        }
    }

    /**
     * Сохранить приложенные изображения
     *
     * @param audios изображения
     * @param userId id пользователя
     * @throws java.io.IOException эксепшын
     */
    public static void saveAudios(List<MultipartFile> audios, Long userId)
            throws IOException {
        if (!(audios.size() == 1 && audios.get(0).isEmpty())) {
            String path = CATALINA_HOME + SEPARATOR + "webapps" + SEPARATOR + "ROOT";
            String imgDir = path + SEPARATOR + "audio";
            createDir(imgDir);
            String userDir = imgDir + SEPARATOR + userId;
            createDir(userDir);
//            String postDir = userDir + SEPARATOR + elementName;
//            createDir(postDir);
//            String postIdDir = postDir + SEPARATOR + id;
//            createDir(postIdDir);
            for (MultipartFile audio : audios) {
                String originalName = audio.getOriginalFilename();
                File f = new File(userDir + SEPARATOR + originalName);
                InputStream inputStream = audio.getInputStream();
                OutputStream out = new FileOutputStream(f);
                byte buf[] = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                inputStream.close();
            }
        }
    }

    /**
     * Сохранить приложенные видео
     *
     * @param videos видео
     * @param userId id пользователя
     * @throws java.io.IOException эксепшын
     */
    public static void saveVideos(List<MultipartFile> videos, Long userId)
            throws IOException {
        if (!(videos.size() == 1 && videos.get(0).isEmpty())) {
            String path = CATALINA_HOME + SEPARATOR + "webapps" + SEPARATOR + "ROOT";
            String imgDir = path + SEPARATOR + "video";
            createDir(imgDir);
            String userDir = imgDir + SEPARATOR + userId;
            createDir(userDir);
//            String postDir = userDir + SEPARATOR + elementName;
//            createDir(postDir);
//            String postIdDir = postDir + SEPARATOR + id;
//            createDir(postIdDir);
            for (MultipartFile video : videos) {
                String originalName = video.getOriginalFilename();
                File f = new File(userDir + SEPARATOR + originalName);
                InputStream inputStream = video.getInputStream();
                OutputStream out = new FileOutputStream(f);
                byte buf[] = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                inputStream.close();
            }
        }
    }

    /**
     * Создать директорию если не существует
     *
     * @param imgDir путь к директории
     */
    private static void createDir(String imgDir) {
        File dir = new File(imgDir);
        if (!(dir.exists())) {
            dir.mkdir();
        }
    }
}
