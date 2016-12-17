package org.ircenter.server.web.tv;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.OnlineTv;
import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
import org.ircenter.server.bean.evidence.SecretSpiritualWorld;
import org.ircenter.server.dao.evidence.ProgrammTvDAO;
import org.ircenter.server.dao.online_tv.OnlineTvDAO;
import org.ircenter.server.service.evidence.SecretSpiritualWorldService;
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
 * Time: 0:30
 */
@Controller
@RequestMapping("/secret_spiritual_world")
public class SecretSpiritualWorldController {

    private static final int Evidence_LIMIT = 10;
    private SecretSpiritualWorldService secretSpiritualWorldService;
    private ProgrammTvDAO programmTvDAO;
    private OnlineTvDAO onlineTvDAO;


    @RequestMapping(method = RequestMethod.GET)
    public String getSecretSpiritualWorld(Model model, HttpServletRequest request) { //TODO
        long unReadMessage = 0;//database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = 0;//database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());
        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);
        int page = 1;

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
        }

        int secretSpiritualWorldCount = secretSpiritualWorldService.getSecretSpiritualWorldCount();
        List<SecretSpiritualWorld> secretSpiritualWorldList = secretSpiritualWorldService.getSecretSpiritualWorld((page - 1) * Evidence_LIMIT, Evidence_LIMIT);

        if (request.getParameter("link_id") != null) {
            long link = Long.parseLong(request.getParameter("link_id"));
            SecretSpiritualWorld secretSpiritualWorld = secretSpiritualWorldService.getSecretSpiritualWorld(link);
            secretSpiritualWorldService.setViewCount(secretSpiritualWorld, 1);
            model.addAttribute("link", secretSpiritualWorld.getLink());
            model.addAttribute("secretSpiritualWorld", secretSpiritualWorld);
        } else {
            model.addAttribute("secretSpiritualWorld", secretSpiritualWorldList.get(0));
        }


        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/secret_spiritual_world?page=", Evidence_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(secretSpiritualWorldCount);
        pageListPlain.generate(builder);

        model.addAttribute("secretSpiritualWorldCount", secretSpiritualWorldCount);

        List<ProgrammTv> programmTvList = programmTvDAO.getProgrammTvByType(ProgrammTvType.CNL);

        model.addAttribute("programmTvList", programmTvList);

        model.addAttribute("pagination", builder);
        model.addAttribute("page", page);

        model.addAttribute("secretSpiritualWorldList", secretSpiritualWorldList);
        OnlineTv onlineTv = onlineTvDAO.getOnlineTv();
        if (onlineTv != null) {
            model.addAttribute("onlineLeft", onlineTv.getDate().getTime());
        }
        return "new/secret_spiritual_world";
    }

    @Autowired
    public void setSecretSpiritualWorldService(SecretSpiritualWorldService secretSpiritualWorldService) {
        this.secretSpiritualWorldService = secretSpiritualWorldService;
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
