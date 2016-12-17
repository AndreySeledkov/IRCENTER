<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="profile_right_column">
    <div class="profile_right_column_header_elements">
        <div class="list_element_center">
            <ul class="fl_left">
                <li><a href="favorites.html" class="select">Статьи</a></li>
                <li><a href="favorites_videos.html">Видео</a></li>
            </ul>
        </div>
    </div>
    <div class="profile_right_column_content">
        <div class="profile_messages_header">
            <span class="messages_counter">У Вас ${pieceNewsViewList.size()} статьи в закладках</span>

            <div class="messages_search">
                <a href="" class="messages_search_button">&nbsp;</a>
                <input type="text" title="Поиск закладок" class="messages_search_input">
            </div>
        </div>
        <div class="favorites">
            <c:forEach items="${pieceNewsViewList}" var="pieceNews" varStatus="i">
                <div class="favorite silver_bg">
                    <a href="news.html" class="favorite_group">Здоровье</a>
                    <a href="news.html" class="favorite_name">Смысл жизни</a>
                    <a href="javascript:void(0)" onclick="removeFavoritePost(${pieceNews.getId()})" class="favorite_delete">x</a>

                    <div class="favorite_body_bottom">
                        <div class="favorite_body_bottom_in">
                            <a href="profile.html" class="favorite_author">${pieceNews.getAuthorName()}</a>
                            <span class="favorite_date">${pieceNews.startDate}</span>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>