package org.ircenter.server.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.MUtil;
import org.ircenter.server.bean.OnlineTv;
import org.ircenter.server.bean.calendarEvent.CalendarEvent;
import org.ircenter.server.bean.calendarEvent.CalendarEventBean;
import org.ircenter.server.bean.evidence.OnlineEvidence;
import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
import org.ircenter.server.bean.evidence.TextEvidence;
import org.ircenter.server.bean.news.NewsChapter;
import org.ircenter.server.bean.news.NewsPart;
import org.ircenter.server.bean.news.PieceNews;
import org.ircenter.server.bean.slider.Slider;
import org.ircenter.server.bean.slider.SliderType;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.media.MediaHelper;
import org.ircenter.server.service.news.NewsService;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.web.news.NewsBean;
import org.ircenter.server.web.registration.RegistrationFormController;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/")
public class WelcomeController {

    private static final Log LOGGER = LogFactory.getLog(WelcomeController.class.getClass());
    private Database database;
    private static final int Evidence_LIMIT = 10;

    private RegistrationFormController registrationFormController;

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String welcome(Model model, HttpServletRequest request, HttpServletResponse response) {
        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        List<Slider> sliders = database.getSliderDAO().getSlider(SliderType.MAIN_SLIDER);

        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);
        List<PieceNews> list = database.getNewsService().getNews(0, NewsService.DEFAULT_NEWS_PER_PAGE / 2 + 1, -1);
        List<NewsBean> beans = new ArrayList<NewsBean>(NewsService.DEFAULT_NEWS_PER_PAGE / 2 + 1);
        model.addAttribute("sliders", sliders);

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

        List<TextEvidence> textEvidenceList = database.getTextEvidenceDAO().getTextEvidence(0, 3);

        for (TextEvidence textEvidence : textEvidenceList) {
            if (textEvidence.getTitle().length() > 30) {
                textEvidence.setTitle(textEvidence.getTitle().substring(0, 30) + "...");
            }
            textEvidence.setText(Jsoup.parse(textEvidence.getText()).text());
            if (textEvidence.getText().length() > 40) {
                textEvidence.setText(textEvidence.getText().substring(0, 40) + "...");
            } else {
                textEvidence.setText(Jsoup.parse(textEvidence.getText()).text() + "...");
            }
        }

        model.addAttribute("newsSection", -1);
        model.addAttribute("news", beans);
        model.addAttribute("textEvidenceList", textEvidenceList);

        List<ProgrammTv> programmTvList = database.getProgrammTvDAO().getProgrammTvByType(ProgrammTvType.CNL);

        model.addAttribute("programmTvList", programmTvList);
        OnlineTv onlineTv = database.getOnlineTvDAO().getOnlineTv();
        if (onlineTv != null) {
            model.addAttribute("onlineLeft", onlineTv.getDate().getTime());
        }

        return "new/welcome_new";
    }

    @RequestMapping(value = "/svid_online", method = RequestMethod.GET)
    public String getOnlineEvidence(Model model, HttpServletRequest request) {

        List<OnlineEvidence> onlineEvidenceList = database.getOnlineEvidenceDAO().getOnlineEvidence(0, Evidence_LIMIT);
        model.addAttribute("evidenceOnlineList", onlineEvidenceList);

        return "new/svid_online";
    }

    @RequestMapping(value = "/programm_tv", method = RequestMethod.GET)
    public String getProgrammTv(Model model, HttpServletRequest request) {
        int id = 0;
        if (request.getParameter("id") != null) {
            id = MUtil.str2int(request.getParameter("id"), 0);
        }
        List<ProgrammTv> programmTvList = database.getProgrammTvDAO().getProgrammTvByType(ProgrammTvType.valueOf(id));

        model.addAttribute("programmTvList", programmTvList);
        return "new/programm_tv";
    }


    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public ModelAndView uploadAvatar(HttpServletRequest request, HttpServletResponse httpResponse) throws
            IOException {
        List<MultipartFile> pictures = new ArrayList<MultipartFile>();


        if (request instanceof DefaultMultipartHttpServletRequest) {
            pictures = ((DefaultMultipartHttpServletRequest) request).getMultiFileMap().get("pictures");
            if (pictures != null) {
                MediaHelper.saveImages(pictures, UserHelper.getUserId());
            }

        }

        return new ModelAndView("redirect:/profile", new HashMap<String, Object>());
    }

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String show(Model model) {
        Collection<CalendarEvent> events = database.getCalendarEventService().getEvents();
        List<CalendarEventBean> eventList = new ArrayList<CalendarEventBean>(events.size());
        Calendar calendar = Calendar.getInstance();
        String[] month = MUtil.getMonthNames(new Locale("RU"));
        Locale locale = new Locale("RU");
        for (CalendarEvent event : events) {
            CalendarEventBean bean = new CalendarEventBean();
            bean.setDefImageId(event.getDefImageId());
            calendar.setTime(event.getStartEvent());
            bean.setStartEvent(event.getStartEvent());
            bean.setEndEvent(event.getEndEvent());
            String mnh = month[calendar.get(Calendar.MONTH)];
            bean.setMonth(mnh.substring(0, mnh.length() - 1) + 'я');
            bean.setEventName(event.getEventName());
            bean.setPlace(event.getPlace());
            bean.setCity(database.getUserLocationDAO().getCity(event.getRegionId(), event.getCityId()).getCityName());
            eventList.add(bean);
        }

        List<ProgrammTv> programmTvList = database.getProgrammTvDAO().getProgrammTvByType(ProgrammTvType.CNL);

        model.addAttribute("programmTvList", programmTvList);

        model.addAttribute("events", eventList);
        return "/new/calendar";
    }

    public RegistrationFormController getRegistrationFormController() {
        return registrationFormController;
    }

    @Autowired
    public void setRegistrationFormController(RegistrationFormController registrationFormController) {
        this.registrationFormController = registrationFormController;
    }
}
