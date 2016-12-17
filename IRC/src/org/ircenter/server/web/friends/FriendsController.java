package org.ircenter.server.web.friends;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.friends.Friend;
import org.ircenter.server.bean.friends.FriendRequest;
import org.ircenter.server.dao.Database;
import org.ircenter.server.service.authentication.LoggedUsers;
import org.ircenter.server.service.user.UserHelper;
import org.ircenter.server.service.user.UserProfile;
import org.ircenter.server.util.paging.PageListShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/friends")
public class FriendsController {

    private static final int FRIENDS_LIMIT = 10;
    private Database database;

    @RequestMapping(method = RequestMethod.GET)
    public String friends(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
            if (page < 1) {
                page = 1;
            }
        }

        long unReadMessage = database.getPrivateMessageDAO().getNotReadedPrivateMessageCount();
        long requestFriend = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        request.getSession().setAttribute("unReadMessage", unReadMessage);
        request.getSession().setAttribute("requestFriend", requestFriend);
        model.addAttribute("isFriendTab", true);
        int privateMessagesInboxCount = database.getPrivateMessageDAO().getPrivateMessageCount(false);
        int privateMessagesOutboxCount = database.getPrivateMessageDAO().getPrivateMessageCount(false);

        model.addAttribute("privateMessagesInboxCount", privateMessagesInboxCount);
        model.addAttribute("privateMessagesOutboxCount", privateMessagesOutboxCount);

