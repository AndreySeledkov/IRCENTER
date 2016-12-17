<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="wphead">
    <h1 id="site-heading" class="long-title">
        <a href="http://ircenter.eu/" title="Перейти на сайт">
            <span id="site-title">IrCenter.eu</span>
        </a>
    </h1>

    <div id="wphead-info">
        <div id="user_info">
            <p>
                Привет,
                <a href=""
                   title="Изменить профиль"><%= ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getLoginName()%>
                </a>
                |
                <a href="<c:url value="/j_spring_security_logout"/>" title="Выйти">Выйти</a>
            </p>
        </div>
    </div>
</div>