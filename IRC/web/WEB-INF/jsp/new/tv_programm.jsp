<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="<c:url value="/static/js/programm_tv_online.js"/>" type="text/javascript"></script>
</head>
<body>
<div class="widget mb13">
    <span class="header tv_program">Программа ТВ</span>

    <div class="content">
        <div id="tv_program">
            <table cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td>
                        <a href="javascript:void(0);"
                           onclick="show_navig('tv_program' ,this, 'channel_1');programm_tv_online.switchSection(0,this)"
                           class="link first select" title="CNL">CNL</a>
                    </td>
                    <td>
                        <a href="javascript:void(0);"
                           onclick="show_navig('tv_program' ,this, 'channel_2');programm_tv_online.switchSection(1,this)"
                           class="link" title="LifeTV">LIFE TV</a>
                    </td>
                    <td>
                        <a href="javascript:void(0);"
                           onclick="show_navig('tv_program' ,this, 'channel_3');programm_tv_online.switchSection(2,this)"
                           class="link" title="Impact">IMPACT</a>
                    </td>
                    <td>
                        <a href="javascript:void(0);"
                           onclick="show_navig('tv_program' ,this, 'channel_4');programm_tv_online.switchSection(3,this)"
                           class="link last" title="ТЕТ">TET</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <div id="channel_1" class="down_menu" style="display: block">
                <c:forEach items="${programmTvList}" var="programmTv">
                    <div class="element">
                        <span class="elem_header">${programmTv.getDays()}</span>
                        <span class="elem_left">${programmTv.getTitle()}</span>
                        <span class="elem_right">Время<span class="fl_right"><fmt:formatDate pattern="HH:mm"
                                                                                             value="${programmTv.getDate()}"/></span></span>
                    </div>
                </c:forEach>
            </div>
            <div id="channel_2" class="down_menu" style="display: none">

            </div>
            <div id="channel_3" class="down_menu" style="display: none">

            </div>
            <div id="channel_4" class="down_menu" style="display: none">

            </div>
        </div>
    </div>
</div>
</body>
</html>
