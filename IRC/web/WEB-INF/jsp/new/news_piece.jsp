<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Новости</title>
    <link rel="icon" href="/static/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/news.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/redactor.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/redactor.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>
    <script src="/static/js/news.js" type="text/javascript"></script>
    <script src="/static/js/jMyCarousel.js" type="text/javascript"></script>
    <script src="/static/js/jquery.lightbox.js" type="text/javascript"></script>
    <script type="text/javascript">
        function sendDelivery() {
            $.post("/feedback/delivery", { mail:$('#mail_delivery').val()},
                    function (response) {
                        if (response.complete) {
                            show_message('Отправлено', 2);
                            placeholder_imitator();
                            Recaptcha.reload();
                        }
                    }, 'json')
        }

        $(function () {

        });
        $(function () {
            $("#jMyCarousel").jMyCarousel({
                visible:'100%'
            });
            $('#jMyCarousel a').lightBox();
        });
        $(document).ready(function () {
            $(this).countdown(new Date(2012, 3, 20, 23, 59, 59));
            $('#comment_content').redactor();

        });
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
    <jsp:include page="anonymous_header_news.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="authenticated_header_news.jsp"/>
</sec:authorize>

<div id="content">
    <div class="content_center_outer_out">
        <div class="content_center_outer">
            <div id="right_sidebar">

                <div class="last_news mb13">
                    <div class="last_news_header">ПОСЛЕДНИЕ НОВОСТИ</div>
                    <c:forEach items="${lastNews}" var="item">
                        <div class="last_news_element">
                            <a href="" class="lne_image"><img src="/image/getimage/4/${item.getImageId()}" alt=""/></a>
                            <a href="/news/${item.getId()}" class="lne_header">${fn:substring(item.getTitle(),0,50)}...</a>
                <span class="lne_date"><fmt:formatDate pattern="yyyy-MM-dd"
                                                       value="${item.getStartDate()}"/></span>
                        </div>
                    </c:forEach>
                    <a href="/news" class="all_news button31 silver">Все новости<span class="r"></span></a>
                </div>

            </div><!-- /right_sidebar -->

            <div class="main_content main_content_center top bottom">
                <div id="left_column">
        <div class="news_block">
            <div class="topic_news">
                <a href="/news" class="group">${news.getPartName()}</a>
                <span class="group_element">${news.getTitle()}</span>
                <sec:authorize access="isAuthenticated()">
                    <a href="javascript:void(0)" class="group_likes"
                       onclick="news.like(1, ${like.getContentId()})">${like.getLikeCount()}</a>
                </sec:authorize>
            </div>
            <div class="author_name_topic">
                <div class="fl_right">
                    <span style="color:#aaaaaa;">Просмотров:</span>
                    <span style="margin-left: 5px;color: #000000;">${news.getViewCount()}</span>
                </div>
            </div>
            <div class="topic_data">
                ${news.getText()}
            </div>


        </div>
                </div><!-- /left_column -->
            </div><!-- /main_content -->
        </div>
    </div>
</div>
    <jsp:include page="footer.jsp"/>
    <!-- /footer_out -->
</body>
</html>




<%--<sec:authorize access="isAuthenticated()">--%>
<%--<div class="read_next">--%>
<%--<a href="javascript:void(0);"--%>
<%--onClick="$('body,html').animate({scrollTop: $('#comment_content').offset().top + 'px'}, 600, function(){$('#comment_content').focus();});"--%>
<%--class="comment_button">Комментировать</a>--%>

<%--<div class="read_next_right">--%>
<%--<a href="javascript:void(0);" onClick="show_local_message(this, 'Добавлено');"--%>
<%--class="bookmarks">Добавить--%>
<%--в--%>
<%--избранное</a>--%>
<%--<a href="#" class="twetter">&nbsp;</a>--%>
<%--<a href="#" class="facebook">&nbsp;</a>--%>
<%--${news.getDate()}--%>
<%--</div>--%>
<%--<!-- /read_next_right -->--%>
<%--</div>--%>
<%--</sec:authorize>--%>
<!-- /read_next -->



<%--<sec:authorize access="isAuthenticated()">--%>
<%--<div class="comments">--%>
<%--<c:forEach items="${comments}" var="comment" varStatus="status">--%>
<%--<c:choose>--%>
<%--<c:when test="${status.index % 2 == 0}">--%>
<%--<div class="comment silver_bg">--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--<div class="comment white_bg">--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--&lt;%&ndash;<a href="profile_other.html" class="avatar"><img src="images/tmp47.jpg" alt=""/></a>&ndash;%&gt;--%>

<%--<div class="comment_body">--%>
<%--<div class="comment_body_in">--%>
<%--<a href="#" class="comment_name">${comment.getName()}</a>--%>

<%--<div class="comment_message">--%>
<%--${comment.getText()}--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="comment_body_bottom">--%>
<%--<div class="comment_body_bottom_in">--%>
<%--<span class="comment_date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"--%>
<%--value="${comment.getDate()}"/></span>--%>

<%--&lt;%&ndash;<div class="comment_bottom_buttons">&ndash;%&gt;--%>
<%--&lt;%&ndash;<a href="" class="comment_citata">Цитировать</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;<a href="" class="comment_likes">3</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</c:forEach>--%>

<%--<span class="comment_content_header">Ваш ответ</span>--%>
<%--<textarea id="comment_content" name="comment_content"--%>
<%--style="width: 100%; height: 100px;"></textarea>--%>

<%--<div>--%>
<%--<span class="silver_shadow comment_button_send"><a href="javascript:void(0)"--%>
<%--onclick="news.comment(${news.getId()})"--%>
<%--class="gradient_silver_button comment_button_send_in">Комментировать</a></span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</sec:authorize>--%>












