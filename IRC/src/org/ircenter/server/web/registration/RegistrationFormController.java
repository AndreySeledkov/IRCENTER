package org.ircenter.server.web.registration;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.dao.registration.RegistrationDAO;
import org.ircenter.server.dao.user.UserInfoDAO;
import org.ircenter.server.service.authentication.LoggedUsers;
import org.ircenter.server.service.mail.RegistrationMail;
import org.ircenter.server.service.mail.RememberPasswordMail;
import org.ircenter.server.service.media.MediaHelper;
import org.ircenter.server.service.registration.RegistrationAjaxResponse;
import org.ircenter.server.service.registration.RegistrationValidator;
import org.ircenter.server.service.user.CustomUserDetailsService;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.service.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Properties;

@Controller
@RequestMapping("/signup")
public class RegistrationFormController extends PersistentTokenBasedRememberMeServices {
    private static final String RECAPTCHA_HTML = "reCaptchaHtml";

    @Autowired
    private ReCaptcha reCaptcha;


    private static final Log LOGGER = LogFactory.getLog(RegistrationFormController.class.getClass());

    private RegistrationDAO registrationDAO;
    private RegistrationValidator registrationValidator;
    private MessageSource messages;
    private UserInfoDAO userInfoDAO;
    private JdbcTokenRepositoryImpl jdbcTokenRepository;
    private JavaMailSender mailSender;

    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationFormController(CustomUserDetailsService customUserDetailsService) throws Exception {
        super();
        setKey("SpringSecured");
        setUserDetailsService(customUserDetailsService);
    }

    @Autowired
    public void setRegistrationDAO(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registration(HttpServletRequest request,
                                     HttpServletResponse response) {
        Properties properties = new Properties();
        properties.setProperty("theme", "white");

        String html = reCaptcha.createRecaptchaHtml(null, properties);
        ModelMap modelMap = new ModelMap();
        modelMap.put(RECAPTCHA_HTML, html);
        return new ModelAndView("new/signup", modelMap);
    }


//    @RequestMapping(value = "/availability", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    AvailableAjaxResponse getAvailability(@RequestParam String value, @RequestParam String valueName) {
//        boolean registrationFieldUniq = registrationDAO.checkFieldUnique(valueName, value.trim());
//        String messageKey = registrationFieldUniq ? valueName + "Uniq" : valueName + "NotUniq";
//        return new AvailableAjaxResponse(registrationFieldUniq, messages.getMessage("registration." + messageKey, null, null));
//    }

    @Autowired
    public void setMessages(MessageSource messages) {
        this.messages = messages;
    }

    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public
    @ResponseBody
    AvailableAjaxResponse manualyActivateRegistration(@RequestParam String activationCode,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response) {
        String uid = UserHelper.getUserName();
        if (activation(activationCode, request, response, "".equals(uid) ? "false" : uid)) {
            return new AvailableAjaxResponse(true, null);
        } else {
            return new AvailableAjaxResponse(false, messages.getMessage("activation.wrong", null, null));
        }
    }

    @RequestMapping(value = "/rememberpassword", method = RequestMethod.POST)
    public
    @ResponseBody
    AvailableAjaxResponse rememberPassword(@RequestParam("recaptcha_challenge_field") String challenge,
                                           @RequestParam("recaptcha_response_field") String captchaResponse, @RequestParam String login, HttpServletRequest request, HttpServletResponse response) {
        String remoteAddr = request.getRemoteAddr();
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, captchaResponse);

        if (!reCaptchaResponse.isValid()) {
            return new AvailableAjaxResponse(false, "Ошибка при вводе символов");
        }

        int count = userInfoDAO.getNameCount(login.trim());
        if (count > 0 || userInfoDAO.getLoginCountByLogin(login.trim()) > 0) {
            UserProfile userProfile;
            if (count > 0) {
                userProfile = userInfoDAO.getUserByName(login);
            } else {
                userProfile = userInfoDAO.getUserByLogin(login);
            }

            String newPassword = registrationValidator.generateRandomString(10);
            String newEncodePassword = passwordEncoder.encodePassword(newPassword, null);

            userInfoDAO.changePassword(newEncodePassword, userProfile.getUserId());

            //reAuthenticate(request, response, userProfile.getUsername());

            (new Thread(new RememberPasswordMail(newPassword, userProfile.getUsername(),
                    mailSender))).start();
            return new AvailableAjaxResponse(true, "e-mail с паролем отправлен");
        } else {
            return new AvailableAjaxResponse(false, "Неверный псевдоним или e-mail");
        }
    }


//    @RequestMapping("/activation/{activation}/{uid}")
//    public String activateRegistration(@PathVariable String activation, @PathVariable String uid,
//                                       HttpServletRequest request, HttpServletResponse response) {
//        activation(activation, request, response, uid);
//        return "redirect:/";
//    }

