<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="wpbody-content">
    <div class="wrap">

        <h2>
            Новое видео свидетельство
        </h2>
        <form:form id="post" method="post" action="/admin/video_evidence/add_new">
            <div>Название - <input class="date" id="title" name="title"></div>
            <div>Ссылка - <textarea rows="5" cols="40" class="date" id="link" name="link">

            </textarea></div>

            <input type="submit" value="Добавить">
        </form:form>
    </div>
</div>
</div>
