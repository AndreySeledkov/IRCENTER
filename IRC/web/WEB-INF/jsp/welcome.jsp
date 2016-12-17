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
    <title>Church donation</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content="Devoler"/>
    <meta name="copyright" content="Devoler"/>
    <%@ include file="urls.jspf" %>
    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.5.1.min.js"/>"></script>
    <script type="text/javascript">
        function showRegistration() {
            $("#regestration").css('display', 'block');


            $("#login-div").css('display', 'none');
            $("#remember-div").css('display', 'none');
            $("#activation").css('display', 'none');
            return false;
        }

        function showActivation() {
            $("#regestration").css('display', 'none');
            $("#login-div").css('display', 'none');
            $("#remember-div").css('display', 'none');
            $("#activation").css('display', 'block');
            return false;
        }

        function showRememberPassword() {
            $("#regestration").css('display', 'none');
            $("#login-div").css('display', 'none');
            $("#remember-div").css('display', 'block');
            $("#activation").css('display', 'none');
        }


        function registration() {
            $.getJSON(registrationURL, { name:$('#name').val(), login:$('#login').val(),
                password:$('#password').val(), passwordAgain:$('#passwordAgain').val(),
                captcha:'uyvuyuy', license:true }, function (response) {
                if (response.complete) {
                    $("#regestration").css('display', 'none');
                    $("#login-div").css('display', 'none');
                    $("#remember-div").css('display', 'none');
                    $("#cabinet-panel").css('display', 'none');
                    $("#wrap_edit_profile").css('display', 'none');
                    $("#activation").css('display', 'block');
                    $('#regwithoutname').click(function () {
                        showActivation();
                        return false;
                    });
                    $('#logoutwithoutname').click(function () {
                        showActivation();
                        return false;
                    });
                    $('#pmwithoutname').click(function () {
                        showActivation();
                        return false;
                    });
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
        function rememberPassword() {
            $.getJSON(rememberPasswordURL, { login:$('#rememberusername').val() }, function (response) {
                if (response.available) {
                    $('#usernameok').html(response.errorMessage);
                    $('#rememberusernameerror').html("");
                } else {
                    $('#rememberusernameerror').html(response.errorMessage);
                    $('#usernameok').html("");
                }
            });
        }

        function sendActivation() {
            $.getJSON(activationURL, { activationCode:$('#base64').val() },
                    function (response) {
                        if (response.available) {
                            var url = window.location.href;
                            var to;
                            do {
                                to = url.indexOf("activate=1");
                                if (to != -1) {
                                    url = url.substring(0, to) + url.substring(to + 10);
                                }
                            } while (to != -1);
                            $(window.location).attr('href', url);
                        } else {
                            $('#base64error').html(response.errorMessage);
                        }
                    });
        }

        function showEditProfile() {
            $('#user_menu1').css('display', 'none');
            $('#wrap_edit_profile').css('display', 'block');
        }
        function closeCabinetLeftSidebar() {
            $('#user_menu1').css('display', 'block');
            $('#wrap_edit_profile').css('display', 'none');
        }

        function closeLeftSidebar() {
            $("#regestration").css('display', 'none');
            $("#login-div").css('display', 'block');
            $("#remember-div").css('display', 'none');
            $("#activation").css('display', 'none');
        }

        function exitUnregistered(isActivated) {
            if (window.confirm('Уважаемый пользователь у Вас нет имени. Пройдите регистрацию.')) {
                if (isActivated) {
                    showActivation();
                } else {
                    showRegistration();
                }
                return false;
            }
            return true;
        }

        function deleteRegistrationByUid(uid) {
            if (window.confirm('Вы уверены, что хотите удалить предыдущую регистрацию?')) {
                $.getJSON(deleteRegByUidURL + uid + "/", {}, function (responce) {
                    if (responce.available) {
                        $("#regestration").css('display', 'block');
                        $("#login-div").css('display', 'none');
                        $("#remember-div").css('display', 'none');
//                $("#cabinet-panel").css('display', 'none');
                        $("#wrap_edit_profile").css('display', 'none');
                        $("#activation").css('display', 'none');
                        $('#regwithoutname').click(function () {
                            showRegistration();
                            return false;
                        });
                        $('#logoutwithoutname').click(function () {
                            showRegistration();
                            return false;
                        });
                        $('#pmwithoutname').click(function () {
                            showRegistration();
                            return false;
                        });
                    } else {
                        $('#deleteregbyuiderror').html(responce.errorMessage);
                    }
                });
            }
        }

        function deleteRegistrationByLogin(login) {
            if (window.confirm('Вы уверены, что хотите удалить предыдущую регистрацию?')) {
                if (login == '') {
                    login = $("#login").val();
                }
                $.getJSON(deleteRegByUserNameURL + login + "/", {}, function (responce) {
                    if (responce.available) {
                        $("#regestration").css('display', 'block');
                        $("#login-div").css('display', 'none');
                        $("#remember-div").css('display', 'none');
//                $("#cabinet-panel").css('display', 'none');
                        $("#wrap_edit_profile").css('display', 'none');
                        $("#activation").css('display', 'none');
                    } else {
                        $('#deleteregbyuiderror').html(responce.errorMessage);
                    }
                });
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


<div id="left_sidebar">
<sec:authorize access="isAnonymous()">
    <div id="user_menu_wrap">
        <div id="user_menu">
            <div id="login-div">


                    <%--<a href="https://www.facebook.com/dialog/oauth?client_id=162379217205765&redirect_uri=ircenter.eu">--%>
                    <%--<img src="<c:url value="/static/img/facebook_logo.png"/>">--%>
                    <%--</a>--%>
                <a href="http://www.facebook.com/dialog/oauth?client_id=162379217205765&redirect_uri=http://ircenter.eu/&scope=manage_notifications">
                    <img src="<c:url value="/static/img/facebook_logo.png"/>">
                    <span>Связь с Facebook</span>
                </a>


                <a href="http://oauth.vkontakte.ru/oauth/authorize?client_id=2766679&redirect_uri=http://ircenter.eu/&response_type=code">
                    <img src="<c:url value="/static/img/vk32.png"/>">
                </a>


                <div class="clear"></div>
                <c:if test='<%=loginError != null && (Boolean) loginError%>'>
                    <p style="margin: 0 0 0 19px; top: 8px;" class="error">Неправильная пара логин-пароль!</p>
                </c:if>
                <form name="welcome-form" action="<c:url value='/j_spring_security_check'/>" method="POST" onsubmit="">
                    <div>
                        <label for="j_username">Псевдоним или e-mail</label>
                        <input class="input_txt" type='text' name='j_username' id="j_username"
                               value='<c:if test="<%=(loginError != null && (Boolean) loginError) || (captchaValid != null && !(Boolean) captchaValid)%>"><c:out value='<%=session.getAttribute("SPRING_SECURITY_LAST_USERNAME_KEY")%>' escapeXml="false"/></c:if>'/>
                        <br>
                        <label for="j_password">Пароль</label>
                        <input class="input_txt" type='password' name='j_password' id="j_password">

                        <div id="forg_passw">
                            <div id="save_wrap">
                                <input type="checkbox" name="_spring_security_remember_me" checked="checked"
                                       id="save">
                                <label for="save">запомнить</label>
                            </div>
                            <div id="forg_wrap">
                                <a href="" title="" onclick="showRememberPassword(); return false;">забыли
                                    пароль?</a>
                            </div>
                        </div>
                        <c:if test="<%=captchaNeeded != null && (Boolean) captchaNeeded%>">
                            <label for="captcha">Введите символы ниже:</label>
                            <c:if test='<%=captchaValid != null && !(Boolean) captchaValid%>'>
                                <p class="error">Неверно введены символы</p>
                            </c:if>
                            <img width="209" src="<c:url value="/jcaptcha.jpg"/>" alt="captcha"/>
                            <input id="captcha" name="captcha" type="text" class="input_txt"/>
                        </c:if>
                        <div id="butt_wrap">
                            <div id="reg_wrap">
                                <button type="button" id="reg" onclick="showRegistration()">Регистрация
                                </button>
                            </div>
                            <div id="login_wrap">
                                    <%--TODO was id = login--%>
                                <button type="submit" id="enter"
                                        onmouseover="this.style.backgroundPosition='0 -32px'"
                                        onmouseout="this.style.backgroundPosition='0 0'"
                                        onmousedown="this.style.backgroundPosition='0 -63px'">Войти
                                </button>
                            </div>
                        </div>
                        <div class="clear"></div>
                    </div>
                </form>
            </div>
            <div id="regestration" style="display: none;">
                <a class="bk" href="" onclick="closeLeftSidebar(); return false;">
                    Назад
                </a>

                <p id="reg_title">Регистрация</p>

                <div style="width: 209px;">
                    <label for="name">Ваш псевдоним</label>
                    <input name="name" id="name"/>

                    <p style="margin: 0;">
                        <span id="nameerror" class="error"></span>
                    </p>
                    <label for="login">Ваш e-mail</label>
                    <input name="login" id="login"/>

                    <p style="margin: 0;">
                        <span id="usernameerror" class="error"></span>
                    </p>
                    <label for="password">Ваш пароль</label>
                    <input type="password" name="password" id="password"/>
                    <label>Должен содержать не менее 5 символов</label>

                    <p style="margin: 0;">
                        <span id="passworderror" class="error"></span>
                    </p>
                    <label for="passwordAgain">Ваш пароль повторно</label>
                    <input type="password" name="passwordAgain" id="passwordAgain"/>

                    <p style="margin: 0;">
                        <span id="passwordAgainerror" class="error"></span>
                    </p>

                    <div class="clear"></div>
                    <p style="margin: 0;">
                        <span id="agreementerror" class="error"></span>
                    </p>

                    <button id="zareg" onclick="registration();">Зарегистрироваться</button>
                </div>
            </div>
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
            <div id="remember-div" style="display: none;">
                <a class="bk" href="Закрыть" style="margin-left: 220px; margin-top: 8px;"
                   onclick="closeLeftSidebar(); return false;">Назад
                </a>

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
        </div>
    </div>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <div id="user_menu_wrap">
        <div id="user_menu">
            <p style="margin: 0; text-align: center;">
                <b><%= ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getLoginName()%>
                </b>
            </p>

            <c:if test="<%= UserHelper.isBanned()%>">
                <p>
                    <a title="" href="" onclick="return false;" class="error">Ваш профиль забанен!</a>
                </p>
            </c:if>
            <c:if test='<%=UserHelper.UNREG_NAME.equals(UserHelper.getName())%>'>
                <c:if test='<%=activation == null || !(Boolean) activation%>'>
                    <p style="margin: 0; text-align: center;">
                        <a onclick="showRegistration(); return false;" href="" id="regwithoutname">Пройти
                            регистрацию</a>
                    </p>
                </c:if>
                <c:if test='<%=activation != null && (Boolean) activation%>'>
                    <p style="margin: 0; text-align: center;">
                        <a onclick="showActivation(); return false;" href="" id="regwithoutname">Пройти регистрацию</a>
                    </p>
                </c:if>
            </c:if>
            <div id="user_menu_panel">

                <a href="<c:url value="/edit"/>" title="">
                    Редактировать
                </a>


                    <%--<a href="<c:url value="/cabinet"/>" title="">--%>
                    <%--<fmt:message key="cabinet"/>--%>
                    <%--</a>--%>

                <br>
                <a href="<c:url value="/friends"/>" title="">
                    Друзья
                </a>
                <br>

                <a href="<c:url value="/search"/>">Поиск</a>
                <br>

                <c:if test='<%=UserHelper.UNREG_NAME.equals(UserHelper.getName())%>'>
                    <a href="<c:url value="/mail"/>" title="" style="margin-left: 20px;"
                       onclick="return exitUnregistered(<%=activation != null && (Boolean) activation%>);"
                       id="pmwithoutname">Личные сообщения</a> <c:if
                        test='${notReadedPrivateMessagesCount >= 0}'><b>${notReadedPrivateMessagesCount}</b>/${allPrivateMessagesCount}</c:if>
                </c:if>
                <c:if test='<%=!UserHelper.UNREG_NAME.equals(UserHelper.getName())%>'>
                    <a href="<c:url value="/mail"/>" title="" style="margin-left: 20px;">Личные
                        сообщения</a> <c:if
                        test='${notReadedPrivateMessagesCount >= 0}'><b>${notReadedPrivateMessagesCount}</b>/${allPrivateMessagesCount}</c:if>
                </c:if>
                <br>

                <a href="<c:url value="/forum"/>">Форум</a>

                <c:if test='<%=UserHelper.UNREG_NAME.equals(UserHelper.getName())%>'>
                    <a href="" title="" class="logout"
                       onclick="return exitUnregistered(<%=activation != null && (Boolean) activation%>); return false;"
                       id="logoutwithoutname">
                        <fmt:message key="logout"/>
                    </a>
                </c:if>
                <c:if test='<%=!UserHelper.UNREG_NAME.equals(UserHelper.getName())%>'>
                    <a href="<c:url value="/j_spring_security_logout"/>" title="" class="logout">
                        <fmt:message key="logout"/>
                    </a>
                </c:if>
                <br>
                <a href="<c:url value="/settings"/>">Настройки</a>
                <br>
            </div>
        </div>
    </div>

    <br>
    <a href="<c:url value="/groups"/>">Мои группы</a>

    <br>
    <img src='/img/<%= UserHelper.getUserId()%>/<%= UserHelper.getUserId()%>.jpg'>


    <form:form id="post" class="MultiFile-intercepted" enctype="multipart/form-data" method="post" action="/avatar">

        <span>Выберите изображения</span>
        <input type="file" name="pictures" id="pictures"/>

        <button type="submit">Загрузить</button>

    </form:form>

</sec:authorize>
</div>
</body>
</html>
