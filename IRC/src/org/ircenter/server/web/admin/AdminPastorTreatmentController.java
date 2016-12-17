package org.ircenter.server.web.admin;

import org.ircenter.server.bean.PastorTreatment;
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
 * Date: 15.05.12
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/pastor")
public class AdminPastorTreatmentController {

    private Database database;

    @RequestMapping(method = RequestMethod.GET)
    public String getEvidenceHealing(Model model, HttpServletRequest request) {

        PastorTreatment pastorTreatment = database.getPastorTreatmentDAO().getPastorTreatment();

        model.addAttribute("pastorTreatment", pastorTreatment);
        return "admin/pastor_treatment";

    }

    @RequestMapping(value = "/add_new", method = RequestMethod.POST)
    public String savePastorTreatment(Model model, HttpServletRequest request, @RequestParam int defaultImage, @RequestParam String text) {

        PastorTreatment pastorTreatment = new PastorTreatment();
        pastorTreatment.setDefaultImage(defaultImage);
        pastorTreatment.setText(text);

        database.getPastorTreatmentDAO().savePastorTreatment(pastorTreatment);
        return "redirect:/admin/pastor_treatment";
    }

    @RequestMapping(value = "/get_new", method = RequestMethod.GET)
    public String getNewPastorTreatment(Model model, HttpServletRequest request) {
        return "admin/pastor_treatment_new";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deletePastorTreatment(@PathVariable Long id) {
        database.getPastorTreatmentDAO().removePastorTreatment(id);
        return "redirect:/admin/pastor_treatment";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPastorTreatment(Model model, HttpServletRequest request, @PathVariable Long id) {

        PastorTreatment pastorTreatment = database.getPastorTreatmentDAO().getPastorTreatment();
        model.addAttribute("pastorTreatment", pastorTreatment);

        return "admin/pastor_treatment_edit";
    }


    @RequestMapping(value = "/submit_edit_pastor_treatment", method = RequestMethod.POST)
    public String saveEditPastorTreatment(Model model, HttpServletRequest request, @RequestParam long id, @RequestParam String text, @RequestParam int defaultImage) {

        PastorTreatment pastorTreatment = new PastorTreatment();
        pastorTreatment.setId(id);
        pastorTreatment.setText(text);
        pastorTreatment.setDefaultImage(defaultImage);

        database.getPastorTreatmentDAO().updatePastorTreatment(pastorTreatment);
        return "admin/pastor_treatment";
    }

    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

}
