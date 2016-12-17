package org.ircenter.server.web;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
import org.ircenter.server.bean.news.NewsComment;
import org.ircenter.server.dao.evidence.ProgrammTvDAO;
import org.ircenter.server.service.mail.BusRegistrationMail;
import org.ircenter.server.service.mail.RememberPasswordMail;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.web.edit.EditAjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.05.12
 * Time: 1:52
 */
@Controller
@RequestMapping("/city")
public class CityController {

    private ProgrammTvDAO programmTvDAO;
    private JavaMailSender mailSender;

    @RequestMapping("/{id}")
    public String book(@PathVariable String id, Model model) {
        List<ProgrammTv> programmTvList = programmTvDAO.getProgrammTvByType(ProgrammTvType.CNL);
        Calendar newCal = new GregorianCalendar();

        for (ProgrammTv programmTv : programmTvList) {
            newCal.setTime(programmTv.getDate());
            int day = newCal.get(Calendar.DAY_OF_WEEK);
            switch (day) {
                case 1:     //Воскре
                    programmTv.setDay("Воскресенье");
                    break;
                case 2:             //Понед
                    programmTv.setDay("Понедельник");
                    break;
                case 3:
                    programmTv.setDay("Вторник");
                    break;
                case 4:
                    programmTv.setDay("Среда");
                    break;
                case 5:
                    programmTv.setDay("Четверг");
                    break;
                case 6:
                    programmTv.setDay("Пятница");
                    break;
                case 7:
                    programmTv.setDay("Суббота");
                    break;
            }

            Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
            calendar.setTime(programmTv.getDate());   // assigns calendar to given date
            int hours = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
            int minutes = calendar.get(Calendar.MINUTE);

            programmTv.setTime(hours + ":" + minutes);
        }

        model.addAttribute("programmTvList", programmTvList);
        return "/city/" + id;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public
    @ResponseBody
    EditAjaxResponse registration(@RequestParam String name, @RequestParam String phone, @RequestParam String email, @RequestParam String message) {
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || message.isEmpty() || !MUtil.isValidLogin(email)) {
            return new EditAjaxResponse(false);
        }

        if (name.length() > 50) {
            name = name.substring(0, 50);
        }

        if (phone.length() > 30) {
            phone = phone.substring(0, 30);
        }

        if (email.length() > 50) {
            email = email.substring(0, 50);
        }

        if (message.length() > 300) {
            message = message.substring(0, 300);
        }

        new Thread(new BusRegistrationMail(name, phone, email, message, mailSender)).start();
        return new EditAjaxResponse(true);
    }

    @Autowired
    public void setProgrammTvDAO(ProgrammTvDAO programmTvDAO) {
        this.programmTvDAO = programmTvDAO;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
