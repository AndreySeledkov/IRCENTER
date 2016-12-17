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
        <th>Глава 9</th>
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
      <span style="font-size: 20pt; line-height: 115%;">9</span>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Списки знакомых</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Следующий пункт, который я получил относительно личного евангелизма, - списки знакомых. Список знакомых – это конкретный план того, кого ты будешь посещать , за кого молиться, с кем подружишься в течении нескольких месяцев. Каждый человек в церкви должен составить списки своих друзей и знакомых. Желательно вспомнить всех, с кем вы когда-то дружили, учились. Вспомните тех , с кем вы когда-то работали или занимались спортом, вспомните имена этих людей. Каждого из них сегодня несложно найти, есть Интернет и мобильная связь. Каждый из этих людей- потенциальный христианин.Возможно, вы не видели этого человека много лет, пусть вас это не останавливает.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      <span> </span>
      Сделайте списки – это самый первый шаг. Не сомневайтесь, многих людей из этого списка вы скоро увидете у себя в церкви. Когда я вернулся с молитвенной горы и мы первый раз сделали это у себя в церкви, многие верующие привели по тридцать человек за месяц. Я знаю, что у вас тоже получится, и уже очень скоро вы увидете покаяние многих людей, которых вы привели к Богу.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Неправильные списки</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Многое зависит от того, как человек составляет списки своих знакомых.Именно от этого зависит – будет успех или нет. Можно неправильно составить списки и , не увидев результата, подумать, что Бог не действует в твоей евангелизации.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      На самом деле подход к этому делу должен быть очень щепетильным.Когда люди составляют списки знакомых, они должны знать, что там должен быть записан правильный человек.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Первая ошибка, которую делают люди : они записывают в списки своих близких и родных.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Например, женщина записывает своего мужа, своего сына, которым она уже проповедовала и которые уже имеют определенное настроение относительно церкви. Или же человек записывает тех, с кем он встречается на работе каждый день и кого он уже много раз приглашал на собрание и
      <span> </span>
      получал отказ. Это неправильный подход к спискам, такая евангелизация не принесет особого результата.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Относительно спасения наших семей есть другая стратегия : приглашай в свой дом людей из церкви, которые могли бы подружиться с твоими родными и повлиять на их жизнь .
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Правильные списки</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Запишите тех людей, которых вы не видели какое-то время, может, даже много лет. Возможно, эти люди не знают , что вы верующий, или знают это через кого-то.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Что делать, если вы уже давно верующий и вам некому позвонить и некого записать ? В таком случае нужно действовать через новообращенных.Если вы обратите внимание на человека в церкви, который покаялся недавно и возьмете за него ответственность, у вас появится целое поле деятельности.
    </span>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      У каждого новообращенного есть родственники, друзья и знакомые. Вам необходимо взять его за руку
      <span> </span>
      и пойти к нему в гости, чтобы помолиться зв его дом, познакомиться с его родными. И в один вечер у вас появится несколько человек, которых вы сможете привести в церковь в ближайшее воскресенье. Затем вы можете познакомиться с друзьями вашего новообращенного и найти с ними что-то общее, о чем им интересно будет с вами поговорить.
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Новообращенный – это дверь в целый мир людей.</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Попросите новообращенного сделать список своих знакомых , поверьте, их у него много, - это ваша возможность. Когда он сделает список , помолитесь с ним и идите вместе к этим людям. Таким образом, у вас появится много знакомых, с которыми вы сможете дружить и привести их к Богу. Важно быть активным. Я не раз видел случаи, когда новообращенный, который познакомил наставника со своей семьей и друзьями, через время отступал от Бога, а те, с кем познакомился наставник, остались в церкви и стали служителями.
      <span> </span>
      Итак, списки знакомых новообращенного могут через время стать и вашими списками. Делайте правильные списки, и вы увидете реальный результат. 
    </span>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">Человек, который с трепетом подходит</span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">
          <span> </span>
          к составлению списка знакомых,
        </span>
      </em>
    </strong>
  </p>
  <p class="MsoNormal" style="text-align: center;" align="center">
    <strong>
      <em>
        <span style="font-size: 18pt; line-height: 115%;">
          <span> </span>
          без плода не останется.
        </span>
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