<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="wpbody-content">
    <div class="wrap">

        <h2>
            Редактировать Свидетельство исцеления
        </h2>

        <form:form id="post" method="post" action="/admin/evidence_healing//submit_edit_evidence_healing">
            <input type="hidden" name="id" value="${evidenceHealing.getId()}">

            <div>Номер картинки - <input class="date" id="default_image" name="defaultImage"
                                         value="${evidenceHealing.getDefaultImage()}">
            </div>
            Текст - <textarea id="area3" name="text" style="width: 300px; height: 100px;">
                ${evidenceHealing.getText()}
        </textarea>
            <ckeditor:replace replace="area3" basePath="/static/ckeditor/"/>

            <input type="submit" value="Добавить">
        </form:form>
    </div>
</div>
</div>
