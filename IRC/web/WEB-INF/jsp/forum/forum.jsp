<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="javax.servlet.jsp.jstl.core.LoopTagStatus" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta nam="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content="Devoler"/>
    <meta name="copyright" content="Devoler"/>
    <%@ include file="../urls.jspf" %>
    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.5.1.min.js"/>"></script>
    <script type="text/javascript">

        function createTheme(partId) {
            $.getJSON(createThemeURL, { partId:partId, nametheme:$('#nametheme').val(), textmessage:$('#textmessage').val()}, function (response) {
                        if (response.complete) {
                            window.location.reload();
                        }
                    }
            );
        }

        function addMessage(themeId) {
            $.getJSON(addForumMessageURL, { themeId:themeId, message:$('#message').val()}, function (response) {
                        if (response.complete) {
                            window.location.reload();
                        }
                    }
            );
        }

        function showNewTheme() {
            $("#newtheme").css('display', 'block');
            $("#content").css('display', 'none');

            return false;
        }


    </script>
</head>
<body>
<div id="fix_min_width">
    <div id="wrap">
        ФОРУМ

        <br>

        <div id="newtheme" style=" display: none;">
            Тема<br>
            <textarea id="nametheme"></textarea>
            <br>
            Текст первого сообщения
            <br>
            <textarea id="textmessage"></textarea>
            <br>
            <button type="button" id="reg" onclick="createTheme(${partId})">Создать тему
            </button>
        </div>
        <div id="content">

            <c:forEach items="${mainPartList}" var="mainpart" varStatus="i">
                <b>${mainpart.name}</b>
                <br>
                <c:forEach items="${mainpart.parts}" var="parts" varStatus="i">
                    <a href='<c:url value="/forum/${parts.id}/"/>'>${parts.name}</a>${parts.themeCount}/${parts.messagesCount}
                    <br>
                </c:forEach>
            </c:forEach>

            <c:if test="${partId!=null}">
                <a href="#" onclick="showNewTheme()">Новая тема</a>
                <br>
            </c:if>

            <c:forEach items="${themes}" var="theme" varStatus="i">
                <br>
                <a href='<c:url value="/forum/theme/${theme.id}/"/>'>${theme.name}</a>  ${theme.lastEdit}
            </c:forEach>

            <c:if test="${firstmessage!=null}">
                <b>${firstmessage.text}</b><br>
                <table>
                    <c:forEach items="${userforumlist}" var="userforum" varStatus="j">
                        <tr>
                            <td>
                                <img alt="image" src="/image/getimage/${userforum.id}/">
                            </td>
                            <td>
                                <a href="/blabla/${userforum.id}/">${userforum.name}</a><br>
                                    ${userforum.country},${userforum.city}<br>
                                    ${userforum.messagePrint}
                            </td>


                        </tr>
                        <tr>
                            <td>
                                    ${userforum.message}
                            <td>
                        </tr>
                    </c:forEach>
                </table>

                Текст сообщения
                <br>
                <textarea id="message"></textarea>
                <br>
                <button type="button" id="reg" onclick="addMessage(${firstmessage.themeId})">Написать
                </button>

            </c:if>
        </div>


    </div>
</div>
</body>
</html>