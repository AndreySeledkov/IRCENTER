package org.ircenter.server.web.admin;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.VideoEvidence;
import org.ircenter.server.service.evidence.VideoEvidenceService;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Seledkov Andriy
 * Date: 10.05.12
 * Time: 13:52
 */
@Controller
@RequestMapping("/admin/video_evidence")
public class EvidenceVideoController {
    private static final int Evidence_LIMIT = 10;
    private VideoEvidenceService evidenceService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUsers(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        int evidenceVideoCount = evidenceService.getVideoEvidenceCount();
        List<VideoEvidence> videoEvidenceList = evidenceService.getVideoEvidence((page - 1) * Evidence_LIMIT, Evidence_LIMIT);
        List<VideoEvidence> copyList = new ArrayList<VideoEvidence>(videoEvidenceList.size());
        for (VideoEvidence videoEvidence : copyList) {
            VideoEvidence copy = new VideoEvidence(videoEvidence);
            copy.setLink(MUtil.replaceHTMLtext(copy.getLink()));
            copyList.add(copy);
        }


        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/admin/video_evidence?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(evidenceVideoCount);
        pageListPlain.generate(builder);

        model.addAttribute("evidenceVideoCount", evidenceVideoCount);
        model.addAttribute("pagination", builder);
        model.addAttribute("videoEvidenceList", copyList);
        return "admin/video_evidence";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editVideoEvidence(Model model, HttpServletRequest request, @PathVariable Long id) {

        VideoEvidence videoEvidence = evidenceService.getVideoEvidence(id);
        VideoEvidence copy = new VideoEvidence(videoEvidence);
        copy.setLink(MUtil.replaceHTMLtext(copy.getLink()));
        model.addAttribute("videoEvidence", copy);

        return "admin/video_evidence_edit";
    }


    @RequestMapping(value = "/submit_edit_video_evidence", method = RequestMethod.POST)
    public String saveEditVideoEvidence(Model model, HttpServletRequest request, @RequestParam long id, @RequestParam String title, @RequestParam String link) {

        VideoEvidence videoEvidence = new VideoEvidence();
        videoEvidence.setId(id);
        try {
            link = link.trim();
            link = link.replaceAll("width=\"420\"", "width=\"640\"");
            link = link.replaceAll("height=\"315\"", "height=\"360\"");
            link = link.replaceAll("allowscriptaccess=\"always\"", "allowscriptaccess=\"always\" wmode=\"transparent\"");

            int g = link.indexOf("src=") + "src=http://www.youtube.com/v/".length() + 1;
            String url = link.substring(g, link.indexOf("?", g));
            videoEvidence.setLink(link);
            videoEvidence.setYoutubeId(url);
            videoEvidence.setTitle(title.trim());

            evidenceService.updateVideoEvidence(videoEvidence);
        } catch (Exception e) {

        }
        return "redirect:/admin/video_evidence";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteVideoEvidence(@PathVariable Long id) {
        evidenceService.removeVideoEvidence(id);
        return "redirect:/admin/video_evidence";
    }


    @RequestMapping(value = "/get_new", method = RequestMethod.GET)
    public String getNewVideoEvidence(Model model, HttpServletRequest request) {

        return "admin/video_evidence_new";
    }

    @RequestMapping(value = "/add_new", method = RequestMethod.POST)
    public String saveTv(Model model, HttpServletRequest request, @RequestParam String title, @RequestParam String link) {


        VideoEvidence videoEvidence = new VideoEvidence();
        videoEvidence.setCreateDate(new Date());
        try {
            link = link.trim();
            link = link.replaceAll("width=\"420\"", "width=\"640\"");
            link = link.replaceAll("height=\"315\"", "height=\"360\"");
            link = link.replaceAll("allowscriptaccess=\"always\"", "allowscriptaccess=\"always\" wmode=\"transparent\"");

            int g = link.indexOf("src=") + "src=http://www.youtube.com/v/".length() + 1;
            String url = link.substring(g, link.indexOf("?", g));
            videoEvidence.setLink(link);
            videoEvidence.setYoutubeId(url);
            videoEvidence.setTitle(title);

            evidenceService.saveVideoEvidence(videoEvidence);
        } catch (Exception e) {

        }
        return "redirect:/admin/video_evidence";
    }

    @Autowired
    public void setEvidenceService(VideoEvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }
}
