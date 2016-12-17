package org.ircenter.server.service.authentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class LoginFailureCounter implements Runnable {

    private final static Log LOGGER = LogFactory.getLog(LoginFailureCounter.class.getClass());
    private static ConcurrentHashMap<String, Integer> loginFailureCounterByUserName = new ConcurrentHashMap<String, Integer>();
    private static ConcurrentHashMap<String, Integer> loginFailureCounterByRemoutHost = new ConcurrentHashMap<String, Integer>();
    private static final int MAX_LOGIN_FAILURE = 3;
    private ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(1);

    /**
     * Увеличить счетчик ошибок логина по имени пользователя
     * @param userName имя пользователя
     */
    public static void addLoginFailureByUserName(String userName) {
        Integer integer = loginFailureCounterByUserName.get(userName);
        if (integer != null) {
            int i = integer + 1;
            loginFailureCounterByUserName.put(userName, i > 3 ? 3 : i);
        } else {
            loginFailureCounterByUserName.put(userName, 1);
        }
    }

    /**
     * Нужна ли капча
     * @param userName имя пользователя
     * @return Нужна ли капча
     */
    public static boolean isCaptchaNeededByUserName(String userName) {
        Integer loginFailuresCount = loginFailureCounterByUserName.get(userName);
        return loginFailuresCount != null && loginFailuresCount >= MAX_LOGIN_FAILURE;
    }

    /**
     * Удалить пользователя из хранилища
     * @param userName имя пользователя
     */
    public static void removeByUserName(String userName) {
        if (userName != null) {
            loginFailureCounterByUserName.remove(userName);
        }
    }

    /**
     * Увеличить счетчик ошибок логина по удалённому хосту
     * @param remoutHost удалённый хост
     */
    public static void addLoginFailureByRemoutHost(String remoutHost) {
        Integer integer = loginFailureCounterByRemoutHost.get(remoutHost);
        if (integer != null) {
            int i = integer + 1;
            loginFailureCounterByRemoutHost.put(remoutHost, i > 3 ? 3 : i);
        } else {
            loginFailureCounterByRemoutHost.put(remoutHost, 1);
        }
    }

    /**
     * Нужна ли капча
     * @param remoutHost удалённый хост
     * @return Нужна ли капча
     */
    public static boolean isCaptchaNeededByRemoutHost(String remoutHost) {
        Integer loginFailuresCount = loginFailureCounterByRemoutHost.get(remoutHost);
        return loginFailuresCount != null && loginFailuresCount >= MAX_LOGIN_FAILURE;
    }

    /**
     * Удалить удалённый хост из хранилища
     * @param remoutHost удалённый хост
     */
    public static void removeByRemoutHost(String remoutHost) {
        if (remoutHost != null) {
            loginFailureCounterByRemoutHost.remove(remoutHost);
        }
    }

    @PostConstruct
    public void schedule() {
        stpe.scheduleAtFixedRate(this, 60, 60, TimeUnit.MINUTES);
    }

    @PreDestroy
    public void stop() {
        stpe.shutdown();
    }

    @Override
    public void run() {
        loginFailureCounterByUserName.clear();
        loginFailureCounterByRemoutHost.clear();
    }
}
