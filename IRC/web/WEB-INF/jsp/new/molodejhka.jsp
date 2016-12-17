<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Просмотр</title>
    <link rel="icon" href="/static/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/video.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $(this).countdown(new Date(2012, 2, 10, 23, 59, 59));
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
    <jsp:include page="anonymous_header.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="authenticated_header.jsp"/>
</sec:authorize>


<div id="content_video_out" style="background:url(/static/img/molodejhka_bg.jpg) repeat;">
    <div id="content_video">
        <img src="/static/img/molodejhka_logo.png" alt=""/>

        <div class="video_main_content">
            <div style="overflow:hidden;">
                <div id="video_content_main">
                    <div class="video_box">
                        ${curEl.getLink()}

                        <%--<div class="sending_block">--%>
                        <%--<div class="in_block url">--%>
                        <%--<input type="text" value="http://irc.ua//watch?v=65cE" class="url style">--%>

                        <%--<div class="tv_params">--%>
                        <%--<input type="checkbox"> Начать с <input type="text" value="2:03" class="time style">--%>
                        <%--</div>--%>
                        <%--<a href="javascript:void(0);" onClick="tv_params_block_show(this);"--%>
                        <%--class="fl_right mini_link"><span>Параметры</span> <img--%>
                        <%--src="/static/img/arrow_link_down.png" alt=""/></a>--%>
                        <%--</div>--%>
                        <%--<div class="in_block separate">&nbsp;</div>--%>
                        <%--<div class="in_block url">--%>
                        <%--<span class="no_shadow ml10 fl_left"><a href="#"--%>
                        <%--class="gradient_silver_button add_video">Встроить--%>
                        <%--на сайт <img src="images/add_to_site.png" alt=""/></a></span>--%>
                        <%--</div>--%>
                        <%--<div class="in_block separate">&nbsp;</div>--%>
                        <%--<div class="in_block url">--%>
                        <%--<a href="#"><img src="/static/img/social_add_facebook.png" alt=""/></a>--%>
                        <%--<a href="#"><img src="/static/img/social_add_vkontakte.png" alt=""/></a>--%>
                        <%--<a href="#"><img src="/static/img/social_add_odnoklassniki.png" alt=""/></a>--%>
                        <%--<a href="#"><img src="/static/img/social_add_mail.png" alt=""/></a>--%>
                        <%--<a href="#" class="mini_link disp" style="margin-top: 5px;">Еще <img--%>
                        <%--src="/static/img/arrow_link_down.png" alt=""/></a>--%>
                        <%--</div>--%>
                        <%--<a href="javascript:sending_block_show();" class="fl_right close"><img--%>
                        <%--src="/static/img/cross.png" alt=""/></a>--%>
                        <%--</div>--%>
                    </div>
                </div>
                <div id="video_right_sidebar">

                    <div class="video_right_sidebar_element">
                        <div class="m10">
                            <h4>Молодежка - это движение!</h4>

                            <p>Ты молодой, креативный, яркий,
                                талантливый?
                                Тебе сюда!
                                Мы мечтаем так же, как и ты!
                                Мы - святая молодёжь, имеющая чистые
                                сердца, огненный дух, яркий свет
                                в глазах, мы те, рождённые свыше,
                                которые имеют власть двигать Небом!!!
                                У нас есть сила, энергия Святого Духа,
                                которая проходит сквозь сердца,
                                и оставляет Небесные отпечатки,
                                небесные образы. Здесь оживают
                                давно забытые мечты, надежды.
                                Здесь ты обретешь судьбу от Царя царей.
                                И придёт время, когда каждый из нас
                                станет тем, о ком мечтает Небо. Нас
                                невозможно остановить! Мы идём вперёд,
                                бежим, кричим, любим, молимся,
                                провозглашаем, верим. Мы изменим
                                наши города!Мы вместе круто отдыхаем,
                                общаемся, и обретаем друзей, вместе
                                на праздниках и тусовках, вместе
                                в одной большой Божьей семье.
                                Знай, ты не один, мы ждём тебя!</p>

                            <p>Приходи каждый четверг
                                на улицу Рабочая 23в. Начало в 18:00!</p>
                        </div>
                    </div>
                    <!-- /video_right_sidebar_element -->
                </div>
                <!-- /video_right_sidebar -->
            </div>
            <div class="video_bottom_text">
                    <img width="300px" height="234px" style="float:right;"
                         data-thumb="http://img.youtube.com/vi/${curEl.getYoutubeId()}/0.jpg"
                         alt="Значок видео"
                         src="http://img.youtube.com/vi/${curEl.getYoutubeId()}/0.jpg"
                         data-group-key="thumb-group-0">

                    <h2 style="margin-top:0px;">${curEl.getDescription()}</h2>
                    ${curEl.getText()}

            </div>
            <div id="navig_menu_tv" class="navig_menu_tv">
                <div class="links">
                    <a href="javascript:void(0);" class="link select">Видео</a>
                </div>
                <div id="video" class="down_menu" style="display: block">
                    <div class="video_elements pt20">
                        <c:forEach items="${videos}" var="item">
                            <a href="/molodejhka?p=${curPage + 1}&id=${item.getId()}" class="video_element">
                                <div class="img_abs">
                                    <img data-thumb="http://img.youtube.com/vi/${item.getYoutubeId()}/0.jpg" alt="Значок видео"
                                         src="http://img.youtube.com/vi/${item.getYoutubeId()}/0.jpg"
                                         data-group-key="thumb-group-0">
                                </div>
                                <span class="title">${item.getDescription()}</span>
                            </a><!-- /video_element -->
                        </c:forEach>
                    </div>
                    <!-- /video_elements -->
                    <jsp:include page="pagination.jsp"/>
                </div>
            </div>
        </div>
    </div>
    <!-- /content_video -->
</div>
<!-- /content_video_out -->

<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>