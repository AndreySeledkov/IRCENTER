<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    long unReadMessage = (Long) request.getSession().getAttribute("unReadMessage");
    long requestFriend = (Long) request.getSession().getAttribute("requestFriend");
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Текстовые свидетельства</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link type="image/x-icon" rel="shortcut icon" href="/static/img/favicon.ico">
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/forum.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>
    <script src="/static/js/openapi.js" type="text/javascript"></script>
    <script src="/static/js/all.js" type="text/javascript"></script>

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
            <c:forEach items="${textEvidenceList}" var="textEvidence" varStatus="varStatuc">
                <div class="topic">
                    <a href="/text_evidence?id=${textEvidence.getId()}"
                       class="group_element">${textEvidence.getTitle()}</a>

                    <div class="topic_block">
                        <a href="/text_evidence?id=${textEvidence.getId()}"><img
                                src="/image/getimage/6/${textEvidence.getDefaultImage()}"/></a>

                        <p>
                                ${textEvidence.getText()}
                        </p>
                    </div>
                    <!-- /topic_block -->
                </div>
                <!-- /topic -->

                <div class="read_next">
                    <a href="/text_evidence?id=${textEvidence.getId()}" class="read_next_button">Читать дальше &gt;</a>

                    <div class="fl_left ml10" style="font-size: 11px;">
                        <span style="color:#515152;line-height: 26px;">Просмотров:</span>
                        <span style="margin-left: 5px;color: #000000;">${textEvidence.getCountViews()}</span>
                    </div>
                    <div class="read_next_right">
                        <a hhref="javascript:void(0);" onClick="show_local_message(this, 'Добавлено');"
                           class="bookmarks">Добавить
                            в избранное</a>
                        <a href="javascript:void(0);" class="twetter">&nbsp;</a>
                        <a href="javascript:void(0);" class="facebook">&nbsp;</a>
                            ${textEvidence.getCreateDate()}
                    </div>
                    <!-- /read_next_right -->
                </div>
                <!-- /read_next -->
            </c:forEach>

            <c:if test="${evidenceTextCount>10}">
                <div class="mt13"><jsp:include page="../new/pagination.jsp"/></div>
            </c:if>
                </div><!-- /left_column -->
            </div><!-- /main_content -->
        </div>
    </div>
</div>


<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>
