<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Город</title>
    <link rel="icon" href="/static/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/redactor.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            setInterval(function(){update_timer(new Date(${onlineLeft}));}, 1000/30);

            $('.fly_element').bind('mouseenter', function(){
                to_element(this);
            });
            $('.fly_element').bind('mouseleave', function(){
                to_element($('.fly_element.active'));
            });
            to_element($('.fly_element.active'));

            $("div#content_gallery_slider_in").smoothDivScroll({
                autoScroll: "onstart",
                autoScrollDirection: "backandforth",
                autoScrollStep: 1,
                autoScrollInterval: 15,
                startAtElementId: "startAtMe",
                visibleHotSpots: "always"
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
    </script>
</head>
<body>

<sec:authorize access="isAnonymous()">
    <jsp:include page="../new/anonymous_header.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="../new/authenticated_header.jsp"/>
</sec:authorize>

<div id="content">
    <div class="content_center_outer_out">
        <div class="content_center_outer">
            <div id="right_sidebar">
                <jsp:include page="registration.jsp"/>
                <jsp:include page="../new/tv_programm.jsp"/>
                <jsp:include page="../new/delivery.jsp"/>
                <jsp:include page="../new/banners.jsp"/>
            </div>
            <!-- /right_sidebar -->

            <div class="main_content main_content_center top bottom">
                <div id="left_column">
<table class="contentpaneopen">
  <tbody>
    <tr>
      <td valign="top">
        <p style="float:left;margin-bottom: 10px;margin-right:10px;">
          <img style="float: left;" src="/static/img/2.jpg" alt="" style="margin-right: 10px;margin-bottom: 10px;">
        </p>
        <h2>ИНФОРМАЦИЯ ПО ОТПРАВЛЕНИЮ АВТОБУСОВ</h2>
        <p>
           Дорогой друг! Если Вы хотите приехать на служение Разрушение Родового Проклятия в Дворец Спорта, и Вы не нашли в списке городов своего города, мы хотим Вам помочь. Для этого, напишите нам, или позвоните, что бы мы могли внести Ваш город в список. Или возможно у Вас есть друзья, знакомые, которые хотели бы поехать с Вами на служение, в таком случае мы сможем помочь Вам организовать автобусы из Вашего города.Пишите нам, в поле регистрации, заполните анкету или звоните по телефонам: 
          <br>
          офис Киев +3(044) 303 99 77
        </p>
          <table class="city_table" cellspacing="0" cellpadding="0">
              <tr>
                  <td style='background:#2dc1ff;'>Область</td>
                  <td style='background:#2dc1ff;'>Населенный пункт</td>
                  <td style='background:#2dc1ff;'>Время</td>
                  <td style='background:#2dc1ff;'>Место отправления</td>
                  <td style='background:#2dc1ff;'>Ответственный</td>
                  <td style='background:#2dc1ff;'>Телефон</td>
              </tr>

          </table>
      </td>
    </tr>
  </tbody>
</table>
                </div>
                <!-- /left_column -->
            </div>
            <!-- /main_content -->
        </div>
    </div>
</div>
<!-- /content -->

<a href="javascript:void(0);" id="back-top" class="scrollTop"><span>Наверх</span></a>

<jsp:include page="../new/footer.jsp"/>
<!-- /footer_out -->
</body>
</html>