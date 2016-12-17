<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="bottom_side_bar">
    <c:if test="${isMessageTab!=null && isMessageTab}">
        <jsp:include page="message_form_inbox.jsp"/>
    </c:if>
    <c:if test="${isFriendTab!=null && isFriendTab}">
        <c:if test="${isFriendAll}">
            <jsp:include page="friends_form.jsp"/>
        </c:if>
        <c:if test="${isFriendOnline}">
            <jsp:include page="friends_online_form.jsp"/>
        </c:if>
        <c:if test="${isFriendRequest}">
            <jsp:include page="friends_request_form.jsp"/>
        </c:if>
        <c:if test="${isFriendSearch}">
            <jsp:include page="friends_search_form.jsp"/>
        </c:if>
    </c:if>
    <c:if test="${isArticleTab!=null && isArticleTab}">
        <jsp:include page="article_form.jsp"/>
    </c:if>
    <c:if test="${isFavoriteTab!=null && isFavoriteTab}">
        <jsp:include page="favorite_form.jsp"/>
    </c:if>
    <c:if test="${isPhotosTab!=null && isPhotosTab}">
        <jsp:include page="photos_form.jsp"/>
    </c:if>
</div>