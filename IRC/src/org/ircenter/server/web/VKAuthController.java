package org.ircenter.server.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.dao.registration.RegistrationDAO;
import org.ircenter.server.dao.user.UserInfoDAO;
import org.ircenter.server.service.registration.RegistrationAjaxResponse;
import org.ircenter.server.web.registration.RegistrationFormController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

@Controller
@RequestMapping("/vkauth")
public class VKAuthController {

    private static final Log LOGGER = LogFactory.getLog(WelcomeController.class.getClass());
    private UserInfoDAO userInfoDAO;
    private RegistrationFormController registrationFormController;

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    RegistrationAjaxResponse getVKAuth(HttpServletRequest request, HttpServletResponse httpResponse) {

        RegistrationAjaxResponse registrationAjaxResponse = new RegistrationAjaxResponse();
        registrationAjaxResponse.setComplete(true);
        StringBuffer tmp = new StringBuffer();
        BufferedReader in = null;
        URL ssl;
        try {
            String line;
            Object json;
            String accessToken = request.getParameter("sid");
            String uid = request.getParameter("mid");

            String firstName = request.getParameter("user[first_name]");
            String lastName = request.getParameter("user[last_name]");

            //todo не работает на реальном сервере

            if (userInfoDAO.isVKUserNew(uid)) {
//                ssl = new URL("https://api.vkontakte.ru/method/getProfiles?" +
//                        "fields=first_name,last_name,bdate,city,country,sex,photo,photo_big&uid=" + uid + "&access_token=" + accessToken);
//                InputStream is = ssl.openStream();
//                in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//                tmp.setLength(0);
//                tmp.append(in.readLine());
//                while ((line = in.readLine()) != null) {
//                    tmp.append(line);
//                }
//                in.close();
//                json = JSONValue.parse(tmp.toString());
//                JSONArray response = (JSONArray) ((JSONObject) json).get("response");
//                JSONObject userParams = (JSONObject) response.get(0);
//                String firstName = (String) userParams.get("first_name");
//                String lastName = (String) userParams.get("last_name");
//                String bdate = (String) userParams.get("bdate");
//                String city = (String) userParams.get("city");
//                String country = (String) userParams.get("country");
//                Long sex = (Long) userParams.get("sex");
//                String photo = (String) userParams.get("photo");
//                String photo_big = (String) userParams.get("photo_big");
//
//
//                String cityId = (String) userParams.get("city");
//                if (cityId != null && cityId.length() != 0) {
//                    ssl = new URL("https://api.vkontakte.ru/method/getCities?api_id=2315302&v=2.0&cids=" + cityId
//                            + "&access_token=" + accessToken);
//                    is = ssl.openStream();
//                    in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//                    tmp.setLength(0);
//                    tmp.append(in.readLine());
//                    while ((line = in.readLine()) != null) {
//                        tmp.append(line);
//                    }
//                    in.close();
//                }
//                json = JSONValue.parse(tmp.toString());
//                response = (JSONArray) ((JSONObject) json).get("response");
//                userParams = (JSONObject) response.get(0);
//                String cityName = (String) userParams.get("name");
//                cityId = userLocationDAO.getCityId(cityName);
//                Set<String> uniqNames = new HashSet<String>();
//                uniqNames.add(firstName + " " + lastName);

                userInfoDAO.createVKUser(firstName + " " + lastName, uid);
                registrationFormController.authenticateSocial(request, httpResponse, firstName + " " + lastName);


//                if (nickname.length() != 0) {
//                    uniqNames.add(nickname.length() > 20 ? nickname.substring(0, 20) : nickname);
//                    addName(firstName + " " + nickname, uniqNames);
//                    addName(nickname + " " + firstName, uniqNames);
//                    addName(lastName + " " + nickname, uniqNames);
//                    addName(nickname + " " + lastName, uniqNames);
//                    addName(firstName + " " + lastName + " " + nickname, uniqNames);
//                    addName(firstName + " " + nickname + " " + lastName, uniqNames);
//                    addName(lastName + " " + firstName + " " + nickname, uniqNames);
//                    addName(nickname + " " + firstName + " " + lastName, uniqNames);
//                    addName(nickname + " " + lastName + " " + firstName, uniqNames);
//                    addName(lastName + " " + nickname + " " + firstName, uniqNames);
//                }
//                uniqNames.add(firstName.length() > 20 ? firstName.substring(0, 20) : firstName);
//                uniqNames.add(lastName.length() > 20 ? lastName.substring(0, 20) : lastName);
//                addName(firstName + " " + lastName, uniqNames);
//                addName(lastName + " " + firstName, uniqNames);
//                Iterator iterator = uniqNames.iterator();
//                while (iterator.hasNext()) {
//                    if (userInfoDAO.getNameCount((String) iterator.next()) > 0) {
//                        iterator.remove();
//                    }
//                }
//                HashMap<String, Object> model = new HashMap<String, Object>();
//                model.put("uniqNames", uniqNames);
//                model.put("vkId", uid);
//                model.put("cityId", cityId);

                return registrationAjaxResponse;
            } else {
                String login = userInfoDAO.getLoginByVkId(uid);
                registrationFormController.authenticateSocial(request, httpResponse, login);

                return registrationAjaxResponse;
            }
        } catch (Exception e) {
            LOGGER.error("Exception in VKauth", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error("Exception in VKauth", e);
                }
            }
        }
        return registrationAjaxResponse;
    }


    @Autowired
    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    @Autowired
    public void setRegistrationFormController(RegistrationFormController registrationFormController) {
        this.registrationFormController = registrationFormController;
    }

}
