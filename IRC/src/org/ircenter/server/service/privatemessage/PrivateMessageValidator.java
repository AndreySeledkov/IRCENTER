package org.ircenter.server.service.privatemessage;

import org.ircenter.server.bean.privatemessage.PrivateMessage;import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PrivateMessageValidator implements Validator {

    private static final int MAX_BODY_LENGTH = 1000;

    @Override
    public boolean supports(Class<?> clazz) {
        return PrivateMessage.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PrivateMessage privateMessage = (PrivateMessage) target;
        String body = privateMessage.getBody();
        if (body.length() > MAX_BODY_LENGTH) {
            errors.rejectValue("body", "addpost.body2long", new Object[]{MAX_BODY_LENGTH}, null);
        } else if (body.isEmpty()) {
            errors.rejectValue("body", "addpm.emptyBody", null, null);
        }
//        if (!privateMessage.isCaptchaPassed()) {
//            errors.rejectValue("captcha", "addpost.captchaNotPassed");
//        }
    }
}
