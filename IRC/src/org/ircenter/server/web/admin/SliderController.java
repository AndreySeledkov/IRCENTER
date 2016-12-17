package org.ircenter.server.web.admin;

import org.ircenter.server.bean.slider.Slider;
import org.ircenter.server.bean.slider.SliderType;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 03.05.12
 * Time: 9:05
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/slider")
public class SliderController {


    private Database database;


    @RequestMapping(method = RequestMethod.GET)
    public String slider(Model model, HttpServletRequest request) {
        List<Slider> sliderList = database.getSliderDAO().getSlider(SliderType.MAIN_SLIDER);
        model.addAttribute("sliders", sliderList);
        return "admin/slider";
    }

    @RequestMapping(value = "/delete_slider", method = RequestMethod.GET)
    @ResponseBody
    public String deleteSlider(@RequestParam Long id) {
        return database.getSliderDAO().removeSlider(id) ? "Ok" : "False";
    }

    @RequestMapping(value = "/add_slider", method = RequestMethod.GET)
    @ResponseBody
    public String addSlider(@RequestParam int imageId) {
        Slider slider = new Slider();
        slider.setSliderType(SliderType.MAIN_SLIDER);
        slider.setImgId(imageId);

        return database.getSliderDAO().addSlider(slider) ? "Ok" : "False";
    }


    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
