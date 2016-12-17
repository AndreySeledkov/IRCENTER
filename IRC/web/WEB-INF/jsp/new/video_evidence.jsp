<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Видео свидетельства</title>
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
    <jsp:include page="anonymous_header_svid.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="authenticated_header_svid.jsp"/>
</sec:authorize>


<div id="content_video_out">
    <div id="content_video">

        <div id="video_content_main">
            <div class="video_box">
                <c:if test="${link!=null}">
                    ${link.getLink()}
                </c:if>
                <c:if test="${link==null}">
                    ${videoEvidenceList.get(0).getLink()}
                </c:if>
            </div>
        </div>

        <div id="video_right_sidebar">

            <div class="widget mb13">
                <jsp:include page="timeout_left.jsp"/>
                <!-- /timeout_left -->
            </div>
            <jsp:include page="banners.jsp"/>

        </div>

    </div>
    <!-- /content_video -->
</div>
<!-- /content_video_out -->

<div id="content">
    <div class="video_main_content">
        <div class="arch_of_minist">Свидетельства</div>
        <div class="arrow_to_down">&nbsp;</div>

        <div class="video_elements pt20">
            <c:forEach items="${videoEvidenceList}" var="videoEvidence" varStatus="varStatuc">
                <a href="/video_evidence?id=${videoEvidence.getId()}&page=${page}" class="video_element">
                    <div class="img_abs">
                        <img width="165" data-thumb="http://img.youtube.com/vi/${videoEvidence.getYoutubeId()}/0.jpg"
                             alt="Значок видео"
                             src="http://img.youtube.com/vi/${videoEvidence.getYoutubeId()}/0.jpg"
                             data-group-key="thumb-group-0">
                    </div>
                    <span class="title">${videoEvidence.getTitle()}</span>
                </a>
            </c:forEach>

        </div>
        <!-- /video_elements -->

        <c:if test="${evidenceVideoCount>10}">
            <jsp:include page="../new/pagination.jsp"/>
        </c:if>

    </div>
</div>

<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>