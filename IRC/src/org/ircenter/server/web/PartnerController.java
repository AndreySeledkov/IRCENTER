package org.ircenter.server.web;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.ircenter.server.MUtil;
import org.ircenter.server.bean.Partner;
import org.ircenter.server.bean.PastorTreatment;
import org.ircenter.server.bean.evidence.EvidenceHealing;
import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
import org.ircenter.server.bean.location.Country;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.web.edit.EditAjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 15.05.12
 * Time: 2:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/partner")
public class PartnerController {
    private static final String RECAPTCHA_HTML = "reCaptchaHtml";
    private Database database;
    @Autowired
    private ReCaptcha reCaptcha;

    @RequestMapping(method = RequestMethod.GET)
    public String getPatnerView(Model model, HttpServletRequest request) {
        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());
        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);
        Collection<Country> countries = database.getUserLocationDAO().getAllCountries();

        List<ProgrammTv> programmTvList = database.getProgrammTvDAO().getProgrammTvByType(ProgrammTvType.CNL);

        model.addAttribute("programmTvList", programmTvList);
        model.addAttribute("countries", countries);

        Properties properties = new Properties();
        properties.setProperty("theme", "white");

        String html = reCaptcha.createRecaptchaHtml(null, properties);
        model.addAttribute(RECAPTCHA_HTML, html);

        PastorTreatment pastorTreatment = database.getPastorTreatmentDAO().getPastorTreatment();
        if (pastorTreatment != null) {
            if (pastorTreatment.getText().length() > 150) {
                pastorTreatment.setFirstText(pastorTreatment.getText().substring(0, 150) + pastorTreatment.getText().substring(150, pastorTreatment.getText().indexOf(" ", 150)) + "...");
                pastorTreatment.setSecondText(pastorTreatment.getText());
            } else {
                pastorTreatment.setFirstText(pastorTreatment.getText());
            }
        }

        model.addAttribute("pastorTreatment", pastorTreatment);

        EvidenceHealing evidenceHealing = database.getEvidenceHealingDAO().getEvidenceHealing();
        model.addAttribute("evidenceHealing", evidenceHealing);
        if (pastorTreatment != null) {
            if (evidenceHealing.getText().length() > 150) {
                evidenceHealing.setFirstText(evidenceHealing.getText().substring(0, 150) + evidenceHealing.getText().substring(150, evidenceHealing.getText().indexOf(" ", 150)) + "...");
                evidenceHealing.setSecondText(evidenceHealing.getText());
            } else {
                evidenceHealing.setFirstText(evidenceHealing.getText());
            }
        }

        return "new/partner";
    }

    @RequestMapping(value = "/send_partner", method = RequestMethod.POST)
    @ResponseBody
    public EditAjaxResponse sendPartner(@RequestParam("recaptcha_challenge_field") String challenge,
                                        @RequestParam("recaptcha_response_field") String captchaResponse, @RequestParam String name, @RequestParam String mail, @RequestParam String phone, @RequestParam int country, @RequestParam String text, HttpServletRequest request, HttpServletResponse response) {

        String remoteAddr = request.getRemoteAddr();
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, captchaResponse);

        if (!reCaptchaResponse.isValid() || !MUtil.isValidLogin(mail)) {
            return new EditAjaxResponse(false);
        } else {
            Partner partner = new Partner();
            partner.setMail(mail);
            partner.setCountry(country);

            if (name.length() > 50) {
                name = MUtil.replaceHTMLtext(name.substring(0, 50));
            }
            if (phone.length() > 50) {
                phone = MUtil.replaceHTMLtext(phone.substring(0, 50));
            }
            if (text.length() > 500) {
                text = MUtil.replaceHTMLtext(text.substring(0, 500));
            }

            partner.setName(name);
            partner.setPhone(phone);
            partner.setText(text);
            partner.setDate(new Date());

            database.getPartnerDAO().savePartner(partner);

            return new EditAjaxResponse(true);
        }
    }

    @RequestMapping(value = "/send_partner_prayer", method = RequestMethod.POST)
    @ResponseBody
    public EditAjaxResponse sendPartnerPrayer(@RequestParam String name, @RequestParam String mail, @RequestParam String phone, @RequestParam int country, @RequestParam String text, HttpServletRequest request, HttpServletResponse response) {

        if (!MUtil.isValidLogin(mail)) {
            return new EditAjaxResponse(false);
        } else {
            Partner partner = new Partner();
            partner.setMail(mail);
            partner.setCountry(country);

            if (name.length() > 50) {
                name = MUtil.replaceHTMLtext(name.substring(0, 50));
            }
            if (phone.length() > 50) {
                phone = MUtil.replaceHTMLtext(phone.substring(0, 50));
            }
            if (text.length() > 500) {
                text = MUtil.replaceHTMLtext(text.substring(0, 500));
            }
            partner.setName(name);
            partner.setPhone(phone);
            partner.setText(text);
            partner.setDate(new Date());

            database.getPartnerPrayerDAO().savePartnerPrayer(partner);

            return new EditAjaxResponse(true);
        }
    }

    @RequestMapping(value = "/get_form", method = RequestMethod.GET)
    public String getPartnerForm(Model model, HttpServletRequest request, HttpServletResponse response) {
        Collection<Country> countries = database.getUserLocationDAO().getAllCountries();

        model.addAttribute("countries", countries);
        Properties properties = new Properties();
        properties.setProperty("theme", "white");

        String html = reCaptcha.createRecaptchaHtml(null, properties);
        model.addAttribute(RECAPTCHA_HTML + "1", html);

        return "new/partner_form";
    }


    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
