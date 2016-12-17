<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div id="navig_menu">
    <div class="menu_header">
        <c:choose>
            <c:when test="${newsSection == -1}">
                <span>Все</span>
                <a href="javascript:void(0);" onclick="news.switchSection(2,this)">Днепропетровск</a>
                <a href="javascript:void(0);" onclick="news.switchSection(3,this)">Киев</a>
            </c:when>
            <c:when test="${newsSection == 2}">
                <a href="javascript:void(0);" onclick="news.switchSection(-1,this)">Все</a>
                <span>Днепропетровск</span>
                <a href="javascript:void(0);" onclick="news.switchSection(3,this)">Киев</a>
            </c:when>
            <c:when test="${newsSection == 3}">
                <a href="javascript:void(0);" onclick="news.switchSection(-1,this)">Все</a>
                <a href="javascript:void(0);" onclick="news.switchSection(2,this)">Днепропетровск</a>
                <span>Киев</span>
            </c:when>
        </c:choose>
    </div>

    <c:forEach var="piece" items="${news}" varStatus="status">
        <div class="topic">
            <a href="/news" class="group">${piece.getPartName()}</a>
            <a href="/news/${piece.getId()}" class="group_element">${piece.getTitle()}</a>

            <div class="topic_block">
                <a href="/news/${piece.getId()}"><img src="/image/getimage/6/${piece.getImageId()}" alt=""/></a>

                <p>${piece.getText()}</p>
            </div>
            <!-- /topic_block -->
        </div>
        <!-- /topic -->

        <div class="read_next">
            <a href="/news/${piece.getId()}" class="read_next_button">Читать дальше &gt;</a>

            <div class="fl_left ml10" style="font-size: 11px;">
                <span style="color:#515152;line-height: 26px;">Просмотров:</span>
                <span style="margin-left: 5px;color: #000000;">${piece.getViewCount()}</span>
            </div>
            <div class="read_next_right">
                <a hhref="javascript:void(0);" onClick="show_local_message(this, 'Добавлено');" class="bookmarks">Добавить
                    в
                    избранное</a>
                <a href="#" class="twetter">&nbsp;</a>
                <a href="#" class="facebook">&nbsp;</a>
                ${piece.getDate()}
            </div>
            <!-- /read_next_right -->
        </div>
        <!-- /read_next -->
    </c:forEach>

    <a href="/news" style="display: inline-block;margin-top: 10px;">Еще новости</a>

    <%--<sec:authorize access="isAuthenticated()">--%>
    <%--<a href="javascript:void(0)" onclick="addPostInFavorite(${piece.getId()})" class="bookmarks">Добавить--%>
    <%--в избранное</a>--%>
    <%--</sec:authorize>--%>

    <%--<div class="yashare-auto-init" data-yashareL10n="ru" data-yashareType="none"--%>
    <%--data-yashareQuickServices="vkontakte,facebook,twitter,lj"></div>--%>
    <%--${piece.getDate()}--%>
    <%--</div>--%>
    <%--<!-- /read_next_right -->--%>
    <%--</div>--%>
    <%--<!-- /read_next -->--%>
    <%--</c:forEach>--%>
    <!-- /navig_menu -->
</div>
</body>
</html>




