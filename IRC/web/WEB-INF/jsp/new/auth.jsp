<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%

    Object loginError = request.getSession().getAttribute("loginError");
    Object captchaValid = request.getSession().getAttribute("captchaValid");
    Object captchaNeeded = request.getSession().getAttribute("captchaNeeded");
    Object reCaptchaHtml = request.getSession().getAttribute("reCaptchaHtml");
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Вход</title>
    <link rel="icon" href="/static/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/reg.css" type="text/css"/>
    <script src="<c:url value="/static/js/jquery.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/jquery.countdown.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/widget.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/smoothDivScroll.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/static/js/common.js"/>" type="text/javascript"></script>
    <script src="/static/js/openapi.js" type="text/javascript"></script>
    <script src="/static/js/all.js" type="text/javascript"></script>


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
<sec:authorize access="isAuthenticated()">
    <c:redirect url="/"/>
</sec:authorize>

<sec:authorize access="isAnonymous()">
    <jsp:include page="anonymous_header.jsp"/>

    <div id="content">

        <div class="clear_fix">&nbsp;</div>

        <div class="main_content top bottom">
            <div class="reg_block">
                <div class="overflow ">
                    <div class="main_content_header_right">Еще нет аккаунта? <span class="shadow_silver fl_right"><a
                            href="/signup"
                            class="gradient_green_button reg_main_button p13">Зарегистрируйтесь</a></span>
                    </div>
                    <div class="main_content_header">Войдите</div>
                </div>
                <div class="reg_left_block">
                    <c:if test='<%=loginError != null && (Boolean) loginError%>'>
                        <p>Неправильная пара логин-пароль!</p>
                    </c:if>

                    <form id="auth_form_place" name="welcome-form" action="<c:url value='/j_spring_security_check'/>" method="POST"
                          onsubmit="">
                        <div id="auth_block">
                            <div class="reg_element">
                                <span>Псевдоним или e-mail</span>
                                <input class="input_txt" type='text' name='j_username' id="j_username"
                                       value='<c:if test="<%=(loginError != null && (Boolean) loginError) || (captchaValid != null && !(Boolean) captchaValid)%>"><c:out value='<%=session.getAttribute("SPRING_SECURITY_LAST_USERNAME_KEY")%>' escapeXml="false"/></c:if>'/>
                            </div>
                            <div class="reg_element">
                                <span>Пароль</span>
                                <input type="password" name='j_password' id="j_password">
                            </div>
                            <div class="reg_element full">
                                <label for="memorize_me"><input type="checkbox" name="_spring_security_remember_me"
                                                                id="memorize_me" checked="checked">
                                    запомнить</label>
                                <a href="#">Забыли пароль?</a>
                            </div>


                            <c:if test="<%=captchaNeeded != null && (Boolean) captchaNeeded%>">
                                <div class="reg_element">
                                    <%=reCaptchaHtml.toString()%>
                                </div>
                            </c:if>

                            <div class="reg_element">
                <span class="shadow_silver fl_left"><a href="javascript:void(0);" onclick="$('#auth_form_place').submit();" type="submit" style="padding: 0 15px;" class="gradient_dark_silver_button auth_button">Войти</a></span>
                            </div>
                        </div>
                    </form>

                </div>

                <jsp:include page="social_enter.jsp"/>

            </div>
        </div>
    </div>
    <!-- /main_content -->


    <jsp:include page="footer.jsp"/>
</sec:authorize>


</body>
</html>
