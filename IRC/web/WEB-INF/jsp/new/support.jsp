<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <option value="0">Предложение</option>
                <option value="1">Ошибка</option>
            </select>
        </div>
        <div class="support_modal_element">
            <span class="header">* Информация:</span>
            <textarea class="support_full_text" id="info"></textarea>
        </div>

        <div class="support_modal_element">
            <span class="no_shadow fl_right"><a href="javascript:void(0)" onclick="sendSupport()"
                                                class="gradient_blue_button support_modal_button p13" onClick="">Отправить</a></span>
        </div>
    </div>
</div>
