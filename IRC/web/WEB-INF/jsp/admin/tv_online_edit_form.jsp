<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <script type="text/javascript" src="/static/protoplasm/protoplasm.js"></script>
    <script type="text/javascript">
        Protoplasm.use('datepicker')
                .transform('.datetimepicker', { locale:'ru_RU', dateTimeFormat:'yyyy-MM-dd HH:mm', timePicker:true, use24hrs:true });
    </script>
</head>
<body>
<div class="wrap">
    <div id="icon-edit" class="icon32 icon32-posts-post">
        <br/>
    </div>
    <form:form id="post" method="post" action="/admin/tv_online/submit_edit_online_tv">
        <input type="hidden" name="id" value="${onlineTv.getId()}">
        <h2>Онлайн тв</h2>
        Введите время начала события -
        <input type="text" name="date" class="datetimepicker" id="time_start"/>
        <input type="submit" value="Добавить">
    </form:form>

    <div>
    </div>
</div>
</body>
</html>