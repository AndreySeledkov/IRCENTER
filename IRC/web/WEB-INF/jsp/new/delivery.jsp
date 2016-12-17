<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        function sendDelivery() {
            $.post("/feedback/delivery", { mail:$('#mail_delivery').val()},
                    function (response) {
                        if (response.complete) {
                            show_message('Отправлено', 2);
                            placeholder_imitator();
                        }
                    }, 'json')
        }
    </script>
</head>
<body>
<div class="widget distribution mb13">
    <span>Введите свой email:</span>

    <span class="text"><input type="text" id="mail_delivery" name="mail" title="Подписаться на рассылку"/></span>
    <input type="button" value="" onclick="sendDelivery()" class="button"/>
</div>
</body>
</html>
