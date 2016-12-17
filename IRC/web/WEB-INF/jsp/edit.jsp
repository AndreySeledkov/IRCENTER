<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content="Devoler"/>
    <meta name="copyright" content="Devoler"/>
    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.5.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/js/profile_edit.js"/>"></script>
    <script type="text/javascript">
        (function (d, w, c) {
            (w[c] = w[c] || []).push(function() {
                try {
                    w.yaCounter15559465 = new Ya.Metrika({id:15559465, enableAll: true, webvisor:true});
                } catch(e) {}
            });

            var n = d.getElementsByTagName("script")[0],
                    s = d.createElement("script"),
                    f = function () { n.parentNode.insertBefore(s, n); };
            s.type = "text/javascript";
            s.async = true;
            s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";

            if (w.opera == "[object Opera]") {
                d.addEventListener("DOMContentLoaded", f);
            } else { f(); }
        })(document, window, "yandex_metrika_callbacks");
    </script>
</head>
<body>
<a href='<c:url value="?act=main"/>'>
    Основное
</a>
<br>
<a href='<c:url value="?act=contacts"/>'>
    Контакты
</a>
<br>
<a href='<c:url value="?act=interests"/>'>
    Интересы
</a>
<br>

<c:choose>
    <c:when test="${userMainInfo != null}">
        <form:form action="" modelAttribute="userMainInfo" method="post">
            <div id="country_row">
                <label>Страна:</label>
                <form:select path="countryId" onchange="getRegionList(this);">
                    <form:option value="0" label="Не выбрана" />
                    <form:options items="${countries}" itemValue="countryId" itemLabel="countryName" />
                </form:select>
            </div>
            <div id="region_row"
                    <c:choose>
                        <c:when test="${userMainInfo.countryId == 0}">
                            <c:out value="style=display:none;"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="style=display:block;"/>
                        </c:otherwise>
                    </c:choose>
                    >
                <label>Область:</label>
                <form:select path="regionId" onchange="getCityList(this);" class="region_row" id="region_row">
                    <form:option value="0" label="Не выбрана" />
                    <form:options items="${regions}" itemValue="regionId" itemLabel="regionName" />
                </form:select>
            </div>

            <div id="city_row" <c:choose>
                <c:when test="${userMainInfo.cityId == 0}">
                    <c:out value="style=display:none;"/>
                </c:when>
                <c:otherwise>
                    <c:out value="style=display:block;"/>
                </c:otherwise>
            </c:choose>
                    >

                <label>Город:</label>
                <form:select path="cityId" class="city_row">
                    <form:option value="0" label="Не выбран" />
                    <form:options items="${cities}" itemValue="cityId" itemLabel="cityName" />
                </form:select>
            </div>
            <label>Имя:</label>
            <form:input path="name" value=""/>
            <br>
            <label>Фамилия:</label>
            <form:input path="surname" value=""/>
            <br>
            <label>Пол:</label>
            <form:select path="genderId" >
                <form:options items="${genders}" itemValue="genderId" itemLabel="genderName" />
            </form:select>
            <br>
            <label>День рождения:</label>
            <form:select path="dayDb" name="db_day" id="db_day">
                <form:option value="0" label="День" />
                <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
                    <form:option value="${i}" label="${i}" />
                </c:forEach>
            </form:select>
            <form:select path="monthDb" id="db_month" onchange="changeDate();">
                <form:option value="0" label="Месяц" />
                <c:forEach var="month" items="${months}" varStatus="status">
                    <form:option value="${status.index + 1}" label="${month}" />
                </c:forEach>
            </form:select>
            <form:select path="yearDb" id="db_year">
                <form:option value="0" label="Год" />
                <c:forEach var="i" begin="1940" end="2000" step="1" varStatus="status">
                    <form:option value="${i}" label="${i}" />
                </c:forEach>
            </form:select>
            <button type="submit" name="saveMainInfo">Сохранить</button>
        </form:form>
    </c:when>
    <c:when test="${userContacts != null}">
        <form:form action="" modelAttribute="userContacts" method="post">
            <form:hidden path="id"></form:hidden>
            <form:hidden path="userId"></form:hidden>
            <label for="cell_phone">Мобильный телефон:</label>
            <form:input path="cellPhone" id="cell_phone" class="input_txt" value=""/>
            <br>
            <label for="home_phone">Домашний телефон:</label>
            <form:input path="homePhone" id="home_phone" class="input_txt" value=""/>
            <br>
            <label for="_skype">Skype:</label>
            <form:input path="skype" id="_skype" class="input_txt" value=""/>
            <br>
            <label for="own_phone">Личный сайт:</label>
            <form:input path="ownSite" id="own_phone" class="input_txt" value=""/>
            <br>
            <button type="submit" name="saveUserContacts">Сохранить</button>
        </form:form>
    </c:when>
    <c:when test="${userInterests != null}">
        <form:form action="" modelAttribute="userInterests" method="post">
            <label for="_activities">Деятельность:</label>
            <form:textarea path="activities" id="_activities" class="input_txt" value=""/>
            <br>
            <label for="_interests">Интересы:</label>
            <form:textarea path="interests" id="_interests" class="input_txt" value=""/>
            <br>
            <label for="_books">Любимые книги:</label>
            <form:textarea path="books" id="_books" class="input_txt" value=""/>
            <br>
            <label for="_about">О себе:</label>
            <form:textarea path="about" id="_about" class="input_txt" value=""/>
            <br>
            <button type="submit" name="saveUserInterests">Сохранить</button>
        </form:form>
    </c:when>
</c:choose>
</body>
</html>