<%@ page import="org.ircenter.server.service.user.UserHelper" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    long unReadMessage = (Long) request.getSession().getAttribute("unReadMessage");
    long requestFriend = (Long) request.getSession().getAttribute("requestFriend");
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Vo.org.ua - Духовный Центр Возрождение</title>
    <link rel="icon" href="/static/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>
    <script src="<c:url value="/static/js/svid_online.js"/>" type="text/javascript"></script>
    <script src="/static/js/openapi.js" type="text/javascript"></script>
    <script src="/static/js/all.js" type="text/javascript"></script>
    <script src="/static/js/news.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            setInterval(function(){update_timer(new Date(${onlineLeft}));}, 1000/30);

            $('.fly_element').bind('mouseenter', function () {
                to_element(this);
            });
            $('.fly_element').bind('mouseleave', function () {
                to_element($('.fly_element.active'));
            });
            to_element($('.fly_element.active'));

//            $("div#content_gallery_slider_in").smoothDivScroll({
//                autoScroll:"onstart",
//                autoScrollDirection:"backandforth",
//                autoScrollStep:1,
//                autoScrollInterval:15,
//                startAtElementId:"startAtMe",
//                visibleHotSpots:"always"
//            });
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
    <jsp:include page="anonymous_header_main.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="authenticated_header_main.jsp"/>
</sec:authorize>

<div id="slideshow">
    <div id="slider">
        <c:forEach var="slider" items="${sliders}" varStatus="status">
            <div class="active" style="background-image:url(/image/getimage/0/${slider.getImgId()});">

            </div>
        </c:forEach>

    </div>
    <!-- /slider -->
    <div id="slides_control_out">
        <div id="slides_control">
            <c:forEach var="slider" items="${sliders}" varStatus="status">
                <a href="javascript: ToggleSlide(${status.count});" class="active"></a>
            </c:forEach>

        </div>
        <!-- /slides_control -->
    </div>
    <!-- /slides_control_out -->
</div>
<!-- /slideshow -->


<div id="content">


<div id="fly_menu">
    <div id="fly_bg">
        <div>
            <span class="l">&nbsp;</span>
            <span class="r">&nbsp;</span>
        </div>
    </div>
    <!-- /fly_bg -->


    <a id="fly_one" href="/signup" class="fly_element first">
        <div class="fl_left">
            <span class="top">Бесплатная</span>
            <span class="bottom">РЕГИСТРАЦИЯ</span>
        </div>
        <img src="/static/img/img_ava_1.png" class="fl_right" alt=""/>
    </a>
    <a id="fly_two" href="/partner" class="fly_element">
        <div class="fl_left">
            <span class="top">Партнерская</span>
            <span class="bottom">ПРОГРАММА</span>
        </div>
        <img src="/static/img/img_ava_2.png" class="fl_right" alt=""/>
    </a>
    <a id="fly_three" href="/calendar" class="fly_element">
        <div class="fl_left">
            <span class="top">Календарь</span>
            <span class="bottom">СОБЫТИЙ</span>
        </div>
        <img src="/static/img/img_ava_3.png" class="fl_right" alt=""/>
    </a>
    <a id="fly_four" href="/tv_online" class="fly_element active last">
        <div class="fl_left">
            <span class="top">Интернет церковь</span>
            <span class="bottom">ПРЯМОЙ ЭФИР</span>
        </div>
        <img src="/static/img/img_ava_4.png" class="fl_right" alt=""/>
    </a>
</div>

