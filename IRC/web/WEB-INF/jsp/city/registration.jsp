<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="widget mb13">
    <center class="header">Регистрация билетов</center>
    <div class="content">
        <div class="support_modal_element">
            <div class="elem_header">Ваше имя:</div>
            <center><input type="text" id="bus_name" name="FirstNameBox" value=""></center>
        </div>
        <div class="support_modal_element">
            <div class="elem_header">Ваш телефон:</div>
            <center><input type="text" id="bus_phone" name="LastNameBox" value=""></center>
        </div>
        <div class="support_modal_element">
            <div class="elem_header">Email адрес:</div>
            <center><input type="text" id="bus_email" name="EmailBox" value=""></center>
        </div>
        <div class="support_modal_element">
            <div class="elem_header">Сообщение:</div>
            <textarea class="support_full_text" id="bus_message"></textarea>
        </div>
        <div class="support_modal_element">
            <center><span class="no_shadow"><a href="javascript:void(0)" class="gradient_blue_button support_modal_button cFFFFFF" onClick="busResistration()">Отправить</a></span></center>
        </div>
    </div>
</div>
<style type="text/css">
    .support_modal_element {
        overflow: hidden;
        display: block;
        width: 270px;
        margin: 0 auto;
        margin-bottom: 5px;
        font-size: 14px;
        text-align: left;
    }
    .support_modal_element .elem_header {
        margin-left: 7px;
        margin-bottom: 5px;
        color: #000000;
    }
    .support_modal_element input {
        width: 250px;
        margin: 0 auto;
    }
    .support_modal_button {
        width: 250px;
        height: 33px;
        margin: 0 auto;
        line-height: 31px;
        color: #fbfaf9;
        text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.3);
    }
    .support_full_text {
        display: block;
        margin: 0 auto;
        width: 250px;
        height: 70px;
        margin: 0 auto;
    }
    .mb20 {
        margin-bottom: 20px;
    }
</style>
