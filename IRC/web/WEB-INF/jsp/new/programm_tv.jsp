<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${programmTvList}" var="programmTv">
    <div class="element">
        <span class="elem_header">${programmTv.getDays()}</span>
        <span class="elem_left">${programmTv.getTitle()}</span>
        <span class="elem_right">Время<span class="fl_right"><fmt:formatDate pattern="HH:mm"
                                                                             value="${programmTv.getDate()}"/></span></span>
    </div>
</c:forEach>
