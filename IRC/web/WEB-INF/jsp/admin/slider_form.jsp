<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrap">
    <div id="icon-edit" class="icon32 icon32-posts-post">
        <br/>
    </div>
    <h2>Cлайдер</h2>

    <div id="post-body-content">
        Добавить новый слайд <input class="slider" id="slider"></div>
    <input type="button" value="Добавить" onclick="sendSlider()">

    <%--<form method="POST">--%>
    <%--Добавить новый слайд - <input type="file"> <input type="submit" value="Добавить">--%>
    <%--</form>--%>

    <div>
        <c:forEach items="${sliders}" var="slider" varStatus="i">
            <div class="index_slides_element">
                <img src="/image/getimage/6/${slider.getImgId()}" alt=""/>
                <a href="javascript:void(0);" onclick="removeSlider(${slider.getId()})">X</a>
            </div>
            <br>
        </c:forEach>
    </div>
</div>
<div>
