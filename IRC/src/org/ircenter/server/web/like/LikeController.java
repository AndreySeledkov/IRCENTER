package org.ircenter.server.web.like;

import org.ircenter.server.bean.like.ServiceType;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.like.LikeService;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.web.edit.EditAjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: Seledkov Kostyantyn
 * Date: 18.05.12
 * Time: 2:31
 */
@Controller
@RequestMapping("/like")
public class LikeController {

    private Database database;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public
    @ResponseBody
    EditAjaxResponse like(@RequestParam int type, @RequestParam long id) {
        LikeService.QueryStatus status = database.getLikeService().likeContent(UserHelper.getUserId(), UserHelper.getUserId(), ServiceType.valueOfDbType(type), id);
        if (status == LikeService.QueryStatus.UPDATED_WITHOUT_ROW_EFFECT) {
            return new EditAjaxResponse(false);
        } else {
            return new EditAjaxResponse(true);
        }
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
