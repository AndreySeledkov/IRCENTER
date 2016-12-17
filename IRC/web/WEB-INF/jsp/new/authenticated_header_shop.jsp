<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    long unReadMessage = (Long) request.getSession().getAttribute("unReadMessage");
    long requestFriend = (Long) request.getSession().getAttribute("requestFriend");
%>
<div id="profile_pad_menu" onMouseOver="show_pad2_menu('profile_pad_menu', 'profile_pad_button')" onMouseOut="hide_pad2_menu('profile_pad_menu', 'profile_pad_button')">
    <%--<a href="/messages" class="messages_black">Мои сообщения--%>
    <%--<c:if test='<%=unReadMessage >0%>'>--%>
    <%--<span class="counter"><%=unReadMessage%></span>--%>
    <%--</c:if>--%>
    <%--</a>--%>
    <%--<hr class="black"/>--%>
    <%--<a href="/friends" class="friends_black">Мои друзья--%>
    <%--<c:if test='<%=requestFriend >0%>'>--%>
    <%--<span class="counter"><%=requestFriend%></span>--%>
    <%--</c:if>--%>
    <%--</a>--%>
    <%--<hr class="black"/>--%>
    <%--<a href="/favorite" class="star_black">Мое избранное</a>--%>
    <%--<hr class="black"/>--%>
    <%--<a href="/edit" class="sett_black">Редактировать профиль</a>--%>
    <%--<hr class="black"/>--%>
    <%--<a href="javascript:void(0)" class="settings_black">Мои настройки</a>--%>
    <%--<hr class="black"/>--%>
    <a href="<c:url value="/j_spring_security_logout"/>" class="close_black">Выйти</a>
</div>

<div id="header_out">
    <div id="header">
        <div id="header_top">
            <a href="/" id="logo" class="fl_left">&nbsp;</a>

            <div id="header_top_right">
                <a href="javascript:void(0)" id="profile_pad_button" class="header_center_line_pad_menu_button mt12" onMouseOver="show_pad2_menu('profile_pad_menu', 'profile_pad_button')" onMouseOut="hide_pad2_menu('profile_pad_menu', 'profile_pad_button')">
                    <c:if test='<%=unReadMessage >0 || requestFriend >0%>'>
                        <div class="summ_counter"><%=(unReadMessage + requestFriend)%>
                        </div>
                    </c:if>
                    <img src="/static/img/user_img.png"
                         alt=""/> <%= ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getLoginName()%>
                </a>

            </div>
        </div>


        <div id="header_bottom">
            <div id="header_menu">
                <a class="link null"></a>
                <a href="/" class="link">Главная</a>
                <a id="news_button" href="/news" class="link" onMouseOver="show_pad_menu('news', 'news_button')" onMouseOut="hide_pad_menu('news', 'news_button')">Новости</a>
                <a href="/partner" class="link">Партнерство</a>
                <a id="svidet_button" href="/text_evidence" class="link" onMouseOver="show_pad_menu('svidet', 'svidet_button')" onMouseOut="hide_pad_menu('svidet', 'svidet_button')">Свидетельства</a>
                <a id="media_button" href="/tv_online" class="link" onMouseOver="show_pad_menu('media', 'media_button')" onMouseOut="hide_pad_menu('media', 'media_button')">Телевидение</a>
                <a class="link" href="/molodejhka">Молодежка</a>
                <%--<a href="/forum" class="link">Форум</a>--%>
                <a href="http://vo.org.ua/shop/index.php?option=com_jshopping&controller=category&task=view&category_id=5&Itemid=157" class="link select"><span class="l">&nbsp;</span>Магазин<span class="r">&nbsp;</span></a>
            </div>
            <div id="news" class="out_menu" onMouseOver="show_pad_menu('news', 'news_button')" onMouseOut="hide_pad_menu('news', 'news_button')">
                <div class="menu">
                    <div class="in_block">
                        <a href="/news?partId=2">Днепропетровск</a>
                        <hr>
                        <a href="/news?partId=3">Киев</a>
                    </div>
                </div>
            </div>
            <div id="media" class="out_menu" onMouseOver="show_pad_menu('media', 'media_button')" onMouseOut="hide_pad_menu('media', 'media_button')">
                <div class="menu">
                    <div class="in_block">
                        <a href="/tv_online">Прямой эфир</a>
                        <hr>
                        <a href="/tv">Телепередачи</a>
                        <hr>
                        <a href="/minutes_to_god">Пять минут для Бога</a>
                        <hr>
                        <a href="/secret_spiritual_world">Тайны Духовного мира</a>
                        <%--<hr>--%>
                        <%--<a href="tv.html">Разное</a>--%>
                    </div>
                </div>
            </div>
            <div id="svidet" class="out_menu" onMouseOver="show_pad_menu('svidet', 'svidet_button')" onMouseOut="hide_pad_menu('svidet', 'svidet_button')">
                <div class="menu">
                    <div class="in_block">
                        <a href="/text_evidence">Текстовые</a>
                        <hr>
                        <a href="/video_evidence">Видео</a>
                    </div>
                </div>
            </div>
            <form accept-charset="UTF-8" action="/search" method="get">
                <div id="main_serach" class="search fl_right">
                    <div class="search_bg">
                        <input type="text" name="q" class="srarch_input" placeholder="Поиск по сайту">
                        <a class="search_button" href="javascript:void(0)" onclick="$('#searchpanel').submit();">&nbsp;</a>
                        <input type="hidden" name="s" value="0"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
