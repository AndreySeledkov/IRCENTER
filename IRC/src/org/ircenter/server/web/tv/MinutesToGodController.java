package org.ircenter.server.web.tv;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.OnlineTv;
import org.ircenter.server.bean.evidence.MinutesToGod;
import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
import org.ircenter.server.dao.evidence.ProgrammTvDAO;
import org.ircenter.server.dao.online_tv.OnlineTvDAO;
import org.ircenter.server.service.evidence.MinutesToGodService;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: Seledkov Andriy
 * Date: 11.05.12
 * Time: 0:28
 */
@Controller
@RequestMapping("/minutes_to_god")
public class MinutesToGodController {

    private static final int Evidence_LIMIT = 10;
    private MinutesToGodService minutesToGodService;
    private ProgrammTvDAO programmTvDAO;
    private OnlineTvDAO onlineTvDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String getMinutesToGod(Model model, HttpServletRequest request) {           //TOFO ureadMESSAGES
        long unReadMessage = 0;//database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = 0;//database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());
        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);
        int page = 1;

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
        }

        int minutesToGodCount = minutesToGodService.getMinutesToGodCount();
        List<MinutesToGod> minutesToGodList = minutesToGodService.getMinutesToGod((page - 1) * Evidence_LIMIT, Evidence_LIMIT);

        if (request.getParameter("link_id") != null) {
            long link = Long.parseLong(request.getParameter("link_id"));
            MinutesToGod minutesToGod = minutesToGodService.getMinutesToGod(link);
            minutesToGodService.setViewCount(minutesToGod, 1);
            model.addAttribute("link", minutesToGod.getLink());
            model.addAttribute("minutesToGod", minutesToGod);
        } else {
            model.addAttribute("minutesToGod", minutesToGodList.get(0));
        }


        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/minutes_to_god?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(minutesToGodCount);
        pageListPlain.generate(builder);

        model.addAttribute("minutesToGodCount", minutesToGodCount);

        List<ProgrammTv> programmTvList = programmTvDAO.getProgrammTvByType(ProgrammTvType.CNL);

        model.addAttribute("programmTvList", programmTvList);


        model.addAttribute("pagination", builder);
        model.addAttribute("minutesToGodList", minutesToGodList);
        model.addAttribute("page", page);
        OnlineTv onlineTv = onlineTvDAO.getOnlineTv();
        if (onlineTv != null) {
            model.addAttribute("onlineLeft", onlineTv.getDate().getTime());
        }

        return "new/minutes_to_god";
    }

    @Autowired
    public void setMinutesToGodService(MinutesToGodService minutesToGodService) {
        this.minutesToGodService = minutesToGodService;
    }

    @Autowired
    public void setProgrammTvDAO(ProgrammTvDAO programmTvDAO) {
        this.programmTvDAO = programmTvDAO;
    }

    @Autowired
    public void setOnlineTvDAO(OnlineTvDAO onlineTvDAO) {
        this.onlineTvDAO = onlineTvDAO;
    }
}
