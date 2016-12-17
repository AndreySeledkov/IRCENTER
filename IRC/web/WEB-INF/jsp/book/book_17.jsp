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
          <a href="/book/book_4clink">Глава 4</a> - Важность евангелизма
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
          <a href="/book/book_15link">Глава 15</a> - Личный евангелизм и грех
        </td>
      </tr>
      <tr>
        <td>
          <a href="/book/book_16" class="toclink">Глава 16</a> - Личный евангелизм и исповедание
        </td>
      </tr>
      <tr>
        <th>Глава 17</th>
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
      <span style="font-size: 20pt; line-height: 115%;">17</span>
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
        <span style="font-size: 18pt; line-height: 115%;">и ходатайство</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">Итак всякого, кто исповедает Меня</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">пред людьми, того исповедаю и Я пред</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">Отцом Моим Небесным …</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        Матфея 10:32
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Наш самый главный Ходатай – это Иисус Христос, Который постоянно стоит перед престолом Отца и ходатайствует за нас.Он наш Адвокат, наш Защитник.Его благодатью мы спасены .Как это происходит в духовном мире ?Во времена Ветхого Завета для того, чтобы грех был прощен, человек брал тельца или ягненка, приходил в храм и отдавал жертву священнику, затем человек возлагал свои руки на голову животного и говорил, исповедовал все свои грехи. После этого священник перерезал горло животному и его кровью кропил жертвенник. Грех был искуплен кровью, и тогда человек уходил домой прощенным.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      За грехи людей убивали животных, но вот Иоанн Креститель говорит : «Вот Агнец Божий…» - он говорил об Иисусе. И Писание говорит, что Христос вошел в храм не с кровью тельцов, но со Своею кровью вошел в Небесное Святилище. Есть храм на Небе, когда Моисей строил скинию, он увидел ее образ в небе и построил в точности так, как увидел. Представьте, в Небесный Храм вошел Сын Божий со Своею кровью,пролитой за тебя и меня.И Он встал на нашу защиту, провозгласив перед всем духовным миром : «Я был распят за этих людей, Я заплатил за их грехи». Иисус ходатайствует за нас перед Отцом.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Но когда это ходатайство начинается ? Когда мы исповедуем Иисуса перед людьми, тогда Он исповедует наши имена перед Отцом Небесным. Каждый день, 24 часа в сутки, нам необходимо , чтобы кровь Христа нас покрывала. Если бы мы могли своей праведностью достигнуть спасения, Иисусу не нужно было бы умирать , но Писание говорит, что никто не может «оправдаться законом». Только осознавая на 100 %, что я грешник , я могу принять кровь Христа. Более того, Библия открывает, что самонадеянные , 
      <span> </span>
      самоправедные люди не могут быть спасены через кровь Христа.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">Если же живое мясо изменится и </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">обратится в белое, пусть он придет к</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">священнику ; священник осмотрит его,</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">и если язва обратилась в белое,</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">священник объявит больного чистым;</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">он чист.</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        Левит 13:16-17
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Это закон о проказе. Прокаженный человек приходит к священнику, и священник тщательно осматривает тело этого человека с головы до самых ног. Если его тело было покрыто проказой не полностью и был виден хоть кусочек живого мяса – человек объявлялся нечистым. Но если его тело было покрыто проказой абсолютно и не было даже сантиметра живого мяса – человек объявлялся чистым. Парадокс.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Но на самом деле это яркий прообраз того, что происходит с нами : проказа символизирует грех , а живое мясо – нашу самоправедность , и если мы,приходя к
      <span> </span>
      Богу, хоть как-то оправдываемся – мы остаемся нечистыми. Но если мы осознаем себя полностью грешниками, при этом ни на грамм не оправдываясь, - Священник объявляет нас чистыми, мы полностью оправданы. Нам нечего показать Богу, наша праведность, как запачканная одежда.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Когда мы это осознаем, Господь говорит : «Теперь ты оправдан!» Когда мы исповедуем Христа, мы уже в этот момент вписаны в список тех , кого очищает и оправдывает кровь Христа. Без исповедания нет действия крови. Он ходатайствует за нас, потому что мы несем весть о Нем другим людям. Самое главное в нашей проповеди – весть о вечной жизни. Свою кровь Христос пролил именно ради вечности, чтобы спасти нас от ада. Когда я несу Евангелие – я несу вечное Евангелие.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Итак, если ты спасаешь людей, у тебя есть Ходатай, Ходатай Нового Завета.Поэтому , когда в жизни человека прекращается личный евангелизм, приходят проблемы, теряется Божье покровительство.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Личный евангелизм проводит линию защиты</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">в нашей жизни.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Суть ходатайства заключается в том, что Христос видит твои завтрашние дни , видит годы, которые впереди, Он стоит над временем и, зная все, что может произойти в твоей жизни, получает от Отца полную аннуляцию планов сатаны относительно твоей судьбы.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Своим личным евангелизмом ты прокладываешь</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">путь в свое будущее.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">…Подвизайся добрым подвигом веры,</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">держись вечной жизни, к которой ты и </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">призван и исповедал доброе исповедание </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">пред многими свидетелями. Пред Богом,</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">
        все животворящим,
        <span> </span>
        и пред Христом
      </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">Иисусом, Который засвидетельствовал </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">пред Понтием Пилатом доброе </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">Исповедание , завещеваю тебе соблюсти</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">заповедь чисто и неукоризненно , даже </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">до явления Господа нашего Иисуса Христа…</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        1 Тимофею 6 : 12-14
      </span>
    </strong>
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