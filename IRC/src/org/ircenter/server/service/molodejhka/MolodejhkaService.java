package org.ircenter.server.service.molodejhka;

import org.ircenter.server.bean.molodejhka.MolodejhkaVideo;
import org.ircenter.server.dao.molodejhka.MolodejhkaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.05.12
 * Time: 2:39
 */
@Service
public class MolodejhkaService {
    public static final int DEFAUTL_VIDEOS_COUNT = 10;
    private MolodejhkaDAO molodejhkaDAO;

//    public MolodejhkaPhoto getPhoto(long id) {
//        return molodejhkaDAO.getPhoto(id);
//    }
//
//    public List<MolodejhkaPhoto> getPhotos(int offset, int count) {
//        return molodejhkaDAO.getPhotos(offset, count);
//    }
//
//    public void addPhoto(long userId, long imageId, String description) {
//        molodejhkaDAO.addPhoto(userId, imageId, description);
//    }
//
//    public void editPhoto(long imageId, String description, long id) {
//        molodejhkaDAO.editPhoto(imageId, description, id);
//    }
//
//    public void removePhoto(long id) {
//        molodejhkaDAO.removePhoto(id);
//}

    public MolodejhkaVideo getVideo(long id) {
        return molodejhkaDAO.getVideo(id);
    }

    public List<MolodejhkaVideo> getVideos(int offset, int count) {
        return molodejhkaDAO.getVideos(offset, count);
    }

    public void addVideo(long userId, String link, String description, String text) {
        try {
            link = link.trim();
            link = link.replaceAll("width=\"420\"", "width=\"640\"");
            link = link.replaceAll("height=\"315\"", "height=\"360\"");
            link = link.replaceAll("allowscriptaccess=\"always\"", "allowscriptaccess=\"always\" wmode=\"transparent\"");

            int g = link.indexOf("src=") + "src=http://www.youtube.com/v/".length() + 1;
            String url = link.substring(g, link.indexOf("?", g));

            String youtubeId = url;
            molodejhkaDAO.addVideo(userId, youtubeId, link, description, text);
        } catch (Exception e) {

        }
    }

    public void editVideo(String link, String description, String text, long id) {
        try {
            link = link.trim();
            link = link.replaceAll("width=\"420\"", "width=\"640\"");
            link = link.replaceAll("height=\"315\"", "height=\"360\"");
            link = link.replaceAll("allowscriptaccess=\"always\"", "allowscriptaccess=\"always\" wmode=\"transparent\"");

            int g = link.indexOf("src=") + "src=http://www.youtube.com/v/".length() + 1;
            String url = link.substring(g, link.indexOf("?", g));

            String youtubeId = url;
            molodejhkaDAO.editVideo(youtubeId, link, description, text, id);
        } catch (Exception e) {

        }
    }

    public void removeVideo(long id) {
        molodejhkaDAO.removeVideo(id);
    }

    public int getTotalVideosCount() {
        return molodejhkaDAO.getTotalVideosCount();
    }

    @Autowired
    public void setMolodejhkaDAO(MolodejhkaDAO molodejhkaDAO) {
        this.molodejhkaDAO = molodejhkaDAO;
    }


}