<div class="clear_fix"></div>
<div class="main_content top bottom">
    <div class="elem_out ml13 mt13 mr13">
        <div class="afisha">АФИША</div>
        <div class="top_element">
            <div class="in_top_element">
                <a href="/molodejhka"><img src="/static/img/tmp22.jpg" alt=""/></a>
            </div>
        </div>
    </div>
    <div class="elem_out mt13 mr13">
        <div class="our_read">НАШИ ПАСТОРЫ</div>
        <div class="top_element">
            <div class="in_top_element wauto">
                <a href="/biography"><img src="/static/img/m1.jpg" alt=""/></a>
                <a href="/biography"><img src="/static/img/m2.jpg" alt=""/></a>
            </div>
        </div>
    </div>
    <div class="elem_out mt13">
        <div class="certificates">ИСЦЕЛЕНИЯ</div>
        <div class="top_element">
            <div class="in_top_element right_sidebar_elem">
                <c:forEach items="${textEvidenceList}" var="textEvidence">
                    <a href="/text_evidence?id=${textEvidence.getId()}" class="certificates_link">
                        <div><img src="/image/getimage/4/${textEvidence.getDefaultImage()}" alt=""/></div>
                        <span class="link_header">${textEvidence.getTitle()}</span>
                        <span class="link_description">${textEvidence.getText()}</span>
                    </a>
                    <hr class="px1"/>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
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

    <div class="widget mb13">
        <div id="vk_groups"></div>
        <script type="text/javascript">
            VK.Widgets.Group("vk_groups", {mode:0, width:"279", height:"290"}, 20168308);
        </script>
    </div>

    <div class="widget mb13">
        <iframe src="//www.facebook.com/plugins/likebox.php?href=http://www.facebook.com/regeneration.in.ua&amp;width=292&amp;height=350&amp;colorscheme=light&amp;show_faces=true&amp;
    border_color&amp;header=true" scrolling="no" frameborder="0"
                style="border:none;overflow:hidden;width:279px;height:350px;"></iframe>
    </div>
</div>
<!-- /right_sidebar -->

<div class="main_content main_content_center top bottom">
<div id="left_column">
<div id="navig_menu">
<table cellspacing="0" cellpadding="0">
    <tr>
        <td><a href="javascript:void(0);" class="link select" onClick="show_navig('navig_menu', this, 'reg_bus');"
               style="border-bottom: none;border-radius: 5px;font-weight: bold;">Регистрация на дворец спорта</a></td>
        <td><a href="javascript:void(0);" class="link"
               onClick="show_navig('navig_menu', this, 'news_embed');">Новости</a>
        </td>
        <td><a href="javascript:void(0);" class="link"
               onClick="show_navig('navig_menu', this, 'svid_online');svid_online.switchSection(2,this)"
               style="border-bottom: none;border-radius: 5px;">Свидетельства онлайн</a></td>
    </tr>
