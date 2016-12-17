package org.ircenter.server.web.admin;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.OnlineTv;
import org.ircenter.server.dao.online_tv.OnlineTvDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

/**
 * User: Seledkov Andriy
 * Date: 11.05.12
 * Time: 0:27
 */
@Controller
@RequestMapping("/admin/tv_online")
public class AdminTvOnlineController {
    private OnlineTvDAO onlineTvDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String getTvOnline(Model model, HttpServletRequest request) {

        List<OnlineTv> onlineTv = onlineTvDAO.getOnlineTv10();

        model.addAttribute("onlineTv", onlineTv);
        return "admin/tv_online";

    }

    @RequestMapping(value = "/add_new", method = RequestMethod.POST)
    public String saveOnlineTv(Model model, HttpServletRequest request,@RequestParam String date) {

        OnlineTv onlineTv = new OnlineTv();
        onlineTv.setDate(MUtil.string2DateTime(date,Calendar.getInstance()));
        onlineTvDAO.saveOnlineTv(onlineTv);
        return "redirect:/admin/tv_online";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editOnlineTv(Model model, HttpServletRequest request, @PathVariable Long id) {

        OnlineTv onlineTv = onlineTvDAO.getOnlineTv(id);
        model.addAttribute("onlineTv", onlineTv);

        return "admin/tv_online_edit";
    }


    @RequestMapping(value = "/submit_edit_online_tv", method = RequestMethod.POST)
    public String saveEditOnlineTv(Model model, HttpServletRequest request, @RequestParam long id, @RequestParam String date) {
        OnlineTv onlineTv = new OnlineTv();
        onlineTv.setId(id);
        onlineTv.setDate(MUtil.string2DateTime(date, Calendar.getInstance()));

        onlineTvDAO.updateOnlineTv(onlineTv);
        return "redirect:/admin/tv_online";
    }


    @RequestMapping(value = "/get_new", method = RequestMethod.GET)
    public String getNewOnlineTv(Model model, HttpServletRequest request) {
        return "admin/tv_online_new";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteEvidenceHealing(@PathVariable Long id) {
        onlineTvDAO.removeOnlineTv(id);
        return "redirect:/admin/tv_online";
    }

    @Autowired
    public void setOnlineTvDAO(OnlineTvDAO onlineTvDAO) {
        this.onlineTvDAO = onlineTvDAO;
    }
}
