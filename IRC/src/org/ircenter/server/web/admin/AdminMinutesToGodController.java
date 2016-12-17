package org.ircenter.server.web.admin;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.MinutesToGod;
import org.ircenter.server.service.evidence.MinutesToGodService;
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
import java.util.Date;
import java.util.List;

/**
 * User: Seledkov Andriy
 * Date: 11.05.12
 * Time: 0:28
 */

@Controller
@RequestMapping("/admin/minutes_to_god")
public class AdminMinutesToGodController {
    private MinutesToGodService minutesToGodService;
    private static final int Evidence_LIMIT = 10;

    @RequestMapping(method = RequestMethod.GET)
    public String getMinutesToGod(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        int minutesToGodCount = minutesToGodService.getMinutesToGodCount();
        List<MinutesToGod> minutesToGodList = minutesToGodService.getMinutesToGod((page - 1) * Evidence_LIMIT, Evidence_LIMIT);
        List<MinutesToGod> copyList = new ArrayList<MinutesToGod>(minutesToGodList.size());
        for (MinutesToGod minutesToGod : minutesToGodList) {
            MinutesToGod copy = new MinutesToGod(minutesToGod);
            copy.setLink(MUtil.replaceHTMLtext(copy.getLink()));
            copyList.add(copy);
        }

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/admin/minutes_to_god?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(minutesToGodCount);
        pageListPlain.generate(builder);

        model.addAttribute("minutesToGodCount", minutesToGodCount);

        model.addAttribute("pagination", builder);
        model.addAttribute("minutesToGodList", copyList);
        return "admin/minutes_to_god";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editMinutesToGod(Model model, HttpServletRequest request, @PathVariable Long id) {

        MinutesToGod item = minutesToGodService.getMinutesToGod(id);
        MinutesToGod copy = new MinutesToGod(item);
        copy.setLink(MUtil.replaceHTMLtext(copy.getLink()));
        model.addAttribute("minutesToGod", copy);

        return "admin/minutes_to_god_edit";
    }


    @RequestMapping(value = "/submit_edit_minutes_to_god", method = RequestMethod.POST)
    public String saveEditTextEvidence(Model model, HttpServletRequest request, @RequestParam long id, @RequestParam String title, @RequestParam String link, @RequestParam String text) {

        MinutesToGod minutesToGod = new MinutesToGod();
        minutesToGod.setId(id);
        minutesToGod.setText(text);
        minutesToGod.setTitle(title);
        try {
            link = link.trim();
            link = link.replaceAll("width=\"420\"", "width=\"640\"");
            link = link.replaceAll("height=\"315\"", "height=\"360\"");
            link = link.replaceAll("allowscriptaccess=\"always\"", "allowscriptaccess=\"always\" wmode=\"transparent\"");

            int g = link.indexOf("src=") + "src=http://www.youtube.com/v/".length() + 1;
            String url = link.substring(g, link.indexOf("?", g));

            minutesToGod.setLink(link);
            minutesToGod.setYoutubeId(url);

            minutesToGodService.updateMinutesToGod(minutesToGod);
        } catch (Exception e) {

        }
        return "redirect:/admin/minutes_to_god";
    }

    @RequestMapping(value = "/get_new", method = RequestMethod.GET)
    public String getNewMinutesToGod(Model model, HttpServletRequest request) {

        return "admin/minutes_to_god_new";
    }

    @RequestMapping(value = "/add_new", method = RequestMethod.POST)
    public String saveMinutesToGod(Model model, HttpServletRequest request, @RequestParam String title, @RequestParam String link, @RequestParam String text) {

        MinutesToGod minutesToGod = new MinutesToGod();
        minutesToGod.setCreatedDate(new Date());
        minutesToGod.setText(text);
        minutesToGod.setTitle(title);

        try {
            link = link.trim();
            link = link.replaceAll("width=\"420\"", "width=\"640\"");
            link = link.replaceAll("height=\"315\"", "height=\"360\"");
            link = link.replaceAll("allowscriptaccess=\"always\"", "allowscriptaccess=\"always\" wmode=\"transparent\"");

            int g = link.indexOf("src=") + "src=http://www.youtube.com/v/".length() + 1;
            String url = link.substring(g, link.indexOf("?", g));
            minutesToGod.setLink(link);
            minutesToGod.setYoutubeId(url);

            minutesToGodService.saveMinutesToGod(minutesToGod);
        } catch (Exception e) {

        }
        return "redirect:/admin/minutes_to_god";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteMinutesToGod(@PathVariable Long id) {
        minutesToGodService.removeMinutesToGod(id);
        return "redirect:/admin/minutes_to_god";
    }

    @Autowired
    public void setMinutesToGodService(MinutesToGodService minutesToGodService) {
        this.minutesToGodService = minutesToGodService;
    }
}
