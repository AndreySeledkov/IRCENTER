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
        <th>Глава 3</th>
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
      <em>
        <span style="font-size: 26pt; line-height: 115%;">3</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Сила желания</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Сильное внутреннее желание является движущей силой в достижении движущей силой в достижении любой цели. Это касается и спасения людей. Без огромного желания мы не увидим огромного покаяния.</span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <span style=" line-height: 115%;">Но вот коснулась меня рука и поставила меня на колени мои и на длани рук моих. И сказал он мне: «Даниил, муж желаний! Вникни в слова, которые я скажу тебе, и стань прямо на ноги твои; ибо к тебе я послан ныне»…</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;">
        <span> </span>
        <span> </span>
        Даниил 10:10-11
      </span>
    </strong>
    <strong> </strong>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style=" line-height: 115%;" lang="EN-US"> </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Даниил молился долгое время. На двадцать первый день к нему пришел ангел. Пророк в оцепенении упал вниз лицом. Он был шокирован приходом Божьего посланника. Ангел, прикоснувшись к нему, произнес: «Даниил, муж желаний!». Это обращение характеризует тех, кого в этом мире называют «личностями». Ангел пришел к Даниилу по одной причине – в сердце пророка было огромное желание и великая ревность за свой народ. </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;" lang="EN-US"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Ясность цели и жажда ее достигнуть – </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">вот составляющие успеха.</span>
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
    <span style=" line-height: 115%;">Секретным оружием всех великих успешных людей является огромное желание в их сердце. Ясность цели и жажда ее достигнуть – вот составляющие успеха. Человек, не имеющий огромного внутреннего влечения и страсти к жизни, разрушает себя, хороня лучшее в своей жизни под обломками своего неверия, лени и страха. Подавление внутренней жизни внешними обстоятельствами является грубым преступлением против самого себя.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Жизнь… Кто-то дал тебе ее, вложив суть. Без цели, значит, без смысла. Таинство сотворения в том, что твоя жизнь и твоя судьба вложены внутри тебя. Именно там хранятся все ответы. Если ты хочешь раскодировать свою жизнь – слушай свое сердце. </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;" lang="EN-US"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">Тайное станет явным</span>
    </strong>
    <strong> </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style=" line-height: 115%;" lang="EN-US"> </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      Божья робота протекает по определенным принципам – из невидимого происходит видимое, тайное Бог делает явным. Скрытое в сердце человека является
      <span> </span>
      строительным материалом для Святого Духа. Именно эти вещи Он материализует.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Обстоятельства нашей жизни ясно отображают то, что мы скрывали в сердце некоторое время назад. То, что мы скрываем сейчас, неизбежно отобразится в скором будущем. Человек, небрежно относящийся к тому, что происходит в его сердце, уже окружил себя плохими обстоятельствами и серией неудач и случайностей со знаком минус. Все это уже есть в невидимом мире, и в скором будущем из невидимого произойдет видимое. </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      Имея огромное желание в своем сердце, храня мечту, мы становимся успешными в духовном мире. Такой человек уже победитель,
      <span> </span>
      и очень скоро его мечты начнут материализоваться. Люди будут задаваться вопросами: « Почему ему повезло? Почему
      <span> </span>
      это имеет?»
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Но секрет в том, что он давно уже имел все это, имел в своем сердце. </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Все это просто материализовалось. Если бы нечему было материализоваться – оно бы никогда не появилось. Нам нужно научиться возгревать в себе огромные желания и хранить их.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">Враги в твоей внутренней жизни</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Есть множество врагов, которые убивают человека изнутри. Именно поэтому множество людей, начавших духовную жизнь, вскоре теряют ее, меняя на плачевную, полную страданий. Так что же является нашим врагом?</span>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">Враг 1: НЕПОСТОЯНСТВО</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Непостоянство – это неспособность сохранять свое внутреннее направление. Человек начинает о чем-то молиться, чего-то желать, но вскоре он меняет свое направление и не достигает желаемых результатов. Особенно это проявляется, когда достижение цели чего-то стоит. Хранить пламенное желание – это черта победителей. Если у вас есть желание, не давайте ему угаснуть. </span>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">Враг 2: СТРАХ</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      У каждого свои страхи. Чем выше наши цели,
      <span> </span>
      тем больше иногда наши страхи. Страхи могут приходить, но в них нельзя жить. Жить человек должен в победе. Пытаясь спрятаться от страха, кто-то отказывается от цели. Путь к осуществлению твоей мечты проложен через внутреннюю борьбу. Что бы достигнуть цели, иногда необходимо выйти из зоны комфорта. Многие люди, чтобы обрести внутренний мир, приносят в жертву свою мечту. Они получают мир, но это не тот мир – это мир на условиях врага. Не давай страху разрушить твое желание.
    </span>
  </p>
  <p class="MsoNormal">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">Враг 3: ЛЕНЬ</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Лень отдаляет человека от дел Божьих. Лень всегда будет загримирована и одета в лукавство. Этот обман из категории самых подлых, потому что в конечном счете человек обманывает сам себя. Лень похоронила множество зачатых проектов, этот замаскированный грех нужно возненавидеть лютой ненавистью и победить, как заклятого врага. Оставаясь ленивым, ты остаешься лукавым, а с лукавым Бог поступает по лукавству его. Будь осторожен. Не играй с Богом в прятки, как Адам. Помни, у тебя великая судьба. </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">Усталость тела и души</span>
    </strong>
    <strong> </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;" lang="EN-US"> </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Усталость – это очень опасный враг; это состояние не считается греховным, но разрушительная сила усталости бывает сильнее, чем сила греха. В моей жизни мне довелось столкнуться с очень серьезным служителем, это человеку было около 70 лет. Через всю жизнь он пронес апостольское служение, он открыл несчетное количество церквей по всему миру. Его служение, его подвиги ради Бога всегда приводят меня в состояние благословения. Несколько лет назад он приезжал в Днепропетровск. Глядя на нашу активность в служении, он дал мне несколько важных советов. Одним из них был совет относительно отдыха.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Он рассказал историю своей жизни: « Я уже несколько лет пребывал в служении, за это время я открыл несколько церквей, построил молитвенные дома для каждой из них, - это были растущие церкви. Бог реально двигался через мое служение, я трудился не жалея сил, но постепенно я стал чувствовать давящую на меня усталость. Игнорируя это чувство, я продолжал это движение с той же скоростью. Так прошло еще несколько лет, и, несмотря на то, что вокруг все двигалось, церкви открывались одна за одной, в моем сердце я стал замечать желание все бросить. </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;" lang="EN-US"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Когда человек истощен и физически и эмоционально,</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Он может уронить свою мечту.</span>
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
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Однажды утром я проснулся, и первое, что я сказал: «Я оставляю все». И в тот день я оставил служение. Целый год мне потребовался на реабилитацию и восстановление. Через год я вернулся в служение». Заканчивая свой рассказ, он сказал: « Не позволь усталости похоронить твое признание, уделяй время для отдыха».</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">История этого великого служителя шокировала меня, и я стал понимать, насколько смертелен яд усталости. Усталость тела через время начнет давить на душу. </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Именно тогда, когда человек истощен и физически и эмоционально, он может уронить свою мечту. Во время сильной усталости виденье как будто исчезает, и на первый план выходят проблемы, проблемы, неудачи и раздражительность. Когда ты уставший – ты уязвимый, но когда мы отдыхаем, проблемы уходят на задний план, обостряются духовные чувства, и на первом плане пылает наше виденье.</span>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <em>
        <span style=" line-height: 115%;">Отдых для энергичного служителя –</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <em>
        <span style=" line-height: 115%;">стратегический шаг.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <em>
        <span style=" line-height: 115%;">Отдых для активного служителя –</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <em>
        <span style=" line-height: 115%;">оружие против врага.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <em>
        <span style=" line-height: 115%;">Отдых для оригинального служителя –</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <em>
        <span style=" line-height: 115%;">лаборатория творчества. </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <em>
        <span style=" line-height: 115%;">Стратегический отдых – это не безделье,</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <em>
        <span style=" line-height: 115%;">это время восстановления и подъема, это время, </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <em>
        <span style=" line-height: 115%;">когда у нас вырастают крылья и мы снова парим в потоках</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: justify;">
    <strong>
      <em>
        <span style=" line-height: 115%;">Святого Духа.</span>
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
      <span style="font-size: 18pt; line-height: 115%;"> </span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">Бог приходит к тем людям, которые имеют огромное желание</span>
    </strong>
    <strong> </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;" lang="EN-US"> </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      Очень часто мы делаем что-то, потому что от нас этого требуют. В таком случае мы находимся под законом и поступаем, как рабы, а не сыны. В результате такого отношения мы не видим успеха.
      <span> </span>
      Желание является движущей силой любого прогресса. Поэтому основанием нашей жизни, нашего служение и бизнеса, также наших достижений в семье должно быть огромное желание, а не «обязаловка». Люди, движимые нуждой или проблемой, подобны птицам, которым не нано парить на высоте, они, как мелководные рыбы, лишены наслаждения, данного тем, кто движем страстью к победе.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Один молодой человек имел большие финансовые проблемы и не мог заработать ни копейки до тех пор, пока ситуация не зашла в тупик. Когда все стало совсем плохо, этот парень нашел выход из тупика. Но он не насладился вкусом победы, а лишь на короткое время облегчил свою жизнь. Мы не можем позволить внешним обстоятельствам стимулировать нашу жизнь. Основой нашей жизни и служения должно быть огромное желание внутри нас.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;" lang="EN-US"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Желание рождает возможности, </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">большие желания – большие возможности.</span>
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
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Бог устроил мир так, что даже для неверующих людей, которые не знают Его, но имеют огромные желания, открыты все двери.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Всемирно известный акробат Дикуль, упав с большой высоты, сломал себе позвоночник. Четыре года он был парализован, но он говорил: « Я встану и буду ходить». Это огромное желание к жизни сделало чудо. Человек с поломанным позвоночником через четыре года встал и на цирковой арене начал поднимать машины. Внутри этот человек – муж желаний. Сильное желание отметило реальный приговор и привело в действие Божественные законы.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">В этой жизни можно ничего не достигнуть только потому, что твои желания очень лимитированы и размеры желаний очень малы. Иногда замечаешь, что человек прожил жизнь, как серая мышь, от такого человека практически никогда и ничего не зависело. Чаще всего за такими людьми стоит равнодушие к жизни и к окружающим, даже к самим себе. Жизнь таких людей похожа на диетпитание, в котором нет вкуса и запаха, а главное – жизненной энергии. Судьба играет такими людьми, а не они судьбой. На твоем жизненном пути попадались люди, которые смотрели на самих себя, как на жертву, потому что у них не было сильного желания вырваться из стандартов, как из клетки, обрести свободу и достичь своей мечты?</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Глубокий отпечаток на нашем мышлении оставили наши родители, детство и юность, которые прошли при коммунизме. Когда инициатива была наказуема, мечты и желания были не в моде. Но это не приговор, а повод для работы над собой.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;" lang="EN-US"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Самое главное в нашей жизни – это мечта.</span>
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
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      Огромная мечта в сердце дороже всего, что вы можете иметь. План дьявола в том, чтобы человек жил с пустой душой. Если нет мечты, значит, ее кто-то украл. Если у вас украдут ваши деньги, это не страшно, Бог восстановит ваши финансы, и уже очень скоро вы забудете об этом. У вас все впереди. Если у вас украдут
      <span> </span>
      автомобиль – не плачьте, значит, скоро у вас будет еще лучше авто. У вас еще все впереди. Если вы остались без жилья – не рыдайте, вы еще будете жить во дворцах, это временно, у вас все впереди. Но если у вас украли мету, то, что бы вы не имели, у вас все позади. Пусть пропадет все, только не дай пропасть мечте.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Можно ли иметь мечту и не увидеть ее исполнения? Конечно. Есть люди, которые говорят о своем видении, но даже пальцем не хотят пошевелить в эту сторону. О таких людях говорят: « Дурень думкой богатеет». Это не мечты – это «панты».</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Правда в том, что для того, чтобы мечта исполнилась, нужно быть одержимым этой мечтой. Это когда ты засыпаешь и думаешь об этом, просыпаешься с мыслями об этом. Давид вставал очень рано, что бы искать Господа, его мечтой был Сам Бог. Он мог ночами молиться и днями приносить жертвы своему Господу. Одержимость мечтой – это внутреннее состояние, которого достигали великие люди. Мартин Лютер мог неделями не выходить на улицу, изучая Писание. Именно поэтому он стал реформатором. </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Часто можно заметить, что христиане одержимы не мечтой, а духом лени. Если с этим не разобраться, можно упустить время и возможности. Дьявол пытается украсть не то, что вне нас, а то, что внутри нас. Он может оставить в покое наши внешние обстоятельства, чтобы завладеть нашей душой.</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;" lang="EN-US"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Видение – это дар Божий и Его благословение</span>
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
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Как-то я разговаривал с одним служителем, у этого человека был огромный успех, но потом, по какой то причине, все сошло на нет. Меня это волновало, и при встрече я задал вопрос ему на эту тему. Его ответ был таким: « Ты знаешь, внешне все шло нормально, а в душе было пусто, вот и вся причина». Тогда я в очередной раз убедился, что больше всего хранимого нужно беречь свое сердце. Виденье – это дар Божий, Его благословение. Береги свою мечту, свое виденье, свое желание. Не привыкай к этому, не считай это чем-то обычным – то дар Божий и Его благословение. Он «производит и хотения и действия по Своему благоволению».</span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;" lang="EN-US"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">Где рождаются мечты?</span>
    </strong>
    <strong> </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;" lang="EN-US"> </span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      Не секрет, что мечты многих христиан сменяют одна другую так же быстро, как рекламные ролики по телевизору. Но есть мечты, которые не горят в огне
      <span> </span>
      и не тонут в воде, не исчезают с переменой погоды. Это мечты, которые пришли с Неба. Именно в Божьем присутствии рождаются мечты, которые способны победить мир. Есть виденье человеческое, которое имеет каждый деловой человек, и есть виденье Божье, которое приходит к тем, кто общается с Ним. 
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">Соломон имел виденье построить храм, оно было от Бога, именно поэтому он переживал Божью поддержку все годы строительства. Виденье, которое осуществится благодаря поддержке Всемогущего Бога. Виденье, которое должно быть у каждого верующего человека – это спасение неверующих людей. </span>
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