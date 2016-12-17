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

        function deleteMessage(messageId) {
            $.ajax({
                url:'<c:url value="/mail/delete_message"/>',
                dataType:'text',
                data:{ message_id:messageId },
                success:function (response) {
                    if (response == 'Ok') {
                        location.reload();
                    }
                }
            })
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

    <a href='<c:url value="?section=inbox"/>'>
        Входящие
    </a>
    <br>

    <a href='<c:url value="?section=outbox"/>' onclick="">
        Отправленные
    </a>
    <br>

    <a href='<c:url value="?section=spam"/>'>
        Спам
    </a>
    <br>
    <c:forEach var="privateMessage" items="${privateMessages}" varStatus="i">
        ${privateMessage.created}
        <br>
        ${privateMessage.body}
        <a href="#" onclick="deleteMessage(${privateMessage.messageId})">Удалить</a>
        <br>
        <br>
    </c:forEach>
</div>
</body>
</html>