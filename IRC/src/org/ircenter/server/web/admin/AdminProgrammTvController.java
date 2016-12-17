package org.ircenter.server.web.admin;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
import org.ircenter.server.bean.evidence.SecretSpiritualWorld;
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
 * Date: 17.05.12
 * Time: 1:04
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/programm_tv")
public class AdminProgrammTvController {

    private Database database;
    private static final int Evidence_LIMIT = 10;

    @RequestMapping(method = RequestMethod.GET)
    public String getProgrammTv(Model model, HttpServletRequest request) {
        List<ProgrammTv> programmTvList = database.getProgrammTvDAO().getProgrammTv();

        model.addAttribute("programmTvList", programmTvList);
        return "admin/programm_tv";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editMinutesToGod(Model model, HttpServletRequest request, @PathVariable Long id) {

        ProgrammTv programmTv = database.getProgrammTvDAO().getProgrammTv(id);
        model.addAttribute("programmTv", programmTv);

        return "admin/programm_tv_edit";
    }


    @RequestMapping(value = "/submit_edit_programm_tv", method = RequestMethod.POST)
    public String saveEditTextEvidence(Model model, HttpServletRequest request, @RequestParam long id, @RequestParam String title, @RequestParam String days, @RequestParam String date, @RequestParam int type) {

        ProgrammTv programmTv = new ProgrammTv();
        programmTv.setId(id);
        programmTv.setTitle(title);
        programmTv.setDays(days);
        programmTv.setDate(MUtil.string2DateTime(date, Calendar.getInstance()));
        programmTv.setProgrammTvType(ProgrammTvType.valueOf(type));

        database.getProgrammTvDAO().updateProgrammTv(programmTv);
        return "redirect:/admin/programm_tv";
    }

    @RequestMapping(value = "/get_new", method = RequestMethod.GET)
    public String getNewProgrammTv(Model model, HttpServletRequest request) {
        return "admin/programm_tv_new";
    }

    @RequestMapping(value = "/add_new", method = RequestMethod.POST)
    public String saveProgrammTv(HttpServletRequest request, @RequestParam String title, @RequestParam String days, @RequestParam String date, @RequestParam int type) {

        ProgrammTv programmTv = new ProgrammTv();
        programmTv.setDate(MUtil.string2DateTime(date, Calendar.getInstance()));
        programmTv.setDays(days);
        programmTv.setProgrammTvType(ProgrammTvType.valueOf(type));
        programmTv.setTitle(title);

        database.getProgrammTvDAO().saveProgrammTv(programmTv);
        return "redirect:/admin/programm_tv";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteSecretSpiritWorld(@PathVariable Long id) {
        database.getProgrammTvDAO().removeProgrammTv(id);
        return "redirect:/admin/programm_tv";
    }


    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

}
