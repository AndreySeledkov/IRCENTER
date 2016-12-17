package org.ircenter.server.web.admin;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.Tv;
import org.ircenter.server.bean.evidence.TvType;
import org.ircenter.server.service.evidence.TvService;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Seledkov Andriy
 * Date: 11.05.12
 * Time: 0:28
 */
@Controller
@RequestMapping("/admin/tv")
public class AdminTvController {

    private static final int Evidence_LIMIT = 10;
    private TvService tvService;


    @RequestMapping(method = RequestMethod.GET)
    public String getMinutesToGod(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
        }
        int tvCount = tvService.getTvCount();
        List<Tv> tvList = tvService.getTV((page - 1) * Evidence_LIMIT, Evidence_LIMIT);
        List<Tv> copyList = new ArrayList<Tv>(tvList.size());
        for (Tv tv : tvList) {
            Tv copy = new Tv(tv);
            copy.setLink(MUtil.replaceHTMLtext(copy.getLink()));
            copyList.add(copy);
        }

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/admin/tv?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(tvCount);
        pageListPlain.generate(builder);

        model.addAttribute("tvCount", tvCount);

        model.addAttribute("pagination", builder);
        model.addAttribute("tvList", copyList);

        return "admin/tv";
    }

    @RequestMapping(value = "/get_new", method = RequestMethod.GET)
    public String getNewTv(Model model, HttpServletRequest request) {

        return "admin/tv_new";
    }

    @RequestMapping(value = "/add_new", method = RequestMethod.POST)
    public String saveTv(Model model, HttpServletRequest request, @RequestParam String title, @RequestParam String link, @RequestParam int type) {

        if (type < 0) {
            return "redirect:/admin/tv";
        }
        try {
            Tv tv = new Tv();
            tv.setTvType(TvType.valueOf(type));

            link = link.trim();
            link = link.replaceAll("width=\"420\"", "width=\"640\"");
            link = link.replaceAll("height=\"315\"", "height=\"360\"");
            link = link.replaceAll("allowscriptaccess=\"always\"", "allowscriptaccess=\"always\" wmode=\"transparent\"");

            int g = link.indexOf("src=") + "src=http://www.youtube.com/v/".length() + 1;
            String url = link.substring(g, link.indexOf("?", g));
            tv.setLink(link);
            tv.setYoutubeId(url);

            tv.setTitle(title);

            tvService.saveTv(tv);
        } catch (Exception e) {

        }
        return "redirect:/admin/tv";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editTv(Model model, HttpServletRequest request, @PathVariable Long id) {

        Tv tv = tvService.getTv(id);
//        tv.setLink(MUtil.replaceHTMLtext(tv.getLink()));
        model.addAttribute("tv", tv);

        return "admin/tv_edit";
    }


    @RequestMapping(value = "/submit_edit_tv", method = RequestMethod.POST)
    public String saveEditTv(Model model, HttpServletRequest request, @RequestParam long id, @RequestParam String title, @RequestParam String link, @RequestParam int type) {
        if (type < 0) {
            return "redirect:/admin/tv";
        }
        try {
            link = link.trim();

            Tv tv = new Tv();
            tv.setId(id);
            tv.setLink(link);
            tv.setTitle(title);
            tv.setTvType(TvType.valueOf(type));

            link = link.trim();
            link = link.replaceAll("width=\"420\"", "width=\"640\"");
            link = link.replaceAll("height=\"315\"", "height=\"360\"");
            link = link.replaceAll("allowscriptaccess=\"always\"", "allowscriptaccess=\"always\" wmode=\"transparent\"");

            int g = link.indexOf("src=") + "src=http://www.youtube.com/v/".length() + 1;
            String url = link.substring(g, link.indexOf("?", g));
            tv.setLink(link);
            tv.setYoutubeId(url);

            tvService.updateTv(tv);
        } catch (Exception e) {

        }
        return "redirect:/admin/tv";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteTv(@PathVariable Long id) {
        tvService.removeTv(id);
        return "redirect:/admin/tv";
    }

    public TvService getTvService() {
        return tvService;
    }

    @Autowired
    public void setTvService(TvService tvService) {
        this.tvService = tvService;
    }
}
