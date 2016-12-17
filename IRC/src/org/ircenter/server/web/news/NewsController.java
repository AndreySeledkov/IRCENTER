package org.ircenter.server.web.news;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
import org.ircenter.server.bean.like.ServiceType;
import org.ircenter.server.bean.news.NewsChapter;
import org.ircenter.server.bean.news.NewsComment;
import org.ircenter.server.bean.news.NewsPart;
import org.ircenter.server.bean.news.PieceNews;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.news.NewsService;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.util.paging.PageListShort;
import org.ircenter.server.web.edit.EditAjaxResponse;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 26.03.12
 * Time: 15:57
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    private static final Log LOGGER = LogFactory.getLog(NewsController.class.getClass());
    private Database database;


    @RequestMapping(value = "/embed_news", method = RequestMethod.GET)
    public String edit(Model model, HttpServletRequest request) {
        String partId = request.getParameter("partId");
        int partId0 = MUtil.str2int(partId, 0);
        setModel(model, partId0);
        return "new/embed_news";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String news(Model model, @PathVariable Long id) {
        PieceNews pieceNews = database.getNewsService().getPieceOfNews(id);
        if (pieceNews == null) {
            return "redirect:/news";
        }
        database.getNewsService().setViewCount(pieceNews, 1);
        NewsPart newsPart = database.getNewsService().getPart(pieceNews.getPartId());
        NewsBean newsBean = new NewsBean();
        newsBean.setId(pieceNews.getId());
        newsBean.setPartId(pieceNews.getPartId());
        newsBean.setTitle(pieceNews.getTitle());
        newsBean.setText(pieceNews.getText());
        newsBean.setDate(MUtil.dateToString(pieceNews.getStartDate(), Calendar.getInstance()));
        newsBean.setPartName(newsPart.getName());
        newsBean.setImageId(pieceNews.getImageId());
        newsBean.setViewCount(pieceNews.getViewCount());
        model.addAttribute("news", newsBean);

        List<PieceNews> list = database.getNewsService().getNews(0, 4, -1);
        model.addAttribute("lastNews", list);
        model.addAttribute("like", database.getLikeService().getQuantityInfo(ServiceType.NEWS, id));
        model.addAttribute("comments", database.getNewsService().getNewsComments(id, 0, NewsService.DEFAULT_COMMENTS_PER_PAGE));
        return "/new/news_piece";
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String news(Model model, HttpServletRequest request) {
        int sect = MUtil.str2int(request.getParameter("partId"), -1);
        int newsCount = database.getNewsService().getNewsCount(sect);

        String page = request.getParameter("p");
        int p = MUtil.str2int(page, -1);
        if (p <= 0 || p > Math.round((double) newsCount / (double) NewsService.DEFAULT_NEWS_PER_PAGE)) {
            p = 1;
        }

        List<PieceNews> list = database.getNewsService().getNews((p - 1) * NewsService.DEFAULT_NEWS_PER_PAGE, NewsService.DEFAULT_NEWS_PER_PAGE, sect);
        List<NewsBean> beans = new ArrayList<NewsBean>(NewsService.DEFAULT_NEWS_PER_PAGE);

        for (PieceNews news : list) {
            NewsPart newsPart = database.getNewsService().getPart(news.getPartId());
            NewsChapter chapter = database.getNewsService().getChapter(newsPart.getChapterId());
            NewsBean newsBean = new NewsBean();
            newsBean.setId(news.getId());
            newsBean.setViewCount(news.getViewCount());
            newsBean.setTitle(news.getTitle());
            String text = Jsoup.parse(news.getText()).text();
            text = text.length() > NewsService.MAX_LETTERS_COUNT ? text.substring(0, NewsService.MAX_LETTERS_COUNT) + "..." : text;
            newsBean.setText(text);
            newsBean.setImageId(news.getImageId());
            newsBean.setDate(MUtil.dateToString(news.getStartDate(), Calendar.getInstance()));
            newsBean.setPartName(newsPart.getName());
            newsBean.setChapterName(chapter.getChapter());
            beans.add(newsBean);
        }
        model.addAttribute("newsSection", sect);
        model.addAttribute("news", beans);


        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/news?partId=" + sect + "&p=", NewsService.DEFAULT_NEWS_PER_PAGE);
        pageListPlain.setCurrentPage(p - 1);
        pageListPlain.setItemsCount(newsCount);
        pageListPlain.generate(builder);
        model.addAttribute("newsCount", newsCount);
        model.addAttribute("pagination", builder);
        List<ProgrammTv> programmTvList = database.getProgrammTvDAO().getProgrammTvByType(ProgrammTvType.CNL);
        model.addAttribute("programmTvList", programmTvList);
        return "/new/news";
    }

    private void setModel(Model model, int partId) {
        List<NewsBean> beans = new ArrayList<NewsBean>(NewsService.DEFAULT_NEWS_PER_PAGE / 2);
        List<PieceNews> list = database.getNewsService().getNews(0, NewsService.DEFAULT_NEWS_PER_PAGE / 2, partId);
        for (PieceNews news : list) {
            NewsPart newsPart = database.getNewsService().getPart(news.getPartId());
            NewsChapter chapter = database.getNewsService().getChapter(newsPart.getChapterId());
            NewsBean newsBean = new NewsBean();
            newsBean.setId(news.getId());
            newsBean.setViewCount(news.getViewCount());
            newsBean.setTitle(news.getTitle());
            String text = Jsoup.parse(news.getText()).text();
            text = text.length() > NewsService.MAX_LETTERS_COUNT ? text.substring(0, NewsService.MAX_LETTERS_COUNT) + "..." : text;
            newsBean.setText(text);
            newsBean.setImageId(news.getImageId());
            newsBean.setDate(MUtil.dateToString(news.getStartDate(), Calendar.getInstance()));
            newsBean.setPartName(newsPart.getName());
            newsBean.setChapterName(chapter.getChapter());
            beans.add(newsBean);
        }
        model.addAttribute("newsSection", partId);
        model.addAttribute("news", beans);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public
    @ResponseBody
    EditAjaxResponse comment(@RequestParam String text, @RequestParam Long id) {
        NewsComment newsComment = new NewsComment();
        newsComment.setDate(new Date());
        newsComment.setNewsId(id.longValue());
        newsComment.setUserId(UserHelper.getUserId());
        newsComment.setText(MUtil.replaceHTMLtext(text));
        database.getNewsService().addNewsComment(newsComment);
        return new EditAjaxResponse(true);
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
