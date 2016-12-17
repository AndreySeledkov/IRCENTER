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
      <tr>
        <th>Глава 4</th>
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
      <em>
        <span style="font-size: 26pt; line-height: 115%;">4</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Важность евангелизма</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Чуть позже мы приедем ко всем моментам, связанным с тем, как евангелизировать, но сейчас я бы хотел сказать о важности личного евангелизма в жизни верующего человека. Евангелие – это самое драгоценное, что Бог доверил Своей Церкви. Как верующий, ты призван, прежде всего, спасать людей на личном уровне, для этого не нужно особенного призвания.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;" lang="EN-US"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">К спасению людей посредством личного свидетельства призван каждый человек без исключения.</span>
      </em>
    </strong>
    <strong>
      <em> </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <em>
      <span style=" line-height: 115%;" lang="EN-US"> </span>
    </em>
  </p>
  <p class="MsoNormal" style="margin-left: 317.25pt; text-align: justify; text-indent: -317.25pt;">
    <span style=" line-height: 115%;">Еще совсем недавно за свидетельство о Христе людей сажали в тюрьмы,</span>
  </p>
  <p class="MsoNormal" style="margin-left: 317.25pt; text-align: justify; text-indent: -317.25pt;">
    <span style=" line-height: 115%;">расстреливали, лишали общения с семьей. Люди приносили огромные</span>
  </p>
  <p class="MsoNormal" style="margin-left: 317.25pt; text-align: justify; text-indent: -317.25pt;">
    <span style=" line-height: 115%;">жертвы ради того, чтобы хотя бы один человек был спасен.</span>
  </p>
  <p class="MsoNormal" style="margin-left: 317.25pt; text-align: justify; text-indent: -317.25pt;">
    <span style=" line-height: 115%;">Буквально вчера я слушал свидетельство одного пастора, которого</span>
  </p>
  <p class="MsoNormal" style="margin-left: 317.25pt; text-align: justify; text-indent: -317.25pt;">
    <span style=" line-height: 115%;">Приговорили к расстрелу. В последний момент приговор был отменен, но он </span>
  </p>
  <p class="MsoNormal" style="margin-left: 317.25pt; text-align: justify; text-indent: -317.25pt;">
    <span style=" line-height: 115%;">об этом не знал. Во время расстрела в него выстрелили холостым патроном. </span>
  </p>
  <p class="MsoNormal" style="margin-left: 317.25pt; text-indent: -317.25pt;">
    <span style=" line-height: 115%;">Люди были готовы умереть ради проповеди Евангелия. Сегодня мы должны</span>
  </p>
  <p class="MsoNormal" style="text-indent: -83.35pt;">
    <span style=" line-height: 115%;">
      <span> </span>
      быть готовы оставить свои суетные дела и пойти у нашим знакомым и друзьям, чтобы проповедовать им.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      Несколько лет назад Бог подарил мне встречу с особенным человеком – это был Виктор Иванович Билых. 
      <span> </span>
      Целый вечер он рассказывал о том, как за проповедь Евангелия он был лишен свободы на 25 лет .
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Двадцать пять лет лагерей , не видя своей семьи , жены и детей, только за то, что свидетельствовал людям о воскресении Христа. Он рассказал одну интересную деталь :
      <span> </span>
      неоднократно ему предлагали подписать документ, что он не будет проповедовать Евангелие , и взамен ему обещали немедленное освобождение .
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Сегодня нас не лишают свободы и не сажают в тюрьмы за то , что мы говорим о Боге …
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Наша цель – веру во Христа распространить.</span>
      </em>
    </strong>
    <strong>
      <em> </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;" lang="EN-US"> </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style="font-size: 18pt; line-height: 115%;">
      <span> </span>
    </span>
    <span style=" line-height: 115%;">Целью прошлого поколения верующих было – веру сохранить , а наша цель – веру во Христа распространить . Для этого нам необходимо вкладывать всю свою мудрость , время и финансы в распространение Евангелия . И сегодня мы видим , что есть люди , которые со всей искренностью и посвящением приносят большие жертвы для спасения людей. Многие продают свои машины , золото , во многом себе отказывают, делают возможное и невозможное ради того, что бы больные исцелялись , проклятия рушились и драгоценные души, за которые умер Христос , обретали рождение свыше и новую жизнь.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      21 июня 2009 года на служении в Ледовом дворце один парень свидетельствовал. Когда он вышел на сцену, ему было очень тяжело дышать и говорить, из его тела выходили трубки, которые свисали прямо из-под рубашки, было видно, что он серьезно болен, и его рассказ никого не оставил равнодушным. Он свидетельствовал о том, что пожертвовал на служении в Ледовом дворце деньги, собранные им на операцию , от которой зависела его жизнь.
    </span>
  </p>
  <p class="MsoNormal" style="margin-bottom: 0.0001pt; line-height: normal;">
    <span style="">
      <span> </span>
      С трудом переводя дыхание , он рассказал : «Две недели назад с температурой тела 39 
    </span>
    <span style="font-size: 10pt; font-family: &quot;Arial CYR&quot;,&quot;sans-serif&quot;;">
      °
      <span> </span>
    </span>
    <span style="">меня привезли в больницу на «скорой помощи». Установили диагноз : левосторонний плеврит. Сделали несколько пункций, но они не помогали, легкие наполнялись жидкостью, и врачи назначили операцию. На операцию нужны были деньги, и я начал собирать их.</span>
  </p>
  <p class="MsoNormal" style="margin-bottom: 0.0001pt; line-height: normal;">
    <span style="">
      <span> </span>
      В прошлое воскресенье братья из церкви приехали на машине ко мне в больницу и забрали меня на служение . В тот день, заходя в зал, я обратился к Богу : «Сегодня, Господь, пожалуйста , скажи слово в мою жизнь».Прошло 15 минут проповеди , и Дух Святой прикоснулся ко мне , я начал рыдать. И когда был призыв жертвовать на Ледовый дворец, Господь проговорил ко мне : « Богдан, Я всегда с тобой, Я не оставлю тебя, всегда помни- Я рядом , и даже если ты умрёшь , ты будешь со Мной.»
    </span>
  </p>
  <p class="MsoNormal" style="margin-bottom: 0.0001pt; line-height: normal;">
    <span style="">
      <span> </span>
      В тот момент этот парень, находясь в критическом состоянии , думал о тех людях, у которых нет надежды на вечную жизнь, которые не знают Бога и , умирая, идут в ад. Он сделал свой выбор и, заканчивая свидетельство, сказал : «Что бы ни случилось, я знаю, я спасен, но многие не имеют этой уверенности. Я хочу, чтобы люди, которые ещё не знают Бога, имели возможность услышать о Нём и, приехав в Ледовый дворец, получить рождение свыше и новую жизнь».
    </span>
  </p>
  <p class="MsoNormal" style="margin-bottom: 0.0001pt; line-height: normal;">
    <span style="">
      <span> </span>
      Богдан пожертвовал деньги, собранные им на операцию, на Ледовый. Множество людей на том служении получили исцеление , были освобождены от проклятия , и когда люди вышли на покаяние , они заполнили собою все огромное пространство возле сцены и в проходах .По милости Божьей тысячи людей были рождены свыше.
    </span>
  </p>
  <p class="MsoNormal" style="margin-bottom: 0.0001pt; line-height: normal;">
    <span style="">
      <span> </span>
      Сегодня есть верующие , которые ради спасения людей готовы рисковать своей жизнью.
    </span>
  </p>
  <p class="MsoNormal" style="margin-bottom: 0.0001pt; line-height: normal;">
    <span style="">
      <span> </span>
      У нашего Господа есть нужда. Какая, спросим мы, разве может Великий Всемогущий Бог в чем-то нуждаться ? Да.
      <span> </span>
      <span> </span>
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style=" line-height: 115%;">Единственная нужда Бога – это спасение Его драгоценных , обманутых дьяволом детей.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      И если мы, те , кто уже спасены , избираем самой главной целью своей жизни – стать ответом на нужду Бога, Бог станет ответом для нас.
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