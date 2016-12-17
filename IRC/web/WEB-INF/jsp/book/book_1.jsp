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
        <td>
          <a href="/book/book_main" class="toclink">Оглавление</a>
        </td>
		</tr>
      <tr>
        <th>Глава 1</th>
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
      <span style="font-size: 26pt; line-height: 115%;">1</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 26pt; line-height: 115%;">Молитва</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <span style=" line-height: 115%;">
      В 2004 
      <span> </span>
      году я пошёл молиться. Кризис в моём служении был причиной этого уединения. Нашей церкви было уже полтора года, но не было никакого роста. Каждое воскресенье на собрание приходило 130 человек и покаяние было не больше двух, трёх. Всё это повергало меня в депрессию и разочарование. Прежде чем открыть церковь в Днепропетровске , я получил виденье от Бога , я получил его в деталях, но проходило время , и ничего не происходило . Это и стало причиной моего серьёзного уединения.
      <span> </span>
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <span style=" line-height: 115%;">
      <span> </span>
      <strong>И сказал Господь Моисею : взойди ко</strong>
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">Мне на гору , и будь там; и дам тебе</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        <span> </span>
        скрижали каменные, и закон и заповеди,
      </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">которые Я написал для научения их.</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        Исход 24 :12
      </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;"> </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Эти слова приобрели для меня серьезный смысл, и я пошел. После нескольких дней молитвы Бог начал проговаривать ко мне. Первым было не откровение о служении , а требование к моему сердцу, самым первым было требование смириться. Я не видел проблемы в этом вопросе до этой молитвы, но, когда начало приходить Божье присутствие , я увидел , что я горд по отношению к двум служителям. Я смирился , и Бог сразу проговорил мне принести финансовую жертву. Нужно было опять смирение , потому что у меня не было таких денег и я никогда столько не жертвовал.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Всё это что-то производило в моем сердце и в духовном мире. Моя молитва продолжалась, и , когда всё было сделано , я начал получать от Бога откровение о служении. Я получил около 20 пунктов относительно построения служения, личного евангелизма , также наставлений личного характера. Самым главным во всём этом было послание, которое дал мне Бог,- это откровение о родовых проклятиях ... В тот день я услышал от Бога:"Весь мир лежит во зле".Это были слова из Первого послания Иоанна. 
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">Мы знаем, что мы от Бога и [что] весь мир лежит во зле.</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        1 Иоанна 5:19
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Я читал этот стих сотни раз, но в то момент я увидел его по-другому. Но в этой книге я хотел бы говорить только о том , что связано с личным евангелизмом.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      После того как я вернулся с молитвенной горы, я собрал своих лидеров и рассказал им о том , что получил в этом уединении .Шаг за шагом я рассказал наши дальнейшие действия , и мы двинулись в этом откровении. Мы не сделали ни одной уличной евангелизации , мы держались стратегии , которую получили. На наших собраниях начались массовые покаяния, стало каяться сначала по 20 , потом
      <span> </span>
      по 40 и 50 человек. Ровно через месяц на нашем собрании, вместо 130 человек, было 280. Это было настоящее чудо. Покаяние продолжается уже несколько лет, разница только в том , что растут масштабы.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Сегодня на каждом воскресном собрании мы видим покаяние около ста человек. На служениях, которые мы проводим в Ледовом дворце , самом большом зале города, где собирается
      <span> </span>
      более пяти тысяч, на покаяние выходит не менее полторы тысячи людей. На этом откровении есть реальное помазание. И если вы уже перестали верить в то, что такое возможно в вашем служении и
      <span> </span>
      вашей жизни, я хочу воодушевить вас, думаю, что Бог дал это откровение не только для нашей церкви, но и для прорыва других служений.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Многие люди сегодня отравлены идеей о том , что покаяние было в девяностых , чудеса были в девяностых , а сегодня Бог уже так не движется. Это понимание, как твердыня, пленило многих, мы оправдываем свои неудачи такими словами. Запомните - все, что мы оправдываем , будет процветать в нашей жизни , все, что мы осудим , - засохнет . Если люди критикуют успех и процветание , они не будут его иметь, нам нужно критиковать поражение и оправдывать успех. 
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