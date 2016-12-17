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
    <script src="/static/js/common.js" type="text/javascript"></script>
    <script src="/static/js/openapi.js" type="text/javascript"></script>
    <script src="/static/js/all.js" type="text/javascript"></script>

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
                            <th>Оглавление</th>
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
                    <p>
                        <em>
    <span style="line-height: 115%;">

        ... Для Иудеев я был как Иудей, чтобы приобрести Иудеев ; для подзаконных [был] как подзаконный , чтобы приобрести подзаконных ; для чуждых закона - как чуждый закона ,

      <span> </span>

        - не будучи чужд закона пред Богом , но подзаконен Христу ,

      <span> </span>

        - чтобы приобрести чуждых закона ; для немощных был как немощный , чтобы приобрести немощных. Для всех я сделался всем , чтобы спасти по крайней мере некоторых.

    </span>
                        </em>
                    </p>
                    <p class="MsoNormal">
                        <em>
                            <span style="font-weight:bold;line-height: 115%;">1 Коринфянам 9: 20-22 </span>
                        </em>
                    </p>
                    <p class="MsoNormal" style="text-align: center;" align="center">
                        <strong>
                            <em>
                                <span style="font-size: 26pt; line-height: 115%;">Вступление</span>
                            </em>
                        </strong>
                    </p>
                    <p class="MsoNormal">
    <span style="line-height: 115%;">
      <span> </span>
      Прекрасное служение , и левиты сегодня постарались , однако было одно НО ... Эти пустые места красным ядовитым цветом обжигали моё сердце .
    </span>
                    </p>
                    <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Но почему ?
    </span>
                    </p>
                    <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Опять в моём сердце поднялся этот вопрос. Множество людей, сотни тысяч умирают
      <span> </span>
      в больницах от тяжёлых форм рака, молодые ребята , которые ещё ничего в своей жизни не сделали, умирают от СПИДа и туберкулёза. Пожилые
      <span> </span>
      люди, прошедшие
      <span> </span>
      все ужасы войны и послевоенной жизни, стоят в больничных очередях , ожидая получить хоть какую - нибудь
      <span> </span>
      помощь.
    </span>
                    </p>
                    <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Почему ?
    </span>
                    </p>
                    <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Опять из глубины наболевшего сердца поднялся этот вопрос. Так много детей уже в начале своей жизни имеют такие заболевания, которые не имеют взрослые . Сотни мыслей и воспоминаний пронзили мой разум. Множество людей в больницах , ужас в глазах от сказанного диагноза и предстоящей операции...
    </span>
                    </p>
                    <p class="MsoNormal">
    <span style=" line-height: 115%;">
      <span> </span>
      Вдруг яркой молнией вспыхнула мысль о спасении людей. Иисус Христос - единственный ответ в этой жизни и в будущей .Эта мысль всегда поднимала меня и являлась смыслом всей моей жизни. И тогда я сказал себе, что буду делать всё возможное и невозможное для того , чтобы каждая душа в этом городе узнала об Иисусе.
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