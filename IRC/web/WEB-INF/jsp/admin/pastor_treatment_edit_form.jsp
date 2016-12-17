<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrap">

    <h2>
        Редактирование Обращение Пастаря
        <%--<a href="/admin/tv/add_new" class="button add-new-h2">Добавить новую</a>--%>
    </h2>

    <div>Номер картинки - <input class="date" id="default_image" value="${pastorTreatment.getDefaultImage()}"></div>
    Текст - <textarea id="area3" style="width: 300px; height: 100px;">
    ${pastorTreatment.getText()}
</textarea>
    <ckeditor:replace replace="area3" basePath="/static/ckeditor/"/>
    <br>
    <input type="button" value="Добавить" onclick="sendEditPastorTreatment()">

    <div>
