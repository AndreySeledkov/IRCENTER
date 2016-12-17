<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Книга</title>
    <link type="image/x-icon" rel="shortcut icon" href="/static/img/favicon.ico">
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
        function sendDelivery() {
            $.post("/feedback/delivery", { mail:$('#mail_delivery').val()},
                    function (response) {
                        if (response.complete) {
                            show_message('Отправлено', 2);
                            placeholder_imitator();
                        }
                    }, 'json')
        }
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
    </script>
</head>
<body>
<sec:authorize access="isAnonymous()">
    <jsp:include page="../new/anonymous_header.jsp" />
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="../new/anonymous_header.jsp" />
</sec:authorize>

<div id="content">
<div class="content_center_outer_out">
<div class="content_center_outer">
<div id="right_sidebar">
    <jsp:include page="../new/tv_programm.jsp"/>
    <jsp:include page="../new/delivery.jsp"/>
    <jsp:include page="../new/banners.jsp"/>
</div>
<!-- /right_sidebar -->

<div class="main_content main_content_center top bottom">
<div id="left_column">
	<center><h1>Личный Евангелизм</h1></center>
  <table cellpadding="0" cellspacing="0" class="contenttoc">
    <tbody>
      <tr>
          <a href="/book/book_main" class="toclink">Оглавление</a>
      </tr>
      <tr>
        <td>
          <a href="/book/book_1" class="toclink">Глава 1</a> - Молитва
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_2" class="toclink">Глава 2</a> - Всё начинается в молитве
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_3" class="toclink">Глава 3</a> - Сила желания
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_4" class="toclink">Глава 4</a> - Важность евангелизма
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_5" class="toclink">Глава 5</a> - Личный евангелизм
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_6" class="toclink">Глава 6</a> - Евангелие – хорошая новость
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_7" class="toclink">Глава 7</a> - Правила личного евангелизма. Как нести Евангелие на личном уровне
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_8" class="toclink">Глава 8</a> - Сначала взаимоотношения, потом взаимодействие
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_9" class="toclink">Глава 9</a> - Списки знакомых
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_10" class="toclink">Глава 10</a> - Планирование времени
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_11" class="toclink">Глава 11</a> - Посещения по домам
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_12" class="toclink">Глава 12</a> - Личный евангелизм – основание роста церкви
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_13" class="toclink">Глава 13</a> - Личный евангелизм и помазание
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_14" class="toclink">Глава 14</a> - Личный евангелизм и судьба
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_15" class="toclink">Глава 15</a> - Личный евангелизм и грех
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_16" class="toclink">Глава 16</a> - Личный евангелизм и исповедание
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_17" class="toclink">Глава 17</a> - Личный евангелизм и ходатайство
        </td>
      </tr>
      <tr>
        <th>Глава 18</th>
      </tr>
      <tr>
        <td>
          <a href="/book/book_19" class="toclink">Глава 19</a> - Личный евангелизм и дела
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_20" class="toclink">Глава 20</a> - Личный евангелизм и Дух Святой
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_21" class="toclink">Глава 21</a> - Личный евангелизм и страх
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_22" class="toclink">Глава 22</a> - Кто такие неверующие ?
        </td>
      </tr>
    </tbody>
  </table>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 20pt; line-height: 115%;">18</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Личный евангелизм и дьявол</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style=" line-height: 115%;"> </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">…Они победили его кровию Агнца и сло-</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        вом свидетельства своего и не возлю-
      </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        били души своей даже до смерти
      </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        <span> </span>
        <span> </span>
        Откровение 12:11
      </span>
    </strong>
    <strong> </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;" lang="EN-US"> </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;">Личный евангелизм – это провозглашение крови Христа, свидетельство о нашей победе над дьяволом и полное посвящение. Мы должны твердо держаться своего исповедания, своего свидетельства. Нужно очень сильно следить за своим сердцем, за своими мыслями, словами, поступками, иначе мы можем моментально потерять ту позицию, тот уровень, который был. То есть исповедание и свидетельство должны быть постоянны.</span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;">
      <span> </span>
      Давид говорит: «Во всякое время призову Господа, во всякое время буду славить Его, во всякое время буду говорить».Свидетельство и только свидетельство разрушает все дела дьявола во всех сферах нашей жизни.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style=" line-height: 115%;">Мы свидетели Иисуса Христа на этой земле</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style=" line-height: 115%;">и для этих людей</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">И иногда свидетельство становится и для нас этим спасительным канатом, который выравнивает наше внутреннее состояние и поднимает веру. «Что у вас произошло? Какое чудо сделал Господь</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      В вашей жизни?»-задал я вопрос и с трепетом ожидал еще одного яркого свидетельства на этом служении. Сестра очень сильно нервничала, но, переборов волнение, поделилась:
      <span> </span>
      «У меня была проблема со щитовидной железой уже много лет ,я пришла на служение и услышала, как одна сестра свидетельствовала о полном исцелении щитовидной железы. Я поверила, что Бог может и меня исцелить, Дух Святой прикоснулся ко мне, и теперь я полностью здорова, вот подтверждение врачей».
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Однажды в моей жизни произошел такой случай. Это было более десяти лет назад ,еще в начале моего пасторского служения. В то время я был в другом городе, и наша молодая церковь почти ежедневно делала евангелизации с колонками и аппаратурой.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Мы ездили по всей округе, проповедуя Евангелие. Именно в то время у нас было множество различных проблем. Казалось, что все бесы и демоны восстали и ополчились против нас. Однажды я размышляя над этим, как вдруг кто-то проговорил в мой разум: «Если ты умеришь свой пыл и перестанешь делать эти еванге-лизации, я сделаю так, что все проблемы уйдут» Я сразу понял, чьи это слава, - это был дьявол.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Это наглое предложение привело меня в сильное негодование, и я твердо ответил: «Дьявол, этого никогда не будет. Теперь я буду проповедовать в два раза больше И даже если проблем станет еще больше – это меня никогда не остановит!» С того момента мы действительно стали проповедовать в два раза больше и сильнее. И что сделал дьявол? Стало ли проблем больше? Нет,- он в очередной раз потерпел поражение. И чем больше я проповедовал, тем больше славы Божьей видел в своей жизни. А дьявол - лжец, у него одна цель- остановить тебя любой ценой.
    </span>
  </p>

</div>
<!-- /left_column -->
</div>
<!-- /main_content -->
</div>
</div>
<jsp:include page="../new/shop_slide.jsp"/>
</div>
<!-- /content -->

<a href="javascript:void(0);" id="back-top" class="scrollTop"><span>Наверх</span></a>

<jsp:include page="../new/footer.jsp"/>
<!-- /footer_out -->
</body>
</html>