<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script src="/static/js/profile_edit.js" type="text/javascript"></script>
</head>
<body>
<div id="profile_right_column">
<div class="profile_right_column_header_elements">
    <div class="list_element_center">
        <ul id="pedit_filters">
            <li>
                <c:choose>
                    <c:when test="${userMainInfo != null}">
                        <a href="/edit" class="select" onclick="return ProfilerEditor.go(this, event)">Основные</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/edit" onclick="return ProfilerEditor.go(this, event)">Основные</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${userContacts != null}">
                        <a href="/edit?act=contacts" class="select"
                           onclick="return ProfilerEditor.go(this, event)">Контакты</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/edit?act=contacts" onclick="return ProfilerEditor.go(this, event)">Контакты</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${userInterests != null}">
                        <a href="/edit?act=interests" class="select"
                           onclick="return ProfilerEditor.go(this, event)">Интересы</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/edit?act=interests" onclick="return ProfilerEditor.go(this, event)">Интересы</a>
                    </c:otherwise>
                </c:choose>
            </li>

            <li><a href="#">Образование</a></li>
            <li><a href="#">Карьера</a></li>
            <li><a href="#">Военная служба</a></li>
            <li><a href="#">Места</a></li>
            <li><a href="#">Убеждения</a></li>
        </ul>
    </div>
</div>
<div class="profile_right_column_content">
<c:choose>
<c:when test="${userMainInfo != null}">
    <form:form action="" modelAttribute="userMainInfo" method="post">
        <div class="edit_block">
            <script type="text/javascript">
                function defineDaysCount() {
                    var d = document.getElementById('db_day');
                    var m = document.getElementById('db_month');
                    var y = document.getElementById('db_year');
                    m = m.options[m.selectedIndex];
                    y = y.options[y.selectedIndex];
                    var day_count;
                    if (m.value == "na") {
                        day_count = 31;
                    } else if (m.value == 2) {
                        if (y.value == 'na') {
                            day_count = 29;
                        } else {
                            var yr = new Date(y.value, 1, 29).getDate() == 29;
                            if (yr) {
                                day_count = 29;
                            } else {
                                day_count = 28;
                            }
                        }

                    } else if (m.value == 4 || m.value == 6 || m.value == 9 || m.value == 11) {
                        day_count = 30;
                    } else {
                        day_count = 31;
                    }
                    if (d.selectedIndex > day_count + 1) {
                        d.selectedIndex = 0;
                    }
                    if (d.length - 1 > day_count) {
                        while (d.length - 1 > day_count) {
                            d.remove(d.length - 1);
                        }
                    } else {
                        for (var i = d.length; i < day_count + 1; i++) {
                            var opt = document.createElement('option');
                            opt.text = i;
                            opt.value = i;
                            d.appendChild(opt);
                        }
                    }
                }

                function fillYears() {
                    var el = document.getElementById('db_year');
                    for (var i = 1920; i < 2005; i++) {
                        var opt = document.createElement('option');
                        opt.text = i;
                        opt.value = i;
                        el.appendChild(opt);
                    }
                }

                function getRegionList(select) {
                    var country = select.options[select.selectedIndex].value;
                    if (country == 0) {
                        $("#region_div_id").css('display', 'none');
                        $("#city_div_id").css('display', 'none');
                    } else {
                        $.getJSON('/location/getregions', { countryId:country }, function (regions) {
                            $("#region_div_id").css('display', 'block');
                            $("#city_div_id").css('display', 'none');
                            $("#region_row").empty();
                            var options = '<option value="0">Не выбрана</option>';
                            for (var i = 0; i < regions.length; i++) {
                                options += '<option value="' + regions[i].regionId + '">' + regions[i].regionName + '</option>';
                            }
                            $("#region_row").html(options);
                        });
                    }
                }

                function getCityList(select) {
                    var region = select.options[select.selectedIndex].value;
                    if (region == 0) {
                        $("#city_div_id").css('display', 'none');
                    } else {
                        $.getJSON('/location/getcities', {regionId:region}, function (cities) {
                            $("#city_row").empty();
                            $("#city_div_id").css('display', 'block');
                            var options = '<option value="0">Не выбран</option>';
                            for (var i = 0; i < cities.length; i++) {
                                options += '<option value="' + cities[i].cityId + '">' + cities[i].cityName + '</option>';
                            }
                            $("#city_row").html(options);
                        });
                    }
                }
            </script>
            <label class="option_name">Имя:</label>
            <form:input path="name" value="" cssClass="option_value"/>
            <br>
            <label class="option_name">Фамилия:</label>
            <form:input path="surname" value="" cssClass="option_value"/>
            <br>
            <label class="option_name">Пол:</label>
            <form:select path="genderId" cssClass="option_value">
                <form:options items="${genders}" itemValue="genderId" itemLabel="genderName"/>
            </form:select>
            <br>
            <label class="option_name">День рождения:</label>
            <form:select path="dayDb" name="db_day" id="db_day" cssClass="option_value day">
                <form:option value="0" label="День"/>
                <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
                    <form:option value="${i}" label="${i}"/>
                </c:forEach>
            </form:select>
            <form:select path="monthDb" id="db_month" onchange="defineDaysCount()" cssClass="option_value month">
                <form:option value="0" label="Месяц"/>
                <c:forEach var="month" items="${months}" varStatus="status">
                    <c:if test="${status.index < 12}">
                        <form:option value="${status.index + 1}" label="${month}"/>
                    </c:if>
                </c:forEach>
            </form:select>
            <form:select path="yearDb" id="db_year" onchange="defineDaysCount()" cssClass="option_value year">
                <form:option value="0" label="Год"/>
                <c:forEach var="i" begin="0" end="80" step="1" varStatus="status">
                    <form:option value="${2000- i}" label="${2000-i}"/>
                </c:forEach>
            </form:select>

            <div id="country_row">
                <label class="option_name">Страна:</label>
                <form:select path="countryId" onchange="getRegionList(this);" cssClass="option_value">
                    <form:option value="0" label="Не выбрана"/>
                    <form:options items="${countries}" itemValue="countryId" itemLabel="countryName"/>
                </form:select>
            </div>
            <div id="region_div_id"
                    <c:choose>
                        <c:when test="${userMainInfo.getCountryId() == 0}">
                            <c:out value="style=display:none;"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="style=display:block;"/>
                        </c:otherwise>
                    </c:choose>
                    >
                <label class="option_name">Область:</label>
                <form:select path="regionId" onchange="getCityList(this);" class="region_row" id="region_row"
                             cssClass="option_value">
                    <form:option value="0" label="Не выбрана"/>
                    <form:options items="${regions}" itemValue="regionId" itemLabel="regionName"/>
                </form:select>
            </div>
            <div id="city_div_id"
                    <c:choose>
                        <c:when test="${userMainInfo.getCityId() == 0}">
                            <c:out value="style=display:none;"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="style=display:block;"/>
                        </c:otherwise>
                    </c:choose>
                    >
                <label class="option_name">Город:</label>
                <form:select path="cityId" id="city_row" cssClass="option_value">
                    <form:option value="0" label="Не выбран"/>
                    <form:options items="${cities}" itemValue="cityId" itemLabel="cityName"/>
                </form:select>
            </div>
        </div>
        <div class="button_bg">
            <button class="button_save_form" name="saveMainInfo" onclick="return ProfilerEditor.saveGeneral(this);">
                Сохранить
            </button>
        </div>
    </form:form>
