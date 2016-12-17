<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    long unReadMessage = (Long) request.getSession().getAttribute("unReadMessage");
    long requestFriend = (Long) request.getSession().getAttribute("requestFriend");
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Форум</title>
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
    <jsp:include page="anonymous_header_forum.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="authenticated_header_forum.jsp"/>
</sec:authorize>


<div id="content">
    <div class="clear_fix">&nbsp;</div>
    <div class="main_content top bottom">

        <c:forEach items="${mainPartList}" var="mainPart" varStatus="i">
            <div class="forum_element ma10">
                <div class="forum_element_header">${mainPart.getName()}</div>
                <table cellspacing="0" cellpadding="0" class="forum_table">
                    <tr class="forum_table_header">
                        <td colspan="2" class="pl15">Форум</td>
                        <td>Темы</td>
                        <td>Сообщениия</td>
                        <td>Последнее сообщение</td>
                    </tr>
                    <c:forEach items="${mainPart.getParts()}" var="part" varStatus="rowCounter">
                        <c:if test="${rowCounter.count % 2 == 0}">
                            <tr class="forum_table_element blue">
                                <td class="forum_column_1"><img src="/static/img/forum_topic_icon.png" alt=""/></td>
                                <td class="forum_column_2">
                                    <a href='<c:url value="/forum/${part.id}"/>'
                                       class="forum_topic_element_header">${part.getName()}</a>

                                </td>
                                <td class="forum_column_3">${part.getThemeCount()}</td>
                                <td class="forum_column_4">${part.getMessagesCount()}</td>
                                <td class="forum_column_5">
                                    <c:forEach items="${themesLastEdit}" var="themeLastEdit">
                                        <c:if test="${themeLastEdit.getPartId()==part.getId()}">
                                            <a href="/forum/${themeLastEdit.id}/messages">${themeLastEdit.getName()}</a>

                                            <div>
                                                <fmt:formatDate value="${themeLastEdit.getLastEdit()}"
                                                                pattern="dd.MM.yy"/> в
                                                <fmt:formatDate
                                                        value="${themeLastEdit.getLastEdit()}" pattern="HH:mm"/>

                                                -${themeLastEdit.getUserProfile().getLoginName()}
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${rowCounter.count % 2 != 0}">
                            <tr class="forum_table_element white">
                                <td class="forum_column_1"><img src="/static/img/forum_topic_icon.png" alt=""/></td>
                                <td class="forum_column_2">
                                    <a href='<c:url value="/forum/${part.id}"/>'
                                       class="forum_topic_element_header">${part.getName()}</a>

                                    <div></div>
                                </td>
                                <td class="forum_column_3">${part.getThemeCount()}</td>
                                <td class="forum_column_4">${part.getMessagesCount()}</td>
                                <td class="forum_column_5">
                                    <c:forEach items="${themesLastEdit}" var="themeLastEdit">
                                        <c:if test="${themeLastEdit.getPartId()==part.getId()}">
                                            <a href="/forum/${themeLastEdit.id}/messages">${themeLastEdit.getName()}</a>

                                            <div>
                                                <fmt:formatDate value="${themeLastEdit.getLastEdit()}"
                                                                pattern="dd.MM.yy"/> в
                                                <fmt:formatDate
                                                        value="${themeLastEdit.getLastEdit()}" pattern="HH:mm"/>
                                                -${themeLastEdit.getUserProfile().getLoginName()}
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </div>
        </c:forEach>


        <div class="forum_footer">
            <div class="forum_footer_element">
                <span class="fl_right">Тем: <b>${themesCount}</b></span>
            </div>
            <div class="forum_footer_element">
                <span class="fl_right">Сообщений: <b>${messagesCount}</b></span>
            </div>
        </div>
    </div>
    <!-- /main_content -->
</div>

<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>
