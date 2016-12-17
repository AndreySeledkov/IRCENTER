<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    long unReadMessage = (Long) request.getSession().getAttribute("unReadMessage");
    long requestFriend = (Long) request.getSession().getAttribute("requestFriend");
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Календарь событий</title>
    <link rel="icon" href="/static/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/news.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/redactor.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/redactor.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
//            setInterval(function(){update_timer(new Date(${onlineLeft}));}, 1000/30);

            $('.fly_element').bind('mouseenter', function () {
                to_element(this);
            });
            $('.fly_element').bind('mouseleave', function () {
                to_element($('.fly_element.active'));
            });
            to_element($('.fly_element.active'));

            $("div#content_gallery_slider_in").smoothDivScroll({
                autoScroll:"onstart",
                autoScrollDirection:"backandforth",
                autoScrollStep:1,
                autoScrollInterval:15,
                startAtElementId:"startAtMe",
                visibleHotSpots:"always"
            });
        });
        function sendDelivery() {
            $.post("/feedback/delivery", { mail:$('#mail_delivery').val()},
                    function (response) {
                        if (response.complete) {
                            show_message('Отправлено', 2);
                            placeholder_imitator();
                        }
                    }, 'json')
        }

        function sendPartner() {
            $.post("/partner/send_partner", {recaptcha_challenge_field:$('#recaptcha_challenge_field').val(), recaptcha_response_field:$('#recaptcha_response_field').val(), name:$('#name').val(), mail:$('#mail').val(), phone:$('#phone').val(), country:$('#country').val(), text:$('#text').val()},
                    function (response) {
                        if (response.complete) {
                            show_message('Отправлено', 2);
                            placeholder_imitator();
                        }
                    }, 'json')
        }


    </script>
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

<sec:authorize access="isAnonymous()">
    <jsp:include page="anonymous_header.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="authenticated_header.jsp"/>
</sec:authorize>

<div id="content">
    <div class="content_center_outer_out">
        <div class="content_center_outer">
            <div id="right_sidebar">
                <jsp:include page="delivery.jsp"/>
                <jsp:include page="tv_programm.jsp"/>
                <jsp:include page="banners.jsp"/>

            </div><!-- /right_sidebar -->

            <div class="main_content main_content_center top bottom">
                <div id="left_column">
            <div class="main_content_header_small">Календарь событий</div>
            <table cellspacing="0" cellpadding="0" class="events_calendar">
                <tr class="events_calendar_header">
                    <td class="date">Дата</td>
                    <td class="time">Время</td>
                    <td class="city">Город</td>
                    <td class="place">Место проведения и название мероприятия</td>
                </tr>
                <c:forEach items="${events}" var="event" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index % 2== 0}">
                            <tr class="calendar_element white">
                        </c:when>
                        <c:otherwise>
                            <tr class="calendar_element blue">
                        </c:otherwise>
                    </c:choose>

                    <td class="date"><span class="date_top"><fmt:formatDate pattern="dd"
                                                                            value="${event.getStartEvent()}"/></span><span
                            class="date_bottom">${event.getMonth()}</span></td>
                    <td class="time"><span class="time_top">Начало:</span><span class="time_bottom"><fmt:formatDate
                            pattern="HH:mm"
                            value="${event.getStartEvent()}"/></span><span
                            class="time_top">Окончание:</span><span class="time_bottom"><fmt:formatDate pattern="HH:mm"
                                                                                                        value="${event.getEndEvent()}"/></span>
                    </td>
                    <td class="city"><a href=""><img src="/image/getimage/4/${event.getDefImageId()}" alt=""/><span
                            class="city_name">${event.getCity()}</span></a></td>
                    <td class="place"><span class="place_name">${event.getPlace()}</span><span
                            class="event_name">${event.getEventName()}</span>
                    </td>
                    </tr>
                </c:forEach>
            </table>
        </div><!-- /left_column -->
    </div><!-- /main_content -->
</div>
</div>
</div>
<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>