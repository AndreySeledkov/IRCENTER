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
          <a href="/book/book_main.book_main.jsp" class="toclink">Оглавление</a>
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
        <th>Глава 14</th>
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
        <td>
          <a href="/book/book_18" class="toclink">Глава 18</a> - Личный евангелизм и дьявол
        </td>
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
      <span style="font-size: 20pt; line-height: 115%;">14</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Личный евангелизм </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">и судьба</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;"> </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">Не сокрыты были от Тебя кости мои,</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        когда я созидаем был в тайне образуем 
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        был во глубине утробы.
      </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        Псалом 138:15
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
      </span>
    </strong>
    <span style=" line-height: 115%;">Писание говорит, что есть судьба, которую Господь написал для каждого из нас еще до сотворения мира. Давайте поразмышляем : у людей, которые не знают Бога, тоже ведь есть судьба, но это судьба от дьявола, у которого есть определенный план. План Бога – чтобы человек покаялся, обрел вечное спасение, стал новым счастливым человеком и принес плод в Царство Божье.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      План дьявола – чтобы человек жил неправильно , страдал , болел, имел постоянные проблемы , боль , страх и в конце концов умер от страшной болезни и пошел в ад.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Что делает личный евангелизм ?
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;"> </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Личный евангелизм отменяет все планы сатаны и</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">приносит в жизнь человека планы Бога. </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      В личном евангелизме сокрыта великая власть от Бога, поэтому Христос и говорил : «Кому простите грехи, тому простятся. А на ком оставите, на том останутся». Ты являешься здесь , на земле, тем , кто определяет дальнейшую участь человека, куда он пойдет : или на небеса, или в геенну огненную .
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Бог начинает действовать в жизни человека только тогда, когда мы приходим к людям и говорим о Христе. Была одна история, когда Авраам пришел к Богу и спросил: «Уничтожишь ли Ты весь город, если там будет 50 праведников?».Бог ответил: «Нет, не уничтожу». – «А если 45, а если 40?» - так Авраам дошел до 10. Суть в чем?
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Судьбу города определяют праведники. Из-за того, что нет личного евангелизма , сегодня миллионы людей на грани отчаяния, тысячи семей находятся в разводах, дети остаются сиротами, люди болеют, страдают и идут в ад.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Причина в том ,что дьявол украл у некоторых верующих сердцевину и смысл их жизни – спасение людей. Он хочет сделать из нас фарисеев, людей, которые будут просто ходить в церковь и давать десятину.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Но 
      <strong>Бог не создавал такую Церковь, Он создал собрание первенцев , торжествующий собор , который несет славу Бога в этот мир.</strong>
       Это простые люди, но на них помазание Духа Святого, они идут в дома и на личном уровне отменяют все приговоры сатаны, разрушают его дела и изменяют судьбы людей.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Одна женщина имела на своей жизни приговор дьявола, на нее легло сильнейшее родовое проклятие. Когда из жизни ушла ее мама, прямо во время похорон она ощутила , как в нее вошел дух – это был демон смерти. Буквально сразу после этого в ее теле стали прогрессировать болезни, от которых умерла ее мать. Ее мать умерла от инсульта , при этом имея сахарный диабет. То же самое
      <span> </span>
      появилось у нее. В течении года ее тело было разбито, врачи, больницы – все было бессильно. В то время, когда доктора ставили ей 16 диагнозов, не было никакой надежды. Смерть была неизбежным заключением всей этой истории. Ее просьбой к родным было желание умереть на родной земле. С далекого Севера ее привезли в Украину. Когда самолет приземлился в Москве, у трапа встречала «скорая помощь», в самолете она едва не умерла. Прибыв в Днепропетровск и доживая свои последние дни, она услышала о Духовном центре «Возрождение». Один человек стал ей свидетельствовать , нести Евангелие. Он рассказал ей о тех чудесах, которые совершает Бог на этих собраниях. Так как она не могла ходить , ее посадили в машину и привезли на собрание. В первое служение ничего не произошло, но сила свидетельства этого человека вселяла в нее веру, и она поехала на собрание во второй раз.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Ее привозили на собрание пять раз подряд, она рассказывала, как во время молитвы на пятом собрании тепло прошло по ее телу, оня поняла , что что-то произошло. После собрания , находясь дома, она ощутила , что в ее теле происходят сильные перемены. Она позвала свою дочку и сказала ей : «Дочь, я исцелена».
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Уже на следующем собрании она вышла на сцену и свидетельствовала о том, что совершил Бог в ее жизни, прямо на сцене она упала на колени и сказала : «Бог ,спасибо Тебе за жизнь». Личный евангелизм одного человека отменил приговор дьявола в жизни этой женщины. Сегодня она жива и здорова.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Мой брат, через твое свидетельство Бог будет отменять планы дьявола и воплощать в жизни людей Свои планы.
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