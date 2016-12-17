<%@ page import="org.ircenter.server.lucene.IndexType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Главная</title>
    <link rel="icon" href="/static/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>
    <script type="text/javascript">
        function changeSection(section) {
            var queryParameters = {}, queryString = location.search.substring(1),
                    re = /([^&=]+)=([^&]*)/g, m;

            while (m = re.exec(queryString)) {
                queryParameters[decodeURIComponent(m[1])] = decodeURIComponent(m[2]);
            }

            queryParameters['s'] = section;
            queryParameters['p'] = '1';
            location.search = $.param(queryParameters);
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
    <jsp:include page="anonymous_header.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="authenticated_header.jsp"/>
</sec:authorize>

<div id="content">
    <div class="clear_fix"></div>
    <div class="main_content top bottom">
        <h2 class="m10">Результат поиска "${searchWord}":</h2>

        <div class="profile_right_column_header_elements m13">
            <div class="list_element_center">
                <ul class="fl_left">
                    <li>
                        <c:choose>
                            <c:when test="${section == 0}">
                                <a href="javascript:void(0)" class="select" onclick="changeSection(0)">Новости</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="changeSection(0)">Новости</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                    <li>
                        <c:choose>
                            <c:when test="${section == 1}">
                                <a href="javascript:void(0)" onclick="changeSection(1)" class="select">Видео</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="changeSection(1)">Видео</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
        </div>
        <div class="video_elements">
            <c:if test="${section == 0}">
                <c:if test="${empty news}">
                    <h2 class="m10">Сожалеем, поиск не дал результатов в разделе Новости</h2>
                </c:if>
            </c:if>
            <c:forEach var="item" items="${videos}">
                <a href="${item.getLink()}${item.getId()}" class="video_element">
                    <div class="img_abs">
                        <img width="180px" height="140px" style="float:right;"
                             data-thumb="http://img.youtube.com/vi/${item.getDefaultImage()}/0.jpg"
                             alt="Значок видео"
                             src="http://img.youtube.com/vi/${item.getDefaultImage()}/0.jpg"
                             data-group-key="thumb-group-0">
                    </div>
                    <span class="title">${item.getTitle()}</span>
                </a>
            </c:forEach>
        </div>
        <div class="archive_block_search m10">
            <c:if test="${section == 1}">
                <c:if test="${empty videos}">
                    <h2 class="m10">Сожалеем, поиск не дал результатов в разделе Видео</h2>
                </c:if>
            </c:if>
            <c:forEach var="item" items="${news}">
                <div class="all_news_element">
                    <a href="/news/${item.getId()}" class="all_news_img">
                        <img src="/image/getimage/4/${item.getImageId()}" alt="" width="90px">
                    </a>

                    <div class="topic topic_all_news">
                        <a href="/news" class="group">
                                ${item.getPartName()}
                            <a href="/news/${item.getId()}" class="group_element">${item.getTitle()}</a>
                            <!-- /topic_block -->
                        </a>
                    </div>
                    <div class="read_next_all_news">
                        <a href="/news/${item.getId()}" class="read_next_button">Читать дальше ></a>

                        <div class="read_next_right">
                                ${item.getDate()}
                        </div>
                        <!-- /read_next_right -->
                    </div>
                </div>
            </c:forEach>
        </div>
        <jsp:include page="pagination.jsp"/>
    </div>
    <!-- /main_content -->
</div>
<!-- /content -->

<jsp:include page="footer.jsp"/>
</body>
</html>