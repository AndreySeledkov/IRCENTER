<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="wrap">

    <h2>
        Новое Тайна духовного мира
        <%--<a href="/admin/tv/add_new" class="button add-new-h2">Добавить новую</a>--%>
    </h2>
    <form:form id="post" method="post" action="/admin/secret_spiritual_world/add_new">
    <div>Название - <input class="date" id="title" name="title"></div>
    <div>Ссылка -
        <textarea name="link" rows="5" cols="40">
        </textarea>
    </div>
    Текст - <textarea id="area3" name="text" style="width: 300px; height: 100px;">
</textarea>
        <ckeditor:replace replace="area3" basePath="/static/ckeditor/"/>
    <br>
    <input type="submit" value="Добавить">
    </form:form>

    <div>