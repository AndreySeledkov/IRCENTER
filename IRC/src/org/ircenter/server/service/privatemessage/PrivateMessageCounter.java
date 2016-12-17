package org.ircenter.server.service.privatemessage;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class PrivateMessageCounter implements Runnable {

    private static ConcurrentHashMap<Long, List<Long>> privateMessageConcurrentHashMap
            = new ConcurrentHashMap<Long, List<Long>>();

    private ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(1);

    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Время за которое проверяется колличество постов (миллисикунды)
     */
    private static final int DELAY_TIME = 1 * 60 * 1000;

    private static final int MAX_PRIVATE_MESSAGE_PER_DELAY_TIME = 5;

    /**
     * Добавить время добавления приватного сообщения в список
     * @param privateMessageCreationTime время добавления приватного сообщения
     */
    public static void addPrivateMessage(long privateMessageCreationTime) {
        List<Long> longs = privateMessageConcurrentHashMap.get(UserHelper.getUserId());
        List<Long> privateMessageAddTimeList = longs == null ? new ArrayList<Long>() : longs;
        privateMessageAddTimeList.add(privateMessageCreationTime);
        privateMessageConcurrentHashMap.put(UserHelper.getUserId(), privateMessageAddTimeList);
    }

    /**
     * Нужна ли капча
     * @return Нужна ли капча
     */
    public static boolean isCaptchaNeeded() {
        List<Long> privateMessageAddTimeList = privateMessageConcurrentHashMap.get(UserHelper.getUserId());
        if (privateMessageAddTimeList == null) {
            return false;
        } else {
            long currentTime = new GregorianCalendar().getTimeInMillis();
            Iterator<Long> iterator = privateMessageAddTimeList.iterator();
            while (iterator.hasNext()) {
                Long privateMessageTime = iterator.next();
                if (currentTime - privateMessageTime > DELAY_TIME) {
                    iterator.remove();
                }
            }
            return privateMessageAddTimeList.size() >= MAX_PRIVATE_MESSAGE_PER_DELAY_TIME;
        }
    }

    @Override
    public void run() {
//        logger.info("Starting privateMessageConcurrentHashMap cleaning");
        long currentTime = new GregorianCalendar().getTimeInMillis();
        for (List<Long> privateMessageAddTimeList : privateMessageConcurrentHashMap.values()) {
            Iterator<Long> iterator = privateMessageAddTimeList.iterator();
            while (iterator.hasNext()) {
                Long privateMessageTime = iterator.next();
                if (currentTime - privateMessageTime > DELAY_TIME) {
                    iterator.remove();
                }
            }
        }
    }

    @PostConstruct
    public void schedule() {
        stpe.scheduleAtFixedRate(this, 5, 5, TimeUnit.MINUTES);
    }

    @PreDestroy
    public void stop() {
        stpe.shutdown();
    }
}
