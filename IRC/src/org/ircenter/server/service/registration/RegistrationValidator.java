package org.ircenter.server.service.registration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.EmailValidator;
import org.ircenter.server.dao.registration.RegistrationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("registrationValidator")
public class RegistrationValidator {
    private static final Log LOGGER = LogFactory.getLog(RegistrationValidator.class.getClass());
    private static final Pattern pattern = Pattern.compile("[0-9a-zA-Z _йцукенгшщзхъёфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ.-]*");
    public static final int MAX_NAME_LENGTH = 20;
    public static final int MAX_EMAIL_LENGTH = 60;
    public static final int MIN_PASSWORD_LENGTH = 5;
    private static final String SYMBOLS = "qwertyuiopasdfghjklzxcvbnm0123456789-_";
    private Random random = new Random();

    private RegistrationDAO registrationDAO;
    private MessageSource messages;


    public void validate(String name, String login, String password, String passwordAgain, boolean isCaptchaValid, RegistrationAjaxResponse registrationAjaxResponse) {
        LOGGER.info("validate registration form");

        login = login.trim();
        Matcher matcher = pattern.matcher(name.trim());
        if (name.isEmpty()) {
            registrationAjaxResponse.setNameError(messages.getMessage("registration.emptyName", null, null));
            registrationAjaxResponse.setComplete(false);
        } else if (name.length() > MAX_NAME_LENGTH) {
            registrationAjaxResponse.setNameError(messages.getMessage("registration.name2Long", new Object[]{MAX_NAME_LENGTH}, null));
            registrationAjaxResponse.setComplete(false);
        } else if (!matcher.matches()) {
            registrationAjaxResponse.setNameError(messages.getMessage("registration.namePattern", null, null));
            registrationAjaxResponse.setComplete(false);
        } else if (!registrationDAO.checkFieldUnique("name", name)) {
            registrationAjaxResponse.setNameError(messages.getMessage("registration.nameNotUniq", null, null));
            registrationAjaxResponse.setComplete(false);
        } else if (!EmailValidator.getInstance().isValid(login)) {
            registrationAjaxResponse.setUserLoginError(messages.getMessage("registration.wrongusername", null, null));
            registrationAjaxResponse.setComplete(false);
        } else if (login.length() > MAX_EMAIL_LENGTH) {
            registrationAjaxResponse.setUserLoginError(messages.getMessage("registration.username2Long", new Object[]{MAX_EMAIL_LENGTH}, null));
            registrationAjaxResponse.setComplete(false);
        } else if (!registrationDAO.checkFieldUnique("username", login)) {
            registrationAjaxResponse.setUserLoginError(messages.getMessage("registration.usernameNotUniq", null, null));
            registrationAjaxResponse.setComplete(false);
        } else if (login.isEmpty()) {
            registrationAjaxResponse.setUserLoginError(messages.getMessage("registration.emptyUserName", null, null));
            registrationAjaxResponse.setComplete(false);
        } else if (password.length() < MIN_PASSWORD_LENGTH) {
            registrationAjaxResponse.setPasswordError(messages.getMessage("registration.shortPassword", null, null));
            registrationAjaxResponse.setComplete(false);
        } else if (!password.equals(passwordAgain)) {
            registrationAjaxResponse.setPasswordAgainError(messages.getMessage("registration.passwordsNotIdentical", null, null));
            registrationAjaxResponse.setComplete(false);
        }
//        else if (!license) {
//            registrationAjaxResponse.setPasswordAgainError(messages.getMessage("registration.passwordsNotIdentical", null, null));
//            registrationAjaxResponse.setComplete(false);
//        }
        else if (!isCaptchaValid) {
            registrationAjaxResponse.setCaptchaError(messages.getMessage("registration.captchaError", null, null));
            registrationAjaxResponse.setComplete(false);
        }

    }

    @Autowired
    public void setRegistrationDAO(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    public String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
        }
        return builder.toString();
    }

    @Autowired
    public void setMessages(MessageSource messages) {
        this.messages = messages;
    }
}
