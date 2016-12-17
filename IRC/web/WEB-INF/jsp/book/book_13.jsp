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
        <th>Глава 13</th>
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
  <p class="MsoNormal" style="margin-left: 39pt; text-align: center;" align="center">
    <strong>
      <span style="font-size: 20pt; line-height: 115%;">13</span>
    </strong>
  </p>
  <p class="MsoNormal" style="margin-left: 39pt; text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Личный евангелизм и помазание</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Как связан личный евангелизм с помазанием ?
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Существует два вида помазания: первый вид дан людям для специальных целей. Это помазание было на Сауле, когда Бог его помазал на царя, он шел и побеждал любого врага. Бог помазал Самсона защищать Израиль от филистимлян , и не было человека равного ему по силе.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Второй вид – это твое личное помазание, оно растет по мере твоего послушания и верности Богу. Для того чтобы сила Божья на тебе увеличивалась, обязательно придут тесные обстоятельства, ситуации , но в конце всегда будет награда и новый уровень . Поэтому в Послании Иакова и говорится :
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">Блажен человек , который переносит </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">искушения, потому что, быв испытан,</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">он получит венец жизни, который даст</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">Господь любящим Его.</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        Иакова 1:12
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Самое главное во время давления – не допустить, чтобы дьявол смог тебя сломать и украсть помазание.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Когда человек лично евангелизирует , он нарабатывает помазание , и оно является следствием конкретно сказанных слов, а не причиной.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style=" line-height: 115%;">Евангелизм- это самое сильное слово в мире.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Цена за помазание – это, прежде всего, быть послушным в евангелизации. Итак, твой личный евангелизм является причиной твоего личного помазания и помазания в служении.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Сегодня я вижу, как сильно действует помазание на наших служениях. Каждый раз перед служением у меня есть момент в молитве, когда я становлюсь на колени и прошу Духа Святого по Своей милости прийти ко мне. В это момент я ощущаю, как Он приходит и касается меня, Его прикосновение становится настолько реальным. Тогда я иду на служение и точно знаю, что сейчас Он придет и исцелит больных. И каждый раз так и происходит. Глядя на это реальное действие Божье, я всегда знаю и помню, какой ценой это приходило.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Я вспоминаю те годы, в начале моего служения, когда мы делали на улице евангелизации по три раза в день. Мы ездили по селам вокруг Перещепино, мы выставляли колонки и пели песни, я проповедовал. Очень часто на эти евангелизации не приходил ровным счетом ни один человек, но я все равно проповедовал, проповедовал пустой улице. Я помню, как мой помощник спросил меня : « А кому ты говоришь?» - « Возможно, люди в своих домах слышат мой голос! И это слово коснется их», - отвечал я. Это выглядело, как безумие, но я был ревностен в своем евангелизме.
      <span> </span>
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Так прошли первые годы моего служения в Перещепино, я не видел тогда плода, но Бог видел мою верность. Прежде чем Бог мне доверил массовое покаяние на служениях ,Он испытал меня, буду ли я верен и ревностен, проповедуя пустой улице.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Для того чтобы иметь помазание, мало, чтобы за тебя помолился великий служитель, у которого есть помазание: если оно у него есть , оно к нему пришло не даром, а значит, и к тебе оно даром не придет. За помазание платится цена – это, прежде всего, молитва, это твоя верность в спасении людей, это материальные жертвы и в конце концов смерть для самого себя. Нужно помнить , Бог нелицеприятен . Будь верен сегодня в том, что тебе доверил Бог, и Он доверит тебе большее. Будь ревностен , и на ревность придет Его благоволение. Бог желает дать тебе сильное помазание.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Несколько лет назад в СНГ приезжал мировой проповедник. На его служении было величайшее помазание, своей молитвой он останавливал бури, на его служениях воскресали мертвые.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Однажды три преступника хотели убить его, они забежали в его дом, увидев их, проповедник с гневом закричал, помазание было таким сильным, что двое из них умерли на месте, а третий убежал. Так вот: все пасторы и служители сильно хотели, чтобы он за них помолился и передал им свое помазание.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Когда они все вышли вперед, он поднял руки и начал просить Бога: «Господи, дай им помазание, которое на мне , дай им , чтобы их предавали , как и меня предавали, дай им бегать босиком в горах от врагов, как и я бегал, дай им испытать голод, как и я испытал». Конечно, никто не ожидал такой молитвы, все ожидали молитвы за помазание.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Но правда в том, что одно без другого существовать не может. Просто этот проповедник знал, что за помазание платится цена и нужно быть готовым и посвященным.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Бог хочет тебя помазать !</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Запомните , когда мы евангелизируем на личном уровне, Бог обязательно помазывает нас, и
      <span> </span>
      Он будет проявлять чудеса и знамения в нашей евангелизации, даже если мы еще новообращенные.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Верность в личном евангелизме –</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">это цена и причина для проявления</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">сверхъестественной силы, знамений и чудес.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Я расскажу вам историю: одна семейная пара, посещая людей на дому, пришла к женщине, которая умирала от цирроза печени. За три последние недели эта женщина ни разу не встала с кровати, она ничего не ела, ее живот был раздут и наполнен жидкостью. Смерть, подобно разъяренному хищнику, поглощала тело этой женщины. Придя туда, они стали ей свидетельствовать .Еле слышным шепотом она повторила за ними молитву покаяния, они возложили на нее руки и приказали дьяволу оставить ее.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Тело, которое не двигалось уже три недели, забилось в конвульсии , из ее горла вышел свирепый рев, помазание двинулось, и эта женщина была освобождена. Невероятное чудо, которое можно увидеть довольно редко, случилось в тот момент. Эта женщина услышала внутреннее побуждение немедленно встать с кровати, не до конца осознавая, что происходит, она сбросила свои ноги на пол и попыталась встать. К ее изумлению – у нее это получилось.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Впоследствии эта женщина была полностью исцелена от всех своих недугов. У нее не откачивали жидкость из живота, случилось чудо – все исчезло. Пережив на себе силу личного евангелизма , она начала рассказывать всем людям в округе о величии Божьем. На одно из собраний , которые мы проводим в Ледовом дворце, она привела 48 человек.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Оставайся верным в личном евангелизме , и Бог двинется через тебя в чудесах и знамениях . Люди , которые не встают с постели и ждут своей смерти, будут исцелены через тебя. Будет уходить рак, цирроз, эпилепсия, туберкулез , дьявол будет повиноваться тебе, потому что ты слуга Божий.
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