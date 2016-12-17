<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    Object loginError = request.getSession().getAttribute("loginError");
    Object captchaValid = request.getSession().getAttribute("captchaValid");
    Object captchaNeeded = request.getSession().getAttribute("captchaNeeded");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<div class="full_auth_reg_block">
    <div class="reg_block">
        <div class="in_block_auth_reg">
            <div class="block_header">Регистрация</div>
            <div class="to_bottom">
                <div class="reg_element">
                    <span class="header">Имя Фамилия</span>

                    <div class="input_bg"><input type="text" id="name"><span></span></div>
                </div>
                <div class="reg_element">
                    <span class="header">E-mail</span>
                    <span id="userLoginError" class="error"></span>

                    <div class="input_bg"><input type="text" id="login"><span></span></div>
                </div>
                <div class="reg_element">
                    <span class="header">Пароль</span>
                    <span id="passwordError" class="error"></span>

                    <div class="input_bg"><input type="password" id="password"
                                                 placeholder="Не менее 5 символов"><span></span></div>
                </div>
                <div class="reg_element">
                    <span class="header">Пароль повторно</span>
                    <span id="passwordAgainError" class="error"></span>

                    <div class="input_bg"><input type="password" id="passwordAgain"
                                                 placeholder="Не менее 5 символов"><span></span></div>
                </div>
                <div class="reg_element overflow_block">
                    <span class="header">Введите указаный код</span>

                    <c:if test='<%=captchaValid != null && !(Boolean) captchaValid%>'>
                        <p class="error">Неверно введены символы</p>
                    </c:if>

                    <div class="input_bg captcha"><input id="captcha" name="captcha" type="text" class="captcha_input"/>
                        <span></span></div>

                    <%--<img width="209" height="80" src="<c:url value="/jcaptcha.jpg"/>" alt="captcha"/>--%>
                </div>
                <div class="reg_element">
                    <label for="i_read_license_reg"><input type="checkbox" name="" id="i_read_license_reg"> Я прочитал и
                        согласен с</label><br/> <a href="#">правилами регистрации</a>
                </div>

                <div class="reg_element">
                    <button class="reg_button" onclick="registration();">Зарегистрироваться</button>
                </div>
            </div>
        </div>
    </div>
    <div class="auth_block">
        <div class="in_block_auth_reg">
            <div class="block_header">Авторизация</div>
            <div class="reg_element">
                <span class="header many">Для входа на наш сайт Вы можете использовать свой аккаунт в этих сервисах:</span>
                <a href=""><img src="/static/img/social_tmp.jpg" alt=""/></a>
                <a href=""><img src="/static/img/social_tmp.jpg" alt=""/></a>
                <a href=""><img src="/static/img/social_tmp.jpg" alt=""/></a>
            </div>
            <div class="to_bottom">

                <c:if test='<%=loginError != null && (Boolean) loginError%>'>
                    <p style="margin: 0 0 0 19px; top: 8px;" class="error">Неправильная пара логин-пароль!</p>
                </c:if>
                <form name="welcome-form" action="<c:url value='/j_spring_security_check'/>" method="POST" onsubmit="">
                    <div class="reg_element">
                        <span class="header">Псевдоним или e-mail</span>

                        <div class="input_bg">
                            <input class="input_txt" type='text' name='j_username' id="j_username"
                                   value='<c:if test="<%=(loginError != null && (Boolean) loginError) || (captchaValid != null && !(Boolean) captchaValid)%>"><c:out value='<%=session.getAttribute("SPRING_SECURITY_LAST_USERNAME_KEY")%>' escapeXml="false"/></c:if>'/>
                            <span></span></div>
                    </div>
                    <div class="reg_element">
                        <span class="header">Пароль</span>

                        <div class="input_bg">
                            <input type="password" name='j_password' id="j_password">
                            <span></span></div>

                        <%--<label for="j_password">Пароль</label>--%>
                        <%--<span class="header">Пароль</span>--%>
                        <%--<input class="input_bg" type='password' name='j_password' id="j_password">--%>

                        <%--<span class="header">Пароль</span>--%>

                        <%--<div class="input_bg"><input type="password"><span></span></div>--%>
                    </div>

                    <div class="reg_element">

                        <%--<input type="checkbox" name="_spring_security_remember_me" checked="checked"--%>
                        <%--id="save">--%>
                        <%--<label for="save">запомнить</label>--%>

                        <label for="save"><input type="checkbox" name="_spring_security_remember_me" checked="checked"
                                                 id="save"> запомнить</label>
                        <a href="#" onclick="showRememberPassword(); return false;" class="small">Забыли пароль?</a>
                    </div>

                    <c:if test="<%=captchaNeeded != null && (Boolean) captchaNeeded%>">
                        <div class="reg_element overflow_block">
                            <span class="header">Введите указаный код</span>
                            <c:if test='<%=captchaValid != null && !(Boolean) captchaValid%>'>
                                <p class="error">Неверно введены символы</p>
                            </c:if>
                            <div class="input_bg captcha"><input id="captcha" name="captcha"
                                                                 class="captcha_input"><span></span></div>
                            <img width="209" height="80" src="<c:url value="/jcaptcha.jpg"/>" alt="captcha"/>
                        </div>
                    </c:if>
                    <div class="reg_element">
                        <button class="auth_button" >Войти</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</html>
