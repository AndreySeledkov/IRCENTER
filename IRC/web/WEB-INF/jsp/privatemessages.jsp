<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Информация о пользователе</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content="Devoler"/>
    <meta name="copyright" content="Devoler"/>

    <%@ include file="urls.jspf" %>
    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.5.1.min.js"/>"></script>
    <script type="text/javascript">

        function changePMSource() {
            var pmsource = parseInt($('#pm-source-choser option:selected').val());
            if (pmsource == 1) {
                $(window.location).attr('href', '<c:url value="/privatemessages/in/"/>');
            } else if (pmsource == 2) {
                $(window.location).attr('href', '<c:url value="/privatemessages/out/"/>');
            } else {
                $(window.location).attr('href', '<c:url value="/privatemessages/all/"/>');
            }
        }

        function deletePrivateMessage(messageId, confirmed) {
            if (confirmed) {
                $.ajax({
                    url:'<c:url value="/privatemessages/deletePrivateMessage"/>',
                    dataType:'text',
                    data:{ messageId:messageId },
                    success:function (response) {
                        if (response == 'Ok') {
                            location.reload();
                        }
                    }
                })
            }
        }
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
<div id="fix_min_width">
    <div id="wrap">

        <div id="wrap_body">

            <div id="right_sidebar">

                <div class="r-star block_themes" style="margin-bottom: 22px;">
                    <div class="cn tr"></div>
                    <div class="cn tl"></div>
                    <h1>Личные сообщения (<%=request.getAttribute("privateMessagesCount")%>)</h1>

                    <!--<div id="pagination_" class="pagination_" style="margin-left: 16px;"></div>
                    <p>
                        <label for="pm-source-choser">Показать</label>
                        <select onchange="changePMSource();" id="pm-source-choser">
                            ${selectoptions}
                        </select>
                    </p>
                    -->

                    <div class="clear"></div>
                    <div class="content themes_wrap">
                        <c:forEach var="privateMessage" items="${privateMessages}" varStatus="i">
                            <div class="r_star_white r_star_white_hover">
                                <div class="cn_white tl_white"></div>
                                <div class="cn_white tr_white"></div>
                                <div class="content_white one_mes">
                                    <div class="wrap_mes_form">
                                        <div class="wrap_col_mess">
                                            <div class="del_person">
                                                <a title="Удалить" href="#"
                                                   onclick="deletePrivateMessage(${privateMessage.messageId}, window.confirm('Удалить личное сообщение?')); return false;">Удалить</a>
                                            </div>
                                        </div>
                                        <div class="float_helper">
                                            <p>
                                                <fmt:formatDate value="${privateMessage.created}" pattern="dd.MM.yy"/> в
                                                <fmt:formatDate
                                                        value="${privateMessage.created}" pattern="HH:mm"/>
                                                <br>
                                                <c:if test="${privateMessage.fromUserName == 'Без регистрации'}">
                                                    <a href="<c:url value='/outercabinet/${privateMessage.fromUserId}/'/>"
                                                       style="border-bottom: 1px solid #a9a9a9; color: #a9a9a9; text-decoration: none;"
                                                       title="Автор">
                                                        <nobr>${privateMessage.fromUserName}</nobr>
                                                    </a>
                                                </c:if>
                                                <c:if test="${privateMessage.fromUserName != 'Без регистрации'}">
                                                    <a href="<c:url value='/outercabinet/${privateMessage.fromUserId}/'/>"
                                                       title="Автор">
                                                        <nobr>${privateMessage.fromUserName}</nobr>
                                                    </a>
                                                </c:if>
                                            </p>
                                                <%--<a style="cursor: hand;" href="<c:url value="/privatemessage/${privateMessage.messageId}"/>" title="">--%>
                                            <div class="mess_text" style="cursor: pointer; cursor: hand;"
                                                 onclick="window.location.href='<c:url value="/privatemessage/${privateMessage.messageId}"/>'">
                                                <c:if test="${!privateMessage.readed}"><b></c:if>
                                                <c:out value="${privateMessage.title}"/>
                                                <c:if test="${!privateMessage.readed}"></b></c:if>
                                            </div>
                                                <%--</a>--%>
                                        </div>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                                <div class="cn_white bl_white"></div>
                                <div class="cn_white br_white"></div>
                            </div>
                        </c:forEach>
                    </div>
                    <div id="pagination" class="pagination"></div>
                    <div class="clear"></div>
                    <div class="cn br"></div>
                    <div class="cn bl"></div>
                </div>
            </div>
        </div>
        <div class="push"></div>
    </div>
</div>
</body>
</html>