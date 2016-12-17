package org.ircenter.serv.dataserver.images.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 7:49
 */
public class LastAccessManager {
    private static final Logger LOGGER = Logger.getLogger(LastAccessManager.class.getName());
    private static final String KEY_STORE_COUNT = "dataserver.ImageService.storeLargePreviewsCount";
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
    private ImageServiceImpl service;
    private Object stub = new Object();
    private ConcurrentHashMap<Long, Object> images = new ConcurrentHashMap<Long, Object>();
    private int storeLargePreviewsCount;

    public LastAccessManager(ImageServiceImpl service) {
        this.service = service;
        storeLargePreviewsCount = 1000000;
        LOGGER.info("storeLargePreviewsCount=" + storeLargePreviewsCount);
    }

    public void init() {
        executorService.scheduleAtFixedRate(new Runnable() {

            public void run() {
                flush();
            }
        }, 10, 10, TimeUnit.MINUTES);


        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 5);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        scheduleTask(new Runnable() {

            public void run() {
                deleteOld();
            }
        }, c.getTime(), TimeUnit.DAYS.toMillis(1));
    }

    private void scheduleTask(Runnable r, Date firstTime, long periodMillis) {
        long delayMillis = firstTime.getTime() - System.currentTimeMillis();
        while (delayMillis < 0) {
            delayMillis += periodMillis;
        }
        executorService.scheduleAtFixedRate(r, delayMillis, periodMillis, TimeUnit.MILLISECONDS);
    }

    public void onLoad(long imageId) {
        images.put(imageId, stub);
    }

    private void flush() {
        //non-atomic, not a problem
        Long[] array = images.keySet().toArray(new Long[0]);
        images.clear();

        //service.getImgDAO().updateAccessTime(array);
    }

    private void deleteOld() {
//        int preserveCount = storeLargePreviewsCount;
//        List<Long> list = service.getImgDAO().getOld(preserveCount);
//        for (Long id : list) {
//            service.getFileStorageLargePreviews().removeFile(id);
//            //service.getImgDAO().setDeletedBigSize(id, true);
//        }

        //LOGGER.warning("DAILY CLEANUP. deleted " + list.size());
    }
}
