<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    long unReadMessage = (Long) request.getSession().getAttribute("unReadMessage");
    long requestFriend = (Long) request.getSession().getAttribute("requestFriend");


%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Тайны Духовного Мира</title>
    <link type="image/x-icon" rel="shortcut icon" href="/static/img/favicon.ico">
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/video.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            setInterval(function(){update_timer(new Date(${onlineLeft}));}, 1000/30);
        });
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
    <jsp:include page="anonymous_header_tv.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="authenticated_header_tv.jsp"/>
</sec:authorize>


<div id="content_video_out">
    <div id="content_video">
        <div id="video_content_main">
            <div class="video_box">

                <c:if test="${link!=null}">
                    ${link}

                </c:if>
                <c:if test="${link==null}">
                    ${secretSpiritualWorldList.get(0).getLink()}

                </c:if>

            </div>


        </div>

        <div id="video_right_sidebar">

            <div class="widget mb13">
                <jsp:include page="timeout_left.jsp"/>
            </div>
            <jsp:include page="tv_programm.jsp"/>
            <!-- /video_right_sidebar -->
        </div>
        <%--<!-- /video_right_sidebar -->--%>
    </div>
    <!-- /content_video -->
</div>
<!-- /content_video_out -->

<div id="content">
    <div class="video_main_content">
        <div class="arch_of_minist">Тайны Духовного Мира</div>
        <div class="arrow_to_down">&nbsp;</div>
        <div class="video_bottom_text">
            <img width="300" style="float:right;"
                 data-thumb="http://img.youtube.com/vi/${secretSpiritualWorld.getYoutubeId()}/0.jpg"
                 alt="Значок видео"
                 src="http://img.youtube.com/vi/${secretSpiritualWorld.getYoutubeId()}/0.jpg"
                 data-group-key="thumb-group-0">

            ${secretSpiritualWorld.getText()}
        </div>
        <div class="other_videos">
            Все видео
        </div>

        <c:forEach items="${secretSpiritualWorldList}" var="varSecretSpiritualWorld">
            <div class="topic m0">
                <a href="/secret_spiritual_world?link_id=${varSecretSpiritualWorld.getId()}"
                   class="group_element">${varSecretSpiritualWorld.getTitle()}</a>

                <div class="topic_block">
                    <a href="/secret_spiritual_world?page=${page}&link_id=${varSecretSpiritualWorld.getId()}">
                        <img width="165" style="float:right;"
                             data-thumb="http://img.youtube.com/vi/${varSecretSpiritualWorld.getYoutubeId()}/0.jpg"
                             alt="Значок видео"
                             src="http://img.youtube.com/vi/${varSecretSpiritualWorld.getYoutubeId()}/0.jpg"
                             data-group-key="thumb-group-0">
                    </a>

                    <p>${varSecretSpiritualWorld.getText()}</p>
                </div>
                <!-- /topic_block -->
            </div>
            <div class="read_next mb20">
                <div class="fl_left ml10" style="font-size: 11px;">
                    <span style="color:#515152;line-height: 26px;">Просмотров:</span>
                    <span style="margin-left: 5px;color: #000000;">${secretSpiritualWorld.getCountViews()}</span>
                </div>
                <div class="read_next_right">
                    <a hhref="javascript:void(0);" onclick="show_local_message(this, 'Добавлено');" class="bookmarks">Добавить
                        в избранное</a>
                    <a href="javscript:void(0)" class="twetter"> </a>
                    <a href="javascript:void(0)" class="facebook"> </a>

                        ${secretSpiritualWorld.getCreatedDate()}

                </div>
                <!-- /read_next_right -->
            </div>
        </c:forEach>

        <c:if test="${secretSpiritualWorldCount>10}">
            <jsp:include page="pagination.jsp"/>
        </c:if>

    </div>
</div>

<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>