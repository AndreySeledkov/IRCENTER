package org.ircenter.server.web.admin;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.TextEvidence;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.user.UserProfile;
import org.ircenter.server.util.paging.PageListShort;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 10.05.12
 * Time: 13:52
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/text_evidence")
public class EvidenceTextController {
    private Database database;
    private static final int Evidence_LIMIT = 10;

    @RequestMapping(method = RequestMethod.GET)
    public String getTextEvidence(Model model, HttpServletRequest request) {
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
            textEvidence.setText(Jsoup.parse(textEvidence.getText()).text());
            if (textEvidence.getText().length() > 60) {
                textEvidence.setText(textEvidence.getText().substring(0, 60) + "...");
            } else {
                textEvidence.setText(Jsoup.parse(textEvidence.getText()).text() + "...");
            }
        }


        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/admin/text_evidence?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(evidenceTextCount);
        pageListPlain.generate(builder);

        model.addAttribute("evidenceTextCount", evidenceTextCount);

        model.addAttribute("pagination", builder);
        model.addAttribute("textEvidenceList", textEvidenceList);
        return "admin/text_evidence";
    }

    @RequestMapping(value = "/get_new", method = RequestMethod.GET)
    public String getNewTextEvidence(Model model, HttpServletRequest request) {

        return "admin/text_evidence_new";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editTextEvidence(Model model, HttpServletRequest request, @PathVariable Long id) {

        TextEvidence textEvidence = database.getTextEvidenceDAO().getTextEvidence(id);
        model.addAttribute("textEvidence", textEvidence);

        return "admin/text_evidence_edit";
    }


    @RequestMapping(value = "/submit_edit_text_evidence", method = RequestMethod.POST)
    public String saveEditTextEvidence(Model model, HttpServletRequest request, @RequestParam long id, @RequestParam int defaultImage, @RequestParam String title, @RequestParam String text) {

        TextEvidence textEvidence = new TextEvidence();
        textEvidence.setId(id);
        textEvidence.setText(text);
        textEvidence.setTitle(title);
        textEvidence.setDefaultImage(defaultImage);

        database.getTextEvidenceDAO().updateTextEvidence(textEvidence);
        return "redirect:/admin/text_evidence";
    }

    @RequestMapping(value = "/add_new", method = RequestMethod.POST)
    public String saveTv(Model model, HttpServletRequest request, @RequestParam int defaultImage, @RequestParam String title, @RequestParam String text) {

        TextEvidence textEvidence = new TextEvidence();
        textEvidence.setCreateDate(new Date());
        textEvidence.setText(text);
        textEvidence.setTitle(title);
        textEvidence.setDefaultImage(defaultImage);

        database.getTextEvidenceDAO().saveTextEvidence(textEvidence);
        return "redirect:/admin/text_evidence";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteTextEvidence(@PathVariable Long id) {
        database.getTextEvidenceDAO().removeTextEvidence(id);
        return "redirect:/admin/text_evidence";
    }


    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
