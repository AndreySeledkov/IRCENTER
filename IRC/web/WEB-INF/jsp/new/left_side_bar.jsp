<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.ircenter.server.service.user.UserHelper" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>


<div id="profile_left_sidebar">
    <div class="profile_sidebar_element">
        <div class="sidebar_header">
            <div class="header_in">
                <div class="overflow">
                    <span class="user_type">Журналист</span><span class="user_type_likes">3</span>
                </div>
                <span class="img">
                    <img src="/img/<%=UserHelper.getUserId()%>/<%=UserHelper.getUserId()%>.jpg" alt=" "/>
                </span>
                <a href="javascript:void(0);" onClick="createModal('/image/load_new_avatar');"
                   class="header_in_link"><img src="/static/img/my_avatar.png" alt=""/> Изменить фотографию</a>
                <a href="/edit" class="header_in_link" onclick="nav.go(this,event);return false;"><img
                        src="/static/img/my_settings.png"
                        alt=""/> Редактировать профиль</a>
            </div>
            <!-- /header_in -->
        </div>
        <!-- /sidebar_header -->
        <div class="sidebar_element_content">
            <a href="/profile" class="text_link my_profile">Мой профиль</a>
            <a href="" class="text_link my_article">Мои статьи</a>
            <a href="/messages?section=inbox"
               onclick="return rightSideUpdateMessagesModal('/messages?section=inbox','inbox')"
               class="text_link my_messages">Мои сообщения</a>
            <a href="/friends?section=all" onclick="return rightSideUpdateModal('/friends?section=all')"
               class="text_link my_friends">Мои
                друзья</a>
            <a href="/favorite" onclick="return rightSideUpdateModal('/favorite')" class="text_link my_favorites">Мое избранное</a>
        </div>
        <!-- /sidebar_element_content -->
        <!-- /sidebar_element_content -->
    </div>
    <!-- /profile_sidebar_element -->
    <div class="profile_sidebar_element">
        <div class="sidebar_header">
            <div class="header_in_for_links">
                <a href="#" class="header_link_16">Мои фотографии</a>
                <a href="#" class="header_link_12">все <img src="/static/img/arrow_white_right.png" alt=""/></a>
            </div>
            <!-- /header_in -->
        </div>
        <!-- /sidebar_header -->
        <div class="sidebar_element_content my_photos">
            <a href="" class="my_photo_element"><img src="/static/img/tmp52.jpg" alt=""/></a>
            <a href="" class="my_photo_element"><img src="/static/img/tmp53.jpg" alt=""/></a>
            <a href="" class="my_photo_element"><img src="/static/imgmages/tmp54.jpg" alt=""/></a>
        </div>
        <!-- /sidebar_element_content -->
    </div>
    <!-- /profile_sidebar_element -->
    <div class="profile_sidebar_element">
        <div class="sidebar_header">
            <div class="header_in_for_links">
                <a href="#" class="header_link_16">Мое видео</a>
                <a href="#" class="header_link_12">все <img src="/static/img/arrow_white_right.png" alt=""/></a>
            </div>
            <!-- /header_in -->
        </div>
        <!-- /sidebar_header -->
        <div class="sidebar_element_content my_photos">
            <a href="" class="my_photo_element"><img src="/static/img/tmp55.jpg" alt=""/></a>
        </div>
        <!-- /sidebar_element_content -->
    </div>
    <!-- /profile_sidebar_element -->
    <c:if test="${userListFriends.size()>0}">

        <div class="profile_sidebar_element">
            <div class="sidebar_header">
                <div class="header_in_for_links">
                    <a href="/friends?section=all" onclick="return rightSideUpdateModal('/friends?section=all')"
                       class="header_link_16">Друзья</a>
                    <a href="/friends?section=all" onclick="return rightSideUpdateModal('/friends?section=all')"
                       class="header_link_12">все <img src="/static/img/arrow_white_right.png"
                                                       alt=""/></a>
                </div>
                <!-- /header_in -->
            </div>
            <!-- /sidebar_header -->
            <div class="sidebar_header_down">
                    ${userListFriends.size()} друг
            </div>
            <!-- /sidebar_header_down -->
            <div class="sidebar_element_content no_bg no_padding">
                <c:forEach items="${userListFriends}" var="userListFriend" varStatus="i" end="4">
                    <a href="javascript:void(0)" class="image_link">
                        <img src="/img/${userListFriend.userId}/${userListFriend.userId}.jpg" alt=""/>
                        <span>${userListFriend.loginName}</span>
                    </a>
                </c:forEach>
            </div>
            <!-- /sidebar_element_content -->
        </div>
    </c:if>
    <!-- /profile_sidebar_element -->
    <c:if test="${friendListOnline.size()>0}">
        <div class="profile_sidebar_element">
            <div class="sidebar_header">
                <div class="header_in_for_links">
                    <a href="/friends?section=online" onclick="return rightSideUpdateModal('/friends?section=online')"
                       class="header_link_16">Друзья онлайн</a>
                    <a href="/friends?section=online" onclick="return rightSideUpdateModal('/friends?section=online')"
                       class="header_link_12">все <img
                            src="/static/img/arrow_white_right.png" alt=""/></a>
                </div>
                <!-- /header_in -->
            </div>
            <!-- /sidebar_header -->
            <div class="sidebar_header_down">
                    ${friendListOnline.size()} друг
            </div>
            <!-- /sidebar_header_down -->
            <div class="sidebar_element_content no_bg no_padding">
                <c:forEach items="${friendListOnline}" var="friendOnline" varStatus="i">
                    <a href="javascript:void(0)" class="image_link">
                        <img src="/img/${friendOnline.userId}/${friendOnline.userId}.jpg" alt=""/>
                        <span>${friendOnline.loginName}</span>
                    </a>
                </c:forEach>

                    <%--<a href="#" class="image_link">--%>
                    <%--<img src="/static/img/tmp34.jpg" alt=""/>--%>
                    <%--<span>Скрипник</span>--%>
                    <%--</a>--%>
                    <%--<a href="#" class="image_link">--%>
                    <%--<img src="/static/img/tmp35.jpg" alt=""/>--%>
                    <%--<span>Калашникова</span>--%>
                    <%--</a>--%>
                    <%--<a href="#" class="image_link">--%>
                    <%--<img src="/static/img/tmp36.jpg" alt=""/>--%>
                    <%--<span>Чилий</span>--%>
                    <%--</a>--%>
                    <%--<a href="#" class="image_link">--%>
                    <%--<img src="/static/img/tmp37.jpg" alt=""/>--%>
                    <%--<span>Кокшарова</span>--%>
                    <%--</a>--%>
            </div>
            <!-- /sidebar_element_content -->
        </div>
    </c:if>
    <!-- /profile_sidebar_element -->
</div>
