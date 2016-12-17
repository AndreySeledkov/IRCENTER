<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="wpbody-content">
    <div class="wrap">

        <h2>
            Редактирование текстовое свидетельство
        </h2>
        <form:form id="post" method="post" action="/admin/text_evidence/submit_edit_text_evidence">
            <input type="hidden" name="id" value="${textEvidence.getId()}">
            <div>Номер картинки - <input class="date" id="default_image" name="defaultImage" value="${textEvidence.getDefaultImage()}">
            </div>
            <div>Название - <input class="date" id="title" name="title" value="${textEvidence.getTitle()}"></div>
            Текст - <textarea id="area3" name="text" style="width: 300px; height: 100px;">
            ${textEvidence.getText()}
        </textarea>
            <ckeditor:replace replace="area3" basePath="/static/ckeditor/"/>
            <input type="submit" value="Изменить">
        </form:form>
    </div>
</div>
</div>
