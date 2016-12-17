<%@ page import="org.ircenter.server.service.user.UserHelper" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    Object activation = request.getSession().getAttribute("activation");
    Object loginError = request.getSession().getAttribute("loginError");
    Object captchaValid = request.getSession().getAttribute("captchaValid");
    Object captchaNeeded = request.getSession().getAttribute("captchaNeeded");
    Object activationUserName = request.getSession().getAttribute("activationUserName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Регистрация</title>
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

    <script type="text/javascript">
        function sendActivation() {
            $.getJSON("/registration/activate", { activationCode:$('#base64').val() },
                    function (response) {
                        if (response.available) {
                            $(window.location).attr('href', "/");

//                            var url = window.location.href;
//                            var to;
//                            do {
//                                to = url.indexOf("activate=1");
//                                if (to != -1) {
//                                    url = url.substring(0, to) + url.substring(to + 10);
//                                }
//                            } while (to != -1);
//                            $(window.location).attr('href', url);
                        } else {
                            $('#base64error').html(response.errorMessage);
                        }
                    });
        }

        function registration() {
            $.getJSON("/registration", { name:$('#name').val(), login:$('#login').val(),
                password:$('#password').val(), passwordAgain:$('#passwordAgain').val(),
                captcha:$('#captcha').val(), license:$('#i_read_license').val()}, function (response) {
                if (response.complete) {
                    $("#reg_block").css('display', 'none');
                    $("#activation").css('display', 'block');

                } else {
                    $('#nameerror').html(response.nameerror);
                    $('#usernameerror').html(response.usernameerror);
                    $('#passworderror').html(response.passworderror);
                    $('#passwordAgainerror').html(response.passwordAgainerror);
                    $('#agreementerror').html(response.agreementerror);
                    $('#cityIderror').html(response.cityIderror);
                }
            });
        }

        function vkClick() {
            VK.Auth.login(post_vk);
        }

        function fbClick() {
            FB.login(post_fb, {
                scope:'user_about_me,user_birthday,user_interests,email'
            });
        }

        function post_fb(response) {
            if (response.authResponse) {
                $.getJSON("/fbauth", response.authResponse, function (response) {
                    $(window.location).attr('href', "/");
                });
            }
            else {
                return false;
            }
        }

        function post_vk(response) {
            if (response.session) {
                $.getJSON("/vkauth", response.session, function (response) {
                    $(window.location).attr('href', "/");
                });
            }
            else {
                return false;
            }
        }

        function post_mailRu(session) {
            $.getJSON("/mailRuAuth/", session[0], function (response) {
                $(window.location).attr('href', "/");
            });
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
<div id="api_scripts">
    <div id="fb-root"></div>
    <script src="/static/js/openapi.js" type="text/javascript"></script>
    <script src="/static/js/all.js" type="text/javascript"></script>
    <script type="text/javascript" src="/static/js/loader.js"></script>
    <script type="text/javascript" language="javascript">
        FB.init({appId:'162379217205765', status:true, cookie:true, xfbml:true});
        VK.init({
            apiId:2766679
        });
        mailru.loader.require('api', function () {
            mailru.connect.init('667673', 'dd84d229e2addcde1c1f3eefbc0b231c');
            mailru.events.listen(mailru.connect.events.login, function (session) {
                mailru.common.users.getInfo(function (result) {
                    post_mailRu(result);
                });
                //window.location.reload();
            });
            mailru.events.listen(mailru.connect.events.logout, function () {
                window.location.reload();
            });
            mailru.connect.getLoginStatus(function (result) {
                if (result.is_app_user != 1) {
                    mailru.connect.initButton();
                } else {
                    mailru.common.users.getInfo(function (result) {
                        post_mailRu(result);
                    });
                }
            });
        });


    </script>


</div>

<div id="header_out">
    <div id="header">
        <div id="header_top_line">
            <div class="fl_left">
                <span class="separate">&nbsp;</span>
                <a href="#" title="#">О центре</a>
                <span class="separate">&nbsp;</span>
                <a href="#" title="#">Контакты</a>
                <span class="separate">&nbsp;</span>
                <a href="#" title="#">Наши проекты</a>
                <span class="separate">&nbsp;</span>
                <a href="#" title="#">Помощь</a>
                <span class="separate">&nbsp;</span>
            </div>
            <!-- /fl_left -->
            <div class="fl_right">
                <a href="#" title="#" class="header_top_line_a"><span>В мире</span></a>
            </div>
            <!-- /fl_right -->
        </div>
        <!-- /header_top_line -->
        <div id="header_center_line">
            <div class="fl_left">
                <a id="logo" href="/" title="#"><img src="/static/img/logo.jpg" alt="#"/></a>
            </div>
            <!-- /fl_left -->
            <div class="fl_right header_center_line_right">
                <a href="/reg" title="#" class="button31 green ml20 fl_left">Регистрация<span
                        class="r">&nbsp;</span></a>
                <a href="/authorize" title="#" class="button31 silver ml20 fl_left">Войти<span
                        class="r">&nbsp;</span></a>
            </div>
            <!-- /fl_right -->
        </div>
        <!-- /header_center_line -->
        <div id="header_bottom_line">
            <div class="pad_menu fl_left">
                <a href="#" title="#" class="link">Главная</a>
                <a href="#" title="#" class="link">Свидетельства</a>
                <a id="media_button" href="#" title="#" class="link pad"
                   onMouseOver="show_pad_menu('media', 'media_button')"
                   onMouseOut="hide_pad_menu('media', 'media_button')">Медиа</a>
                <a href="#" title="#" class="link">События</a>
                <a href="#" title="#" class="link">Интервью</a>
                <a id="education_button" href="#" title="#" class="link pad"
                   onMouseOver="show_pad_menu('education', 'education_button')"
                   onMouseOut="hide_pad_menu('education', 'education_button')">Обучение</a>
                <a href="#" title="#" class="link">Магазин</a>
                <%--<a href="#" title="#" class="link">Форум</a>--%>


                <div id="media" class="menu" onMouseOver="show_pad_menu('media', 'media_button')"
                     onMouseOut="hide_pad_menu('media', 'media_button')">
                    <div class="in_block">
                        <a href="#" title="#" class="online">Онлайн служение</a>
                        <hr>
                        <a href="#" title="#" class="arhive_video">Видео архив</a>
                        <hr>
                        <a href="#" title="#" class="arhive_photo">Фото архив</a>
                        <hr>
                        <a href="#" title="#" class="arhive_audio">Аудио архив</a>
                    </div>
                </div>

                <div id="education" class="menu" onMouseOver="show_pad_menu('education', 'education_button')"
                     onMouseOut="hide_pad_menu('education', 'education_button')">
                    <div class="in_block">
                        <a href="#" title="#" class="online">Библия онлайн</a>
                        <hr>
                        <a href="#" title="#" class="arhive_video">Видео архив</a>
                        <hr>
                        <a href="#" title="#" class="arhive_photo">Фото архив</a>
                    </div>
                </div>

            </div>

            <div class="fl_right">
                <form>
                    <div id="search">
                        <span class="l">&nbsp;</span>
                        <input type="text" name="#" value="" title="Поиск по сайту">
                        <a href="#" title="#" class="search_button">&nbsp;</a>
                    </div>
                </form>
            </div>
            <!-- /search -->
        </div>
        <!-- /header_bottom_line -->
    </div>
    <!-- /header -->
</div>
<!-- /header_out -->


<div id="content">

    <div class="clear_fix">&nbsp;</div>

    <div class="main_content top bottom">
        <div id="activation" style="display: none;">
            <a class="bk" href="" style="margin-left: 200px; margin-top: 8px;"
               onclick="closeLeftSidebar(); return false;">
                Назад
            </a>

            <p id="reg_title">Активация</p>

            <p>Регистрация прошла успешно. На указанный при регистрации
                <nobr>e-mail</nobr>
                отправлено письмо с информацией, требуемой для активации Вашей учетной записи. Вам
                необходимо в течении суток или пройти по ссылке в письме, или ввести ниже код активации,
                указанный в письме.
                Если Вы ввели неверные данные можно удалить регистрацию и зарегистрироваться заново.
                <c:if test='<%=UserHelper.UNREG_NAME.equals(UserHelper.getName())%>'>
                <a href="" onclick="deleteRegistrationByUid('<%=UserHelper.getUserName()%>'); return false;">Удалить
                    регистрацию</a>
                </c:if>
                <c:if test='<%=!UserHelper.UNREG_NAME.equals(UserHelper.getName())%>'>
                <a id="delete-registration-login" href=""
                   onclick="deleteRegistrationByLogin('<%=activationUserName != null ? activationUserName : ""%>'); return false;">
                    Удалить регистрацию
                </a>
                </c:if>
            <p style="margin: 0;">
                <span id="deleteregbyuiderror" class="error"></span>
            </p>
            </p>
            <div style="width: 209px; margin: 0; padding: 0;">
                <input name="base64" id="base64"/>

                <p style="margin: 0;">
                    <span id="base64error" class="error"></span>
                </p>
                <button type="submit" onclick="sendActivation(); return false;">Активировать</button>
            </div>
        </div>


        <div class="reg_block" id="reg_block">

            <div class="main_content_header">Зарегистрируйтесь прямо сейчас!</div>
            <div class="mini_on_block"><span>или</span></div>
            <div class="clear_fix">&nbsp;</div>


            <div class="reg_left_block">

                <a href="#" onclick="fbClick()"
                   title="#" class="social facebook">Войти через
                    <span>Facebook</span></a>
                <a href="#" onclick="vkClick()"
                   title="#" class="social vkontakte">
                    Войти через <span>Vkontakte</span>
                </a>
                <%--<a class="mrc__connectButton">вход@mail.ru</a>--%>

                <a href="#" title="#" class="mrc__connectButton">Войти через <span>Одноклассники</span></a>
            </div>

            <div class="reg_right_block">
                <div class="reg_element">
                    <label for="name">Имя Фамилия</label>
                    <input name="name" id="name"/>
                </div>
                <div class="reg_element">

                    <label for="password">Пароль</label>
                    <input type="password" name="password" id="password" placeholder="Не менее 5 символов"/>

                </div>
                <div class="reg_element">

                    <label for="login">Ваш e-mail</label>
                    <input name="login" id="login"/>
                </div>
                <div class="reg_element">

                    <label for="passwordAgain">Пароль повторно</label>
                    <input type="password" name="passwordAgain" id="passwordAgain" placeholder="Не менее 5 символов"/>
                </div>
                <div class="reg_element">
                    <label for="captcha">Введите символы ниже:</label>
                    <c:if test='<%=captchaValid != null && !(Boolean) captchaValid%>'>
                        <p class="error">Неверно введены символы</p>
                    </c:if>
                    <input id="captcha" name="captcha" type="text" class="captcha_input"/>
                </div>
                <div class="reg_element">
                    <img width="209" height="80" src="<c:url value="/jcaptcha.jpg"/>" alt="captcha"/>
                </div>


                <div class="reg_element full">
                    <label for="i_read_license"><input type="checkbox" name="" id="i_read_license"> Я прочитал и
                        согласен с</label> <a href="#" title="#">правилами регистрации</a>
                </div>
                <div class="reg_element full">
                    <button id="zareg" class="reg_button fl_left" onclick="registration();">Зарегистрироваться<span
                            class="r">&nbsp;</span></button>
                </div>
            </div>
        </div>
        <div class="main_content_header">Вас ждут, Ваши друзья:</div>
        <div class="overflow_block" style="text-align: center;">
            <img src="/static/img/tmp10.jpg" alt=""/>
        </div>
    </div>
</div>
<!-- /main_content -->


<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>
