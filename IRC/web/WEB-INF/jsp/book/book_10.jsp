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
        <th>Глава 10</th>
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
        <span style="font-size: 20pt; line-height: 115%;">10</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 20pt; line-height: 115%;">Планирование времени</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Ещё один важный пункт, который я получил, - это «планирование времени». Самой большой проблемой, о которой я постоянно слышал в то время, было: у меня нет времени, у меня не хватает времени, дома проблемы , нет времени на евангелизацию .
      <span> </span>
      Итак , Бог показал мне, что это неправда, что на самом деле проблема в том, что мы не умеем распоряжаться временем. Когда мы начали разбираться с каждой минутой в своем графике, мы все вместе обнаружили, что у каждого из нас есть от двух до четырех часов свободного времени ежедневно.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Мы разбирали все подетально и поминутно, мы записывали, сколько времени у нас уходит на то, чтобы умыться, сколько – на дорогу к маршрутке.Мы поднимали продуктивность служения, рассчитывая , сколько, звонков можно сделать по дороге на работу, к кому можно заехать по пути с работы, сколько времени мы можем уделить сегодня на второе посещение.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Да,на самом деле все эти мелочи снимают с нас нагрузку и повышают продуктивность .Не планируя свои дни, свое личное время и служение, мы заранее обрекаем себя на бесплодие.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Болезнь верующих людей – неумение управлять своим временем. Мы вроде бы выкладываемся на 100%, устаем, а плода очень мало. Если мы не организуем время, успеха и плода в служении не будет, но если правильно все спланировать , мы сделаем при меньшем усилии в 20 раз больше. 
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Неорганизованный человек принесет себе в жизнь множество проблем.День, который не был организован, не принесет радости и плода. Организованная жизнь по эффекту
      <span> </span>
      умножается в несколько раз.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Правда в том, что у нас есть время</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">для посещения людей.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;"> </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">ПЛАНИРОВАНИЕ ПОСЕЩЕНИЙ</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Каждое посещение мы должны спланировать. Делая списки и назначая день
      <span> </span>
      время, когда вы встретитесь с человеком, вы, образно говоря, провозглашаете это в духовный мир. Вы как будто говорите слово в будущее.Удивительно то, что Бог считается с нашим словом и с нашим решением, и Он начинает действовать в этих обстоятельствах и в этом человеке.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Бога волнует не церковная жизнь, а спасение людей. Бог пришел, чтобы спасти погибшее.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Ты планируешь то, что для тебя важно, и не планируешь неважное. Этим мы говорим с Богом.Если Бог видит, что это для тебя неважно и ты доверяешь это слепому случаю, Он огорчается.Отношения с Богом строятся через наши поступки.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Когда для тебя станет важно то, </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Что важно для Бога, для Бога станет важно то,</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Что важно для тебя.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Бог начнет исполнять твои желания.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Стань ответом для Бога, и Бог станет ответом для тебя.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Наш успех, наши ответы скрыты</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">в неверующих людях.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Бог действует через человека, и, когда мы что-то планируем, Он уже движется там.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Планирование – это подготовка пути для Господа.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Планирование – это слово. Слово прозвучало в духовный мир, и Бог начинает действовать по слову твоему.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Когда человек не планирует свои посещения , они у него происходят редко и с «тяжелыми» последствиями. К примеру, человек, идя на посещение, не запланировал, сколько времени он проведет в гостях, поэтому, когда он разулся, зашел в дом было семь вечера, а когда обулся, все благодарили Бога, что он ушел, потому что уже было двенадцать ночи. Мало того, что он перегрузил людей и они в ближайшее время не захотят его увидеть, но еще и дома проблемы:»Где ты был ?» - это нормальный вопрос любой жены или мужа. Дальше можно не рассказывать.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Не планируя свое время, люди делают много неоправданых ошибок. Правда в том, что, планируя свое посещение , вы можете быть очень продуктывными, вы можете в один вечер сделать два или три посещения и быть дома намного раньше.Люди, которых вы посещаете , будут вам очень благодарны за вашу деликатность во времени, они захотят вас видеть часто, заранее зная, на какое время вы у них задержитесь.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Планирование времени – это духовный момент, Бог планирует времена и сроки.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Планируй спасение людей вместе с Богом.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Бог не двинется через тебя, пока не будет твердого решения и плана. Все, что ты планируешь оносительно спасения людей, Он благословит , Он начнет действовать в твоих нуждах.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Материализация духовного мира связана с нашими образами, мыслями и словами.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Бог , видя это, начинает доверять нам, доверие приходит, когда мы все делаем постоянно.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Бывает , мы долгое время что-то делаем, но не видим ожидаемого результата.Почему ? Бог за нами наблюдает, насколько мы постоянны. Бог за верность нас благословит, важно быть честными в этом.Мы можем ошибаться в чем-то другом, но если мы не
      <span> </span>
      честны в служении, Бог глаза на это не закроет.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Когда мы планируем, Бог может больше нас использовать и посылает нам «спелые яблочки». Мы можем молиться Богу за свои нужды и не получать ожидаемых ответов, но если мы пообещаем Господу быть для Него ответом, Он ответит на все наши молитвы.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Когда платишь цену за евангелизм, ты становишься действительно соработником Христа. Все, кто работают на Бога, видят Его покровительство и заботу.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Хочу поделиться с вами потрясающим свидетельством, которое произошло в жизни моего друга.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Александр занимается бизнесом много лет. Его мечта и призвание – служить Богу и церкви финансами. В бизнесе не все получалось легко, Александр видел и взлеты и падения, но последняя ситуация стала для него крайне тяжелой.Александр оказался в очень серьезных долгах – один миллион гривень. В течении полугода он всеми силами искал выход , все возможности заработать хоть какие-то деньги разрушались прямо на глазах, он молился, но ответа не было. Все было безуспешно, Александр не видел просвета, и это привело его в сильную депрессию. «Тупик... Выхода нет ...»
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Постоянные звонки и угрозы, день начинался с нервных потрясений и заканчивался ужасом бкзысходности, кошмарные сны и еще более кошмарная реальность.Один Бог знает, сколько молитв он вознес в это трудное время, и казалось , что не одна из них не дошла до Его престола, но это было не так ...
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Это было обычное воскресное собрание, но для Александра оно стало судьбоносным. 
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Я делился
      <span> </span>
      со своей церковью откровением о посещении неверующих , я говорил , что это единственная нужда Бога. «Стань ответом для Бога, и Бог станет ответом для тебя», - эту фразу Александр слышал уже не раз, но вдруг Бог заговорил к нему через эти слова.Еще несколько минут назад он взывал : «Господь, почему Ты меня не слышишь ? Почему Ты мне не отвечаешь?». Но то, что он услышал от Бога
      <span> </span>
      просто потрясло его. «Правда в том , что это ты Меня не слышишь, ты не отвечаешь на Мою нужду», - проговорил Господь.В одно мгновение сердце Александра было сокрушено, все внутри изменилось, рыдая, он договорился с Богом, что будет нести Евангелие , каждый день посещать неверующих людей, станет ответом на нужду Бога, а Господь пусть решит его проблему.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Богу несложно решить наши проблемы, и, когда мы делаем то, что Он ожидает , все наши проблемы тают. Как воск от пламени свечи.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Александр вспомнил всех своих знакомых , сослуживцев, родственников, соседей и бывших однокурсников, записал их в список и начал посещать. Каждый день вместе с женой они приходили к неверующим людям. И Бог начал чудесным образом решать его нужду. Буквально через три дня после того собрания Александру позвонили и сделали интересное бизнес-предложение. За следующие три недели он заработал 100 тысяч гривень, а еще через месяц рассчитался со всеми долгами.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Сегодня много знакомых и друзей Александра в церкви, их жизнь изменилась.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Он стал ответом для Бога, и Бог стал ответом для него !
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