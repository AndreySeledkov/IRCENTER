<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="wpbody-content">
    <div class="wrap">

        <h2>
            Редактировать видео свидетельство
        </h2>
        <form:form id="post" method="post" action="/admin/video_evidence/submit_edit_video_evidence">
            <input type="hidden" name="id" value="${videoEvidence.getId()}">

            <div>Название - <input class="date" id="title" name="title" value="${videoEvidence.getTitle()}"></div>
            <div>Ссылка - <textarea rows="5" cols="40" class="date" id="link" name="link">
                    ${videoEvidence.getLink()}
            </textarea></div>


            <input type="submit" value="Изменить">
        </form:form>
    </div>
</div>
</div>