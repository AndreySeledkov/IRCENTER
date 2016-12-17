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
        <td>
          <a href="/book/book_14" class="toclink">Глава 14</a> - Личный евангелизм и судьба
        </td>
      </tr>
      <tr>
        <th>Глава 15</th>
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
      <span style="font-size: 20pt; line-height: 115%;">15</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Личный евангелизм и грех</span>
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
      Множество раз, проповедуя Евангелие людям, я переживал особенную благодать. Я видел, как Бог покрывал мои слабости и недостатки , как Его милость распространялась во все сферы моей жизни. 
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Когда мы евангелизируем на личном уровне, происходит два очень важных момента : во-первых, мы несем людям ответ на их проблему, во-вторых, мы этим сильно угождаем Богу, тем самым призывая милосердие и благоволение на свою жизнь.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">В эти моменты Господь смотрит на нас и наши</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">проблемы через призму крови Христа.</span>
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
      <span style="font-size: 18pt; line-height: 115%;">ГАРАНТИЯ ВЕЧНОЙ ЖИЗНИ</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;">
      <span> </span>
      Несколько лет
      <span> </span>
      назад я имел особенное переживание с Богом. В то время я проходил определенный кризис, это был момент, когда я осознавал цену, которую должен заплатить, если хочу двигаться дальше в том виденье, которое мне дал Бог. Нужно было умереть для себя. Жить для Него. Умирали мои интересы, планы, взгляды на будущее, я должен был отдать себя полностью в распоряжение Бога. Именно в этот момент я начал задавать Богу вопросы.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;">
      <span> </span>
      Один главный вопрос, который волновал меня в тот момент , это вопрос вечной жизни. Если я теряю здесь, на земле, я хочу получить на небесах.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;">
      <span> </span>
      И тогда я задал Богу вопрос, может ли Он дать мне гарантии вечной жизни. В то время я видел, как многие люди которые были верующими долгое время, отступали и теряли все, что им давал Бог.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;">
      Я понял, что надеяться на себя глупо, и я не могу дать гарантии за себя ,потому что я человек, неизвестно, буду ли я через 20 лет думать так, как сейчас. Мне нужны были Его гарантии, что Он сохранит меня в своей руке, для Своего Вечного Царства. Бог ответил 
      <span> </span>
      мне на мою молитву и дал мне два места из Писания, которые стали для меня фундаментом моей веры и гарантией вечной жизни.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;">
      <span> </span>
      Первое место из писания:
    </span>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">И начал Петр говорить Ему: вот, мы</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">оставили все и последовали за Тобою.</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">Иисус сказал в ответ: истинно говорю</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">вам: нет никого, кто оставил бы дом,</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">или братьей, или сестер, или отца, или</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">мать, или жену ,или детей, или земли, ради</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">Меня и Евангелия, и не получил бы ныне,</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">во время сие, среди гонений ,во сто крат</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">более домов, и братьев, и сестер, и отцов,</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">и матерей, и детей, и земель, а в веке грядущем</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">жизни вечной.</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify; line-height: normal;">
    <strong>
      <span style="">
        <span> </span>
        Марка 10:28-30
      </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;">
      <span> </span>
      Иисус говорит здесь о самых дорогих вещах для человека.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;">Это наши самые близкие люди, это наш дом, это земли, то есть материальная сторона. Он сказал, что, если мы оставим все ради Него и Евангелия, Он нам это все вернет здесь, на земле, но самое главное воздаяние будет в веке грядущем – это жизнь вечная.</span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;">
      <span> </span>
      Иисус обещает нам и дает гарантию вечной жизни, если мы здесь, на земле, будем идти на жертвы ради распространения Евангелия. Это сильное обетование, которое работает в жизни тех, кто день и ночь занят Его делом – спасением людей. Это обетование выражается в том, что здесь , на земле , за все ,что ты потеряешь ради Евангелия , ты получишь во сто крат .
    </span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;">
      <span> </span>
      Это первое, а второе и самое главное – это вечная жизнь. Обрати внимание, Иисус сказал : «Ради Меня и Евангелия», - значит , два важных момента - 
      <span> </span>
      это Его присутствие и Его послание.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Каждый день нужно пребывать в Его присутствии,</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">и каждый день нужно распространять Его Евангелие.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <em>
        <span style=" line-height: 115%;">
          <span> </span>
        </span>
      </em>
    </strong>
    <span style=" line-height: 115%;">
      <span> </span>
      Человек, который не пребывает в молитве, вряд ли сможет пребывать в распространении Евангелия. Но тот, кто переживает прикосновение Святого Духа ежедневно, не сможет молчать. Многие люди не проповедуют Евангелие , потому что утратили взаимоотношения с Богом. Вернись в присутствие Божье и к распространению Его доброй вести.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Второе место из Писания , которое Бог мне показал :
    </span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">Когда же исполнятся дни твои, и ты</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">почиешь с отцами твоими, то Я восставлю</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">после тебя семя твое, которое произойдет</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">из чресл твоих ,и упрочу царство его.</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">Он построит дом имени Моему, и Я утвержу</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">престол царства его навеки. Я буду ему</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">отцом, и он будет Мне сыном; и если он</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">согрешит, Я накажу его жезлом мужей и</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">ударами сынов человеческих ;но милости</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">Моей не отниму от него , как Я отнял от </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">Саула, которого Я отверг пред лицом твоим.</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">И будет непоколебим дом твой и царство</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">твое навеки пред лицом Моим, и престол </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">твой устоит вовеки.</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">
        2 Царств 7:12-16
        <span> </span>
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Давид был особенным человеком, он угодил Богу своей верностью и послушанием.За это Господь обещает благословить и упрочить царство Соломона, и, более того, даже если Соломон согрешит, Бог накажет его, но милости Своей не отнимет, как отнял милость от Саула. Когда Саул согрешил, за свой грех он понес наказание : Саул лишился помазания, лишился силы, царства и погиб, но не он один, погиб весь его дом, погибло все , и род Саула покрыло бесславие. О чем же здесь идет речь ? Когда Бог видит, что мы ради Него и ради Евангелия отдаем свою жизнь, ставим главной целью – отношения с Ним и спасение людей, то , даже если мы согрешаем, где-то ошибаемся, Бог гарантирует
      <span> </span>
      - Его милость всегда будет на нашей жизни.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Нам не нужно грешить, грех – это зло, и мы ненавидим грех, но мы не застрахованы от ошибок, в нашей жизни может произойти то, чего бы мы не хотели. Самое главное, чтобы Божья рука была над нами, и Его сердце было открыто для нас. Это будет в твоей жизни, если ты ради Господа и Евангелия отодвинешь личные интересы и будешь двигаться в Его интересах. Если мы умираем для себя ради спасения людей, Бог сохранит нас для вечной жизни. Это должно глубоко войти в наше сердце- у нас есть гарантия вечной жизни, и эта гарантия – в Евангелии.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Личный евангелизм приносит гарантии Божьи </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">в нашу жизнь.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Личный евангелизм приносит милость Бога </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">
          в
          <span> </span>
          то время, когда мы ошибаемся.
        </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <em>
        <span style=" line-height: 115%;">
          <span> </span>
        </span>
      </em>
    </strong>
    <span style=" line-height: 115%;">
      <span> </span>
      Никто из нас не хочет отступить от Бога, уйти из церкви, оказаться в мире, на задворках. Все мы хотим быть в церкви, двигаться вперед, состояться. Но бывает, что, даже думая о таких вещах , как спасение людей, можно искать своего: «мое» призвание, «мне» состояться, «мне» получить.Если мы живем для людей, мы уже не ищем своего.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Апостол Павел сказал, что он готов пойти в ад, лишиться спасения ради своих братьев-израильтян. Бог призвал нас к служению людям, видя нашу верность и постоянство, Он совершит Свою работу над нами,мы станем благословенными Божьими людьми, а Его милость будет сопровождать нашу жизнь и наш род. 
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Итак, есть люди, с которыми Бог поступает, как с домом Саула, а есть люди, с которыми Бог поступает, как с домом Давида.Это значит , что есть люди, которые за грех теряют милость Божью, но есть те, кто согрешает, и Бог наказывает их, но милости не онимает , так говорит Писание. Именно эти два места из Библии стали ответом Божьим на мою молитву о гарантии вечной жизни.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Нет гарантии, что ты не согрешишь в своей жизни,</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">но есть гарантия, что ты никогда не станешь</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">отступником , если ты лично евангелизируешь.</span>
      </em>
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