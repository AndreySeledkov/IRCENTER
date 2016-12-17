<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<div id="profile_right_column">
    <div class="profile_right_column_header_elements">
        <div class="list_element_center">
            <ul class="fl_left">
                <li><span class="select">Написать сообщение</span></li>
            </ul>
            <ul class="fl_right">
                <li><a href="/messages?section=inbox">Полученые</a></li>
                <li><a href="/messages?section=outbox">Отправленые</a></li>
            </ul>
        </div>
    </div>
    <div class="profile_right_column_content">
        <div class="new_message">
            <div class="new_message_header">Новое сообщение</div>
            <div class="new_message_string">
                <div class="new_message_string_left">Кому:</div>
                <div class="new_message_string_right"><a href="javascript:void(0)" class="message_to_name"><c:out
                        value="${userReceiver.loginName}"></c:out>
                </a></div>
            </div>
            <%--<div class="new_message_string">--%>
                <%--<div class="new_message_string_left">Тема:</div>--%>
                <%--<div class="new_message_string_right"><input type="text" id="themeId"></div>--%>
            <%--</div>--%>
            <div class="new_message_string">
                <div class="new_message_string_left">Сообщение:</div>
                <div class="new_message_string_right"><textarea id="messageText"></textarea></div>
            </div>

            <div class="new_message_string_bottom mt20">
                <div class="new_message_string_right full"><a href="javascript:void(0)"
                                                              onclick="sendMessage(${userReceiver.userId})"
                                                              class="message_send blue_button">Отправить</a> <a
                        href="/friends?section=all" class="message_cancel silver_button">Отмена</a></div>
            </div>

        </div>
    </div>
</div>
</body>
</html>