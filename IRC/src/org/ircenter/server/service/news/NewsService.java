package org.ircenter.server.service.news;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.news.*;
import org.ircenter.server.dao.Database;
import org.ircenter.server.util.Locks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.03.12
 * Time: 13:16
 */
@Service
public class NewsService {

    public final static int DEFAULT_NEWS_PER_PAGE = 10;
    public final static int DEFAULT_COMMENTS_PER_PAGE = 10;
    public final static int MAX_LETTERS_COUNT = 300;

    private Locks locks = new Locks(1024);
    private Database database;

    public NewsService() {
    }

    public void addNews(PieceNews pieceNews) {
        database.getNewsDAO().addNews(pieceNews);
        NewsPart newsPart = getPart(pieceNews.getPartId());
        synchronized (newsPart) {
            database.getNewsSectionDAO().increaseNewsCount(newsPart);
        }
    }

    public void updateNews(PieceNews item) {
        database.getNewsDAO().updateNews(item);
    }

    public List<PieceNews> getNews(int skipCount, int recordCount, int partId) {
        Map<Long, PieceNews> map = database.getNewsDAO().getNews();

        List<PieceNews> result = new ArrayList<PieceNews>(recordCount);
        for (PieceNews news : map.values()) {
            if (partId == -1) {
                result.add(news);
            } else {
                if (partId == news.getPartId()) {
                    result.add(news);
                }
            }
        }
        return MUtil.getSafeSubList(result, skipCount, skipCount + recordCount);
    }

    public PieceNews getPieceOfNews(long newsId) {
        return database.getNewsDAO().getNews().get(newsId);
    }

    public void removeNews(long newsId) {
        database.getNewsDAO().removeNews(newsId);
    }

    public int getNewsCount(int partId) {
        Map<Long, PieceNews> m = database.getNewsDAO().getNews();
        int count = 0;
        for (PieceNews news : m.values()) {
            if (partId == -1) {
                count++;
            } else if (news.getPartId() == partId) {
                count++;
            }
        }
        return count;
    }

    public List<NewsComment> getNewsComments(long newsId, int skipCount, int recordCount) {
        synchronized (locks.getLock(newsId)) {
            List<NewsComment> list = database.getNewsCommentDAO().getNewsComments(newsId, skipCount, recordCount);
            synchronized (list) {
                return new ArrayList<NewsComment>(list);
            }
        }
    }

    public void addNewsComment(NewsComment newsComment) {
        String data = newsComment.getText().trim();
        if (data.length() > 255) {
            data = data.substring(0, 255).trim();
        }
        if (data.isEmpty()) {
            return;
        }
        newsComment.setText(data);

//        FilterResult r = getDatabase().getFilterChain().filter(newsComment.getUserId(), data);
//        if (!r.isOk()) {
//            return;
//        }

        PieceNews item = getPieceOfNews(newsComment.getNewsId());
        if (item == null) {
            return;
        }
        synchronized (locks.getLock(item.getId())) {
            database.getNewsCommentDAO().addNewsComment(newsComment);
            database.getNewsDAO().increaseCommentsCount(item);
            item.setCommentsCount(item.getCommentsCount() + 1);
        }
    }

    public NewsChapter getChapter(int chapterId) {
        return database.getNewsSectionDAO().getNewsChapters().get(chapterId);
    }

    public Collection<NewsChapter> getChapters() {
        return database.getNewsSectionDAO().getNewsChapters().values();
    }

    public NewsPart getPart(int partId) {
        return database.getNewsSectionDAO().getParts().get(partId);
    }

    public Collection<NewsPart> getParts() {
        return database.getNewsSectionDAO().getParts().values();
    }

    public void setViewCount(PieceNews pieceNews, int count) {
        database.getNewsDAO().setViewCount(pieceNews,count);
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

}
