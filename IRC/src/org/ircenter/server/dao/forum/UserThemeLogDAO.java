package org.ircenter.server.dao.forum;

/**
 * Date: 06.07.2009
 * Time: 13:19:44
 */
public class UserThemeLogDAO {

//    private static final String TABLENAME = "forum_userthemelog";
//    private Cache cache;
//
//    public UserThemeLogDAO(Database database) {
//        super(database);
//        CacheManager cacheManager = CacheManager.create();
//        cache = new Cache("ForumUserThemeLogCache", 80000, false, false, 0, 60 * 60 * 48);
//        cacheManager.addCache(cache);
//    }
//
//    public void addNew(Message message) throws AdException {
//        Theme theme = database.getForumService().getThemeDAO().get(message.getThemeId());
//        List<UserThemeLog> list = getLast(message.getAuthorId());
//
//        synchronized (list) {
//            Iterator<UserThemeLog> it = list.iterator();
//            while (it.hasNext()) {
//                UserThemeLog userThemeLog = it.next();
//                if (userThemeLog.getTheme().getId() == theme.getId()) {
//                    it.remove();
//                }
//            }
//
//            while (list.size() > 9) {
//                delete(list.remove(list.size() - 1));
//            }
//
//            UserThemeLog userThemeLog = new UserThemeLog(message.getAuthorId(), theme, message);
//            save(userThemeLog);
//            list.add(0, userThemeLog);
//        }
//    }
//
//    private void save(UserThemeLog userThemeLog) throws AdException {
//        long userId = userThemeLog.getUserId();
//        long themeId = userThemeLog.getTheme().getId();
//        String time = "'" + new Timestamp(userThemeLog.getDateLast().getTime()) + "'";
//        long messageId = userThemeLog.getMessage().getId();
//
//        String insert = "INSERT INTO " + TABLENAME + " (userId, theme, datelast, message) VALUES (" + userId + "," + themeId + "," + time + "," + messageId + ") "
//                + "ON DUPLICATE KEY UPDATE datelast=" + time + ", message=" + messageId;
//        database.delayedUpdateExecution(insert);
//    }
//
//    private void delete(UserThemeLog userThemeLog) throws AdException {
//        String update = "DELETE FROM " + TABLENAME + " WHERE userId=" + userThemeLog.getUserId() + " AND theme=" + userThemeLog.getTheme().getId();
//        database.delayedUpdateExecution(update);
//    }
//
//    public List<UserThemeLog> getLast(long userId) throws AdException {
//        Long value = LongCache.valueOf(userId);
//        Element element = cache.get(value);
//        if (element != null) {
//            return (List<UserThemeLog>) element.getObjectValue();
//        }
//
//        List<UserThemeLog> list = new ArrayList<UserThemeLog>(15);
//        DBConnection connection = database.getConnectionPool().getConnection();
//
//        List<Long> tId = new ArrayList<Long>(15);
//        List<Long> mId = new ArrayList<Long>(15);
//        try {
//            DBResult dbr = connection.executeQuery("select * from " + TABLENAME + " where userId=" + userId + " order by message desc");
//            while (dbr.next()) {
//                tId.add(dbr.getLong("theme"));
//                mId.add(dbr.getLong("message"));
//
//                UserThemeLog userThemeLog = new UserThemeLog();
//                userThemeLog.setId(dbr.getLong("userthemelog_id"));
//                userThemeLog.setUserId(dbr.getLong("userId"));
//                userThemeLog.setDateLast(dbr.getTimestamp("datelast"));
//
//                list.add(userThemeLog);
//            }
//            dbr.close();
//        } catch (SQLException e) {
//            LOGGER.log(Level.WARNING, "", e);
//            throw new AdException(e);
//        } finally {
//            connection.close();
//        }
//
//        for (int i = 0; i < list.size(); ++i) {
//            UserThemeLog userThemeLog = list.get(i);
//            userThemeLog.setTheme(database.getForumService().getThemeDAO().get(tId.get(i)));
//            userThemeLog.setMessage(database.getForumService().getMessageDAO().get(tId.get(i), mId.get(i)));
//        }
//
//        cache.put(new Element(value, list));
//
//        return list;
//    }
//
//    public void clearCache() {
//        cache.removeAll();
//    }
}
