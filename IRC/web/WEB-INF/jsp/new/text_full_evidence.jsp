<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Свидетельство</title>
    <link type="image/x-icon" rel="shortcut icon" href="/static/img/favicon.ico">
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/news.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/redactor.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/jcarousel.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/redactor.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>
    <script src="/static/js/jMyCarousel.js" type="text/javascript"></script>
    <script src="/static/js/jquery.lightbox.js" type="text/javascript"></script>

    <script type="text/javascript">

        $(function () {

        });
        $(function () {
            $("#jMyCarousel").jMyCarousel({
                visible:'100%'
            });
            $('#jMyCarousel a').lightBox();
        });
        //        $(document).ready(function(){
        //            $(this).countdown(new Date(2012, 3, 20, 23, 59, 59));
        //            $('#comment_content').redactor();
        //
        //        });
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

<div id="content">
    <div class="content_center_outer_out">
        <div class="content_center_outer">
            <div id="right_sidebar">
                <jsp:include page="delivery.jsp"/>
                <jsp:include page="tv_programm.jsp"/>
                <jsp:include page="banners.jsp"/>

            </div><!-- /right_sidebar -->

            <div class="main_content main_content_center top bottom">
                <div id="left_column">
        <div class="news_block">
            <div class="topic_news">
                <span class="group_element">${textEvidence.getTitle()}</span>
            </div>
                ${textEvidence.getText()}
            </div>
        </div>
                </div><!-- /left_column -->
            </div><!-- /main_content -->
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>