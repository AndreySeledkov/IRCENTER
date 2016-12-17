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
        <th>Глава 8</th>
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
          <a href="book_22" class="toclink">Глава 22</a> - Кто такие неверующие ?
        </td>
      </tr>
    </tbody>
  </table>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <span style="font-size: 20pt; line-height: 115%;">8</span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 20pt; line-height: 115%;">Сначала взаимоотношения,</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 20pt; line-height: 115%;">
          <span> </span>
          потом взаимодействие
        </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Этот принцип приемлем и в отношениях с Богом, и в отношениях с людьми.Прежде чем Бог начнет действовать в жизни человека , человеку необходимо построить отношения с Ним. И мало – помалу Бог начнет действовать в этом человеке, доверяя ему что-то ценноне. Так и с людьми, Писание говорит : «Не могут идти двое не сговорившись».
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      И прежде чем человек останется в церкви или уйдет из нее, должны определиться отношения. Если эти отношения устроят человека, он, конечно же, останется, несмотря на недостатки и минусы. Если же нет , человек уйдет, несмотря на то, что в этой церкви хорошее прославление и проповедь . Даже если там раздают бутерброды под конец каждого служения, если отношения не устраивают , человек там не останется.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      В моей жизни был интересный случай.Это было 16 лет назад, я только пришел в церковь.Я не сильно понимал духовные моменты, но мне запомнилось, что ко мне подошли какие-то ребята , они спросили, кто я и откуда, поговорили со мной минут пять и потом исчезли.Мне хотелось с кем-то поговорить, но я никого там не знал.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      <span> </span>
      Когда я пришел во второй раз произошло то же самое: дежурные вопросы, странные улыбки и неописуемая занятость в их поведении. Было видно, что этим людям не особенно интересно, и мне показалось, что они это делают из сострадания или религиозного чувства долга
      <span> </span>
      . Потом они все ушли в столовую, а я уехал домой. Когда я пришел в третий раз,я ждал конца собрания , мне хотелось общения, впрошлый раз я попросил тех ребят переписать мне стихотворение , которое я услышал на служении, и я был уверен, что меня оставят после собрания пообщаться. Но все произошло , как в прошлый раз: со мной поговорили пять минут, со стихотворением вышла проблема, и потом все удалились (вероятно , молиться о спасении людей).
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Помню , как я посмотрел на всю эту картину и сказал себе : «Я сюда больше не приду, у них свой Бог, у меня свой».Я подошел к двери и взялся за ручку, и вдруг кто-то похлопал меня по плечу, я обернулся, и как вы думаете, кто это был? Наверное, Господь... нет, конечно . Передо мной стояла обычная сестричка в косынке. Она попросила меня вернуться и поговорить с ней , я ожидал, что она уйдет по «срочным Божьим делам», но она говорила со мной два часа. За это время я понял, что буду в этой церкви и мне здесь все нравитсяю Когда закончилась наша беседа, она пригласила меня пообедать, а потом мы поехали на христианский концерт, вернулся я домой вечером.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Этот день был поворотным в моей христианской жизни. Я всегда буду благодарен этой сестре за ее мудрость и терпение. Самое большое чудо – это отношения.Бог действует более всего через отношения, поэтому две главные заповеди – возлюбить Бога и возлюбить ближнего.Послание Божье – это любовь, а любовь – это взаимоотношения. 
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Взаимоотношения спасут мир. В доверии человек открывается человеку.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Сначала – взаимоотношения, потом – Евангелие. Многие верующие закрылись в своей религиозной жизни , в своих домашних группах. Однажды я делал опрос людей в церкви, моим вопросом было: сколько людей за последний год вы привели в церковь ? К моему удивлению , большинство верующих людей не знают, что ответить, потому что за последний год они не привели ни одного человека. В лучшем случае можно было услышать : « Я прочитала с женщиной молитву покаяния на остановке».
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Я всегда говорю : необходимо большее, ведь прочитать молитву покаяния на остановке и не увидеть этого человека больше никогда – не так сложно, намного серьезней дело обстоит, когда нужно взять на себя ответственность привести человека в церковь и позаботиться о том, чтобы он там остался.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;"> </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <span style="font-size: 18pt; line-height: 115%;">ИЗОЛЯЦИЯ</span>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style="font-size: 18pt; line-height: 115%;">
      <span> </span>
      Дьявол хочет изолировать каждого верующего , чтобы тот не имел доступа к неспасенным людям.
    </span>
  </p>
  <p class="MsoNormal">
    <span style="font-size: 18pt; line-height: 115%;">
      <span> </span>
      Можно заметить по любой церкви, что новых людей приводят в основном новообращенные. Причина в том, что у новообращенного есть еще много неверующих родственников, друзей и знакомых. У того, кто верующий долго, в этом проблема, они перестают влиять на этот мир, потому что общаются только со «своими», с теми, с кем удобно, устраивают свою жизнь и занимаются своим духовным ростом.
    </span>
  </p>
  <p class="MsoNormal">
    <span style="font-size: 18pt; line-height: 115%;">
      <span> </span>
      У многих верующих нет ни одного человека, которому они могли бы проповедовать, нет знакомых , к кому они могут прийти, и им будут рады. Почему ? Однажды, когда я учил на лидерском о личном евангелизме, один брат сказал : «Пастор , а что мне делать, если я столько проповедовал, что люди теперь мне двери не открывают ?»
    </span>
  </p>
  <p class="MsoNormal">
    <span style="font-size: 18pt; line-height: 115%;">
      <span> </span>
      Своей проповедью можно поломать отношения с человеком. Очень важно иметь в устах правильное слово, но ещё важней сказать его вовремя. Из-за нашей «проповеди» люди, увидев нас, переходят на другую сторону улицы. Так давайте нести Евангелие так, чтобы , когда они будут идти по другой стороне улицы и увидят вас, они с улыбкой перешли на вашу.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 20pt; line-height: 115%;">Принцип маятника</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Неправильная проповедь Евангелия может оттолкнуть человека от Бога. Срабатывает принцип маятника, мы как будто «толкаем» Евангелие на человека, и он отталкивает его в ответ. Но если мы не навязываем и не давим, мв даем человеку возможность открыться нам навстречу.Мы не «загоняем» людей в Царство Божье , мы дружим и ведем их за собой.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 20pt; line-height: 115%;">Сначала душевное, потом духовное</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Мы должны помнить: если мы хотим прийти к духовному результату, нам нужно начинать с душевного. Никогда не начинай с проповеди, начинай с дружбы. Прежде чем строить духовые мосты, построй душевные. Душевные мосты – это значит обычные человеческие отношения. 
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 20pt; line-height: 115%;">Узнай интересы человека</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Прежде чем строить беседу, нам нужно понять интересы этого человека , о чем он думает положительно и о чем отрицательно, узнать, что его волнует в этой жизни и что его не волнует. В первые пять минут беседы можно сделать поверхностный анализ, чтобы установить, из какой категории людей этот человек.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Возможно, это обычная домохозяйка, любящая пустые разговоры , возможно, целеустремленный успешный человек, имеющий свой бизнес, может быть, это человек связанный с наркотиком или бандитизмом, а может, обычный рабочий, который много лет проработал на заводе.Есть люди, которым интересно говорить о политике, и есть люди, которым это неинтересно.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Итак, узнать о человеке и его интересах является программой номер один.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Когда мы это понимаем, нам легче построить общение и стать интересными собеседниками.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Узнай проблемы человека</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Задачей номер два является узнать проблемы вашего собеседника. Если у нас получилось узнать интересы этого человека и стать достойным собеседником, у нас обязательно получится узнать его проблемы. Люди не любят говорить о своих проблемах незнакомым, и поэтому в первые минуты общения собеседник не будет откровенен с вами. Наоборот, все всегда выглядит примерно так : в начале беседы , если вы спросите у человека, как у него дела, он ответит , что у него все круто , но, пообщавшись 10-15 минут, вы начнете узнавать другую сторону этой медали.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Проявите интерес к проблемам человека, и вы станете для него другом. Вам не нужно ему проповедовать , вам нужно предложить молитву за его нужду. Это должно быть своевременно и уместно. Когда вы помолитесь за его нужду, Бог обязательно ответит, и этот человек изменит свое отношение к вам и к тому, что вы говорите.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 20pt; line-height: 115%;">Как молиться ?</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Перед тем как молиться за нужду человека и после молитвы вы должны сказать этому человеку, что Бог отвечает на ваши молитвы, что вы уже неоднократно молились за людей и Бог делал Свои чудеса. Расскажите кратко какой-то случай из своей жизни, какой-то пример, когда у человека была безвыходная ситуация, вы помолились, и бог ответил и решил эту проблему.Во время молитвы вы сами должны твердо знать, что Бог ответит, потому что вы выполняете Его работу.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Как произносить молитву ?</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Молитва не должна быть долгой, она должна быть краткой, но не скомканной. Вам не обязательно молиться на иных языках с изменением в голосе. Желательно произнести молитву на русском языке, терминология которой будет понятна вашему собеседнику.Пусть эта молитва будет во свидетельство и в назидание.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Другими словами, это должна быть здравая молитва, всему свое место и время. Когда вы будете молиться дома за этого человека, вы можете кричать до хрипоты на иных языках и качаться по полу, но когда вы молитесь рядом с ним – проявите снисходительность.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">За какие нужды молиться ?</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      У человека , с которым вы будете разговаривать , могут быть разные нужды. И может быть одна основная нужда, обратите на нее внимание. Есть нужды, которые могут быть отвечены сейчас, как чудо Божье. Есть нужды, решения которых потребует много времени. Однозначно следует обратить внимание и свою молитву на те нужды, где чудо Божье может произойти в ближайшие дни. Вам нужно чувствовать, где будет проявление Божье. 
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Когда вы будете расставаться с тем, с кем общались, пообещайте этому человеку, что вы будете молиться за его нужды. Помните, Бог обязательно явит Свое чудо.
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
        <span style="font-size: 18pt; line-height: 115%;"> </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Церковный язык</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Один из важнейших моментов проповеди Евангелия –это стиль изложения послания, наша речь. Часто язык веруещего человека с годами приобретает религиозный оттенок, который ставит барьер непонимания. Горя желанием спасать людей, такой христианин при встрече старого друга обрушивает на него шквал подобных фраз: «Шалом, мир Божий!», «Халлилуя, помазанник!», «Будь благословен во имя Иисуса!», «Ты ещё халдей? Господь обрежет тебя!». «Амэн!». А затем прямо посреди улицы начинает молиться за него на иных языках.Не трудно догадаться, какой будет реакция старого друга. Скорее всего он постарается никогда больше не попадаться на глаза этому «горе-евангелисту».
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Речь, богатая церковными терминами, отпугивает окружающих. Слушая такого евангелиста, человек настораживается. Незнакомые слова всегда пугали и напрягали людей. Послание должно быть простым и доступным. Люди не должны, слушая нас, недоумевать и пытаться отыскать в сборнике теологических терминов суть послания.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Иисус проповедовал ученикам и народу притчами, которые были понятны им. Евангелие должно быть таким, чтобы его понимали даже дети. Иисус говорил : «Будьте просты,как голуби, и мудры , как змеи». Мудрость можно излагать простым понятным языком.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      У Бога всегда есть ответ для Своих людей, но часто Ему мешает человек, несущий его. Каждый слой населения имеет свой лексикон , и нам важно быть среди них своими, а не иностранцами.
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