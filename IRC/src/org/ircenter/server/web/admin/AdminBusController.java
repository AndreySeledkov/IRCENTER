package org.ircenter.server.web.admin;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.Bus;
import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
import org.ircenter.server.dao.Database;
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
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 17.05.12
 * Time: 1:04
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/bus")
public class AdminBusController {

    private Database database;
    private static final int Evidence_LIMIT = 10;

    @RequestMapping(method = RequestMethod.GET)
    public String getProgrammTv(Model model, HttpServletRequest request) {
        List<Bus> busList = database.getBusDAO().getBusList();

        model.addAttribute("busList", busList);
        return "admin/bus";

    }

    @RequestMapping(value = "/get_new", method = RequestMethod.GET)
    public String getNewProgrammTv(Model model, HttpServletRequest request) {
        return "admin/bus_new";
    }

    @RequestMapping(value = "/add_new", method = RequestMethod.GET)
    public String saveBus(HttpServletRequest request, @RequestParam long cityId, @RequestParam String date, @RequestParam String descr,@RequestParam int defaultImage) {

        Bus bus = new Bus();
        bus.setCityId(cityId);
        bus.setDeparture(MUtil.string2Date(date,Calendar.getInstance()));
        bus.setDescription(descr);
        bus.setDefaultImage(defaultImage);

        database.getBusDAO().saveBus(bus);
        return "redirect:/admin/bus";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteSecretSpiritWorld(@PathVariable Long id) {
        database.getBusDAO().removeBus(id);
        return "redirect:/admin/bus";
    }


    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

}
