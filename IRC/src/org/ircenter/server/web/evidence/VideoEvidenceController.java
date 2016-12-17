package org.ircenter.server.web.evidence;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.OnlineTv;
import org.ircenter.server.bean.evidence.VideoEvidence;
import org.ircenter.server.dao.online_tv.OnlineTvDAO;
import org.ircenter.server.service.evidence.VideoEvidenceService;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: Seledkov Andriy
 * Date: 10.05.12
 * Time: 13:52
 */
@Controller
@RequestMapping("/video_evidence")
public class VideoEvidenceController {
    private static final int Evidence_LIMIT = 10;
    private VideoEvidenceService evidenceService;
    private OnlineTvDAO onlineTvDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String getEvidenceVideo(Model model, HttpServletRequest request) {//TODO
        long unReadMessage = 0;//database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = 0;//database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());
        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);
        int page = 1;

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
        }

        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));
            VideoEvidence videoEvidence = evidenceService.getVideoEvidence(id);
            model.addAttribute("link", videoEvidence);

        }

        int evidenceVideoCount = evidenceService.getVideoEvidenceCount();
        List<VideoEvidence> videoEvidenceList = evidenceService.getVideoEvidence((page - 1) * Evidence_LIMIT, Evidence_LIMIT);
//        for (VideoEvidence videoEvidence : videoEvidenceList) {
//            videoEvidence.setLink(MUtil.replaceHTMLtextReturn(videoEvidence.getLink()));
//        }

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/video_evidence?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(evidenceVideoCount);
        pageListPlain.generate(builder);

        model.addAttribute("evidenceVideoCount", evidenceVideoCount);
        model.addAttribute("page", page);
        model.addAttribute("pagination", builder);
        model.addAttribute("videoEvidenceList", videoEvidenceList);
        OnlineTv onlineTv = onlineTvDAO.getOnlineTv();
        if (onlineTv != null) {
            model.addAttribute("onlineLeft", onlineTv.getDate().getTime());
        }
        return "new/video_evidence";
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public String deleteVideoEvidence(@PathVariable Long id) {
//        database.getVideoEvidenceDAO().removeVideoEvidence(id);
//        return "redirect:new/video_evidence";
//    }


    @Autowired
    public void setEvidenceService(VideoEvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @Autowired
    public void setOnlineTvDAO(OnlineTvDAO onlineTvDAO) {
        this.onlineTvDAO = onlineTvDAO;
    }
}