        int friendRequestCount = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());
        int friendCount = database.getFriendsDAO().getFriendsCount(UserHelper.getUserId());

        model.addAttribute("friendRequestCount", friendRequestCount);
        model.addAttribute("friendCount", friendCount);

        String section = request.getParameter("section");
        if (section == null || request.getParameter("section").equals("all")) {

            int friendsCount = database.getFriendsDAO().getFriendsCount(UserHelper.getUserId());

            List<Friend> friendList = database.getFriendsDAO().getFriendsClientId(UserHelper.getUserId(), (page - 1) * FRIENDS_LIMIT, FRIENDS_LIMIT);
            List<UserProfile> userList = new ArrayList<UserProfile>();
            for (Friend friend : friendList) {
                UserProfile user = database.getUserInfoDAO().getUser(friend.getFriendId());
                userList.add(user);
            }
            StringBuilder builder = new StringBuilder();
            PageListShort pageListPlain = new PageListShort("/friends?section=all&page=", true, FRIENDS_LIMIT);
            pageListPlain.setCurrentPage(page - 1);
            pageListPlain.setItemsCount(friendsCount);
            pageListPlain.generate(builder);

            model.addAttribute("pagination", builder);
            model.addAttribute("friendsList", userList);
            model.addAttribute("friendsCount", friendsCount);
            model.addAttribute("isFriendAll", true);
            return "new/profile/friends";
        } else if (section != null && request.getParameter("section").equals("online")) {

            List<Friend> friendList = database.getFriendsDAO().getFriendsClientId(UserHelper.getUserId());
            List<UserProfile> userList = new ArrayList<UserProfile>();

            int count = 0;
            for (Friend friend : friendList) {
                if (LoggedUsers.getLoggedUserInfo(friend.getFriendId()) != null) {
                    if (count >= (page - 1) * FRIENDS_LIMIT && count < page * FRIENDS_LIMIT) {
                        UserProfile user = database.getUserInfoDAO().getUser(friend.getFriendId());
                        userList.add(user);
                    }
                    count++;
                }
            }
            StringBuilder builder = new StringBuilder();
            PageListShort pageListPlain = new PageListShort("/friends?section=online&page=", true, FRIENDS_LIMIT);
            pageListPlain.setCurrentPage(page - 1);
            pageListPlain.setItemsCount(count);
            pageListPlain.generate(builder);

            model.addAttribute("pagination", builder);
            model.addAttribute("friendsList", userList);
            model.addAttribute("friendsCount", friendList.size());
            model.addAttribute("isFriendOnline", true);
            return "new/profile/friends_online";
        } else if (section != null && request.getParameter("section").equals("all_requests")) {

            int requestCount = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

            List<FriendRequest> friendRequestList = database.getFriendsRequestDAO().getAllRequestFriend(UserHelper.getUserId(), (page - 1) * FRIENDS_LIMIT, FRIENDS_LIMIT);
            List<UserProfile> userList = new ArrayList<UserProfile>();
            for (FriendRequest friendRequest : friendRequestList) {
                UserProfile user = database.getUserInfoDAO().getUser(friendRequest.getRequestClientFrom());
                userList.add(user);
            }
            StringBuilder builder = new StringBuilder();
            PageListShort pageListPlain = new PageListShort("/friends?section=all_requests&page=", true, FRIENDS_LIMIT);
            pageListPlain.setCurrentPage(page - 1);
            pageListPlain.setItemsCount(requestCount);
            pageListPlain.generate(builder);

            model.addAttribute("pagination", builder);
            model.addAttribute("friendsList", userList);
            model.addAttribute("friendsCount", requestCount);
            model.addAttribute("isFriendRequest", true);
            return "new/profile/friends_request";
        } else if (section != null && request.getParameter("section").equals("search")) {  //todo search
            List<UserProfile> userProfileList = database.getUserInfoDAO().searchUser("");

            for (UserProfile userProfile : userProfileList) {
                userProfile.setRequest(database.getFriendsRequestDAO().isRequest(userProfile.getUserId(), UserHelper.getUserId()));
                userProfile.setFriend(database.getFriendsDAO().isFriends(UserHelper.getUserId(), userProfile.getUserId()));
            }
            model.addAttribute("userProfiles", userProfileList);

            model.addAttribute("isFriendSearch", true);
            return "new/profile/friends_search";
        }
        return "new/profile/friends";
    }


    @RequestMapping(value = "/friends_form", method = RequestMethod.GET)
    public String getFriendsForm(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
            if (page < 1) {
                page = 1;
            }
        }

        int friendsCount = database.getFriendsDAO().getFriendsCount(UserHelper.getUserId());

        List<Friend> friendList = database.getFriendsDAO().getFriendsClientId(UserHelper.getUserId(), (page - 1) * FRIENDS_LIMIT, FRIENDS_LIMIT);
        List<UserProfile> userList = new ArrayList<UserProfile>();
        for (Friend friend : friendList) {
            UserProfile user = database.getUserInfoDAO().getUser(friend.getFriendId());
            userList.add(user);
        }
        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/friends?section=all&page=", true, FRIENDS_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(friendsCount);
        pageListPlain.generate(builder);

        model.addAttribute("pagination", builder);
        model.addAttribute("friendsList", userList);
        model.addAttribute("friendsCount", friendsCount);
        model.addAttribute("isFriendAll", true);


        return "new/profile/friends_form";
    }

    @RequestMapping(value = "/friends_online_form", method = RequestMethod.GET)
    public String getFriendsOnlineForm(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
            if (page < 1) {
                page = 1;
            }
        }

        List<Friend> friendList = database.getFriendsDAO().getFriendsClientId(UserHelper.getUserId());
        List<UserProfile> userList = new ArrayList<UserProfile>();

        int count = 0;
        for (Friend friend : friendList) {
            if (LoggedUsers.getLoggedUserInfo(friend.getFriendId()) != null) {
                if (count >= (page - 1) * FRIENDS_LIMIT && count < page * FRIENDS_LIMIT) {
                    UserProfile user = database.getUserInfoDAO().getUser(friend.getFriendId());
                    userList.add(user);
                }
                count++;
            }
        }
        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/friends?section=online&page=", true, FRIENDS_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(count);
        pageListPlain.generate(builder);

        model.addAttribute("pagination", builder);
        model.addAttribute("friendsList", userList);
        model.addAttribute("friendsCount", friendList.size());
        model.addAttribute("isFriendOnline", true);
        return "new/profile/friends_online_form";
    }

    @RequestMapping(value = "/friends_request_form", method = RequestMethod.GET)
    public String getFriendsRequestForm(Model model, HttpServletRequest request) {
        int page = 1;

        if (request.getParameter("page") != null) {
            page = MUtil.str2int(request.getParameter("page"), 1);
            if (page < 1) {
                page = 1;
            }
        }

        int requestCount = database.getFriendsRequestDAO().getRequestCount(UserHelper.getUserId());

        List<FriendRequest> friendRequestList = database.getFriendsRequestDAO().getAllRequestFriend(UserHelper.getUserId(), (page - 1) * FRIENDS_LIMIT, FRIENDS_LIMIT);
        List<UserProfile> userList = new ArrayList<UserProfile>();
        for (FriendRequest friendRequest : friendRequestList) {
            UserProfile user = database.getUserInfoDAO().getUser(friendRequest.getRequestClientFrom());
            userList.add(user);
        }
        StringBuilder builder = new StringBuilder();
        PageListShort pageListPlain = new PageListShort("/friends?section=all_requests&page=", true, FRIENDS_LIMIT);
        pageListPlain.setCurrentPage(page - 1);
        pageListPlain.setItemsCount(requestCount);
        pageListPlain.generate(builder);

        model.addAttribute("pagination", builder);
        model.addAttribute("friendsList", userList);
        model.addAttribute("friendsCount", requestCount);
        model.addAttribute("isFriendRequest", true);
        return "new/profile/friends_request_form";
    }


    @RequestMapping(value = "/friends_search_form", method = RequestMethod.GET)
    public String getFriendsSearchForm(Model model, HttpServletRequest request) {
        List<UserProfile> userProfileList;

        if (request.getParameter("searchText") != null) {
            userProfileList = database.getUserInfoDAO().searchUser(request.getParameter("searchText"));
        } else {
            userProfileList = database.getUserInfoDAO().searchUser("");
        }

        for (UserProfile userProfile : userProfileList) {
            userProfile.setRequest(database.getFriendsRequestDAO().isRequest(userProfile.getUserId(), UserHelper.getUserId()));
            userProfile.setFriend(database.getFriendsDAO().isFriends(UserHelper.getUserId(), userProfile.getUserId()));
        }

        model.addAttribute("userProfiles", userProfileList);
        model.addAttribute("isFriendSearch", true);

        return "new/profile/friends_search_form";
    }


    @RequestMapping(value = "/delete_friend", method = RequestMethod.GET)
    @ResponseBody
    public String deleteFriend(@RequestParam Long friend_id) {
        return database.getFriendsDAO().remove(UserHelper.getUserId(), friend_id) ? "Ok" : "False";
    }

    @RequestMapping(value = "/delete_request_friend", method = RequestMethod.GET)
    @ResponseBody
    public String deleteRequestFriend(@RequestParam Long request_friend_id) {
        return database.getFriendsRequestDAO().removeRequest(request_friend_id, UserHelper.getUserId()) ? "Ok" : "False";
    }

    @RequestMapping(value = "/add_to_friend", method = RequestMethod.GET)
    @ResponseBody
    public String addRequestFriend(@RequestParam Long request_friend_id) {
        if (request_friend_id == UserHelper.getUserId()) {
            return "False";
        }

        if (!database.getFriendsDAO().isFriends(request_friend_id, UserHelper.getUserId()) && !database.getFriendsDAO().isFriends(UserHelper.getUserId(), request_friend_id) && !database.getFriendsRequestDAO().isRequest(UserHelper.getUserId(), request_friend_id)) {
            FriendRequest friendRequest = new FriendRequest();
            friendRequest.setRequestClientFrom(UserHelper.getUserId());
            friendRequest.setRequestClientTo(request_friend_id);
            friendRequest.setDate(new Date());
            return database.getFriendsRequestDAO().insertRequestFriend(friendRequest) ? "Ok" : "False";
        }

        if (!database.getFriendsDAO().isFriends(request_friend_id, UserHelper.getUserId()) && !database.getFriendsDAO().isFriends(UserHelper.getUserId(), request_friend_id) && database.getFriendsRequestDAO().isRequest(UserHelper.getUserId(), request_friend_id)) {

            Date date = new Date();

            Friend selfFriend = new Friend();
            selfFriend.setSelfId(UserHelper.getUserId());
            selfFriend.setFriendId(request_friend_id);
            selfFriend.setFriendDate(date);
            database.getFriendsDAO().insertFriend(selfFriend);

            Friend otherFriend = new Friend();
            otherFriend.setSelfId(request_friend_id);
            otherFriend.setFriendId(UserHelper.getUserId());
            otherFriend.setFriendDate(date);
            database.getFriendsDAO().insertFriend(otherFriend);

            return database.getFriendsRequestDAO().removeRequest(request_friend_id, UserHelper.getUserId()) ? "Ok" : "False";
        }
        return "False";
    }

//    @RequestMapping(value = "/get_friends", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Friend> getFriends() {
//        return database.getFriendsDAO().getFriendsClientId(UserHelper.getUserId());
//    }

    public Database getDatabase() {
        return database;
    }

    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

}
