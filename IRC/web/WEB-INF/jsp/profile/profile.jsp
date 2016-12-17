<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="javax.servlet.jsp.jstl.core.LoopTagStatus" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content="Devoler"/>
    <meta name="copyright" content="Devoler"/>
    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.5.1.min.js"/>"></script>
    <script type="text/javascript">

        function deleteFriend(id) {
            $.ajax({
                url:'<c:url value="/delete_friend"/>',
                dataType:'text',
                data:{ friend_id:id },
                success:function (response) {
                    if (response == 'Ok') {
                        location.reload();
                    }
                }
            })
        }

        function addToFriend(id) {
            $.ajax({
                url:'<c:url value="/add_to_friend"/>',
                dataType:'text',
                data:{request_friend_id:id },
                success:function (response) {
                    if (response == 'Ok') {
                        location.reload();
                    }
                }
            })
        }

        function showSendMessage() {
            $("#messsage_form").css('display', 'block');
        }

        function sendMessage(id) {
            $("#messsage_form").css('display', 'none');
            $.getJSON("/profile/send_message", { user_id:id, message:$('#message').val()},
                    function (response) {
                        if (response.Ok) {
                            $("#messsage_form").css('display', 'none');
                        }
                    }
            );
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
${user.name}

<c:if test='${isRequest==true}'>
    Заявка подана
</c:if>

<c:if test='${isFriend==false}'>
    <c:if test='${isRequest==false}'>
        <a href='<c:url value="?add_to_friend="/>${user.userId}'> Добавить в друзья</a>
    </c:if>
</c:if>
<c:if test='${isFriend==true}'>
    <a href='<c:url value="?delete_friend="/>${user.userId}'> Удалить из друзей</a>
</c:if>

<button type="button" onclick="showSendMessage()">Написать сообщение
</button>
<div id="messsage_form" style="display: none;">
    <textarea rows="5" cols="10" id="message"></textarea>
    <br>
    <button type="button" onclick="sendMessage(${user.userId})">Написать сообщение
    </button>

</div>


</body>
</html>