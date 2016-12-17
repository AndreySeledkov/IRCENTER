<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="profile_right_column">
    <div class="profile_right_column_header_elements">
        <div class="list_element_center">
            <%--<ul class="fl_left">--%>
            <%--<li><a href="messages_new.html">Написать сообщение</a></li>--%>
            <%--</ul>--%>
            <ul class="fl_right">
                <li><a href="/messages?section=inbox"
                       onclick="return rightSideUpdateMessagesModal('/messages?section=inbox','inbox')"
                       class="select">Полученые</a></li>
                <li><a href="/messages?section=outbox"
                       onclick="return rightSideUpdateMessagesModal('/messages?section=outbox','outbox')">Отправленые</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="profile_right_column_content">
        <div class="profile_messages_header">
            <span class="messages_counter">Вы получили ${privateMessagesCount} сообщений</span>
            <%--Выделить: <a href="">все</a>, <a href="">прочитаные</a>, <a href="">новые</a>--%>

            <div class="messages_search">
                <a href="" class="messages_search_button">&nbsp;</a>
                <input type="text" title="Поиск сообщений" class="messages_search_input">
            </div>
        </div>
        <div class="messages">
            <c:forEach items="${privateMessages}" var="privateMessage" varStatus="i">
                <div class="message silver_bg">
                    <a href="" class="avatar"><img src="/img/${privateMessage.userId}/th_${privateMessage.userId}.jpg"
                                                   alt=""/></a>

                    <div class="message_body">
                        <div class="message_body_in">
                            <a href="javascript:void(0)" onclick="deleteMessage(${privateMessage.messageId})"
                               class="message_delete">x</a>
                            <a href="" class="message_name">${privateMessage.userName}</a>

                            <div class="message_message">
                                    ${privateMessage.body}
                            </div>
                        </div>
                        <div class="message_body_bottom">
                            <div class="message_body_bottom_in">
                                <span class="message_date"> ${privateMessage.created}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <c:if test="${privateMessagesCount>10}">
            <jsp:include page="pagination.jsp"/>
        </c:if>
    </div>
</div>