</c:when>
<c:when test="${userContacts != null}">
    <form:form action="" modelAttribute="userContacts" method="post">
        <div class="edit_block">
            <form:hidden path="id"></form:hidden>
            <form:hidden path="userId"></form:hidden>
            <label class="option_name" for="cell_phone">Мобильный телефон:</label>
            <form:input path="cellPhone" id="cell_phone" class="option_value" value=""/>
            <br>
            <label class="option_name" for="home_phone">Домашний телефон:</label>
            <form:input path="homePhone" id="home_phone" class="option_value" value=""/>
            <br>
            <label class="option_name" for="skype">Skype:</label>
            <form:input path="skype" id="skype" class="option_value" value=""/>
            <br>
            <label class="option_name" for="own_site">Личный сайт:</label>
            <form:input path="ownSite" id="own_site" class="option_value" value=""/>
            <br>
        </div>
        <div class="button_bg">
            <button class="button_save_form" name="saveUserContacts"
                    onclick="return ProfilerEditor.saveContacts(this);">Сохранить
            </button>
        </div>
    </form:form>
</c:when>
<c:when test="${userInterests != null}">
    <form:form action="" modelAttribute="userInterests" method="post">
        <div class="edit_block">
            <label class="option_name" for="activities">Деятельность:</label>
            <form:textarea path="activities" id="activities" class="option_value" value=""/>
            <br>
            <label class="option_name" for="interests">Интересы:</label>
            <form:textarea path="interests" id="interests" class="option_value" value=""/>
            <br>
            <label class="option_name" for="books">Любимые книги:</label>
            <form:textarea path="books" id="books" class="option_value" value=""/>
            <br>
            <label class="option_name" for="about">О себе:</label>
            <form:textarea path="about" id="about" class="option_value" value=""/>
            <br>
        </div>
        <div class="button_bg">
            <button class="button_save_form" name="saveUserInterests"
                    onclick="return ProfilerEditor.saveInterests(this)">Сохранить
            </button>
        </div>
    </form:form>
</c:when>
</c:choose>
</div>
</div>
</body>
</html>