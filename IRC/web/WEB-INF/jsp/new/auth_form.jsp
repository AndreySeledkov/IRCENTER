<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%

    Object loginError = request.getSession().getAttribute("loginError");
    Object captchaValid = request.getSession().getAttribute("captchaValid");
%>
<div class="full_auth_block">
    <div class="in_block_auth">
        <form id="auth_form" name="welcome-form" action="<c:url value='/j_spring_security_check'/>" method="POST" onsubmit="">
            <div class="block_modal_header">Авторизация</div>
            <div class="reg_modal_element">
                <span class="header">Псевдоним или e-mail</span>
                <div class="input_bg">
                    <input class="input_txt" type='text' name='j_username' id="j_username"
                           value='<c:if test="<%=(loginError != null && (Boolean) loginError) || (captchaValid != null && !(Boolean) captchaValid)%>"><c:out value='<%=session.getAttribute("SPRING_SECURITY_LAST_USERNAME_KEY")%>' escapeXml="false"/></c:if>'/>
                    <span></span></div>
            </div>
            <div class="reg_modal_element">
                <span class="header">Пароль</span>
                <div class="input_bg"><input type="password" name='j_password' id="j_password"><span></span></div>
            </div>

            <div class="reg_modal_element">
                <label for="memorize_me"><input type="checkbox" name="_spring_security_remember_me" id="memorize_me"
                                                checked="checked">
                    запомнить</label>
            </div>
            <div class="reg_modal_element">

                <span class="no_shadow fl_right">
                     <a href="javascript:void(0);" onclick="$('#auth_form').submit();" type="submit" class="gradient_blue_button auth_modal_button p13">Войти</a>
                </span>
                <a href="/auth/remind_password" class="small">Забыли пароль?</a>
            </div>
            <div class="reg_element small_right">
                Нужен аккаунт? <a href="/signup">зарегестрируйтесь</a>
            </div>
        </form>
    </div>
</div>
<style type="text/css">
    .full_auth_block {
        overflow: hidden;
        display: block;
        width: 320px;
        padding: 10px 0;
        background-color: #eae9e9;
        border-radius: 5px;
    }
    .in_block_auth {
        overflow: hidden;
        display: block;
        width: 280px;
        margin: 0 auto;
    }
    .block_modal_header {
        display: block;
        width: 100%;
        margin: 0 auto;
        margin-bottom: 10px;
        font-size: 23px;
        font-family: "Tahoma";
        color: #52585e;
        text-align: center;
    }
    .reg_modal_element {
        overflow: hidden;
        display: block;
        width: 280px;
        margin: 0 auto;
        margin-bottom: 5px;
        font-size: 14px;
        text-align: left;
    }
    .reg_modal_element span.header {
        display: block;
        margin-bottom: 3px;
        font-size: 14px;
        color: #4c4c4c;
        text-align: left;
    }
    .reg_modal_element span.header.many {
        margin-bottom: 10px;
        font-size: 12px;
    }
    .reg_modal_element img.captcha {
        float: right;
        vertical-align: middle;
    }
    .reg_modal_element a.small {
        font-size: 12px;
        line-height: 33px;
    }
    .input_bg {
        display: block;
        height: 25px;
        width: 272px;
        background: transparent url(/static/img/auth_reg_input_bg_l.png) no-repeat;
    }
    .input_bg.captcha {
        float: left;
        display: inline-block;
        width: 135px;
        margin-top: 5px;
    }
    .input_bg.captcha input {
        width: 130px;
    }
    .input_bg span {
        float: right;
        display: block;
        width: 5px;
        height: 25px;
        margin-right: -5px;
        background: transparent url(/static/img/auth_reg_input_bg_r.png) no-repeat;
    }
    .input_bg input {
        float: left;
        display: block;
        width: 265px;
        margin-top: 3px;
        margin-left: 3px;
        background: none;
        border: none;
    }
    .auth_modal_button {
        height: 33px;
        line-height: 31px;
        color: #fbfaf9;
        text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.3);
    }
    .small_right {
        display: block;
        padding-top: 10px;
        text-align: right;
        font-size: 12px;
    }
</style>
