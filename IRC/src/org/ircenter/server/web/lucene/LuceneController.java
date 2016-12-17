package org.ircenter.server.web.lucene;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.*;
import org.ircenter.server.bean.news.NewsPart;
import org.ircenter.server.bean.news.PieceNews;
import org.ircenter.server.lucene.CategoryType;
import org.ircenter.server.lucene.IndexType;
import org.ircenter.server.lucene.LuceneSearchProcessor;
import org.ircenter.server.lucene.SearchResult;
import org.ircenter.server.service.evidence.MinutesToGodService;
import org.ircenter.server.service.evidence.SecretSpiritualWorldService;
import org.ircenter.server.service.evidence.TvService;
import org.ircenter.server.service.evidence.VideoEvidenceService;
import org.ircenter.server.service.news.NewsService;
import org.ircenter.server.util.paging.PageListShort;
import org.ircenter.server.web.news.NewsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 31.05.12
 * Time: 10:24
 */
@Controller
@RequestMapping("/search")
public class LuceneController {
    private LuceneSearchProcessor searchProcessor;
    private NewsService newsService;
    private MinutesToGodService minutesToGodService;
    private SecretSpiritualWorldService secretSpiritualWorldService;
    private TvService tvService;
    private VideoEvidenceService evidenceService;

    @RequestMapping(value = {"/", "", "?{q}"}, method = RequestMethod.GET)
    public String search(Model model, HttpServletRequest request, @RequestParam(value = "q", required = false) String q) {
        if (q.length() > 50) {
            q = q.substring(0, 50);
        }
        int p = MUtil.str2int(request.getParameter("p"), 1);
        int section = MUtil.str2int(request.getParameter("s"), 0);
        CategoryType t = CategoryType.getCatType(section);
        if (t == null) {
            t = CategoryType.NEWS;
        }
        switch (t) {
            case NEWS:
                SearchResult result = searchProcessor.search(q, IndexType.NEWS, LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT, (p - 1) * LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT);
                setModel(model, result, IndexType.NEWS, q, p);
                break;

            case VIDEO:
                int totalHits = 0;
                List<SearchResultBean> list = new ArrayList<SearchResultBean>(LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT);
                List<SearchResult> listResult = new ArrayList<SearchResult>();
                for (IndexType type : IndexType.values()) {
                    if (type == IndexType.NEWS) {
                        continue;
                    }
                    result = searchProcessor.search(q, type, LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT, (p - 1) * LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT);
                    listResult.add(result);
                    totalHits += result.getTotalHits();
                }
                int index = (p - 1) * LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT;
                int tempPage = p;
                for (SearchResult searchResult : listResult) {
                    if (list.size() < LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT) {
                        setVideoModel(searchResult, searchResult.getType(), q, index, tempPage, list);
                    }
                    index = 0;
                    tempPage = 1;
                }
                if (list.size() > LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT) {
                    list = MUtil.getSafeSubList(list, 0, LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT);
                }
                model.addAttribute("videos", list);
                StringBuilder builder = new StringBuilder();
                PageListShort pageListPlain = new PageListShort("/search?q=" + q + "&s=" + t.getId() + "&p=", LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT);
                pageListPlain.setCurrentPage(p - 1);
                pageListPlain.setItemsCount(totalHits);
                pageListPlain.generate(builder);
                model.addAttribute("pagination", builder);
                break;
            default:
                break;
        }

        model.addAttribute("searchWord", q);
        model.addAttribute("section", section);
        return "new/search";
    }

