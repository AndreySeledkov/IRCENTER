<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="profile_part_header">Мои сообщения</div>
<div class="profile_right_column_header_elements">
    <div class="list_element_center">
        <ul class="fl_right">
            <li><a href="/messages?section=inbox"
                   onclick="return rightSideUpdateMessagesModal('/messages?section=inbox','inbox',1)" class="select">Полученые</a>
            </li>
            <li><a href="/messages?section=outbox"
                   onclick="return rightSideUpdateMessagesModal('/messages?section=outbox','outbox',1)">Отправленые</a>
            </li>
        </ul>
    </div>
</div>
<div class="profile_right_column_content">
    <c:choose>
        <c:when test="${privateMessagesInboxCount==0}">
            ПУСТО
        </c:when>
        <c:otherwise>
            <div class="profile_messages_header">
                <span class="messages_counter">Вы получили ${privateMessagesInboxCount} сообщение</span>
            </div>
            <div class="messages">
                <c:forEach items="${privateMessages}" var="privateMessage" varStatus="status">
                    <c:choose>
                        <c:when test="${status.count%2!=0}">
                            <div class="message silver_bg">
                                <a href="" class="avatar"><img src="/static/img/tmp46.jpg" alt=""/></a>

                                <div class="message_body">
                                    <div class="message_body_in">
                                        <a href="javascript:void(0)"
                                           onclick="deleteMessage(${privateMessage.messageId})"
                                           class="message_delete">x</a>
                                        <a href="" class="message_name">${privateMessage.userName}</a>

                                        <div class="message_message">
                                                ${privateMessage.body}
                                        </div>
                                    </div>
                                    <div class="message_body_bottom">
                                        <div class="message_body_bottom_in">
                                             <span class="message_date">
                                            <fmt:formatDate value="${privateMessage.created}" pattern="dd.MM.yy"/> в
                                            <fmt:formatDate
                                                    value="${privateMessage.created}" pattern="HH:mm"/>
                                            </span>

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:when>
                        <c:otherwise>
                            <div class="message white_bg">
                                <a href="" class="avatar"><img src="/static/img/tmp46.jpg" alt=""/></a>

                                <div class="message_body">
                                    <div class="message_body_in">
                                        <a href="javascript:void(0)" class="message_delete"
                                           onclick="deleteMessage(${privateMessage.messageId})">x</a>
                                        <a href="javascript:void(0)" class="message_name">${privateMessage.userName}</a>

                                        <div class="message_message">
                                                ${privateMessage.body}
                                        </div>
                                    </div>
                                    <div class="message_body_bottom">
                                        <div class="message_body_bottom_in">
                                             <span class="message_date">
                                            <fmt:formatDate value="${privateMessage.created}"
                                                                                                                       pattern="dd.MM.yy"></fmt:formatDate> в
                                            <fmt:formatDate
                                                    value="${privateMessage.created}" pattern="HH:mm"/>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${privateMessagesCount>3}">
                    <jsp:include page="../pagination.jsp"/>
                </c:if>

            </div>
        </c:otherwise>
    </c:choose>
</div>
