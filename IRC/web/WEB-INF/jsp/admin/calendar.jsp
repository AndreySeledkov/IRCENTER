<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" SYSTEM "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="ru-RU">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Календарь</title>
    <link rel="stylesheet" href="/static/css/admin/load-styles.css" type="text/css" media="all">
    <link rel="stylesheet" id="akismet.css-css" href="/static/css/admin/akismet.css" type="text/css" media="all">
    <link rel="stylesheet" id="colors-css" href="/static/css/admin/colors-fresh.css" type="text/css" media="all">
    <!--[if lte IE 7]>
    <link rel='stylesheet' id='ie-css' href='/static/css/admin/ie.css' type='text/css' media='all'/>
    <![endif]-->
    <link rel="stylesheet" id="nggmenu-css" href="/static/css/admin/menu.css" type="text/css" media="all">
    <script type="text/javascript" src="/static/js/admin/l10n.js"></script>
    <link type="image/x-icon" rel="shortcut icon" href="/static/img/favicon.ico">
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/admin/load-scripts.js"></script>
    <script type="text/javascript" src="/static/js/admin/akismet.js"></script>
    <script type="text/javascript" src="/static/js/admin/load-scripts1.js"></script>
    <script type="text/javascript" src="/static/js/admin/update_right_side.js"></script>
    <link rel="stylesheet" id="ru_RU-css" href="/static/css/admin/ru_RU.css" type="text/css" media="all">
    <!--[if IE]>
    <link rel='stylesheet' id='ru_RU-ie-css' href='/static/css/admin/ru_RU-ie.css' type='text/css' media='all'/>
    <![endif]-->
</head>
<body>
<div>
    <div>
        <jsp:include page="header.jsp"/>

        <div id="wpbody">
            <div id="wpbody-content">
                <jsp:include page="left_side_bar.jsp"/>
                <div class="wrap">
                    <div class="main_content_header_small">Календарь событий</div>
                    <a href="/admin/add_calendar" class="button add-new-h2">Добавить</a>
                    <table cellspacing="0" cellpadding="0" class="events_calendar">
                        <tr class="events_calendar_header">
                            <td class="time">Дата проведения</td>
                            <td class="city">Город</td>
                            <td class="place">Место проведения и название мероприятия</td>
                        </tr>

                        <c:forEach items="${events}" var="item" varStatus="status">
                            <c:choose>
                                <c:when test="${status.step % 2 == 0}">
                                    <tr class="calendar_element white">
                                </c:when>
                                <c:otherwise>
                                    <tr class="events_calendar_header">
                                </c:otherwise>
                            </c:choose>
                            <%--<td class="date"><span class="date_top">12</span><span class="date_bottom">Сентября</span>--%>
                            <%--</td>--%>
                            <td class="time">
                                <span class="time_top">Начало:</span><span
                                    class="time_bottom"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                                        value="${item.getStartEvent()}"/></span><span
                                    class="time_top">Окончание:</span><span
                                    class="time_bottom"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                                        value="${item.getEndEvent()}"/></span></td>
                            <td class="city"><a href="">
                                <img src="/image/getimage/1/${item.getDefImageId()}" alt=""/>
                                    <%--<img src="images/tmp118.jpg" alt=""/>--%>
                                <span class="city_name">${item.getCity()}</span></a></td>
                            <td class="place">
                                <a href="/admin/edit_calendar?id=${item.getEventId()}" class="edit">Изменить</a>
                                <a href="/admin/remove_calendar?id=${item.getEventId()}" class="edit">Удалить</a>
                                <span class="place_name">${item.getPlace()}</span>
                                <span class="event_name">${item.getEventName()}</span></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="clear">
                </div>
            </div>
            <!-- wpbody-content -->
        </div>
    </div>
</div>
</body>
</html>