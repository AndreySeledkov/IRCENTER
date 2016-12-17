<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="profile_part_header">Мое избранное</div>

<div class="profile_right_column_content">
    <div class="profile_messages_header">
        <span class="messages_counter">У Вас 2 статьи в закладках</span>
        <div class="messages_search">
            <a href="" class="messages_search_button">&nbsp;</a>
            <input type="text" title="Поиск закладок" class="messages_search_input">
        </div>
    </div>
    <div class="favorites">
        <div class="favorite silver_bg">
            <a href="news.html" class="favorite_group">Здоровье</a>
            <a href="news.html" class="favorite_name">Смысл жизни</a>
            <a href="" class="favorite_delete">x</a>
            <div class="favorite_body_bottom">
                <div class="favorite_body_bottom_in">
                    <a href="profile.html" class="favorite_author">Serj Rudakov</a>
                    <span class="favorite_date">03 фев. 2012, 13:53</span>
                </div>
            </div>
        </div>
        <div class="favorite white_bg">
            <a href="news.html" class="favorite_group">Здоровье</a>
            <a href="news.html" class="favorite_name">Смысл жизни</a>
            <a href="" class="favorite_delete">x</a>
            <div class="favorite_body_bottom">
                <div class="favorite_body_bottom_in">
                    <a href="profile.html" class="favorite_author">Serj Rudakov</a>
                    <span class="favorite_date">03 фев. 2012, 13:53</span>
                </div>
            </div>
        </div>
    </div>
    <div class="navi_pagination in_column">
        <span class="silver_shadow mb_10 disabled"><a class="button gradient_silver_button"><img src="images/arrow_left.png" alt="" /> Назад</a></span>
        <div class="pages">
            <a href="#"  class="page_link">11</a>
            <a href="#"  class="page_link">12</a>
            <a href="#"  class="page_link">13</a>
            <a href="#"  class="page_link">14</a>
            <a href="#"  class="page_link">15</a>
            <span class="page_link">16</span>
            <a href="#"  class="page_link">17</a>
            <a href="#"  class="page_link">18</a>
            <a href="#"  class="page_link">19</a>
            <a href="#"  class="page_link">20</a>
        </div>
        <span class="silver_shadow mb_10"><a href="#"  class="gradient_silver_button button">Вперед <img src="images/arrow_right.png" alt="" /></a></span>
    </div>
</div>
