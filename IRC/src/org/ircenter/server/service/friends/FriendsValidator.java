package org.ircenter.server.service.friends;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class FriendsValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void validate(Object o, Errors errors) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
