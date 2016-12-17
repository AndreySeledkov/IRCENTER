<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrap">
    <div id="icon-edit" class="icon32 icon32-posts-post">
        <br/>
    </div>
    <h2>
        Новое 5 минут для бога
        <%--<a href="/admin/tv/add_new" class="button add-new-h2">Добавить новую</a>--%>
    </h2>
    <form:form id="post" method="post" action="/admin/minutes_to_god/add_new">
    <div>Название - <input class="date" id="title" name="title"></div>

    <div>Ссылка - <textarea rows="5" cols="40" class="date" id="link" name="link">

    </textarea></div>

    Текст - <textarea id="area3" name="text" style="width: 300px; height: 100px;">

</textarea>
        <ckeditor:replace replace="area3" basePath="/static/ckeditor/"/>
    <br>
    <input type="submit" value="Добавить">
    </form:form>

    <div>
