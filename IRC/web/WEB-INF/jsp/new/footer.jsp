<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        function sendSupport() {
            var m = $('#mail').val();
            var th = $('#theme').val();
            var inf = $('#info').val();
            if (m == '' || th == '' || inf == '' || inf.length > 300) {
                hideModal();
                return;
            }
            $.post("/feedback/leave_feedback", { firstName:$('#first_name').val(), lastName:$('#last_name').val(), mail:m, theme:th, info:inf},
                    function (response) {
                        if (response.complete) {
                            hideModal();
                        }
                    }, 'json')
        }
    </script>
</head>
<body>
<div id="footer_out">
    <a class="webim_button" href="" rel="webim"><img src="http://regenerationumla.pro-service.webim.ru/webim/button.php" border="0"/></a>

    <script type="text/javascript">
        webim = {
            accountName: "regenerationumla",
            domain: "regenerationumla.pro-service.webim.ru"
        };
        (function () {
            var s = document.createElement("script");
            s.type = "text/javascript";
            s.src = "http://regenerationumla.pro-service.webim.ru/webim/js/button.js";
            document.getElementsByTagName("head")[0].appendChild(s);
        })();
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
    <%--<a id="consult_online" href="" onMouseOver="$(this).animate({'left': '0px'}, 300);" onMouseOut="$(this).animate({'left': '-232px'}, 300);">&nbsp;</a>--%>
    <div id="footer">
        <div class="fl_left">Copyright © 1998-2012 vo.org.ua. Все права защищены.</div>
        <div class="fl_right">
            <ul>
                <li><a href="/">Главная</a></li>
                <li><a href="javascript:void(0)">О нас</a></li>
                <li><a href="javascript:void(0);" onClick="createModal('/feedback');">Обратная связь</a></li>
            </ul>
        </div>
    </div>
    <!-- /footer -->
</div>
</body>
</html>
