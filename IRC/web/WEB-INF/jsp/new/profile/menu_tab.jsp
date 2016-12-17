<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="list_element_center profile_menu">
    <ul class="fl_left">
        <li>

            <c:choose>
                <c:when test="${isMessageTab!=null && isMessageTab}">
                    <a href="/messages?section=inbox" class="select"><span
                            class="profile_messages">&nbsp;</span>Сообщения
                        <span class="counter">${privateMessagesInboxCount}/${privateMessagesOutboxCount}</span><span
                                class="r">&nbsp;</span></a>
                </c:when>
                <c:otherwise>
                    <a href="/messages?section=inbox"><span
                            class="profile_messages">&nbsp;</span>Сообщения<span
                            class="counter">${privateMessagesInboxCount}/${privateMessagesOutboxCount}</span><span
                            class="r">&nbsp;</span></a>
                </c:otherwise>
            </c:choose>
        </li>
        <li>

            <c:choose>
                <c:when test="${isFriendTab!=null && isFriendTab}">
                    <a href="/friends?section=all" class="select"><span
                            class="profile_messages">&nbsp;</span>Друзья<span
                            class="counter">${friendCount}/${friendRequestCount}</span><span class="r">&nbsp;</span></a>

                </c:when>
                <c:otherwise>
                    <a href="/friends?section=all"><span
                            class="profile_messages">&nbsp;</span>Друзья<span
                            class="counter">${friendCount}/${friendRequestCount}</span><span class="r">&nbsp;</span></a>
                </c:otherwise>
            </c:choose>
        </li>
        <li>

            <c:choose>
                <c:when test="${isFavoriteTab!=null && isFavoriteTab}">
                    <a href="/favorite"
                       class="select"><span class="profile_messages">&nbsp;</span>Избранное<span
                            class="counter">123</span><span class="r">&nbsp;</span></a>

                </c:when>
                <c:otherwise>
                    <a href="/favorite"><span
                            class="profile_messages">&nbsp;</span>Избранное<span
                            class="counter">123</span><span class="r">&nbsp;</span></a>
                </c:otherwise>
            </c:choose>
        </li>

        <li>

            <c:choose>
                <c:when test="${isPhotosTab!=null && isPhotosTab}">
                    <a href="messages.html" class="select"><span
                            class="profile_messages">&nbsp;</span>Фотографии<span
                            class="counter">123</span><span class="r">&nbsp;</span></a>

                </c:when>
                <c:otherwise>
                    <a href="messages.html"><span class="profile_messages">&nbsp;</span>Фотографии<span
                            class="counter">123</span><span class="r">&nbsp;</span></a>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</div>
<div class="media_menu_middle">
    <div class="media_menu_user_name">
        <%= ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getLoginName()%>
    </div>
    <a href="javascript:void(0)" class="media_menu_edit_profile">Редактировать профиль</a>

    <div class="media_menu_user_status pastor_status_6">Старший Пастор</div>

    <div class="media_menu_menu bottom_right_shadow">
        <span>Добавить</span>
        <a href="javascript:void(0)" class="media_menu_add_docs"><span>&nbsp;</span></a>
        <a href="javascript:void(0)" class="media_menu_add_photos"><span>&nbsp;</span></a>
    </div>
    <div class="media_menu_photos">
        <div class="media_menu_photo">
            <a href="javascript:void(0)" class="media_menu_photo_avatar"><img
                    src="/static/img/tmp100.jpg" alt=""/></a>
            <a href="javascript:void(0)" class="media_menu_photo_like">123789</a>
        </div>
        <div class="media_menu_photo">
            <a href="javascript:void(0)" class="media_menu_photo_avatar"><img
                    src="/static/img/tmp101.png" alt=""></a>
            <a href="javascript:void(0)" class="media_menu_photo_like">123789</a>
        </div>
        <div class="media_menu_photo">
            <a href="javascript:void(0)" class="media_menu_photo_avatar"><img
                    src="/static/img/tmp102.png" alt=""></a>
            <a href="javascript:void(0)" class="media_menu_photo_like">123789</a>
        </div>
        <div class="media_menu_photo">
            <a href="" class="media_menu_photo_avatar"><img src="/static/img/tmp103.png" alt=""></a>
            <a href="" class="media_menu_photo_like">123789</a>
        </div>
        <a href="" class="media_menu_more_photo">Еще фото</a>
    </div>
</div>