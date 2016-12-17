package org.ircenter.server.service.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class RememberPasswordMail implements Runnable {

    private static final Log LOGGER = LogFactory.getLog(RememberPasswordMail.class.getClass());
    private String password;
    private String login;
    private JavaMailSender javaMailSender;

    public RememberPasswordMail(String password, String login, JavaMailSender javaMailSender) {
        this.password = password;
        this.login = login;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void run() {
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
            helper.setTo(login);
            helper.setSubject("Password reminder");
            helper.setText("Ваш пароль для входа на сайт <a href=\"http://vo.org.ua/\">vo.org.ua</a> - <b>"
                    + password + "</b>", true);
            javaMailSender.send(msg);
        } catch (Throwable e) {
            LOGGER.error("RememberPasswordMail sent error " + login, e);
        }
    }
}