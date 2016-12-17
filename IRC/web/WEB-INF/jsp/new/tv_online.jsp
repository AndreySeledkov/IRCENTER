<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    long unReadMessage = (Long) request.getSession().getAttribute("unReadMessage");
    long requestFriend = (Long) request.getSession().getAttribute("requestFriend");
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Онлайн телевидение</title>
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

            if ((navigator.userAgent.indexOf('iPhone') != -1) || (navigator.userAgent.indexOf('iPod') != -1) || (navigator.userAgent.indexOf('iPad') != -1)) {
                $("#i_player").css('display', 'block');
            } else {
                $("#simple_player").css('display', 'block');
            }
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


                <object id="simple_player" style="display:none;" width="640" height="400" name="player"
                        classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000">

                    <param value="http://vo.org.ua/index.php?option=com_webplayer&view=player" name="movie">
                    <param value="opaque" name="wmode">
                    <param value="true" name="allowfullscreen">
                    <param value="always" name="allowscriptaccess">
                    <param value="baseJ=http://vo.org.ua/&category=live&autoStart=true&skinMode=static&playListAutoStart=false&playListOpen=false&playListRandom=false&buffer=15&volumeLevel=100&stretch=fill&controlBar=true&playPauseDock=true&progressBar=true&timerDock=true&shareDock=false&volumeDock=true&fullScreenDock=true&playDock=true&playList=false"
                           name="flashvars">
                    <object width="640" height="400" data="http://vo.org.ua/index.php?option=com_webplayer&view=player"
                            type="application/x-shockwave-flash">
                        <param value="http://vo.org.ua/index.php?option=com_webplayer&view=player" name="movie">
                        <param value="opaque" name="wmode">
                        <param value="true" name="allowfullscreen">
                        <param value="always" name="allowscriptaccess">
                        <param value="baseJ=http://vo.org.ua/&category=live&autoStart=true&skinMode=static&playListAutoStart=false&playListOpen=false&playListRandom=false&buffer=15&volumeLevel=100&stretch=fill&controlBar=true&playPauseDock=true&progressBar=true&timerDock=true&shareDock=false&volumeDock=true&fullScreenDock=true&playDock=true&playList=false"
                               name="flashvars">
                    </object>
                </object>

                <div id="i_player" style="display: none;">
                    <p><a href="http://83.142.233.146:1935/live/mpegts.stream/playlist.m3u8"><img
                            style="display: block; margin-left: auto; margin-right: auto;"
                            src="/static/img/movies.png"/></a></p>
                </div>
            </div>
        </div>

        <div id="video_right_sidebar">
            <div class="widget mb13">
                <jsp:include page="timeout_left.jsp"/>
            </div>

            <jsp:include page="banners.jsp"/>
        </div>
        <%--<!-- /video_right_sidebar -->--%>
    </div>
    <!-- /content_video -->
</div>
<!-- /content_video_out -->

<div id="content">
    <div class="video_main_content">
        <div class="arch_of_minist">Свидетельства он-лайн</div>
        <div class="arrow_to_down">&nbsp;</div>

        <div class="svids">

            <c:forEach items="${onlineEvidenceList}" var="evidenceOnline">
                <div class="svid white_bg">
                        <%--<a href="" class="avatar">--%>
                    <img src="/static/img/svid_ava.png" alt="">
                        <%--</a>--%>

                    <div class="svid_body">
                        <div class="svid_body_in">
                            <span class="svid_name">${evidenceOnline.getTitle()}</span>

                            <div class="svid_message">
                                    ${evidenceOnline.getText()}
                            </div>
                        </div>
                        <div class="svid_body_bottom">
                            <div class="svid_body_bottom_in">
                                <span class="svid_date"> <fmt:formatDate type="both"
                                                                         dateStyle="short" timeStyle="short"
                                                                         value="${evidenceOnline.getCreateDate()}"/></span>

                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>


        <c:if test="${evidenceOnlineCount>10}">
            <jsp:include page="../new/pagination.jsp"/>
        </c:if>


    </div>
</div>

<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>