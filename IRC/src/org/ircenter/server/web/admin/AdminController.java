package org.ircenter.server.web.admin;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.calendarEvent.CalendarEvent;
import org.ircenter.server.bean.calendarEvent.CalendarEventBean;
import org.ircenter.server.bean.molodejhka.MolodejhkaVideo;
import org.ircenter.server.bean.news.NewsChapter;
import org.ircenter.server.bean.news.NewsPart;
import org.ircenter.server.bean.news.PieceNews;
import org.ircenter.server.dao.Database;
import org.ircenter.server.dao.images.ImageDAO;
import org.ircenter.server.service.molodejhka.MolodejhkaService;
import org.ircenter.server.service.news.NewsService;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.web.edit.EditAjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private Database database;


    @RequestMapping(method = RequestMethod.GET)
    public String admin(Model model, HttpServletRequest request) {

        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);
        int page = 0;
        String pg = request.getParameter("p");
        if (pg != null && !pg.isEmpty()) {
            page = MUtil.str2int(pg, 0);
        }
        model.addAttribute("page", page);
        int newsCount = database.getNewsService().getNewsCount(-1);
        model.addAttribute("countNews", newsCount);
        int countPage = newsCount / NewsService.DEFAULT_NEWS_PER_PAGE;
        if (newsCount % NewsService.DEFAULT_NEWS_PER_PAGE != 0) {
            countPage++;
        }
        model.addAttribute("countPage", countPage);
        model.addAttribute("news", database.getNewsService().getNews(page * NewsService.DEFAULT_NEWS_PER_PAGE, (page + 1) * NewsService.DEFAULT_NEWS_PER_PAGE, -1));
        return "admin/index";
    }


    @RequestMapping(value = "/add_news", method = RequestMethod.GET)
    public String edit(Model model, HttpServletRequest request) {
        model.addAttribute("parts", database.getNewsService().getParts());
        model.addAttribute("chapters", database.getNewsService().getChapters());
        model.addAttribute("newsChapters", new NewsChapter());
        model.addAttribute("newsParts", new NewsPart());
        return "admin/add_news";
    }

    @RequestMapping(value = "/add_category", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView saveCategory(@RequestParam String newcategory, @RequestParam String chapterId, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("redirect:add_news");
        int chId = MUtil.str2int(chapterId, -1);
        if (chId == -1 || newcategory == null || newcategory.equals("")) {
            return mv;
        }
        database.getNewsSectionDAO().createPart(chId, newcategory);
        return mv;
    }

    @RequestMapping(value = "/save_news", method = RequestMethod.POST)
    public
    @ResponseBody
    EditAjaxResponse saveNews(@RequestParam String title, @RequestParam String partId, @RequestParam String startDate,
                              @RequestParam String defimage, @RequestParam String body, String newsId) {
        int pId = MUtil.str2int(partId, -1);
        int dI = MUtil.str2int(defimage, -1);
        Date date = MUtil.string2Date(startDate, Calendar.getInstance());
        if (pId == -1 || dI == -1 || date == null) {
            return new EditAjaxResponse(false);
        }
        PieceNews item = new PieceNews();
        item.setTitle(title);
        item.setPartId(pId);
        item.setText(body);
        item.setStartDate(date);
//        item.setStopDate(MUtil.string2Date(stopDate, Calendar.getInstance()));
        item.setImageId(dI);
        item.setAuthorId(UserHelper.getUserId());
        if (newsId == null) {
            database.getNewsService().addNews(item);
        } else {
            item.setId(MUtil.str2long(newsId, -1));
            database.getNewsService().updateNews(item);
        }

        return new EditAjaxResponse(true);
    }

    @RequestMapping(value = "/edit_news", method = RequestMethod.GET)
    public String editNews(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        long item = MUtil.str2long(id, -1);
        PieceNews piece = getDatabase().getNewsService().getPieceOfNews(item);
        model.addAttribute("parts", database.getNewsService().getParts());
        model.addAttribute("chapters", database.getNewsService().getChapters());
        model.addAttribute("newsChapters", new NewsChapter());
        NewsPart newsPart = new NewsPart();
        newsPart.setId(piece.getPartId());
        model.addAttribute("newsParts", newsPart);
        model.addAttribute("news", piece);

        return "/admin/edit_news";
    }

    @RequestMapping(value = "/remove_news", method = RequestMethod.GET)
    public
    @ResponseBody
    String removeNews(HttpServletRequest request) {
        String id = request.getParameter("id");
        long item = 0;
        if (id == null || id.equals("") || (item = MUtil.str2long(id, -1)) == -1) {
            return "/admin";
        }
        database.getNewsService().removeNews(item);
        return "/admin";
    }

    @RequestMapping(value = "/event_calendar", method = RequestMethod.GET)
    public String calendar(Model model, HttpServletRequest request) {
        Collection<CalendarEvent> events = database.getCalendarEventService().getEvents();
        List<CalendarEventBean> list = new ArrayList<CalendarEventBean>();
        for (CalendarEvent event : events) {
            CalendarEventBean bean = new CalendarEventBean();
            bean.setEventId(event.getEventId());
            bean.setStartEvent(event.getStartEvent());
            bean.setEndEvent(event.getEndEvent());
            bean.setCity(database.getUserLocationDAO().getCity(event.getRegionId(), event.getCityId()).getCityName());
            bean.setPlace(event.getPlace());
            bean.setEventName(event.getEventName());
            bean.setDefImageId(event.getDefImageId());
            list.add(bean);
        }
        model.addAttribute("events", list);
        return "admin/calendar";
    }

    @RequestMapping(value = "/add_calendar", method = RequestMethod.GET)
    public String addCalendar(Model model, HttpServletRequest request) {
        model.addAttribute("countries", database.getUserLocationDAO().getAllCountries());
        return "admin/add_calendar";
    }

    @RequestMapping(value = "/edit_calendar", method = RequestMethod.GET)
    public String editCalendar(Model model, HttpServletRequest request) {
        String calId = request.getParameter("id");
        int calendarId = MUtil.str2int(calId, -1);
        if (calendarId == -1) {
            return "admin/calendar";
        }
        CalendarEvent event = database.getCalendarEventService().getEvent(calendarId);
        model.addAttribute("calendar", event);
        model.addAttribute("countries", database.getUserLocationDAO().getAllCountries());
        model.addAttribute("regions", database.getUserLocationDAO().getRegionById(event.getCountryId()));
        model.addAttribute("cities", database.getUserLocationDAO().getCityById(event.getRegionId()));
        return "admin/edit_calendar";
    }

    @RequestMapping(value = "remove_calendar", method = RequestMethod.GET)
    public String removeCalendar(Model model, HttpServletRequest request) {
        String calId = request.getParameter("id");
        int calendarId = MUtil.str2int(calId, -1);
        if (calendarId == -1) {
            return "redirect:/admin/event_calendar";
        }
        database.getCalendarEventService().removeEvent(calendarId);
        return "redirect:/admin/event_calendar";
    }

    @RequestMapping(value = "/save_calendar", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView saveCalendar(@RequestParam String cityId, @RequestParam String countryId, @RequestParam String datefinish,
                              @RequestParam String datestart, @RequestParam String pictures, @RequestParam String place, @RequestParam String regionId, @RequestParam String title, Integer calendarId) {
        int cId = MUtil.str2int(cityId, -1);
        int ctryId = MUtil.str2int(countryId, -1);
        int rId = MUtil.str2int(regionId, -1);
        Date start = MUtil.string2DateTime(datestart, Calendar.getInstance());
        Date finish = MUtil.string2DateTime(datefinish, Calendar.getInstance());
        int pictId = MUtil.str2int(pictures, -1);
        if (cId == -1 || ctryId == -1 || rId == -1 || start == null || finish == null || pictId == -1 || start.compareTo(finish) > 0) {
            if (calendarId == null) {
                return new ModelAndView("redirect:/admin/add_calendar");
            } else {
                return new ModelAndView("redirect:/admin/event_calendar");
            }
        }
        CalendarEvent event = new CalendarEvent();
        event.setStartEvent(start);
        event.setEndEvent(finish);
        event.setEventName(title);
        event.setPlace(place);
        event.setCountryId(ctryId);
        event.setRegionId(rId);
        event.setCityId(cId);
        event.setDefImageId(pictId);
        if (calendarId == null || calendarId == -1) {
            database.getCalendarEventService().addEvent(event);
            return new ModelAndView("redirect:/admin/add_calendar");
        } else {
            event.setEventId(calendarId);
            database.getCalendarEventService().editEvent(event);
        }
        return new ModelAndView("redirect:/admin/event_calendar");
    }

    @RequestMapping(value = "/media", method = RequestMethod.GET)
    public String media(Model model, HttpServletRequest request) {
        int page = 0;
        String pg = request.getParameter("p");
        if (pg != null && !pg.isEmpty()) {
            page = MUtil.str2int(pg, 0);
        }
        model.addAttribute("page", page);

        int imagesCount = database.getImageDAO().getTotalCount();
        model.addAttribute("countElements", imagesCount);
        int countPage = imagesCount / ImageDAO.DEFAULT_COUNT_PER_PAGE;
        if (imagesCount % ImageDAO.DEFAULT_COUNT_PER_PAGE != 0) {
            countPage++;
        }
        model.addAttribute("countPage", countPage);

        model.addAttribute("files", database.getImageDAO().getImages(page * ImageDAO.DEFAULT_COUNT_PER_PAGE, ImageDAO.DEFAULT_COUNT_PER_PAGE));
        return "admin/media";
    }

    @RequestMapping(value = "/media_add", method = RequestMethod.POST)
    public ModelAndView uploadAvatar(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {
        List<MultipartFile> pictures = new ArrayList<MultipartFile>();
        if (request instanceof DefaultMultipartHttpServletRequest) {
            String title = request.getParameter("title");
            pictures = ((DefaultMultipartHttpServletRequest) request).getMultiFileMap().get("picture");
            if (pictures != null) {
                InputStream inputStream = pictures.get(0).getInputStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];

                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }

                buffer.flush();
                database.getImageDAO().addNewPhoto(title, UserHelper.getUserId(), buffer.toByteArray());
            }
        }
        return new ModelAndView("redirect:/admin/media", new HashMap<String, Object>());
    }

    @RequestMapping(value = "/media_add", method = RequestMethod.GET)
    public String addMedia(Model model, HttpServletRequest request) {
        return "admin/media_add";
    }

    @RequestMapping(value = "/molodejhka")
    public String molodejhka(Model model, HttpServletRequest request) {
        int page = 0;
        String pg = request.getParameter("p");
        if (pg != null && !pg.isEmpty()) {
            page = MUtil.str2int(pg, 0);
        }
        model.addAttribute("page", page);
        int newsCount = database.getMolodejhkaService().getTotalVideosCount();
        model.addAttribute("countItems", newsCount);
        int countPage = newsCount / MolodejhkaService.DEFAUTL_VIDEOS_COUNT;
        if (newsCount % MolodejhkaService.DEFAUTL_VIDEOS_COUNT != 0) {
            countPage++;
        }
        model.addAttribute("countPage", countPage);
        List<MolodejhkaVideo> list = database.getMolodejhkaService().getVideos(page * MolodejhkaService.DEFAUTL_VIDEOS_COUNT, MolodejhkaService.DEFAUTL_VIDEOS_COUNT);
        for (MolodejhkaVideo item : list) {
            item.setLink(MUtil.replaceHTMLtext(item.getLink()));
        }
        model.addAttribute("molod", list);
        return "admin/molodejhka";
    }

    @RequestMapping(value = "/add_molodejhka_video")
    public String molodejhkaVideo(Model model, HttpServletRequest request) {
        return "admin/add_molodejhka_video";
    }

    @RequestMapping(value = "/add_molodejhka_v", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView addMolodejhkaVideo(@RequestParam String link, @RequestParam String description, @RequestParam String text) {
        if (link == null || link.isEmpty() || description == null || description.isEmpty() || text == null || text.isEmpty()) {
            return new ModelAndView("redirect:/admin/molodejhka");
        }

        database.getMolodejhkaService().addVideo(UserHelper.getUserId(), link, description, text);
        return new ModelAndView("redirect:/admin/molodejhka");
    }

    @RequestMapping(value = "/edit_molodejhka_v", method = RequestMethod.GET)
    public String editMolodejhkaVideo(Model model, HttpServletRequest request) {
        long ident = MUtil.str2long(request.getParameter("id"), -1);
        if (ident == -1) {
            return "redirect:/admin/molodejhka";
        }

        MolodejhkaVideo video = database.getMolodejhkaService().getVideo(ident);
        video.setLink(MUtil.replaceHTMLtext(video.getLink()));
        model.addAttribute("item", video);
        return "/admin/edit_molodejhka_video";
    }

    @RequestMapping(value = "/remove_molodejhka_v", method = RequestMethod.GET)
    public String removeMolodejhkaVideo(Model model, HttpServletRequest request) {
        long ident = MUtil.str2long(request.getParameter("id"), -1);
        if (ident == -1) {
            return "redirect:/admin/molodejhka";
        }
        database.getMolodejhkaService().removeVideo(ident);
        return "redirect:/admin/molodejhka";
    }

    @RequestMapping(value = "/submit_edit_molodejhka_v", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView editMolodejhkaVideo(@RequestParam String id, @RequestParam String link, @RequestParam String description, @RequestParam String text) {
        if (id == null || id.isEmpty() || link == null || link.isEmpty() || description == null || description.isEmpty() || text == null || text.isEmpty()) {
            return new ModelAndView("redirect:/admin/molodejhka");
        }
        long ident = MUtil.str2long(id, -1);
        if (ident == -1) {
            return new ModelAndView("redirect:/admin/molodejhka");
        }

        database.getMolodejhkaService().editVideo(link, description, text, ident);
        return new ModelAndView("redirect:/admin/molodejhka");
    }

//    @RequestMapping(value = "/online_tv", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    ModelAndView onlineTv(@RequestParam String datestart) {
//        if (datestart == null || datestart.isEmpty()) {
//            return new ModelAndView("redirect:/admin");
//        }
//        database.getOnlineTvDAO().addOnlineTv(MUtil.string2DateTime(datestart, Calendar.getInstance()));
//        return new ModelAndView("redirect:/admin");
//    }

    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

}
