<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="wpbody-content">
    <div class="wrap">

        <h2>
            Новая Телепередачи
            <%--<a href="/admin/tv/add_new" class="button add-new-h2">Добавить новую</a>--%>
        </h2>
        <form:form id="post" method="post" action="/admin/tv/add_new">
        <div>Название - <input class="date" id="title" name="title"></div>
        <div>
            Ссылка - <textarea rows="5" cols="40" class="date" id="link" name="link">

        </textarea></div>

        <div>Тип -
            <select id="type" name="type">
                <option value="-1">Выберите город</option>
                <option value="0">Киев</option>
                <option value="1">Днепр</option>
            </select>
            <br>
            <input type="submit" value="Добавить">
            </form:form>
            <br class="clear">
        </div>
    </div>
</div>
