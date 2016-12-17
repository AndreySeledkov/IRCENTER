<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Vo.org.ua - Духовный Центр Возрождение прямая трансляция служения официальный сайт Владимира Мунтяна</title>
    <link type="image/x-icon" rel="shortcut icon" href="/static/img/favicon.ico">
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <link type="text/css" href="/static/css/news.css" rel="stylesheet">
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>
    <script src="/static/js/svid_online.js" type="text/javascript"></script>
    <script src="/static/js/openapi.js" type="text/javascript"></script>
    <script src="/static/js/all.js" type="text/javascript"></script>
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
                <div class="widget mb13">
                    <jsp:include page="timeout_left.jsp"/>

                    <!-- /timeout_left -->
                </div>
                <jsp:include page="tv_programm.jsp"/>
                <jsp:include page="delivery.jsp"/>

                <jsp:include page="banners.jsp"/>


                <a href="/book/book_main" class="widget mb13 abs_position book">
                    <span class="header abs_position">ЧИТАЕМ ОН-ЛАЙН</span>
                    <div class="content abs_position"><img src="/static/img/tmp143.png" alt=""/></div>
                </a>
            </div>

            <div class="main_content main_content_center top bottom">
                <div id="left_column">
                    <div class="news_block">
                        <div class="topic_news">
                            <span class="group_element">Биография пастора Владимира Мунтяна</span>
                            <%--<a href="#" class="group_likes">78</a>--%>
                        </div>
                        <div class="topic_data">
                            <p>
                                <img style="margin: 15px; float: left;" src="/static/img/pastor.jpg">
                                Владимир Мунтян является основателем и старшим пастором Духовного Центра «Возрождение» в г. Днепропетровске с 2002 года. Автор многочисленных проповедей, книг, авторских телепрограмм и онлайн трансляций, Владимир Мунтян сегодня служит тысячам людей. Имея откровение от Бога о личности Святого Духа, о Его могущественной силе, разрушающей проклятия, пастор Владимир Мунтян проводит служения разрушения проклятий и исцеления в городах Украины. Каждую субботу такие служения проходят в г. Киеве в ДК Королева, в 14.00, и в г. Днепропетровске в Храме Святого Духа в 11.00.Кроме этого около тысячи человек каждое воскресенье участвуют в богослужении во время онлайн трансляции. Более 150 телевизионных программ ежемесячно служат духовной пищей и ответом для тысяч людей в разных уголках мира.
                            </p>
                            <p>   В самом большом зале г. Днепропетровска за несколько лет состоялось 11 служений разрушения проклятий и исцеления и одно служение на стадионе. 7 июля в городе Киеве во Дворце спорта произойдет грандиозное богослужение с молитвой за исцеление.</span>
                            <p>  Тысячи людей, примирившихся с Богом, огромное множество свидетельств об исцелении неизлечимых болезней, разрушении проклятий, измененных судьбах, восстановленных семьях – плоды служения пастора Владимира и Виктории Мунтян.</p>
                            <p>  В декабре пастор Владимир и его жена Виктория отметят 23-летие семейной жизни. Они воспитывают троих прекрасных детей: Даниила, Арину и Виолетту.</span>
                            <center>
                                <img src="/static/img/pasisem.jpg" alt="">
                            </center>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp"/>
</body>
</html>