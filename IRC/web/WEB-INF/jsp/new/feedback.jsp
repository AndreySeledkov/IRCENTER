<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="full_support">
    <div class="in_block_support">
        <div class="block_modal_header">Обратная связь</div>
        <div class="support_modal_element">
            <span class="header">Ваше имя:</span>
            <input type="text" id="first_name" name="FirstNameBox" value="">
        </div>
        <div class="support_modal_element">
            <span class="header">Ваша фамилия:</span>
            <input type="text" id="last_name" name="LastNameBox" value="">
        </div>
        <div class="support_modal_element">
            <span class="header">* Email адрес:</span>
            <input type="text" id="mail" name="EmailBox" value="">
        </div>
        <div class="support_modal_element">
            <span class="header">* Тема:</span>
            <select id="theme">
                <option value="-1">Выберите тему</option>
                <option value="0">Ошибка</option>
                <option value="1">Предложение</option>
            </select>
        </div>
        <div class="support_modal_element">
            <span class="header" >* Информация:</span>
            <textarea class="support_full_text" id="info"></textarea>
        </div>

        <div class="support_modal_element">
            <span class="no_shadow fl_right"><a href="javascript:void(0)" onClick="sendSupport()"
                                                class="gradient_blue_button support_modal_button p13"
                                                onClick="">Отправить</a></span>
        </div>
    </div>
</div>
<style type="text/css">
    .full_support {
        overflow: hidden;
        display: block;
        width: 400px;
        padding: 10px 0;
        background-color: #eae9e9;
        border-radius: 5px;
    }
    .in_block_support {
        overflow: hidden;
        display: block;
        width: 360px;
        margin: 0 auto;
    }
    .block_modal_header {
        display: block;
        width: 100%;
        margin: 0 auto;
        margin-bottom: 10px;
        font-size: 23px;
        font-family: "Tahoma";
        color: #52585e;
        text-align: center;
    }
    .support_modal_element {
        overflow: hidden;
        display: block;
        width: 360px;
        margin: 0 auto;
        margin-bottom: 5px;
        font-size: 14px;
        text-align: left;
    }
    .support_modal_element span.header {
        display: block;
        margin-bottom: 3px;
        font-size: 14px;
        color: #4c4c4c;
        text-align: left;
    }
    .support_modal_element span.header.many {
        margin-bottom: 10px;
        font-size: 12px;
    }
    .support_modal_element img.captcha {
        float: right;
        vertical-align: middle;
    }
    .support_modal_element a.small {
        font-size: 12px;
        line-height: 33px;
    }
    .input_bg {
        display: block;
        height: 25px;
        width: 355px;
        background: transparent url(/static/img/auth_reg_input_bg_l.png) no-repeat;
    }
    .input_bg.captcha {
        float: left;
        display: inline-block;
        width: 135px;
        margin-top: 5px;
    }
    .input_bg.captcha input {
        width: 130px;
    }
    .input_bg span {
        float: right;
        display: block;
        width: 5px;
        height: 25px;
        margin-right: -5px;
        background: transparent url(/static/img/auth_reg_input_bg_r.png) no-repeat;
    }
    .input_bg input {
        float: left;
        display: block;
        width: 348px;
        margin-top: 3px;
        margin-left: 3px;
        background: none;
        border: none;
    }
    .support_modal_button {
        height: 33px;
        line-height: 31px;
        color: #fbfaf9;
        text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.3);
    }
    .small_right {
        display: block;
        padding-top: 10px;
        text-align: right;
        font-size: 12px;
    }
    .support_full_text {
        display: block;
        margin: 0 auto;
        width: 352px;
        height: 150px;
    }
</style>