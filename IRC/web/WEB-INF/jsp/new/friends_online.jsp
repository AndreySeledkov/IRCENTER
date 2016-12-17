<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
    long unReadMessage = (Long) request.getSession().getAttribute("unReadMessage");
    long requestFriend = (Long) request.getSession().getAttribute("requestFriend");


%>
<!DOCTYPE html>
<head>
    <title>Друзья on-line</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="icon" href="/static/img/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.MultiFile.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/jquery.countdown.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/widget.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/smoothDivScroll.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/common.js"/>"></script>
    <script src="<c:url value="/static/js/jquery.imgareaselect.pack.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/jquery.upload.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/common.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/right_side_update.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/social.js"/>" type="text/javascript"></script>

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

<%@ include file="header.jsp" %>

<div id="content">
    <div class="profile_content">
        <%@ include file="left_side_bar.jsp" %>

        <%@ include file="friends_online_form.jsp" %>
    </div>
</div>

<div id="footer_out">
    <div id="footer">


    </div>
    <!-- /footer -->
</div>
<!-- /footer_out -->
</body>
</html>