package org.ircenter.server.service.evidence;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.VideoEvidence;
import org.ircenter.server.dao.evidence.VideoEvidenceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 16.06.12
 * Time: 16:11
 */
@Service
public class VideoEvidenceService {
    private VideoEvidenceDAO videoEvidenceDAO;

    public boolean saveVideoEvidence(VideoEvidence videoEvidence) {
        return videoEvidenceDAO.saveVideoEvidence(videoEvidence);
    }

    public List<VideoEvidence> getVideoEvidence(int start, int limit) {
        List<VideoEvidence> list = new ArrayList<VideoEvidence>(videoEvidenceDAO.getVideoEvidence().values());
        return MUtil.getSafeSubList(list, start, start + limit);
    }

    public void removeVideoEvidence(long id) {
        videoEvidenceDAO.removeVideoEvidence(id);
    }

    public int getVideoEvidenceCount() {
        return videoEvidenceDAO.getVideoEvidence().size();
    }

    public VideoEvidence getVideoEvidence(long id) {
        return videoEvidenceDAO.getVideoEvidence().get(id);
    }

    public boolean updateVideoEvidence(VideoEvidence videoEvidence) {
        return videoEvidenceDAO.updateVideoEvidence(videoEvidence);
    }

    @Autowired
    public void setVideoEvidenceDAO(VideoEvidenceDAO videoEvidenceDAO) {
        this.videoEvidenceDAO = videoEvidenceDAO;
    }
}
