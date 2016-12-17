<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
    long unReadMessage = (Long) request.getSession().getAttribute("unReadMessage");
    long requestFriend = (Long) request.getSession().getAttribute("requestFriend");
%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Партнерство</title>
    <link type="image/x-icon" rel="shortcut icon" href="/static/img/favicon.ico">
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
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
        $(document).ready(function () {
//            setInterval(function(){update_timer(new Date(${onlineLeft}));}, 1000/30);

        });

        function sendPartnerPrayer() {
            $.post("/partner/send_partner_prayer", {name:$('#name').val(), mail:$('#mail').val(), phone:$('#phone').val(), country:$('#country').val(), text:$('#text').val()},
                    function (response) {
                        if (response.complete) {
                            show_message('Отправлено', 2);
                            placeholder_imitator();
                        }
                        hideModal()
                    }, 'json')
        }

        function sendPartner() {
            $.post("/partner/send_partner", {recaptcha_challenge_field:$('#recaptcha_challenge_field').val(), recaptcha_response_field:$('#recaptcha_response_field').val(), name:$('#name').val(), mail:$('#mail').val(), phone:$('#phone').val(), country:$('#country').val(), text:$('#text').val()},
                    function (response) {
                        if (response.complete) {
                            Recaptcha.reload();
                            show_message('Отправлено', 2);
                            placeholder_imitator();
                        }
                        hideModal()
                    }, 'json')
        }


    </script>
    <script type="text/javascript">
        (function (d, w, c) {
            (w[c] = w[c] || []).push(function() {
                try {
                    w.yaCounter15559465 = new Ya.Metrika({id:15559465, enableAll: true, webvisor:true});
                } catch(e) {}
            });

            var n = d.getElementsByTagName("script")[0],
                    s = d.createElement("script"),
                    f = function () { n.parentNode.insertBefore(s, n); };
            s.type = "text/javascript";
            s.async = true;
            s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";

            if (w.opera == "[object Opera]") {
                d.addEventListener("DOMContentLoaded", f);
            } else { f(); }
        })(document, window, "yandex_metrika_callbacks");
    </script>
</head>
<body>

<sec:authorize access="isAnonymous()">
    <jsp:include page="anonymous_header_partner.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="authenticated_header_partner.jsp"/>
</sec:authorize>

<div id="content">
<div class="content_center_outer_out">
<div class="content_center_outer">
<div id="right_sidebar">
    <jsp:include page="delivery.jsp"/>
    <jsp:include page="tv_programm.jsp"/>
    <jsp:include page="banners.jsp"/>

</div>
<!-- /right_sidebar -->

