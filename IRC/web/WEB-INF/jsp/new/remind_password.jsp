<%@ page import="org.ircenter.server.service.user.UserHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%--%>
<%--Object captchaValid = request.getSession().getAttribute("captchaValid");--%>
<%--%>--%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Восстановление пароля</title>
    <link rel="icon" href="/static/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/reg.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>
    <script src="<c:url value="/static/js/right_side_update.js"/>" type="text/javascript"></script>
    <script src="/static/js/openapi.js" type="text/javascript"></script>
    <script src="/static/js/all.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $(this).countdown(new Date(2012, 2, 10, 23, 59, 59));
        });

        function rememberPassword() {
            $.post('/signup/rememberpassword', {recaptcha_challenge_field:$('#recaptcha_challenge_field').val(), recaptcha_response_field:$('#recaptcha_response_field').val(), login:$('#rememberusername').val()
            }, function (response) {
                if (response.available) {
                    $("#auth_block").css('display', 'none');
                    $("#m10").css('display', 'block');
                } else {
                    Recaptcha.reload();
                    $('#errorMessage').html(response.errorMessage);
                }
            }, 'json');
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
<jsp:include page="anonymous_header.jsp"/>


<div id="content">

    <div class="clear_fix">&nbsp;</div>

    <div class="main_content top bottom">
        <div class="reg_block">
            <div class="overflow ">
                <div class="main_content_header">Восстановление пароля</div>
            </div>
            <div class="reg_left_block">
                <span id="errorMessage"></span>

                <div id="auth_block">
                    <div class="reg_element">
                        <span>Введите email</span>
                        <input type="text" id="rememberusername">
                    </div>

                    <div class="reg_element">
                        ${reCaptchaHtml}
                    </div>
                    <div class="reg_element full">
                        <span class="shadow_silver fl_left"><a href="javascript:void(0);" onclick="rememberPassword()"
                                                               class="reg_button gradient_green_button p13">Отправить</a></span>
                    </div>
                </div>

                <div class="m10" id="m10" style="display: none;">
                    <p class="fs22">Восстановление завершено</p>

                    <p>На ваш email отправлен пароль.</p>
                </div>
            </div>
            <jsp:include page="social_enter.jsp"/>
        </div>
    </div>
</div>
<!-- /main_content -->


<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>