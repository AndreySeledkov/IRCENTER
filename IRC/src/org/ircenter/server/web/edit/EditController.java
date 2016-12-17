package org.ircenter.server.web.edit;

import org.ircenter.server.bean.edit.UserContacts;
import org.ircenter.server.bean.edit.UserInterests;
import org.ircenter.server.bean.edit.UserMain;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.edit.UserContactsValidator;
import org.ircenter.server.service.edit.UserInterestsValidator;
import org.ircenter.server.service.edit.UserMainValidator;
import org.ircenter.server.service.user.Gender;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.service.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormatSymbols;
import java.util.Locale;

@Controller
public class EditController {

    private Database database;

    private UserContactsValidator userContactsValidator;
    private UserInterestsValidator userInterestsValidator;
    private UserMainValidator userMainValidator;


    @RequestMapping(value = "/all_edit_profile_entity", method = RequestMethod.GET)
    public String edit(Model model, HttpServletRequest request) { //todo check act for valid if invalid show main
        String act = request.getParameter("act");
        setModel(model, act);
        return "./new/edit_profile_entity";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String allEdit(Model model, HttpServletRequest request) {
        String act = request.getParameter("act");
        setModel(model, act);
        return "./new/edit";
    }

    private void setModel(Model model, String act) {
        if (act == null || act.equals("main")) {
            UserProfile userProfile = UserHelper.getUserProfile();
            UserMain userMain = new UserMain(userProfile.getGivenName(), userProfile.getSurname(), userProfile.getGender().getGenderId(), userProfile.getDayOfBirth(),
                    userProfile.getCountryId(), userProfile.getRegionId(), userProfile.getCityId());
            model.addAttribute("userMainInfo", userMain);
            model.addAttribute("months", new DateFormatSymbols(new Locale("RU")).getMonths());
            model.addAttribute("genders", Gender.values());
            model.addAttribute("countries", database.getUserLocationDAO().getAllCountries());
            if (userMain.getCountryId() > 0) {
                model.addAttribute("regions", database.getUserLocationDAO().getRegionById(userMain.getCountryId()));
            }
            if (userMain.getRegionId() > 0) {
                model.addAttribute("cities", database.getUserLocationDAO().getCityById(userMain.getRegionId()));
            }
        } else if (act.equals("contacts")) {
            UserContacts userContacts = database.getUserContactsDAO().getUserContactsByClientId(UserHelper.getUserId());
            model.addAttribute("userContacts", userContacts);
        } else if (act.equals("interests")) {
            UserInterests userInterests = database.getUserInterestsDAO().getUserInterestsByClientId(UserHelper.getUserId());
            model.addAttribute("userInterests", userInterests);
        }
    }

    @RequestMapping(value = "/edit_profile_entity", params = {"action=general"}, method = RequestMethod.POST)
    public
    @ResponseBody
    EditAjaxResponse saveGeneral(@RequestParam String name, @RequestParam String surname, @RequestParam Integer genderId,
                                 @RequestParam int dbDay, @RequestParam int dbMonth, @RequestParam int dbYear,
                                 @RequestParam int countryId, @RequestParam int regionId, @RequestParam int cityId) {
        database.getUserInfoDAO().editUserInfo(name, surname, genderId, dbDay, dbMonth, dbYear, countryId, regionId, cityId);
        EditAjaxResponse response = new EditAjaxResponse(true);
        return response;
    }

    @RequestMapping(value = "/edit_profile_entity", params = {"action=contacts"}, method = RequestMethod.POST)
    public
    @ResponseBody
    EditAjaxResponse saveContacts(@RequestParam String cellPhone, @RequestParam String homePhone, @RequestParam String skype, @RequestParam String ownSite) {
        database.getUserContactsDAO().saveUserContacts(cellPhone, homePhone, skype, ownSite);
        EditAjaxResponse response = new EditAjaxResponse(true);
        return response;
    }

    @RequestMapping(value = "/edit_profile_entity", params = {"action=interests"}, method = RequestMethod.POST)
    public
    @ResponseBody
    EditAjaxResponse saveInterests(@RequestParam String activities, @RequestParam String interests, @RequestParam String books, @RequestParam String about) {
        database.getUserInterestsDAO().saveUserInterests(activities, interests, books, about);
        EditAjaxResponse response = new EditAjaxResponse(true);
        return response;
    }

//    @RequestMapping(method = RequestMethod.POST, params = "saveUserContacts")
//    public String processContactsSubmit(@ModelAttribute("userContacts") UserContacts userContacts, BindingResult result,
//                                        SessionStatus status, HttpServletRequest request, Model model) {
//        //todo check validator
//
//
//        database.getUserContactsDAO().saveUserContacts(userContacts);
//        return "./new/edit_profile_entity";
//
//    }

//    @RequestMapping(method = RequestMethod.POST, params = "saveUserInterests")
//    public String processInterestsSubmit(@ModelAttribute("userInterests") UserInterests userInterests, BindingResult result,
//                                         SessionStatus status, HttpServletRequest request, Model model) {
//
//        database.getUserInterestsDAO().saveUserInterests(userInterests);
//        return "./new/edit_profile_entity";
//    }

//    @RequestMapping(method = RequestMethod.POST, params = "saveMainInfo")
//    public String processMainInfoSubmit(@ModelAttribute("userMainInfo") UserMain userMain, BindingResult result,
//                                        SessionStatus status, HttpServletRequest request, Model model) {
//        database.getUserInfoDAO().editUserInfo(userMain);
//        status.setComplete();
//        return "./new/edit_profile_entity";
//    }


    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
