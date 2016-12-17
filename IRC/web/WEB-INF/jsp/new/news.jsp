<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Архив новостей</title>
    <link type="image/x-icon" rel="shortcut icon" href="/static/img/favicon.ico">
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/news.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/redactor.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/redactor.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>
    <script src="/static/js/news.js" type="text/javascript"></script>
    <script type="text/javascript">
        function sendDelivery() {
            $.post("/feedback/delivery", { mail:$('#mail_delivery').val()},
                    function (response) {
                        if (response.complete) {
                            show_message('Отправлено', 2);
                            placeholder_imitator();
                            Recaptcha.reload();
                        }
                    }, 'json')
        }
    </script>
    <script type="text/javascript">
        (function (d, w, c) {
            (w[c] = w[c] || []).push(function() {
                try {
                    w.yaCounter15559465 = new Ya.Metrika({id:15559465, enableAll: true, webvisor:true});
                } catch(e) {}
            });

            var n = d.getElementsByTagName("script")[0],
                    s = d.createElement("script"),
                    f = function () { n.parentNode.insertBefore(s, n); };
            s.type = "text/javascript";
            s.async = true;
            s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";

            if (w.opera == "[object Opera]") {
                d.addEventListener("DOMContentLoaded", f);
            } else { f(); }
        })(document, window, "yandex_metrika_callbacks");
    </script>
</head>
<body>
<sec:authorize access="isAnonymous()">
    <jsp:include page="anonymous_header_news.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="authenticated_header_news.jsp"/>
</sec:authorize>
<div id="content">
    <div class="content_center_outer_out">
        <div class="content_center_outer">
            <div id="right_sidebar">
                <jsp:include page="delivery.jsp"/>
                <jsp:include page="tv_programm.jsp"/>
                <jsp:include page="banners.jsp"/>

            </div>
            <!-- /right_sidebar -->

            <div class="main_content main_content_center top bottom">
                <div id="left_column">
                    <div id="navig_menu" style="padding-bottom: 10px;">
                        <div class="profile_right_column_header_elements">
                            <div class="list_element_center">
                                <ul class="fl_left">
                                    <c:choose>
                                        <c:when test="${newsSection == -1}">
                                            <li>
                                                <a class="select" href="/news">Все</a>
                                            </li>
                                            <li>
                                                <a href="/news?partId=2">Днепропетровск</a>
                                            </li>
                                            <li>
                                                <a href="/news?partId=3">Киев</a>
                                            </li>
                                        </c:when>
                                        <c:when test="${newsSection == 2}">
                                            <li>
                                                <a href="/news">Все</a>
                                            </li>
                                            <li>
                                                <a class="select" href="/news?partId=2">Днепропетровск</a>
                                            </li>
                                            <li>
                                                <a href="/news?partId=3">Киев</a>
                                            </li>
                                        </c:when>
                                        <c:when test="${newsSection == 3}">
                                            <li>
                                                <a href="/news">Все</a>
                                            </li>
                                            <li>
                                                <a href="/news?partId=2">Днепропетровск</a>
                                            </li>
                                            <li>
                                                <a class="select" href="/news?partId=3">Киев</a>
                                            </li>
                                        </c:when>
                                    </c:choose>

                                </ul>
                            </div>
                        </div>

                        <c:forEach var="piece" items="${news}" varStatus="status">
                            <div class="topic">
                                <a href="/news" class="group">${piece.getPartName()}</a>
                                <a href="/news/${piece.getId()}" class="group_element">${piece.getTitle()}</a>

                                <div class="topic_block">
                                    <a href="/news/${piece.getId()}"><img src="/image/getimage/6/${piece.getImageId()}"
                                                                          alt=""/></a>

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
                                    <a hhref="javascript:void(0);" onClick="show_local_message(this, 'Добавлено');"
                                       class="bookmarks">Добавить
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
                        <jsp:include page="pagination.jsp"/>
                    </div>
                    <!-- /navig_menu -->
                </div>
                <!-- /left_column -->
            </div>
            <!-- /main_content -->
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</div>
<!-- /footer_out -->
</body>
</html>