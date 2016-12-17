package org.ircenter.server.web.evidence;

import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
import org.ircenter.server.bean.evidence.TextEvidence;
import org.ircenter.server.bean.news.PieceNews;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.util.paging.PageListShort;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 10.05.12
 * Time: 13:52
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/text_evidence")
public class TextEvidenceController {
    private Database database;
    private static final int Evidence_LIMIT = 10;

    @RequestMapping(method = RequestMethod.GET)
    public String getTextEvidence(Model model, HttpServletRequest request) {
        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());
        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);
        List<ProgrammTv> programmTvList = database.getProgrammTvDAO().getProgrammTvByType(ProgrammTvType.CNL);
        model.addAttribute("programmTvList", programmTvList);

        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));
            TextEvidence textEvidence = database.getTextEvidenceDAO().getTextEvidence(id);
            database.getTextEvidenceDAO().setViewCount(textEvidence, 1);

            model.addAttribute("textEvidence", textEvidence);
            List<PieceNews> list = database.getNewsService().getNews(0, 4, -1);
            model.addAttribute("lastNews", list);
            return "new/text_full_evidence";
        }


        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        int evidenceTextCount = database.getTextEvidenceDAO().getTextEvidenceCount();
        List<TextEvidence> textEvidenceList = database.getTextEvidenceDAO().getTextEvidence((page - 1) * Evidence_LIMIT, Evidence_LIMIT);
        for (TextEvidence textEvidence : textEvidenceList) {
            String t = Jsoup.parse(textEvidence.getText()).text();
            if (t.length() > 200) {
                textEvidence.setText(t.substring(0, 200) + "...");
            } else {
                textEvidence.setText(t + "...");
            }
        }

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/text_evidence?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(evidenceTextCount);
        pageListPlain.generate(builder);

        model.addAttribute("evidenceTextCount", evidenceTextCount);

        model.addAttribute("pagination", builder);
        model.addAttribute("textEvidenceList", textEvidenceList);



        return "new/text_evidence";
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public ModelAndView getFullTextEvidence(HttpServletRequest request, @RequestParam Long id) {
        TextEvidence textEvidence = database.getTextEvidenceDAO().getTextEvidence(id);

        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("textEvidence", textEvidence);
        return new ModelAndView("new/text_full_evidence", model);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteTextEvidence(@PathVariable Long id) {
        database.getTextEvidenceDAO().removeTextEvidence(id);
        return "redirect:/text_evidence";
    }


    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
