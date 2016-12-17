<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    long unReadMessage = (Long) request.getSession().getAttribute("unReadMessage");
    long requestFriend = (Long) request.getSession().getAttribute("requestFriend");
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Мои сообщения</title>
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
    <script src="<c:url value="/static/js/right_side_update.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/social.js"/>" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
</head>
<body>

<jsp:include page="../../new/authenticated_header.jsp"/>

<div id="content">
    <div class="main_content profile_content top bottom">
        <jsp:include page="profile_left_sidebar.jsp"/>

        <div class="right_column_main_content">
            <jsp:include page="profile_info.jsp"/>
            <div class="profile_media_block">
                <jsp:include page="menu_tab.jsp"/>

            </div>
            <jsp:include page="bottom_sidebar.jsp"/>

        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>