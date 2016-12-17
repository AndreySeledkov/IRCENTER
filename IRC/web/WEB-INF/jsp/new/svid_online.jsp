<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="svids for_index">
    <c:forEach items="${evidenceOnlineList}" var="evidenceOnline">
        <div class="svid white_bg">
            <img src="/static/img/svid_ava.png" alt="">

            <div class="svid_body">
                <div class="svid_body_in">
                    <span class="svid_name">${evidenceOnline.getTitle()}</span>

                    <div class="svid_message">
                            ${evidenceOnline.getText()}
                    </div>
                </div>
                <div class="svid_body_bottom">
                    <div class="svid_body_bottom_in">
                        <span class="svid_date"> <fmt:formatDate type="both"
                                                                 dateStyle="short" timeStyle="short"
                                                                 value="${evidenceOnline.getCreateDate()}"/></span>

                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
