<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="wpbody-content">
    <div class="wrap">

        <%--<h2>--%>
        <%--Текстовые свидетельства--%>
        <%--<a href="/admin/text_evidence/get_new" class="button add-new-h2">Добавить новую</a>--%>
        <%--</h2>--%>
        <h2>
            Новое текстовое свидетельство
            <%--<a href="/admin/tv/add_new" class="button add-new-h2">Добавить новую</a>--%>
        </h2>
        <form:form id="post" method="post" action="/admin/text_evidence/add_new">
            <div>Номер картинки - <input class="date" id="defaultImage" name="defaultImage"></div>
            <div>Название - <input class="date" id="title" name="title"></div>
            Текст - <textarea id="area3" name="text" style="width: 300px; height: 100px;">
                ${news.getInfo()}
        </textarea>
            <ckeditor:replace replace="area3" basePath="/static/ckeditor/"/>
            <input type="submit" value="Добавить">
        </form:form>
    </div>
</div>
</div>
