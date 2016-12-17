<%@ page import="org.ircenter.server.service.user.UserHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Регистрация</title>
    <link type="image/x-icon" rel="shortcut icon" href="/static/img/favicon.ico">
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/reg.css" type="text/css"/>
    <script src="<c:url value="/static/js/jquery.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/jquery.countdown.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/widget.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/smoothDivScroll.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/common.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/right_side_update.js"/>" type="text/javascript"></script>
    <script src="/static/js/openapi.js" type="text/javascript"></script>
    <script src="/static/js/all.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
    <script type="text/javascript">

        function sendActivation() {
            $.getJSON("/signup/activate", { activationCode:$('#activate_code').val() },
                    function (response) {
                        if (response.available) {
                            $(window.location).attr('href', "/");
                        } else {
                            $('#base64error').html(response.errorMessage);
                        }
                    });
        }

        function registration() {
            $.post('/signup/register', {recaptcha_challenge_field:$('#recaptcha_challenge_field').val(), recaptcha_response_field:$('#recaptcha_response_field').val(), name:$('#name').val(), login:$('#login').val(),
                password:$('#password').val(), passwordAgain:$('#passwordAgain').val(),
                license:$('#i_read_license_reg').val()}, function (response) {
                if (response.complete) {
                    $("#auth_block_left").css('display', 'none');
                    $("#activate").css('display', 'block');
                } else {
                    Recaptcha.reload();
                    $('#nameError').html(response.nameError);
                    $('#passwordAgainError').html(response.passwordAgainError);
                    $('#passwordError').html(response.passwordError);
                    $('#captchaError').html(response.captchaError);
                    $('#userLoginError').html(response.userLoginError);
                    $('#licenseError').html(response.licenseError);
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

<sec:authorize access="isAnonymous()">

    <jsp:include page="anonymous_header.jsp"/>
</sec:authorize>


<sec:authorize access="isAuthenticated()">

    <jsp:include page="authenticated_header.jsp"/>
</sec:authorize>

<div id="content">

    <div class="clear_fix">&nbsp;</div>

    <div class="main_content top bottom">
        <div class="reg_block">
            <div class="overflow " id="overflow">
                <div class="main_content_header_right">
                    <span class="fl_left mr15">Уже есть аккаунт?</span>
                    <span class="shadow_silver fl_right"><a href="javascript:void(0);" onclick="createModal('/auth/auth_form');" class="gradient_silver_button auth_main_button p13">Войдите</a></span></div>
                <div class="main_content_header">Зарегистрируйтесь прямо сейчас!</div>
            </div>
            <%--<div class="reg_left_block">--%>
            <div class="reg_left_block" id="auth_block_left">

                <span id="nameError" class="error"></span>
                <span id="passwordAgainError" class="error"></span>
                <span id="passwordError" class="error"></span>
                <span id="captchaError" class="error"></span>
                <span id="userLoginError" class="error"></span>
                <span id="licenseError" class="error"></span>

                <div id="auth_block">
                    <div class="reg_element">
                        <label for="name">Имя Фамилия</label>

                        <input type="text" name="name" id="name"/>
                    </div>
                    <div class="reg_element">
                        <label for="login">Ваш e-mail</label>
                        <input name="login" id="login"/>
                    </div>
                    <div class="reg_element">
                        <label for="password">Пароль</label>
                        <input type="password" name="password" id="password" placeholder="Не менее 5 символов"/>
                    </div>
                    <div class="reg_element">
                        <label for="passwordAgain">Пароль повторно</label>
                        <input type="password" name="passwordAgain" id="passwordAgain"
                               placeholder="Не менее 5 символов"/>


                    </div>

                    <div class="reg_element full">
                        ${reCaptchaHtml}
                    </div>


                    <%--<div class="reg_element full">--%>
                    <%--<label for="i_read_license"><input type="checkbox" name="" id="i_read_license_reg"> Я прочитал и--%>
                    <%--согласен с</label> <a href="#">правилами регистрации</a>--%>
                    <%--</div>--%>
                    <div class="reg_element full">
                <span class="shadow_silver fl_left"><a href="javascript:void(0);" onclick="registration();" class="reg_button gradient_green_button p13">Зарегистрироваться</a></span>
                    </div>
                </div>
            </div>

            <div class="reg_left_block" id="activate" style="display: none">
                <div class="m10">
                    <p class="fs22">Регистрация прошла успешно.</p>

                    <p>На указанный при регистрации e-mail отправлено письмо с информацией, требуемой для активации
                        Вашей учетной записи. Вам необходимо в течении суток или пройти по ссылке в письме, или ввести
                        ниже код активации, указанный в письме.</p>
                </div>
                <div class="activate_block">
                    <div class="reg_element fl_left">
                        <span>Введите код:</span>
                        <input type="text" id="activate_code">
                    </div>
                    <div class="reg_element fl_right">
                        <span class="shadow_silver fl_left mt19">
                            <a href="javascript:void(0);" class="gradient_dark_silver_button auth_button p13" onclick="sendActivation(); return false;">Активировать</a>
                            </span>
                    </div>
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
