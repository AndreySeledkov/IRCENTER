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
    <title>Вход</title>
    <link type="image/x-icon" rel="shortcut icon" href="/static/img/favicon.ico">
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/reg.css" type="text/css"/>
    <script src="<c:url value="/static/js/jquery.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/jquery.countdown.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/widget.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/smoothDivScroll.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/common.js"/>" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $(this).countdown(new Date(2012, 2, 10, 23, 59, 59));
        });

        function showRememberPassword() {
            $("#reg_block").css('display', 'none');
            $("#remember-div").css('display', 'block');
        }

        function rememberPassword() {
            $.getJSON("/registration/rememberpassword", { login:$('#rememberusername').val() }, function (response) {
                if (response.available) {
                    $('#usernameok').html(response.errorMessage);
                    $('#rememberusernameerror').html("");
                } else {
                    $('#rememberusernameerror').html(response.errorMessage);
                    $('#usernameok').html("");
                }
            });
        }

        function redirect() {
            $(window.location).attr('href', "/");
        }

    </script>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <script type="text/javascript">
        redirect();
    </script>

</sec:authorize>

<sec:authorize access="isAnonymous()">
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
        <div id="remember-div" style="display: none;">
            <p>Восстановление пароля</p>

            <form>
                <div>
                    <label for="login">Псевдоним или e-mail</label>
                    <input class="input_txt" type='text' name='rememberusername' id="rememberusername"/>
                    <span id="rememberusernameerror" class="error"></span>
                    <span id="usernameok" class="ok"></span>

                    <p style="margin: 20px 0 0 0; font: 14px 'PT Sans',arial,serif;">На Ваш e-mail будет
                        отправлено письмо с Вашим паролем.</p>

                    <div id="butt_wrap">
                        <div id="reg_wrap">
                            <button type="button" id="reg" onclick="rememberPassword()">Отправить</button>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </form>
        </div>

        <div class="reg_block" id="reg_block">

            <div class="main_content_header">Зарегистрируйтесь прямо сейчас!</div>
            <div class="mini_on_block"><span>или</span></div>
            <div class="clear_fix">&nbsp;</div>
            <div class="reg_left_block">
                <a href="#" title="#" class="social facebook">Войти через <span>Facebook</span></a>
                <a href="http://oauth.vkontakte.ru/oauth/authorize?client_id=2766679&redirect_uri=http://ircenter.eu/&response_type=code"
                   title="#" class="social vkontakte">
                    Войти через <span>Vkontakte</span>
                </a>
                <a href="#" title="#" class="social odnoklassniki">Войти через <span>Одноклассники</span></a>
            </div>
            <div class="reg_right_block">
                <c:if test='<%=loginError != null && (Boolean) loginError%>'>
                    <p style="margin: 0 0 0 19px; top: 8px;" class="error">Неправильная пара логин-пароль!</p>
                </c:if>
                <form name="welcome-form" action="<c:url value='/j_spring_security_check'/>" method="POST" onsubmit="">
                    <div id="auth_block">
                        <div class="reg_element">

                            <label for="j_username">Псевдоним или e-mail</label>
                            <input class="input_txt" type='text' name='j_username' id="j_username"
                                   value='<c:if test="<%=(loginError != null && (Boolean) loginError) || (captchaValid != null && !(Boolean) captchaValid)%>"><c:out value='<%=session.getAttribute("SPRING_SECURITY_LAST_USERNAME_KEY")%>' escapeXml="false"/></c:if>'/>

                                <%--<span>Псевдоним или e-mail</span>--%>
                                <%--<input type="text">--%>
                        </div>
                        <div class="reg_element">
                            <label for="j_password">Пароль</label>
                            <input class="input_txt" type='password' name='j_password' id="j_password">

                                <%--<span>Пароль</span>--%>
                                <%--<input type="password">--%>
                        </div>
                        <div class="reg_element full">
                            <input type="checkbox" name="_spring_security_remember_me" checked="checked"
                                   id="save">
                            <label for="save">запомнить</label>


                                <%--<label for="i_read_license"><input type="checkbox" name="" id="i_read_license">--%>
                                <%--запомнить</label>--%>
                            <a href="" title="" onclick="showRememberPassword(); return false;">Забыли
                                пароль?</a>
                        </div>
                        <div class="reg_element">
                            <c:if test='<%=captchaValid != null && !(Boolean) captchaValid%>'>
                                <p class="error">Неверно введены символы</p>
                            </c:if>
                            <c:if test="<%=captchaNeeded != null && (Boolean) captchaNeeded%>">
                                <span>Введите указаный код</span>
                                <input id="captcha" name="captcha" type="text" class="captcha_input"/>
                                <img width="209" height="80" src="<c:url value="/jcaptcha.jpg"/>" alt="captcha"/>
                            </c:if>
                        </div>
                        <div class="reg_element">
                            <a href="/registration/" title="#" class="button31 green no_shadow fl_left">Регистрация<span
                                    class="r"></span></a>

                            <button type="submit" class="auth_button ml20 fl_left" id="enter">Войти</button>

                                <%--<button class="auth_button ml20 fl_left">Войти</button>--%>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="main_content_header">Вас ждут, Ваши друзья:</div>
        <div class="overflow_block" style="text-align: center;">
            <img src="/static/img/tmp10.jpg" alt="" align="center"/>
        </div>
    </div>
</div>
<!-- /main_content -->


<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</sec:authorize>


</body>
</html>
