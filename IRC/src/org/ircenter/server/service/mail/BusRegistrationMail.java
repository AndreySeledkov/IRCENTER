package org.ircenter.server.service.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * User: Seledkov Kostyantyn
 * Date: 26.05.12
 * Time: 16:19
 */
public class BusRegistrationMail implements Runnable {
    private static final Log LOGGER = LogFactory.getLog(RememberPasswordMail.class.getClass());
    private String name;
    private String number;
    private String email;
    private String message;
    private JavaMailSender javaMailSender;

    public BusRegistrationMail(String name, String number, String email, String message, JavaMailSender javaMailSender) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.message = message;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void run() {
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
            helper.setTo("bessonovrv@gmail.com");
            helper.setSubject("Регистрация автобусов");
            StringBuilder builder = new StringBuilder();
            builder.append("Имя:").append(name).append(",").append("Номер:").append(number).append("Почта:").append(email).append("Сообщение:").append(message);
            helper.setText(builder.toString(), false);
            javaMailSender.send(msg);
        } catch (Throwable e) {
            LOGGER.error("BusRegistrationMail sent error ", e);
        }
    }
}
