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
        <th>Глава 6</th>
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
      <span style="font-size: 26pt; line-height: 115%;">6</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 26pt; line-height: 115%;">Евангелие – хорошая новость</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Рассмотрим , что такое Евангелие. Значение слова «евангелие» - это благая весть или хорошая новость. Нести Евангелие – это нести хорошую новость о том, что Господь – это ответ на твою конкретную проблему. Самая главная хорошая новость для каждого без исключения человека заключается в том, что Иисус Христос Своей
      <span> </span>
      смертью на кресте спас нас от ада и подарил прощение грехов и вечную жизнь.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      <span> </span>
      Но есть ещё один момент : когда человек приходит к Богу, он нуждается в том, чтобы Господь помог ему в решении его личной проблемы. Кто-то страдает от болезни и нуждается в исцелении, кто-то долго и сильно пьет и нуждается в освобождении, у кого-то семья на грани распада, а кто-то сходит с ума от депрессии и одиночества. Все люди разные, поэтому и проблемы и обстоятельства у нас могут быть разными. Для каждого человека хорошая новость- это ответ на его нужду и проблему.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Евангелие всегда должно ассоциироваться с ответом для человека. Нельзя нести Евангелие слепо, важно видеть и чувствовать нужду каждого, быть снайпером, который четко видит цель и приносит человеку именно тот ответ, в котором он нуждается. Если я скажу тому, кто болен, что для него есть исцеление во Христе Иисуск, - это будет ответ, хорошая новость.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Но если я скажу об исцелении тому, кто здоров, для него это не актуально, это не ответ на его личную проблему, возможно, с самого детства его мучает страх, хорошая новость для него – Бог освобождает от страха .У одного человека нет родителей, для него хорошая новость – Бог будет ему Отцом, а Церковь – семьей.А у кого-то, наоборот, в семье долгое время нет детей, для них ответом будет свидетельство о том, что Бог творит чудеса и исцеляет от бесплодия.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Однажды у меня состоялся разговор с одним молодым человеком. Он был уже несколько лет в наркотической зависимости. Некоторое время назад он пришел в церковь и получил освобождение. Все было очень хорошо , но спустя какое-то время пришло искушение, и он сорвался, он продолжал ходить в церковь и в то же время продолжал употреблять наркотики.Все его близкие перестали верить в него , но самым страшным было то, что он сам перестал верить в свое освобождение.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Во время нашего разговора он сказал : «Пастор, я конченый, после того, что я сделал, мне кажется, Бог меня не примет». Евангелием для него было то, что я рассказал ему о том , что, если Бог что-то начинает, Он не бросает это на половине, Он доводит свое дело до конца. И то, что Он тебя призвал и освободил когда-то, говорит о том, что Он готов это сделать и сейчас. « Что мне делать?» - спросил он в отчаянии. «Прими твердое решение и верь!»
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      После того как мы вместе помолились, Бог начал свою работу в этом человеке. Сейчас это парень имеет хорошую семью, работу , помогает в служении в одной из наших церквей. 
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Евангелие – это слова,</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">
          <span> </span>
          которые освобождают человека.
        </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Итак, Евангелие – это хорошая новость, она заключается в том, что у нашего Всемогущего Любящего Отца есть ответ
      <span> </span>
      и помощь для каждого из нас !
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Евангелие должно жить внутри нас. Если верующий человек сам наполнен сомнениями, болью, обидой или страхом , что он сможет дать неверующим людям ? Ведь от избытка сердца говорят уста. Трудно нести хорошую новость, если сам наполнен плохими новостями. Плохие новости – это мысли , которые приносят безнадежность , они приходят из преисподней. Мы не должны позволять дьяволу сеять нам мысли , отравляющие нашу радость в Боге. Нам можно защититься Евангелием , верой в Его Слово , в Его обетования.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Не только Иисус является Сеятелем, Который сеет семя, правда в том, что дьявол также сеятель, и он сеет свое слово, которое приносит плод боли и страха. Мы должны стать для него самой бесплодной почвой. Люди слышат не звук голоса, а ритм сердца, они все чувствуют , потому что существует уровень подсознания, уровень духа. Прежде чем дать людям ответ, нам нужно наполниться Евангелием, уверенностью в Боге, прекрасными ожиданиями, принять от Господа «хорошие новости» для своей жизни.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Евангелие – это сила внутри тебя.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      И не важно , какие козни строит дьявол, какие стрелы он пускает, Тот, Кто внутри тебя, сильнее того, кто в мире.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Наполнись Евангелием и наполни им других !</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">ЧУДЕСА ТАМ, ГДЕ ЕВАНГЕЛИЕ</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Бог сильно покровительствует евангелизму . Действие Святого Духа всегда связано с неверующими людьми. Чудеса там, где Евангелие. Часто в собраниях , где всегда только верующие, нет никаких
      <span> </span>
      чудес. Но там , где неверующие люди, там всегда много чудес. Если я хочу увидеть чудеса в своей жизни , я должен идти к неверующим людям. Дьявол хочет изолировать нас от неверующих людей, чтобы изолировать нас от Божьих чудес, от Его движения в нашей жизни. Я всегда был свидетелем того, что те люди в церкви , которые стабильно проповедуют Евангелие, приводят неверующих на каждое служение, сами всегда свидетельствуют о чудесах Божьих, которые происходят в их жизни.
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