package org.ircenter.server.web.tv;

import org.ircenter.server.bean.OnlineTv;
import org.ircenter.server.bean.evidence.OnlineEvidence;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 11.05.12
 * Time: 0:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/tv_online")
public class TvOnlineController {

    private static final int TV_LIMIT = 10;
    private Database database;

    @RequestMapping(method = RequestMethod.GET)
    public String getMinutesToGod(Model model, HttpServletRequest request) {
        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());
        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        int evidenceOnlineCount = database.getOnlineEvidenceDAO().getOnlineEvidenceCount();
        List<OnlineEvidence> onlineEvidenceList = database.getOnlineEvidenceDAO().getOnlineEvidence((page - 1) * TV_LIMIT, TV_LIMIT);
        model.addAttribute("onlineEvidenceList", onlineEvidenceList);

        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/tv_online?page=", TV_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(evidenceOnlineCount);
        pageListPlain.generate(builder);

        model.addAttribute("pagination", builder);
        model.addAttribute("evidenceOnlineCount", evidenceOnlineCount);
        OnlineTv onlineTv = database.getOnlineTvDAO().getOnlineTv();
        if (onlineTv != null) {
            model.addAttribute("onlineLeft", onlineTv.getDate().getTime());
        }
        return "new/tv_online";
    }


    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
