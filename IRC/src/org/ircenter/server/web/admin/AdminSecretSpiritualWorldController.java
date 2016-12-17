package org.ircenter.server.web.admin;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.SecretSpiritualWorld;
import org.ircenter.server.service.evidence.SecretSpiritualWorldService;
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
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 11.05.12
 * Time: 0:30
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/secret_spiritual_world")
public class AdminSecretSpiritualWorldController {

    private static final int Evidence_LIMIT = 10;
    private SecretSpiritualWorldService secretSpiritualWorldService;

    @RequestMapping(method = RequestMethod.GET)
    public String getSecretSpiritWorld(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        int secretSpiritualWorldCount = secretSpiritualWorldService.getSecretSpiritualWorldCount();
        List<SecretSpiritualWorld> secretSpiritualWorldList = secretSpiritualWorldService.getSecretSpiritualWorld((page - 1) * Evidence_LIMIT, Evidence_LIMIT);
        List<SecretSpiritualWorld> copyList = new ArrayList<SecretSpiritualWorld>(secretSpiritualWorldList.size());

        for (SecretSpiritualWorld secretSpiritualWorld : secretSpiritualWorldList) {
            SecretSpiritualWorld copy = new SecretSpiritualWorld(secretSpiritualWorld);
            copy.setLink(MUtil.replaceHTMLtext(copy.getLink()));
            copyList.add(copy);
        }


        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/admin/secret_spiritual_world?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(secretSpiritualWorldCount);
        pageListPlain.generate(builder);

        model.addAttribute("secretSpiritualWorldCount", secretSpiritualWorldCount);

        model.addAttribute("pagination", builder);
        model.addAttribute("secretSpiritualWorldList", copyList);
        return "admin/secret_spiritual_world";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editSecretSpiritualWorld(Model model, HttpServletRequest request, @PathVariable Long id) {

        SecretSpiritualWorld secretSpiritualWorld = secretSpiritualWorldService.getSecretSpiritualWorld(id);
        SecretSpiritualWorld copy = new SecretSpiritualWorld(secretSpiritualWorld);
        copy.setLink(MUtil.replaceHTMLtext(copy.getLink()));
        model.addAttribute("secretSpiritualWorld", copy);

        return "admin/secret_spiritual_world_edit";
    }


    @RequestMapping(value = "/submit_edit_secret_spiritual_world", method = RequestMethod.POST)
    public String saveEditTextEvidence(Model model, HttpServletRequest request, @RequestParam long id, @RequestParam String title, @RequestParam String link, @RequestParam String text) {

        SecretSpiritualWorld secretSpiritualWorld = new SecretSpiritualWorld();
        secretSpiritualWorld.setId(id);
        secretSpiritualWorld.setText(text);
        secretSpiritualWorld.setTitle(title);
        try {
            link = link.trim();
            link = link.replaceAll("width=\"420\"", "width=\"640\"");
            link = link.replaceAll("height=\"315\"", "height=\"360\"");
            link = link.replaceAll("allowscriptaccess=\"always\"", "allowscriptaccess=\"always\" wmode=\"transparent\"");

            int g = link.indexOf("src=") + "src=http://www.youtube.com/v/".length() + 1;
            String url = link.substring(g, link.indexOf("?", g));
            secretSpiritualWorld.setLink(link);
            secretSpiritualWorld.setYoutubeId(url);

            secretSpiritualWorldService.updateSecretSpiritualWorld(secretSpiritualWorld);
        } catch (Exception e) {

        }
        return "redirect:/admin/secret_spiritual_world";
    }


    @RequestMapping(value = "/get_new", method = RequestMethod.GET)
    public String getNewSecretSpiritWorld(Model model, HttpServletRequest request) {

        return "admin/secret_spiritual_world_new";
    }

    @RequestMapping(value = "/add_new", method = RequestMethod.POST)
    public String saveSecretSpiritWorld(Model model, HttpServletRequest request, @RequestParam String title, @RequestParam String link, @RequestParam String text) {

        SecretSpiritualWorld secretSpiritualWorld = new SecretSpiritualWorld();
        secretSpiritualWorld.setCreatedDate(new Date());
        secretSpiritualWorld.setText(text);
        secretSpiritualWorld.setTitle(title);
        try {
            link = link.trim();
            link = link.replaceAll("width=\"420\"", "width=\"640\"");
            link = link.replaceAll("height=\"315\"", "height=\"360\"");
            link = link.replaceAll("allowscriptaccess=\"always\"", "allowscriptaccess=\"always\" wmode=\"transparent\"");

            int g = link.indexOf("src=") + "src=http://www.youtube.com/v/".length() + 1;
            String url = link.substring(g, link.indexOf("?", g));
            secretSpiritualWorld.setLink(link);
            secretSpiritualWorld.setYoutubeId(url);

            secretSpiritualWorldService.saveSecretSpiritualWorld(secretSpiritualWorld);
        } catch (Exception e) {

        }
        return "redirect:/admin/secret_spiritual_world";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String deleteSecretSpiritWorld(@PathVariable Long id) {
        secretSpiritualWorldService.removeSecretSpiritualWorld(id);
        return "redirect:/admin/secret_spiritual_world";
    }

    @Autowired
    public void setSecretSpiritualWorldService(SecretSpiritualWorldService secretSpiritualWorldService) {
        this.secretSpiritualWorldService = secretSpiritualWorldService;
    }
}
