package org.ircenter.server.web.admin;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.EvidenceHealing;
import org.ircenter.server.bean.evidence.OnlineEvidence;
import org.ircenter.server.dao.Database;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 16.05.12
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/evidence_online")
public class AdminEvidenceOnlineController {
    private Database database;
    private static final int Evidence_LIMIT = 10;

    @RequestMapping(method = RequestMethod.GET)
    public String getEvidenceOnline(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        int onlineEvidenceCount = database.getOnlineEvidenceDAO().getOnlineEvidenceCount();
        List<OnlineEvidence> onlineEvidenceList = database.getOnlineEvidenceDAO().getOnlineEvidence((page - 1) * Evidence_LIMIT, Evidence_LIMIT);

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/admin/evidence_online?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(onlineEvidenceCount);
        pageListPlain.generate(builder);

        model.addAttribute("onlineEvidenceCount", onlineEvidenceCount);

        model.addAttribute("pagination", builder);
        model.addAttribute("onlineEvidenceList", onlineEvidenceList);
        return "admin/evidence_online";

    }

    @RequestMapping(value = "/add_new", method = RequestMethod.POST)
    public String saveEvidenceOnline(Model model, HttpServletRequest request, @RequestParam String text, @RequestParam String title) {

        OnlineEvidence onlineEvidence = new OnlineEvidence();
        onlineEvidence.setTitle(title);
        onlineEvidence.setText(text);
        onlineEvidence.setCreateDate(new Date());

        database.getOnlineEvidenceDAO().saveOnlineEvidence(onlineEvidence);
        return "redirect:/admin/evidence_online";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editEvidenceOnline(Model model, HttpServletRequest request, @PathVariable Long id) {

        OnlineEvidence onlineEvidence = database.getOnlineEvidenceDAO().getOnlineEvidence(id);
        model.addAttribute("onlineEvidence", onlineEvidence);

        return "admin/evidence_online_edit";
    }


    @RequestMapping(value = "/submit_edit_evidence_online", method = RequestMethod.POST)
    public String saveEditOnlineEvidence(Model model, HttpServletRequest request, @RequestParam long id, @RequestParam String text, @RequestParam String title) {

        OnlineEvidence onlineEvidence = new OnlineEvidence();
        onlineEvidence.setId(id);
        onlineEvidence.setText(text);
        onlineEvidence.setTitle(title);
//        onlineEvidence.setCreateDate(new Date());

        database.getOnlineEvidenceDAO().updateOnlineEvidence(onlineEvidence);
        return "redirect:/admin/evidence_online";
    }


    @RequestMapping(value = "/get_new", method = RequestMethod.GET)
    public String getNewEvidenceHealing(Model model, HttpServletRequest request) {
        return "admin/evidence_online_new";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteOnlineEvidence(@PathVariable Long id) {
        database.getOnlineEvidenceDAO().removeOnlineEvidence(id);
        return "redirect:/admin/online_evidence";
    }

    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
