<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="load_avatar_form">
    <span class="load_avatar_header">Вы можете загрузить сюда только<br/>собственную фотографию.<br/>Поддерживаются форматы JPG, PNG и GIF.</span>
    <form:form id="post" class="MultiFile-intercepted" enctype="multipart/form-data" method="post" action="/avatar">

        <span>Выберите изображения</span>
        <input type="file" name="pictures" id="pictures"/>

        <button type="submit">Загрузить</button>

    </form:form>


    <%--<div class="mc"><a id="upload_photo" href="" class="select_avatar_file_button blue_button">Выберете Фотографию</a></div>--%>
</div>
<%--<script type="text/javascript">--%>
<%--$('#upload_photo').upload({--%>
<%--name: 'file',--%>
<%--method: 'post',--%>
<%--enctype: 'multipart/form-data',--%>
<%--action: '/avatar',--%>
<%--onSubmit: function() {--%>
<%--waitModal();--%>
<%--},--%>
<%--onComplete: function(data){--%>
<%--updateModal('crop_avatar.php');--%>
<%--}--%>
<%--});--%>
<%--</script>--%>