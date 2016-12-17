<%@ page import="org.ircenter.server.service.user.UserHelper" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Форум</title>
    <link rel="icon" href="/static/img/favicon.png" type="image/x-icon"/>
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
        <div class="forum_navig"><a href="/forum" class="forum_navig_link">Форум</a> <span class="forum_arrow_to_right">&nbsp;</span>
            <a href="/forum/${part.id}" class="forum_navig_link">${part.name}</a> <span class="forum_arrow_to_right">&nbsp;</span>
            <span class="forum_navig_last">${theme.name}</span></div>
        <div class="forum_element ma10">
            <div class="forum_element_top">

                <c:if test="${messageCount>10}">
                    <jsp:include page="pagination.jsp"/>
                </c:if>
                <sec:authorize access="isAnonymous()">
                    <div class="fl_right">
                        Чтобы начать тему нужно <a href="/auth">войти</a> или <a href="/signup">зарегистрироваться</a>
                    </div>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <div class="fl_right">
                        <a href="/forum/${theme.id}/forum_new_message" class="new_tem_button">Ответить</a>
                    </div>
                </sec:authorize>

            </div>
            <table cellspacing="0" cellpadding="0" class="forum_table">
                <c:forEach items="${userforumlist}" var="user" varStatus="rowCounter">
                    <c:if test="${rowCounter.count % 2 == 0}">
                        <tr>
                            <td rowspan="3" class="forum_topic_element_left_column">
                                <a href="javascript:void(0)"><b>${user.getName()}</b></a>
                                <img src="/img/${user.id}/${user.id}.jpg" alt=" "/>
                                <b>Пользователь</b>

                            </td>
                            <td class="forum_topic_element_right_column_top">
                                <fmt:formatDate value="${user.getMessagePrint()}"
                                                pattern="dd.MM.yy"/> в
                                <fmt:formatDate
                                        value="${user.getMessagePrint()}" pattern="HH:mm"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="forum_topic_element_right_column_middle silver">
                                    ${user.message}

                            </td>
                        </tr>
                        <tr>
                                <%--<td class="forum_topic_element_right_column_bottom silver">Отредактировано (03.04.2012 14:25:47)</td>--%>
                        </tr>
                    </c:if>
                    <c:if test="${rowCounter.count % 2 != 0}">
                        <tr>
                            <td rowspan="3" class="forum_topic_element_left_column">
                                <a href="javascript:void(0)"><b>${user.getName()}</b></a>
                                <img src="/img/${user.id}/${user.id}.jpg" alt=" "/>
                                <b>Пользователь</b>

                            </td>
                            <td class="forum_topic_element_right_column_top">

                                <fmt:formatDate value="${user.getMessagePrint()}"
                                                pattern="dd.MM.yy"/> в
                                <fmt:formatDate
                                        value="${user.getMessagePrint()}" pattern="HH:mm"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="forum_topic_element_right_column_middle white">
                                    ${user.message}

                            </td>
                        </tr>
                        <tr>
                            <td class="forum_topic_element_right_column_bottom white"></td>
                        </tr>
                    </c:if>
                </c:forEach>

            </table>
            <%--<div class="forum_element_header">Сообщений с 1 по 25 из 100</div>--%>
            <div class="forum_element_foot">
                <c:if test="${messageCount>10}">
                    <jsp:include page="pagination.jsp"/>
                </c:if>

                <sec:authorize access="isAnonymous()">
                    <div class="fl_right">
                        Чтобы начать тему нужно <a href="/auth">войти</a> или <a href="/signup">зарегистрироваться</a>
                    </div>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <div class="fl_right">
                        <a href="/forum/${theme.id}/forum_new_message" class="new_tem_button">Ответить</a>
                    </div>
                </sec:authorize>

            </div>
        </div>

        <div class="forum_navig"><a href="/forum" class="forum_navig_link">Форум</a> <span class="forum_arrow_to_right">&nbsp;</span>
            <a href="/forum/${part.id}" class="forum_navig_link">${part.name}</a> <span class="forum_arrow_to_right">&nbsp;</span>
            <span class="forum_navig_last">${theme.name}</span></div>

    </div>
    <!-- /main_content -->
</div>


<jsp:include page="footer.jsp"/>
</body>
</html>