package org.ircenter.server.web.admin;

import org.ircenter.server.bean.evidence.EvidenceHealing;
import org.ircenter.server.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 16.05.12
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/evidence_healing")
public class AdminEvidenceHealingController {
    private Database database;

    @RequestMapping(method = RequestMethod.GET)
    public String getEvidenceHealing(Model model, HttpServletRequest request) {

        EvidenceHealing evidenceHealing = database.getEvidenceHealingDAO().getEvidenceHealing();

        model.addAttribute("evidenceHealing", evidenceHealing);
        return "admin/evidence_healing";

    }

    @RequestMapping(value = "/add_new", method = RequestMethod.POST)
    public String saveEvidenceHealing(Model model, HttpServletRequest request, @RequestParam int defaultImage, @RequestParam String text) {

        EvidenceHealing evidenceHealing = new EvidenceHealing();
        evidenceHealing.setDefaultImage(defaultImage);
        evidenceHealing.setText(text);

        database.getEvidenceHealingDAO().saveEvidenceHealing(evidenceHealing);
        return "redirect:/admin/evidence_healing";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editEvidenceHealing(Model model, HttpServletRequest request, @PathVariable Long id) {

        EvidenceHealing evidenceHealing = database.getEvidenceHealingDAO().getEvidenceHealing(id);
        model.addAttribute("evidenceHealing", evidenceHealing);

        return "admin/evidence_healing_edit";
    }


    @RequestMapping(value = "/submit_edit_evidence_healing", method = RequestMethod.POST)
    public String saveEditTextEvidence(Model model, HttpServletRequest request, @RequestParam long id, @RequestParam int defaultImage, @RequestParam String text) {

        EvidenceHealing evidenceHealing = new EvidenceHealing();
        evidenceHealing.setId(id);
        evidenceHealing.setText(text);
        evidenceHealing.setDefaultImage(defaultImage);

        database.getEvidenceHealingDAO().updateEvidenceHealing(evidenceHealing);
        return "redirect:/admin/evidence_healing";
    }


    @RequestMapping(value = "/get_new", method = RequestMethod.GET)
    public String getNewEvidenceHealing(Model model, HttpServletRequest request) {
        return "admin/evidence_healing_new";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteEvidenceHealing(@PathVariable Long id) {
        database.getEvidenceHealingDAO().removeEvidenceHealing(id);
        return "redirect:/admin/evidence_healing";
    }

    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
