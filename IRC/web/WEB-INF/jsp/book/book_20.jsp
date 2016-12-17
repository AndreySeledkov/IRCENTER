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
        <th>Глава 20</th>
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
        <span style="font-size: 20pt; line-height: 115%;">20</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Личный евангелизм и </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Дух Святой</span>
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
    <strong>
      <span style=" line-height: 115%;">Духа не угашайте.</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        1 Фессалоникийцам 5:19
      </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
      </span>
    </strong>
    <span style=" line-height: 115%;">Через личный евангелизм приходит сильное действие Святого Духа в нашу жизнь. Писание говорит , что Духа Святого можно угасить, из этого следует, что Его также можно и воспламенить. Огонь Святого Духа воспламеняется в нашем сердце и зажигает наш дух через личный евангелизм. В Ветхом Завете , когда люди угождали Богу своими жертвами, Дух Святой сходил в виде огня.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      В Новом Завете Он по-прежнему приходит в виде огня , который сходит на дух человека. Происходит это через преданность в евангелизме. Одна из вещей , которую делает Святой Дух на этой земле для людей : Он открывает им тайны , доверяет секреты. Он это делает только для тех, которым Он доверяет. 
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Человек , который хочет знать тайны Божьи, двигаться в дарах Святого Духа, должен быть посвященным и преданным Ему в Его главном деле – в спасении людей. Если человек в евангелизме делает все по вдохновению, вряд ли Бог станет открывать ему Свои тайны. Ты не должен быть человеком , который делает что–то для Бога только тогда, когда его вдохновят, ты должен быть человеком принципов и посвящения – делать работу Божью , спасать людей даже тогда , когда этого делать не хочется.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">ПРЕДТЕЧА ПОМАЗАНИЯ</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Дух Святой передает нам чувства Иисуса Христа , Он делится с нами тем, что в сердце нашего Господа. Прежде чем Дух Святой помажет нас, Он наполнит сердце одним важным чувством , которое приходит свыше , - это чувство сострадания к неспасенным , больным людям, к связанным людям.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Сострадание – предтеча помазания.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Однажды , когда я находился в молитве, со мной произошло что-то особенное. Стоя на коленях , я четко осознал , что прямо сейчас, в это момент множество пожилых людей стоят в очередях в наших больницах , кого-то готовят к операции, кому-то объявили смертельный диагноз , кого-то отдали родным , не говоря ему о том, что у него неизлечимый рак. Эти люди, как образы, стали всплывать перед моими глазами.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Я как будто почувствовал и пережил тот ужас, который творится в их душе. Продолжая стоять на коленях, я начал рыдать , я молил Бога о том, чтобы Он начал действовать в исцелении. В тот момент я молился как никогда , я понял , что Бог дал мне выбраться из своей скорлупы и увидеть мир , который видит Он.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      В те часы молитвы я узнал, что Бог видит страдания людей и Его сердце наполняется болью. Я просил
      <span> </span>
      у Бога помазания не для себя, но для людей , и Он мне его дал.После той молитвы что-то изменилось во мне, у меня появилось огромное дерзновение и вера. Я возненавидел болезни, рак, эпилепсию , родовые проклятья , колдовство. Я благодарю Бога за то, что Он поделился со мной тем, чего я не видел.
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
        <span style="font-size: 18pt; line-height: 115%;">Любовь и сострадание – единственное , что</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">касается сердца Бога.</span>
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
      Как много у людей мотивов в служении Богу , большинство из них огорчают Его сердце. Многие из нас смотрят больше на себя , чем на
    </span>
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;"> </span>
      </em>
    </strong>
    <span style=" line-height: 115%;">
      проблемы других. Кто-то хочет состояться, кто-то хочет войти в свое призвание , кто-то хочет быть успешным – все это не плохо, но это не может быть мотивом, на который придет Бог. Любовь и сострадание – единственное, что
      <span> </span>
      касается сердца Бога. Иисус творил великие чудеса , воскрешал мертвых, и перед каждым из этих чудес Он исполнялся жалостью к этим людям, к их близким.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Однажды Он увидел похоронную процессию, женщина- вдова хоронила своего единственного сына. Библия говорит: Иисус сжалился, подошел к гробу и приказал юноше встать. Помазание сильно проявляется, когда наше сердце наполняется состраданием. Юноша был воскрешен и отдан своей матери живым и здоровым.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Когда Иисус пришел ко гробу Лазаря , Он не смог удержаться и начал плакать. Когда Его глаза заблестели и наполнились слезами, люди вокруг поняли, что Иисус по-настоящему любит Лазаря и его семью. После этого Он совершил одно из самых великих чудес, которые видел этот мир, воскресил Лазаря из мертвых.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Сострадание – это чувство, без которого нам не </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">увидеть великого помазания.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Посмотрите , перед тем как въехать в Иерусалим и отдать Свою жизнь за нас, совершить самый великий подвиг и принести самую великую жертву, Иисус стоял на горе и, наполненный состраданием , плакал.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Мой друг , пусть тебя не увлечет стремление быть крутым, иметь хорошие связи , хорошо выглядеть, - это все мусор. Поставь себе цель наполнить свое сердце единственным – Божьей любовью и Его состраданием, соединись со Христом в этом чувстве ! Дух
      <span> </span>
      Святой хочет, чтобы евангелизм для тебя был не просто словами.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Один верующий человек проснулся ночью, Бог разбудил его, сила Духа Святого сошла на него, и он начал кричать на языках. Практически всю ночь он кричал и плакал, повторяя одно 
      <span> </span>
      и то же слово на ином языке. Утром он узнал, что этим словом было название села и именно в эту ночь в этом селе перерезали всех людей. Тогда он побежал к Богу и спросил : «Зачем Ты дал мне это пережить ночью ? Ведь все равно все эти люди погибли ?» Иисус ответил ему : «Я просто поделился с тобой Своими чувствами».
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;">ДУХОВНАЯ ГЛУХОТА</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Иисус говорил, что Дух Святой придет, чтобы мы были свидетелями. Поэтому самое первое предназначение Святого Духа – это проповедь Евангелия. Дух Святой побуждает нас свидетельствовать о Христе, Он всегда будет это делать, но человек может угасить Его своим непослушанием , поэтому это послание говорит : «Духа не угашайте». Дух Святой очень нежный, угашая в себе Его голос , человек становится духовно глухим.Что это значит ?
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Человек теряет различение , что от Бога, что от дьявола, пытается понять , что ему делать в жизни и не может. Он задает себе вопросы : «Кто я?», «Какое у меня призвание?», «На ком мне женться?», «Где работать?». Полная тьма , ответов нет. Только Дух Святой проливает свет на нашу жизнь и будущее. И если мы не угашаем Его , то нас сопровождает
      <span> </span>
      Его водительство. Самое первое место, где угашается Святой Дух, - это личный евангелизм. Действие Святого Духа в нашей жизни напрямую связано с нашим послушанием .Чем больше мы свидетельствуем, тем сильнее Дух Святой будет действовать.
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