    @Transactional
    private boolean activation(String activation, HttpServletRequest request, HttpServletResponse response, String uid) {
        boolean complete = true;
        try {
            if (registrationDAO.isActivationPresent(activation)) {
                // переносим пользователя из временной таблицы в основную
                String login = registrationDAO.activateRegistration(activation, uid);
                authenticate(request, response, login);
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("ircr".equals(cookie.getName())) {
                            cookie.setMaxAge(0);
                        }
                    }
                }
            } else {
                complete = false;
            }
        } catch (Exception e) {
            LOGGER.error("exception when activate registration", e);
            complete = false;
        }
        if (complete) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.setAttribute("activation", false);
            }
        }
        return complete;
    }

    /**
     * Авторизация пользователя
     *
     * @param request  запрос
     * @param response ответ
     * @param login    пользователя
     */
    public void authenticate(HttpServletRequest request, HttpServletResponse response, String login) {
        PersistentRememberMeToken persistentToken = new PersistentRememberMeToken(login, generateSeriesData(), generateTokenData(), new Date());
        // сохраняем в базу информацию о токене запоминания клиента
        jdbcTokenRepository.createNewToken(persistentToken);
        // отдаём клиенту куку для автологина
        setCookie(new String[]{persistentToken.getSeries(), persistentToken.getTokenValue()}, Integer.MAX_VALUE, request, response);
        UserProfile userDetails = userInfoDAO.getUserByLogin(login);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Date bannedTo = userDetails.getBanned();
        if (bannedTo != null && bannedTo.getTime() > new Date().getTime()) {
            userDetails.setBanned(bannedTo);
        }
        //MediaHelper.saveDefaultImages();
        LoggedUsers.addLoggedUserInfo(userDetails);
    }


    public void authenticateSocial(HttpServletRequest request, HttpServletResponse response, String login) {
        PersistentRememberMeToken persistentToken = new PersistentRememberMeToken(login, generateSeriesData(), generateTokenData(), new Date());
        // сохраняем в базу информацию о токене запоминания клиента
        jdbcTokenRepository.createNewToken(persistentToken);

//        registrationDAO.activateSocialRegistration();
        // отдаём клиенту куку для автологина
        setCookie(new String[]{persistentToken.getSeries(), persistentToken.getTokenValue()}, Integer.MAX_VALUE, request, response);
        UserProfile userDetails = userInfoDAO.getUserByLogin(login);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Date bannedTo = userDetails.getBanned();
        if (bannedTo != null && bannedTo.getTime() > new Date().getTime()) {
            userDetails.setBanned(bannedTo);
        }
        //registrationDAO.activateSocialRegistration();

        MediaHelper.saveDefaultImages();
        LoggedUsers.addLoggedUserInfo(userDetails);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    RegistrationAjaxResponse processSubmit(@RequestParam("recaptcha_challenge_field") String challenge,
                                           @RequestParam("recaptcha_response_field") String response, @RequestParam String name, @RequestParam String login, @RequestParam String password,
                                           @RequestParam String passwordAgain,
                                           HttpServletRequest request) {
        RegistrationAjaxResponse registrationAjaxResponse = new RegistrationAjaxResponse();

        String remoteAddr = request.getRemoteAddr();
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, response);

        registrationValidator.validate(name, login, password, passwordAgain, reCaptchaResponse.isValid(), registrationAjaxResponse);
        if (!registrationAjaxResponse.isComplete()) {
            return registrationAjaxResponse;
        } else {
            LOGGER.info("save registration for " + name);
            try {
                String passEncode = passwordEncoder.encodePassword(password, null);

                long activation = registrationDAO.saveRegistration(name, login, passEncode);
                LOGGER.info("send mail registration to " + login);
                (new Thread(new RegistrationMail(name, login, activation, mailSender, request.getServerName(),
                        "" + request.getServerPort(), UserHelper.getUserName(),
                        request.getContextPath().equals("/") ? "/" : request.getContextPath() + "/"))).start();
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.setAttribute("activation", true);
                    session.setAttribute("activationUserName", login);
                }
            } catch (Exception e) {
                LOGGER.error("exception when save registration", e);
                registrationAjaxResponse.setComplete(false);
                return registrationAjaxResponse;
            }
            return registrationAjaxResponse;
        }
    }

    @Autowired
    public void setJdbcTokenRepository(JdbcTokenRepositoryImpl jdbcTokenRepository) {
        this.jdbcTokenRepository = jdbcTokenRepository;
    }

    public ShaPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Autowired
    public void setPasswordEncoder(ShaPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

//    public void authenticateSocial(HttpServletRequest request, HttpServletResponse httpResponse, String login) {
//        String insertAuth = "INSERT INTO authorities (user_id, authority) VALUES (:user_id, :role)";
//        String insertProfile = "INSERT INTO user_profile (user_id) VALUES (:user_id)";
//
//        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("user_id", generatedKeyHolder.getKey().longValue());
//        jdbcTemplate.update(insertProfile, parameterSource);
//        parameterSource.addValue("role", Role.ROLE_USER.getValue());
//        jdbcTemplate.update(insertAuth, parameterSource);
//    }

    private class AvailableAjaxResponse {

        private boolean available;

        private String errorMessage;

        private AvailableAjaxResponse(boolean available, String errorMessage) {
            this.available = available;
            this.errorMessage = errorMessage;
        }

        public boolean isAvailable() {
            return available;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    @Autowired
    public void setRegistrationValidator(RegistrationValidator registrationValidator) {
        this.registrationValidator = registrationValidator;
    }

    @Autowired
    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RequestMapping(value = "/deleteregbyuid/{uid}/", method = RequestMethod.GET)
    public
    @ResponseBody
    AvailableAjaxResponse deleteByUid(@PathVariable String uid, HttpServletRequest request) {
        if (registrationDAO.deleteTmpUserByUid(uid)) {
            HttpSession session = request.getSession();
            if (session != null) {
                session.setAttribute("activation", false);
            }
            return new AvailableAjaxResponse(true, "");
        } else {
            return new AvailableAjaxResponse(false, "Ошибка при удалении регистрации");
        }
    }

    @RequestMapping(value = "/deleteregbyusername/{login}/", method = RequestMethod.GET)
    public
    @ResponseBody
    AvailableAjaxResponse deleteByLogin(@PathVariable String login, HttpServletRequest request) {
        if (registrationDAO.deleteTmpUserByLogin(login)) {
            HttpSession session = request.getSession();
            if (session != null) {
                session.setAttribute("activation", false);
            }
            return new AvailableAjaxResponse(true, "");
        } else {
            return new AvailableAjaxResponse(false, "Ошибка при удалении регистрации");
        }
    }
}
