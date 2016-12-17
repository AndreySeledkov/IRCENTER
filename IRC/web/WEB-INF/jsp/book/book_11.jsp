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
        <th>Глава 11</th>
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
      <span style="font-size: 20pt; line-height: 115%;">11</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Посещения по домам</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Посещения
      <span> </span>
      по домам – важный фактор в спасении людей. Каждый верующий должен делать два, три посещения в неделю.
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
        <span style="font-size: 18pt; line-height: 115%;">Твое посещение – надежда для погибающих.</span>
      </em>
    </strong>
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
      <span style="font-size: 18pt; line-height: 115%;">Спасай взятых на смерть , и неужели откажешься от обреченных на убиение ?</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">
        <span> </span>
        Притчи 24:11
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Посещение должно стать не просто одноразовым мероприятием , а образом жизни, и тогда на это приходит помазание. Если ты хочешь, чтобы Дух Святой с тобой сотрудничал , тебе необходимо принять глубокое решение спасать людей.
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
        <span style="font-size: 18pt; line-height: 115%;">Посещение – воля Божья.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;"> </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Посещая людей, мы идем в их дома. Почему это так важно ?
    </span>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        <span> </span>
        Потом [Иисус] вошел в Иерихон и
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        проходил через него. И вот, некто 
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        именем Закхей, начальник мытарей и 
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        человек богатый, искал видеть Иисуса,
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        кто Он , но не мог за народом, потому
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        что мал был ростом ; и забежав вперед,
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        влез на смоковницу, чтобы увидеть
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        Его , потому что Ему надлежало проходить
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        <span> </span>
        <span> </span>
        мимо нее. Иисус, когда пришел на это место,
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        <span> </span>
        <span> </span>
        взглянув увидел его и сказал: Закхей! сойди
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        скорее, ибо сегодня надобно Мне быть у тебя
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        в доме. И он поспешно сошел и принял Его с 
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        радостью. И все, видя то, начали роптать и 
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        говорили , что Он зашел к грешному человеку.
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        Луки 19:1-7
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        <span> </span>
        <span> </span>
      </span>
    </strong>
    <span style=" line-height: 115%;">В основном все Евангелие говорит о двух вещах :</span>
  </p>
  <p class="MsoListParagraphCxSpFirst" style="margin-left: 57pt; text-indent: -18pt;">
    <span style=" line-height: 115%;">
      <span>
        1)
        <span style="font: 7pt &quot;Times New Roman&quot;;"> </span>
      </span>
    </span>
    <span style=" line-height: 115%;">Иисус ходил по домам.</span>
  </p>
  <p class="MsoListParagraphCxSpLast" style="margin-left: 57pt; text-indent: -18pt;">
    <span style=" line-height: 115%;">
      <span>
        2)
        <span style="font: 7pt &quot;Times New Roman&quot;;"> </span>
      </span>
    </span>
    <span style=" line-height: 115%;">Иисус общался с грешниками.</span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">Когда ты приходишь в дом – это важно, потому что человек на своей территории, а значит , он максимально открыт для дружбы и общения.</span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      В доме ты видишь, как человек живет, видишь то, что он сам тебе не скажет, тебе открываются его нужды и проблемы, и благодаря этому твоя помощь будет более эффективна.
    </span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      В дом мы идем даже тогда, когда человек уже спасен, потому что там есть родственники, которые еще не спасены. Часто нет пророка в своем отечестве. Приводи к себе домой людей на посещение, и тогда твои родные спасутся. Когда мы идем в дом, наша цель – послужить людям.
    </span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt; text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">ПРИДИ НЕ С ПУСТЫМИ РУКАМИ</span>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      Мы идем в гости, и наше уважение к хозяину дома и его семье проявится в том, что мы придем с каким-то угощением. Если в доме есть дети, принесите им фрукты или шоколад , все эти мелочи играют большую роль в становлении отношений.
    </span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt; text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">БУДЬ УМЕРЕН , АККУРАТЕН И СКРОМЕН</span>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt; text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">ЗА СТОЛОМ</span>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      Гостеприимные хозяева могут предложить с ними поужинать. А так как мы, евангелисты и проповедники, очень часто не успеваем пообедать и поужинать, а значит, приходим на посещение голодными, есть искушение не только поделиться Словом Божьим , но и «подкрепить сердца».В такие моменты мы можем получить откровение не только о том , какой демон контролирует этот
      <span> </span>
      дом, но и о том, что лежит за дверцей холодильника у гостеприимных хозяев. И, как Винни-Пух из мультфильма сказать : «Ну ,если у вас уже ничего не осталось , тогда мне пора…»
    </span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      Таким образом, цель поправиться на 10 кг ты достигнешь , но приобрести друзей, которых ты приведешь к Богу , тебе вряд ли удастся.
    </span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt; text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">
        МЫ ИДЕМ С ПОСЛАНИЕМ 
        <br>
         К ЛЮДЯМ, КАК ПОЧТАЛЬОНЫ
      </span>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      Наше послание – это разрушение родовых и наведенных проклятий силой Святого Духа. Бог дал нам откровение о посещении, чтобы мы принесли людям весть о том, что Дух Святой разрушает всякие проклятия, исцеляет болезни, открывает двери темниц. 
    </span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      Мы не просто идем что-то рассказывать людям, посещение – это конкретика. Мы, как духовные воины, несем помазание в жизнь человека, приходим и решаем проблемы, приносим людям ответ. Когда в процессе общения мы приводим примеры, рассказываем свидетельства, у людей появляется вера. Вера растет не просто от информации , она растет от свидетельств Божьих.
    </span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      Идя в дом , мы приходим не просто «притянуть» людей в церковь , а узнавая их нужды, служим людям и приносим им ответ. Это как в пожертвовании: мы сеем не для того, чтобы пожать материальное, но в Царство Божье, а финансовая жатва прилагается.
    </span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt; text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Мы не проповедуем, чтобы просто проповедовать,</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt; text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">мы проповедуем ответ. </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt; text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;"> </span>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt; text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">КОГО МЫ ПОСЕЩАЕМ ?</span>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">
        <span> </span>
        1 категория : НЕВЕРУЮЩИЕ ЛЮДИ
      </span>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      Неверующим людям мы несем послание о том, что Бог разрушает всякие проклятия. Мы говорим Евангелие – хорошую новость, приносим людям ответ на их нужду и разрушаем дела дьявола.
    </span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">
        <span> </span>
        2 категория : НОВООБРАЩЕННЫЕ
      </span>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
      </span>
    </strong>
    <span style=" line-height: 115%;">Новообращенных людей мы «кормим молоком» - Словом Божьим, утверждаем их во Христе. Если лидер сам лично посетил в течении недели новообращенных из своей домашней группы, они практически все останутся в церкви.</span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      У лидера , который умеет разговаривать с «младенцами», всегда будет много людей в домашней группе. Если у них есть нужды , мы им тоже несем Евангелие. Евангелие – это ответ на любую проблему, это хорошая новость , не забывайте об этом. Евангелие нужно неверующим и верующим.
    </span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
    </span>
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">3 категория : ОТСТУПНИКИ</span>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
      </span>
    </strong>
    <span style=" line-height: 115%;">Людям, которые отступили от церкви , мы несем послание о милости Божьей, милости , которая превозносится над судом.</span>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      Мы к ним тоже должны прийти с хорошей новостью. Помните, у этой категории людей много страхов и комплексов , как у блудного сына. «Мне стыдно, на меня все будут смотреть , я боюсь» ,- именно так рассуждают те, кого удалось сбить дьяволу. Причины отступничества бывают разные, постарайтесь привести человека к искреннему покаянию, показывая ему доброту Божью и коварство дьявола.
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