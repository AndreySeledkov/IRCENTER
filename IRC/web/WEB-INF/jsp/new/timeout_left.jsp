<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<div id="timeout_left">
    <div class="top">
        <img src="static/img/img_photo.png" alt="" class="fl_right">
        <span class="top_text">ОСТАЛОСЬ ДО ЭФИРА</span>
        <span class="bottom_text">Онлайн служение</span>
    </div>
    <div class="bottom">
        <span class="hour_day_data">0</span>
        <span class="hour_day_type">д</span>
        <span class="minute_hour_data">0</span>
        <span class="minute_hour_type">ч</span>
        <span class="second_minute_data">0</span>
        <span class="second_minute_type">м</span>
    </div>
</div>