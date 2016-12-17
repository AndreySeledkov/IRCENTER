<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="profile_right_column">
    <div class="profile_right_column_header_elements">
        <div class="list_element_center">
            <ul class="fl_left">
                <li><a href="javascript:void(0)" onclick="return rightSideUpdateModal('/friends?section=all')"
                       class="select">Все
                    друзья</a></li>
                <li><a href="javascript:void(0)" onclick="return rightSideUpdateModal('/friends?section=online')">Друзья
                    on-line</a>
                </li>
                <li><a href="javascript:void(0)" onclick="return rightSideUpdateModal('/friends?section=all_requests')"
                        >Заявки в
                    друзья</a></li>
                <li><a href="javascript:void(0)"
                       onclick="return rightSideUpdateModal('/friends?section=search')">Поиск</a></li>
            </ul>
        </div>
    </div>
    <div class="profile_right_column_content">
        <div class="profile_messages_header">
            <span class="messages_counter">У Вас ${friendsCount} друзей</span>

            <%--<div class="messages_search">--%>
                <%--<a href="javascript:void(0)" class="messages_search_button">&nbsp;</a>--%>
                <%--<input type="text" title="Поиск друзей" class="messages_search_input">--%>
            <%--</div>--%>
        </div>
        <div class="friends">

            <c:forEach items="${friendsList}" var="user" varStatus="i">
                <div class="friend silver_bg">
                    <a href="/profile?id=<c:out value="${user.userId}"/>" class="avatar"><img
                            src="/img/${user.userId}/th_${user.userId}.jpg" alt=""/></a>

                    <div class="friend_body_info">
                        <div class="friend_body_string">
                            <span class="left">Имя:</span>
                                <span class="right"><a href="/profile?id=<c:out value="${user.userId}"/>"
                                                       class="friend_name">${user.loginName}</a></span>
                        </div>
                    </div>
                    <div class="friend_operations">
                        <a href="javascript:void(0);"
                           onclick="return rightSideUpdateMessageModal('/new_message',${user.userId})"
                           class="friend_operation">Написать сообщение</a>
                        <a href="javascript:void(0);" class="friend_operation" onclick="deleteFriend(${user.userId})">Удалить
                            из друзей</a>

                    </div>
                </div>
            </c:forEach>

            <c:if test="${friendsCount>10}">
                <jsp:include page="pagination.jsp"/>
            </c:if>

        </div>
    </div>
</div>

