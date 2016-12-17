<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="wpbody-content">
    <div class="wrap">

        <h2>
            Редактировать Свидетельство онлайн
        </h2>
        <form:form id="post" method="post" action="/admin/evidence_online/submit_edit_evidence_online">
            <input type="hidden" name="id" value="${onlineEvidence.getId()}">

            <div>Название - <input class="date" id="title" name="title" value="${onlineEvidence.getTitle()}"></div>
            Текст - <textarea id="area3" name="text" style="width: 300px; height: 100px;">
                ${onlineEvidence.getText()}
        </textarea>
            <ckeditor:replace replace="area3" basePath="/static/ckeditor/"/>

            <input type="submit" value="Добавить">
        </form:form>
    </div>
</div>
</div>
