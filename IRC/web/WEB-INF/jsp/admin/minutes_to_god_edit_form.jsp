<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrap">
    <div id="icon-edit" class="icon32 icon32-posts-post">
        <br/>
    </div>
    <h2>
        Редактировать 5 минут для бога
        <%--<a href="/admin/tv/add_new" class="button add-new-h2">Добавить новую</a>--%>
    </h2>
    <form:form id="post" method="post" action="/admin/minutes_to_god/submit_edit_minutes_to_god">
    <input type="hidden" name="id" name="id" value="${minutesToGod.getId()}">

    <div>Название - <input class="date" id="title" name="title" value="${minutesToGod.getTitle()}"></div>

    <div>Ссылка -

        <textarea rows="5" cols="40" name="link">
                ${minutesToGod.getLink()}
        </textarea>

    </div>
    Текст - <textarea id="area3" name="text" style="width: 300px; height: 100px;">
        ${minutesToGod.getText()}
</textarea>
        <ckeditor:replace replace="area3" basePath="/static/ckeditor/"/>
    <br>
    <input type="submit" value="Добавить">
    </form:form>

    <div>