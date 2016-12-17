package org.ircenter.server.service.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class RegistrationMail implements Runnable {

    private final  static Log LOGGER = LogFactory.getLog(RegistrationMail.class.getClass());

    private String name;
    private String login;
    private long activation;
    private JavaMailSender javaMailSender;
    private String serverName;
    private String serverPort;
    private String appName;
    private String uid;

    public RegistrationMail(String name, String login, long activation, JavaMailSender javaMailSender,
                      String serverName, String serverPort, String uid, String appName) {
        this.name = name;
        this.login = login;
        this.activation = activation;
        this.javaMailSender = javaMailSender;
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.appName = appName;
        this.uid = uid;
    }

    @Override
    public void run() {
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
            helper.setTo(login);
            helper.setFrom("registration@blabla.com");
            helper.setSubject("Registration confirmation");
            String url = "http://" + serverName + ":" + serverPort + appName + "registration/activation/" + activation
                    + "/" + ("".equals(uid) ? "false" : uid) + "/";
            helper.setText("Уважаемый(ая) " + name + ", Вы начали процесс регистрации на ресурсе" +
                    " <a href=\"http://vo.org.ua/\">vo.org.ua</a>. Для завершения регистрации " +
                    " введите код <b>" + activation + "</b> в строку" +
                    " активации на ресурсе. Если в течении суток Вы не закончите процесс регистрации Ваш код активации" +
                    " будет аннулирован.", true);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            LOGGER.error("mail sent error", e);
        }
    }
}