    private void setModel(Model model, SearchResult result, IndexType type, String q, int p) {
        int count = result.getResult().size();
        switch (type) {
            case NEWS:
                List<PieceNews> list = new ArrayList<PieceNews>(count);
                for (int i = (p - 1) * LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT; i < p * LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT && i < result.getResult().size(); i++) {
                    list.add(newsService.getPieceOfNews(result.getResult().get(i)));
                }
                List<NewsBean> beans = new ArrayList<NewsBean>(count);
                for (PieceNews news : list) {
                    NewsPart newsPart = newsService.getPart(news.getPartId());
                    NewsBean newsBean = new NewsBean();
                    newsBean.setId(news.getId());
                    newsBean.setPartId(news.getPartId());
                    newsBean.setTitle(news.getTitle());
                    newsBean.setDate(MUtil.dateToString(news.getStartDate(), Calendar.getInstance()));
                    newsBean.setPartName(newsPart.getName());
                    newsBean.setImageId(news.getImageId());
                    beans.add(newsBean);
                }

                StringBuilder builder = new StringBuilder();
                PageListShort pageListPlain = new PageListShort("/search?q=" + q + "&s=" + type.getId() + "&p=", LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT);
                pageListPlain.setCurrentPage(p - 1);
                pageListPlain.setItemsCount(result.getTotalHits());
                pageListPlain.generate(builder);
                model.addAttribute("pagination", builder);
                model.addAttribute("news", beans);
                break;

        }

    }

    private void setVideoModel(SearchResult result, IndexType type, String q, int index, int p, List<SearchResultBean> list) {
        int count = result.getResult().size();
        switch (type) {
            case MINUTES_TO_GOD:
                List<MinutesToGod> list1 = new ArrayList<MinutesToGod>(count);
                for (int i = index; i < p * LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT && i < result.getResult().size(); i++) {
                    list1.add(minutesToGodService.getMinutesToGod(result.getResult().get(i)));
                }
                for (MinutesToGod item : list1) {
                    SearchResultBean bean = new SearchResultBean(item.getId(), "/minutes_to_god?link_id=", item.getYoutubeId(), item.getTitle());
                    list.add(bean);
                }
                break;
            case SECRET_SPIRITUAL_WORLD:
                List<SecretSpiritualWorld> list2 = new ArrayList<SecretSpiritualWorld>(count);
                for (int i = index; i < p * LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT && i < result.getResult().size(); i++) {
                    list2.add(secretSpiritualWorldService.getSecretSpiritualWorld(result.getResult().get(i)));
                }
                for (SecretSpiritualWorld item : list2) {
                    SearchResultBean bean = new SearchResultBean(item.getId(), "/secret_spiritual_world?link_id=", item.getYoutubeId(), item.getTitle());
                    list.add(bean);
                }
                break;
            case TV:
                List<Tv> list3 = new ArrayList<Tv>(count);
                for (int i = index; i < p * LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT && i < result.getResult().size(); i++) {
                    list3.add(tvService.getTv(result.getResult().get(i)));
                }
                for (Tv item : list3) {
                    SearchResultBean bean = new SearchResultBean(item.getId(), "/tv?type=1&id=", item.getYoutubeId(), item.getTitle());
                    list.add(bean);
                }
                break;
            case VIDEO_EVIDENCE:
                List<VideoEvidence> list4 = new ArrayList<VideoEvidence>(count);
                for (int i = index; i < p * LuceneSearchProcessor.DEFAULT_SEARCH_RESULT_COUNT && i < result.getResult().size(); i++) {
                    list4.add(evidenceService.getVideoEvidence(result.getResult().get(i)));
                }
                for (VideoEvidence item : list4) {
                    SearchResultBean bean = new SearchResultBean(item.getId(), "/video_evidence?id=", item.getYoutubeId(), item.getTitle());
                    list.add(bean);
                }
                break;
            default:
                break;
        }
    }

    @Autowired
    public void setNewsService(NewsService service) {
        this.newsService = service;
    }

    @Autowired
    public void setMinutesToGodService(MinutesToGodService minutesToGodService) {
        this.minutesToGodService = minutesToGodService;
    }

    @Autowired
    public void setSecretSpiritualWorldService(SecretSpiritualWorldService secretSpiritualWorldService) {
        this.secretSpiritualWorldService = secretSpiritualWorldService;
    }

    @Autowired
    public void setTvService(TvService tvService) {
        this.tvService = tvService;
    }

    @Autowired
    public void setEvidenceService(VideoEvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @Autowired
    public void setSearchProcessor(LuceneSearchProcessor searchProcessor) {
        this.searchProcessor = searchProcessor;
    }
}