</table>
<div id="reg_bus" class="down_menu" style="display:block;">
<div class="archive_block for_index">
<center><img src="/static/img/bus.jpg" alt=""/></center>
<center style="font-size: 16px;" class="mb20">Офис Киев <b>+3(044) 303 99 77</b></center>
<div class="all_news_element">
    <a href="/city/vinnica" class="all_news_img"><img src="/static/img/1.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/vinnica" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Винницкая
            обл.</a>
        <a href="/city/vinnica" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/vinnica" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/volin" class="all_news_img"><img src="/static/img/2.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/volin" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Волынская
            обл.</a>
        <a href="/city/volin" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/volin" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/dnepr" class="all_news_img"><img src="/static/img/ua.jpg" alt="" width="66" height="66"/></a>

    <div class="topic topic_all_news">
        <a href="/city/dnepr" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Днепропетровская
            обл.</a>
        <a href="/city/dnepr" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/dnepr" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/doneck" class="all_news_img"><img src="/static/img/ua.jpg" alt="" width="66" height="66"/></a>

    <div class="topic topic_all_news">
        <a href="/city/doneck" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Донецкая
            обл.</a>
        <a href="/city/doneck" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/doneck" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/jitomir" class="all_news_img"><img src="/static/img/5.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/jitomir" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Житомирская
            обл.</a>
        <a href="/city/jitomir" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/jitomir" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/zakarp" class="all_news_img"><img src="/static/img/6.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/zakarp" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Закарпатская
            обл.</a>
        <a href="/city/zakarp" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/zakarp" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/zaporoje" class="all_news_img"><img src="/static/img/7.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/zaporoje" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Запорожская
            обл.</a>
        <a href="/city/zaporoje" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/zaporoje" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/ivano" class="all_news_img"><img src="/static/img/8.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/ivano" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Ивано-Франковская
            обл.</a>
        <a href="/city/ivano" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/ivano" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/kyev" class="all_news_img"><img src="/static/img/9.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/kyev" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Киевская
            обл.</a>
        <a href="/city/kyev" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/kyev" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/kirovograd" class="all_news_img"><img src="/static/img/10.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/kirovograd" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Кировоградская
            обл.</a>
        <a href="/city/kirovograd" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/kirovograd" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/krym" class="all_news_img"><img src="/static/img/11.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/krym" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Крымская
            обл.</a>
        <a href="/city/krym" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/krym" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/lugansk" class="all_news_img"><img src="/static/img/12.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/lugansk" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Луганская
            обл.</a>
        <a href="/city/lugansk" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/lugansk" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/lvov" class="all_news_img"><img src="/static/img/13.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/lvov" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Львовская
            обл.</a>
        <a href="/city/lvov" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/lvov" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/nikolaev" class="all_news_img"><img src="/static/img/14.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/nikolaev" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Николаевская
            обл.</a>
        <a href="/city/nikolaev" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/nikolaev" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/odessa" class="all_news_img"><img src="/static/img/15.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/odessa" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Одесская
            обл.</a>
        <a href="/city/odessa" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/odessa" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/poltava" class="all_news_img"><img src="/static/img/16.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/poltava" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Полтавская
            обл.</a>
        <a href="/city/poltava" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/poltava" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/rovensk" class="all_news_img"><img src="/static/img/17.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/rovensk" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Ровенская обл.</a>
        <a href="/city/rovensk" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/rovensk" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/summi" class="all_news_img"><img src="/static/img/18.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/summi" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Сумская
            обл.</a>
        <a href="/city/summi" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/summi" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/ternopol" class="all_news_img"><img src="/static/img/19.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/ternopol" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Тернопольская
            обл.</a>
        <a href="/city/ternopol" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/ternopol" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/charkov" class="all_news_img"><img src="/static/img/20.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/charkov" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Харьковская
            обл.</a>
        <a href="/city/charkov" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/charkov" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/cherson" class="all_news_img"><img src="/static/img/21.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/cherson" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Херсонская
            обл.</a>
        <a href="/city/cherson" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/cherson" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/chmelnick" class="all_news_img"><img src="/static/img/22.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/chmelnick" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Хмельницкая
            обл.</a>
        <a href="/city/chmelnick" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/chmelnick" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/cherkassi" class="all_news_img"><img src="/static/img/23.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/cherkassi" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Черкасская
            обл.</a>
        <a href="/city/cherkassi" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/cherkassi" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/chernigov" class="all_news_img"><img src="/static/img/24.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/chernigov" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Черниговская
            обл.</a>
        <a href="/city/chernigov" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/chernigov" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/chernov" class="all_news_img"><img src="/static/img/25.jpg" alt="" width="66" height="66"/></a>
    <div class="topic topic_all_news">
        <a href="/city/chernov" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Черновицкая
            обл.</a>
        <a href="/city/chernov" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/chernov" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/russia" class="all_news_img"><img src="/static/img/ru.jpg" alt="" width="66" height="66"/></a>

    <div class="topic topic_all_news">
        <a href="/city/russia" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Россия</a>
        <a href="/city/russia" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/russia" class="read_next_button">Подробнее ></a>
    </div>
</div>

<div class="all_news_element">
    <a href="/city/belarus" class="all_news_img"><img src="/static/img/bel.jpg" alt="" width="66" height="66"/></a>

    <div class="topic topic_all_news">
        <a href="/city/belarus" class="group"><img src="/static/img/bus.png" alt="" style="vertical-align: middle;"/>Беларусия</a>
        <a href="/city/belarus" class="group_element" style="font-size: 14px;">ОТПРАВЛЕНИЕ АВТОБУСОВ</a>
    </div>
    <div class="read_next_all_news">
        <a href="/city/belarus" class="read_next_button">Подробнее ></a>
    </div>
</div>

</div>
</div>
<div id="news_embed" class="down_menu">
    <jsp:include page="embed_news.jsp"/>
</div>
<div id="svid_online" class="down_menu">

</div>
</div>
<!-- /navig_menu -->
</div>
<!-- /left_column -->
</div>
<!-- /main_content -->
</div>
</div>
<jsp:include page="shop_slide.jsp"/>
</div>
<!-- /content -->

<a href="javascript:void(0);" id="back-top" class="scrollTop"><span>Наверх</span></a>

<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>