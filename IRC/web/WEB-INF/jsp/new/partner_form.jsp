<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="partnerka_molitva">
    <div class="partnerka_molitva_in">
        <p>Став партнером нашего служения, вы не только участвуете в жизни многих людей. Вы получаете благословения, так
            как у нас создана группа ходатаев. И каждый день они молятся за нужды наших партнеров. Эта молитвенная
            группа состоит из сильных служителей. Критерием принятия в молитвенную группу является
            умение высекать ответы в молитве, достигать конкретных результатов. Результатом молитв является множество
            свидетельств наших партнеров.</p>

        <p>Мы хотим служить молитвой каждому партнеру. Вы спросите: почему молитва? Секретом помазания, которое имеет
            пастор Владимир, стало его приоритетное отношение к молитве. На протяжении многих лет он молится по много
            часов в день, уповая во всем только на Бога. Этому же он научил свою команду. Видя его пример, сегодня они
            проводят в ходатайственной молитве от 3-х до 5-ти часов в день. "Именно молитва, - говорит пастор Владимир,
            - является источником пробуждения".</p>

        <p>Мы хотим знать ваши нужды, молиться за вас, хотим быть вашими друзьями. Если вы еще не стали партнером нашего
            служения, мы приглашаем вас. Те финансы, которые мы сеем, не стоят того, что мы приобретаем, становясь
            соучастниками и соработниками в спасении людей. Мы приобретаем друг друга, как друзья. В Слове сказано:
            …ищите добра друг другу и всем. 1-е Фес. 5 : 15 В любой момент Вы можете звонить нам, и мы будем молиться за
            Вас и Вашу семью, поддержим Вас в трудную минуту. Помогите нам служить в спасении и исцелении людей.</p>

        <p>Мы верим в силу молитвы и если вы являетесь партнером, Вы можете написать нам свою нужду и прислать свое
            фото. И специально организованная группа ходатаев за партнеров, на основание Слова Божьего и Ваших жертв
            будет молиться о Вас и о решение ваших нужд, провозглашая спасение, исцеление и процветание в Ваши Дома!</p>

        <h3 align="right" style="font-style: Italic;">Владимир  Мунтян</h3>
        <hr/>
        <div class="partnerka_block_header_in" style="padding: 0px;">
            Прямо сейчас вы можете оставить свою нужду на сайте
            <br>
            (в строке "имя" напишите "Партнёр"). Мы молимся о Вас!
        </div>
        <div class="">
            <div class="input_pole">
                <input type="text" title="Ваше имя">
            </div>
            <div class="input_pole">
                <input type="text" title="E-mail адрес">
            </div>
            <div class="input_pole">
                <input type="text" title="Телефон">
            </div>
            <div class="input_pole">
                <select>
                    <c:forEach items="${countries}" var="country">
                        <option value="${country.getCountryId()}">${country.getCountryName()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="textarea_pole">
                <textarea title="Молитвенная нужда"></textarea>
            </div>
            <div class="input_pole small_pole fl_left">
                ${reCaptchaHtml1}
            </div>
            <a href="javascript:void(0)" onclick="sendPartnerPrayer()" class="partnerka_button fl_right">Отправить</a>
        </div>

    </div>
</div>
<style type="text/css">
    .partnerka_molitva {
        overflow: hidden;
        display: block;
        width: 700px;
        padding: 20px 0;
        background-color: #eae9e9;
        border-radius: 5px;
    }
    .partnerka_molitva_in {
        overflow: hidden;
        display: block;
        width: 640px;
        margin: 0 auto;
        font-weight: none;
        font-size: 14px;
    }
    .partnerka_molitva_in p {
        margin: 0.5em;
    }
    .partnerka_molitva_in h3 {
        margin: 0.5em;
    }
    .partnerka_molitva_in hr {
        border-top: 1px solid #949595;
        border-bottom: 1px solid #ffffff;
    }
</style>
