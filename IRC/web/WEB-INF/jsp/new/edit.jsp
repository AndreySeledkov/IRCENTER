<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    long unReadMessage = (Long) request.getSession().getAttribute("unReadMessage");
    long requestFriend = (Long) request.getSession().getAttribute("requestFriend");
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Редактирование профиля</title>
    <link rel="icon" href="/static/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/jquery.imgareaselect.pack.js" type="text/javascript"></script>
    <script src="/static/js/jquery.upload.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>
    <script src="/static/js/profile_edit.js" type="text/javascript"></script>
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
        <!-- /profile_left_sidebar -->

        <%@ include file="edit_profile_entity.jsp" %>
    </div>
</div>

<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>