<div class="main_content main_content_center top bottom">
<div id="left_column">
    <div class="partnerka_header">
        <span class="partnerka_header_text">Мы благодарны каждому Партнеру<br/>за молитвенную и финансовую поддержку<br/>мы молимся за Вас!</span>
        <img src="/static/img/partnerka_bird.png" alt="" class="partnerka_bird"/>
        <a href="javascript:void(0);" onClick="createModal('/partner/get_form');"
           class="partnerka_button mt20 partnerka_button_header">Главная привилегия партнера</a>
    </div>
    <div class="partnerka_header_big_text">СТАНЬ НАШИМ ПАРТНЁРОМ!</div>
    <div class="partnerka_otziv">
        <img src="/image/getimage/6/${pastorTreatment.getDefaultImage()}" alt=""/>
        <a href="javascript:void(0)" class="partnerka_otziv_header">Обращение пастора к партнерам</a>
						<span class="partnerka_otziv_text">

                               <div id="partnerka_otziv1_1">
                                   ${pastorTreatment.getFirstText()}
                               </div>

							<span id="partnerka_otziv1" style="display:none;">
                                ${pastorTreatment.getSecondText()}
                            </span>
						</span>
        <a href="javascript:void(0);"
           onClick="if ($('#partnerka_otziv1').css('display')=='none'){$('#partnerka_otziv1').css('display', 'inline');$('#partnerka_otziv1_1').css('display', 'none');} else if ($('#partnerka_otziv1').css('display')=='inline'){$('#partnerka_otziv1').css('display', 'none');$('#partnerka_otziv1_1').css('display', 'inline');}"
           class="partnerka_otziv_more">Подробнее</a>
    </div>
    <div class="partnerka_otziv">
        <img src="/image/getimage/6/${evidenceHealing.getDefaultImage()}" alt=""/>
        <a href="javascript:void(0);" class="partnerka_otziv_header">Свидетельство Исцеления</a>
						<span class="partnerka_otziv_text">
                             <div id="partnerka_otziv2_1">
                                 ${evidenceHealing.getFirstText()}
                             </div>

							<span id="partnerka_otziv2" style="display:none;">
                                ${evidenceHealing.getSecondText()}
                            </span>
						</span>
        <a href="javascript:void(0);"
           onClick="if ($('#partnerka_otziv2').css('display')=='none'){$('#partnerka_otziv2').css('display', 'inline');$('#partnerka_otziv2_1').css('display', 'none');} else if ($('#partnerka_otziv2').css('display')=='inline'){$('#partnerka_otziv2').css('display', 'none');$('#partnerka_otziv2_1').css('display', 'inline');}"
           class="partnerka_otziv_more">Подробнее</a>
    </div>
    <a href="javascript:void(0);" onClick="show_partner_block('partner_block_1', this);"
       class="partnerka_block_header">Реквизиты для пожертвования On-line<span
            style="float:right;">+</span></a>

    <div id="partner_block_1" class="partnerka_block">
        <div class="partnerka_block_element m13">
            <span class="colorized fl_left small_left_block_partnerka">Для пользователей:</span> <span
                style="font-size:25px;font-weight:bold;font-style:Italic;color:#000000;float: left;line-height: 30px;">PayPal</span>

            <form id="paypal" action="https://www.paypal.com/cgi-bin/webscr" method="post"
                  style="display: inline-block; margin-left: 20px;">
                <input name="cmd" value="_s-xclick" type="hidden"/>
                <input name="hosted_button_id" value="2D7YAHCQHGAX6" type="hidden"/>
                <a href="javascript:void(0);" onClick="$('#paypal').submit();"
                   class="partnerka_button">Заплатить</a>
            </form>
        </div>
        <hr/>
        <div class="partnerka_block_element m13">
            <span class="colorized fl_left small_left_block_partnerka">Пожертвование с карточки:</span>

            <div class="fl_left"><img src="/static/img/tmp142.png" alt="" style="vertical-align: middle;"/>
            </div>
            <div class="cards_block_enter">
                <form id="bank" action="https://ukrpays.com/frontend/frontend.php" method="post">
                    <input name="service_id" value="369" type="hidden"/>
                    <input name="order" value="&lt;1&gt;" type="hidden"/>
                    <input name="desc" value="Оплата услуг" type="hidden"/>
                    <input name="sus_url" value="http://regeneration.in.ua/partners/index_10.html"
                           type="hidden"/>
                    <input name="back_url" value="http://regeneration.in.ua/partners/index_7.html"
                           type="hidden"/>
                    <input name="charset" value="windows-1251" type="hidden"/>
                    <input name="theme" value="green" type="hidden"/>
                    <input name="lang" value="ru" type="hidden"/>

                    <input type="text" name="note" title="Телефон">
                    <input type="text" name="fio" title="Фамилия">
                    <input type="text" name="amount" title="30.00">
                    <a href="javascript:void(0);" onClick="$('#bank').submit();"
                       class="partnerka_button" style=" margin-left: 13px;">Заплатить</a>
                </form>
            </div>
        </div>
        <hr/>
        <div class="partnerka_block_element m5_10">
                            <span class="colorized small_left_block_partnerka"><img src="/static/img/webmoney_logo.png"
                                                                                    alt="" style="" width="16"
                                                                                    height="16"/> Web Money:</span>

            <div style="oveflow: hidden;display:block;float: left;">
                <div class="big_left_block_partnerka">Счет в евро <span
                        class="black_text">E374717306819</span></div>
                <div class="big_left_block_partnerka">Счет в рублях <span class="black_text">R367298373108</span>
                </div>
                <div class="big_left_block_partnerka">Счет в долларах США <span
                        class="black_text">Z296810641264</span>
                </div>
                <div class="big_left_block_partnerka">Счет в гривнах <span class="black_text">U994101055661</span>
                </div>
            </div>
        </div>
        <hr/>
        <div class="partnerka_block_element m5_10">
                            <span class="colorized small_left_block_partnerka"><img src="/static/img/yandex_logo.png"
                                                                                    alt="" width="16"
                                                                                    height="16"/> <span
                                    style="color:#ff0000;">Я</span>ндекс деньги:</span>

            <div class="small_left_block_partnerka"><span class="black_text"
                                                          style="font-size:20px;line-height: 31px;">41001836044412</span>
            </div>
        </div>
    </div>
    <a href="javascript:void(0);" onClick="show_partner_block('partner_block_2', this)"
       class="partnerka_block_header">Реквизиты для пожертвования для Украины<span style="float:right;">+</span></a>

    <div id="partner_block_2" class="partnerka_block">
        <div class="partnerka_block_element m13">
                            <span class="colorized small_left_block_partnerka"><img src="/static/img/privat_logo.png"
                                                                                    alt="" style="" width="16"
                                                                                    height="16"/> Расчетный счет в банке:</span>

            <div class="fl_left" style="margin-top:9px;">ХРОПЕ «Возрождение» ОКПО 26459902
                <p>
                    р/с 26008050500812 в КБ «Приватбанк» <br/>
                    г. Днепропетровск МФО 305299
                </p>
                Назначение платежа: «добровольное<br/> пожертвование»
            </div>
        </div>
        <hr/>
        <div class="partnerka_block_element">
            <span class="colorized small_left_block_partnerka">Почтовый перевод<br/>на частное лицо: <br/></span>

            <div class="black_text small_text"
                 style="display:inline-block;vertical-align:middle;margin-top:9px;">Получатель: Бессонов
                Роман Владимирович, <br/>
                а/я 1721, г. Днепропетровск, 49026
            </div>
        </div>
    </div>
    <a href="javascript:void(0);" onClick="show_partner_block('partner_block_3', this);"
       class="partnerka_block_header">Реквизиты для пожертвования стран СНГ<span
            style="float:right;">+</span></a>

    <div id="partner_block_3" class="partnerka_block">
        <div class="m5_10">
            <b>Внимание: </b>

            <p>После отправления пожертвования сообщите нам следующие данные:
                Фамилию, имя, отчество, страну, город, номер телефона, адрес, сумму
                и молитвенную нужду по тел. +38 063 171 8302 </p>

            <p>или pomazanik@list.ru ,почтовый адрес: а/я 1721, г. Днепропетровск, 49026</p>

            <p>Вы можете воспользоваться всеми ниже перечисленными способами.
                С других стран по системам:</p>

            <p>Western Union, Money Gram, Private Money, UNI stream</p>

            <p>Получатель: Bessonov Roman, Dnepropetrovsk, Ukraine</p>
        </div>
    </div>
    <a href="javascript:void(0);" onClick="show_partner_block('partner_block_4', this);"
       class="partnerka_block_header">Анкета партнерства<span style="float:right;">+</span></a>

    <div id="partner_block_4" class="partnerka_block">
        <div class="partnerka_block_header_in">Если Вы уже решили стать нашим партнером , то Вам нужно
            заполнить Анкету Партнера
        </div>
        <div class="m5_10">
            <div class="input_pole">
                <input type="text" id="name" title="Ваше имя">
            </div>
            <div class="input_pole">
                <input type="text" id="mail" title="E-mail адрес">
            </div>
            <div class="input_pole">
                <input type="text" id="phone" title="Телефон">
            </div>
            <div class="input_pole">
                <select id="country">
                    <c:forEach items="${countries}" var="country" varStatus="countryStatus">
                        <option value="${countryStatus.count}">${country.getCountryName()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="textarea_pole">
                <textarea id="text" title="Я становлюсь вашим партнером, "></textarea>
            </div>
            <div class="input_pole small_pole fl_left">
                ${reCaptchaHtml}
            </div>
            <a href="javascript:placeholder_imitator();" onclick="sendPartner()"
               class="partnerka_button fl_right">Отправить</a>
        </div>
    </div>
</div>
<!-- /left_column -->
</div>
<!-- /main_content -->
</div>
</div>
</div>

<jsp:include page="footer.jsp"/>
<!-- /footer_out -->
</body>
</html>