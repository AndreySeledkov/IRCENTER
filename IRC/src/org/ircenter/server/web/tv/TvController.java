package org.ircenter.server.web.tv;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.OnlineTv;
import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
import org.ircenter.server.bean.evidence.Tv;
import org.ircenter.server.bean.evidence.TvType;
import org.ircenter.server.dao.evidence.ProgrammTvDAO;
import org.ircenter.server.dao.online_tv.OnlineTvDAO;
import org.ircenter.server.service.evidence.TvService;
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
@RequestMapping("/tv")
public class TvController {

    private static final int TV_LIMIT = 10;
    private TvService tvService;
    private ProgrammTvDAO programmTvDAO;
    private OnlineTvDAO onlineTvDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String getMinutesToGod(Model model, HttpServletRequest request) {//TODO
        long unReadMessage = 0;//database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = 0;//database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());
        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);
        int page = 1;
        int type = TvType.DNEPR.ordinal();

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
        }

        if (request.getParameter("type") != null) {
            type = MUtil.str2int(request.getParameter("type"), 1);
        }


        int tvCount = tvService.getTvCount(TvType.valueOf(type));
        List<Tv> tvList = tvService.getTV((page - 1) * TV_LIMIT, TV_LIMIT, TvType.valueOf(type));
//        for (Tv tv : tvList) {
//            tv.setLink(MUtil.replaceHTMLtextReturn(tv.getLink()));
//        }

        int id;
        Tv tvShow;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            tvShow = tvService.getTv(id);
            model.addAttribute("tvShow", tvShow);
        } else {
            model.addAttribute("tvShow", tvList.get(0));
        }

        model.addAttribute("type", type);
        model.addAttribute("tvList", tvList);

        List<ProgrammTv> programmTvList = programmTvDAO.getProgrammTvByType(ProgrammTvType.CNL);

        model.addAttribute("programmTvList", programmTvList);


        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/tv?type=" + type + "&page=", TV_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(tvCount);
        pageListPlain.generate(builder);

        model.addAttribute("pagination", builder);
        model.addAttribute("tvCount", tvCount);
        model.addAttribute("page", page);
        OnlineTv onlineTv = onlineTvDAO.getOnlineTv();
        if (onlineTv != null) {
            model.addAttribute("onlineLeft", onlineTv.getDate().getTime());
        }
        return "new/tv";
    }

    @Autowired
    public void setTvService(TvService tvService) {
        this.tvService = tvService;
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
