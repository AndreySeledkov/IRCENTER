package org.ircenter.server.web.favorite;

import org.ircenter.server.bean.favorite.FavoritePost;
import org.ircenter.server.bean.news.PieceNews;
import org.ircenter.server.bean.news.PieceNewsView;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 1
 * Date: 09.04.12
 * Time: 9:37
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/favorite")
public class FavoritePostController {

    private Database database;

    @RequestMapping(method = RequestMethod.GET)
    public String favorite(Model model, HttpServletRequest request) {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }

        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);

        List<PieceNews> pieceNewsList = database.getFavoritePostDAO().getPagedFavoritePosts4User(page - 1);

        List<PieceNewsView> pieceNewsViewList = new ArrayList<PieceNewsView>(pieceNewsList.size());

        for (PieceNews pieceNews : pieceNewsList) {
            PieceNewsView pieceNewsView = new PieceNewsView(pieceNews);
            pieceNewsView.setAuthorName(database.getUserInfoDAO().getUser(pieceNews.getAuthorId()).getLoginName());
            pieceNewsViewList.add(pieceNewsView);
        }

        model.addAttribute("pieceNewsViewList", pieceNewsViewList);
        return "new/favorites";
    }

    @RequestMapping(value = "/favorite_form", method = RequestMethod.GET)
    public String getFriendsForm(Model model, HttpServletRequest request) {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) {
                page = 1;
            }
        }
        List<PieceNews> pieceNewsList = database.getFavoritePostDAO().getPagedFavoritePosts4User(page-1);

        List<PieceNewsView> pieceNewsViewList = new ArrayList<PieceNewsView>(pieceNewsList.size());

        for (PieceNews pieceNews : pieceNewsList) {
            PieceNewsView pieceNewsView = new PieceNewsView(pieceNews);
            pieceNewsView.setAuthorName(database.getUserInfoDAO().getUser(pieceNews.getAuthorId()).getLoginName());
            pieceNewsViewList.add(pieceNewsView);
        }

        model.addAttribute("pieceNewsViewList", pieceNewsViewList);
        return "new/favorites_form";
    }

    @RequestMapping(value = "/like", method = RequestMethod.GET)
    public
    @ResponseBody
    String like(@RequestParam Long postId) {
        database.getFavoritePostDAO().addFavoritePost(postId);
        return "Ok";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public
    @ResponseBody
    String remove(@RequestParam Long postId) {
        database.getFavoritePostDAO().deleteFavoritePost(postId);
        return "Ok";
    }

    public Database getDatabase() {
        return database;
    }


    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
