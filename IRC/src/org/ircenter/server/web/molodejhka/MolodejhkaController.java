package org.ircenter.server.web.molodejhka;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.molodejhka.MolodejhkaVideo;
import org.ircenter.server.service.molodejhka.MolodejhkaService;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 24.05.12
 * Time: 2:30
 */
@Controller
@RequestMapping("/molodejhka")
public class MolodejhkaController {
    private MolodejhkaService molodejhkaService;

    @RequestMapping(value = "")
    public String molodejhka(Model model, HttpServletRequest request) {
        int p = MUtil.str2int(request.getParameter("p"), 1);
        long index = MUtil.str2long(request.getParameter("id"), -1);
        MolodejhkaVideo curEl = null;
        int totalCount = molodejhkaService.getTotalVideosCount();
        StringBuilder newLink = new StringBuilder();
        List<MolodejhkaVideo> list = molodejhkaService.getVideos((p - 1) * MolodejhkaService.DEFAUTL_VIDEOS_COUNT, MolodejhkaService.DEFAUTL_VIDEOS_COUNT);
        for (MolodejhkaVideo video : list) {
            if (video.getDescription().length() > 15) {
                video.setDescription(video.getDescription().substring(0, 15) + "...");
            }
        }
        if (index == -1) {
            if (!list.isEmpty()) {
                curEl = list.get(0);
            }
        } else {
            curEl = molodejhkaService.getVideo(index);
        }
        model.addAttribute("videos", list);
        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/molodejhka?p=", MolodejhkaService.DEFAUTL_VIDEOS_COUNT);
        pageListPlain.setCurrentPage(p - 1);
        pageListPlain.setItemsCount(totalCount);
        pageListPlain.generate(builder);
        model.addAttribute("pagination", builder);
        model.addAttribute("curPage", pageListPlain.getCurrentPage());
        if (curEl != null) {
            model.addAttribute("curEl", curEl);
        }
        return "/new/molodejhka";
    }

    @Autowired
    public void setMolodejhkaService(MolodejhkaService molodejhkaService) {
        this.molodejhkaService = molodejhkaService;
    }
}
