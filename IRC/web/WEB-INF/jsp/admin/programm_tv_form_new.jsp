<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div id="wpbody-content">
    <div class="wrap">

        <form:form id="post" method="post" action="/admin/programm_tv/add_new">
            <h2>
                Новое программа тв
                    <%--<a href="/admin/tv/add_new" class="button add-new-h2">Добавить новую</a>--%>
            </h2>

            <div>Название - <input class="date" name="title" id="title"></div>
            <div>Дни - <input class="date" id="days" name="days"></div>
            <div>Дата - <input id="date" name="date" class="datetimepicker"></div>
            Канал -
            <select id="type" name="type">
                <option value="-1">Выберите канал</option>
                <option value="0">CNL</option>
                <option value="1">LIFE_TV</option>
                <option value="2">IMPACT</option>
                <option value="3">TET</option>
            </select>
            <br>
            <input type="submit" value="Добавить">
        </form:form>

    </div>
</div>
</div>
