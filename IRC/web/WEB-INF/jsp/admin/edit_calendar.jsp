<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Календарь</title>
    <link rel='stylesheet' href='/static/css/admin/calendar.css' type='text/css'>
    <link rel="stylesheet" href="/static/css/admin/load-styles.css" type="text/css" media="all">
    <link rel="stylesheet" id="akismet.css-css" href="/static/css/admin/akismet.css" type="text/css" media="all">
    <link rel="stylesheet" id="colors-css" href="/static/css/admin/colors-fresh.css" type="text/css" media="all">
    <!--[if lte IE 7]>
    <link rel='stylesheet' id='ie-css' href='/static/css/admin/ie.css' type='text/css' media='all'/>
    <![endif]-->
    <link rel="stylesheet" id="nggmenu-css" href="/static/css/admin/menu.css" type="text/css" media="all">
    <script type='text/javascript' src='/static/js/admin/calendar.js'></script>
    <script type="text/javascript" src="/static/js/admin/l10n.js"></script>
    <script type="text/javascript" src="/static/js/admin/load-scripts.js"></script>
    <script type="text/javascript" src="/static/js/admin/akismet.js"></script>
    <script type="text/javascript" src="/static/js/admin/load-scripts1.js"></script>
    <script type="text/javascript" src="/static/js/admin/update_right_side.js"></script>
    <script type="text/javascript" src="/static/protoplasm/protoplasm.js"></script>

    <script type="text/javascript">
                Protoplasm.use('datepicker')
                        .transform('.datetimepicker', { locale:'ru_RU', dateTimeFormat:'yyyy-MM-dd HH:mm', timePicker:true, use24hrs:true });

        function getXMLObject() {
            var xmlHttp = false;
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
            }
            catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
                }
                catch (e2) {
                    xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
                }
            }
            if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
                xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
            }
            return xmlHttp;  // Mandatory Statement returning the ajax object created
        }

        function getRegionList(select) {
            var country = select.options[select.selectedIndex].value;
            if (country == 0) {
                var el = document.getElementById('region');
                el.setAttribute('style', 'display:none');
                el = document.getElementById('city');
                el.setAttribute('style', 'display:none');
            } else {
                xmlhttp = getXMLObject();
                if (xmlhttp) {
                    xmlhttp.open("GET", "/location/getregions?countryId=" + country, true);
                    xmlhttp.onreadystatechange = function response() {
                        if (xmlhttp.readyState == 4) {
                            if (xmlhttp.status == 200) {
                                var el = document.getElementById('city');
                                el.setAttribute('style', 'display:none');
                                el = document.getElementById('region');
                                el.setAttribute('style', 'display:inline');
                                var regions = eval(xmlhttp.responseText);
                                el = document.getElementById('region_row');
                                el.innerHTML = '';
                                var options = '<option value="0">Не выбрана</option>';
                                for (var i = 0; i < regions.length; i++) {
                                    options += '<option value="' + regions[i].regionId + '">' + regions[i].regionName + '</option>';
                                }
                                el.innerHTML = options;
                            }
                        }
                    };
                    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xmlhttp.send();
                }
            }
        }

        function getCityList(select) {
            var region = select.options[select.selectedIndex].value;
            if (region == 0) {
                var el = document.getElementById('city');
                el.setAttribute('style', 'display:none');
            } else {
                xmlhttp = getXMLObject();
                if (xmlhttp) {
                    xmlhttp.open("GET", "/location/getcities?regionId=" + region, true);
                    xmlhttp.onreadystatechange = function response() {
                        if (xmlhttp.readyState == 4) {
                            if (xmlhttp.status == 200) {
                                var el = document.getElementById('city');
                                el.setAttribute('style', 'display:inline');
                                var cities = eval(xmlhttp.responseText);
                                el = document.getElementById('city_row');
                                el.innerHTML = '';
                                var options = '<option value="0">Не выбран</option>';
                                for (var i = 0; i < cities.length; i++) {
                                    options += '<option value="' + cities[i].cityId + '">' + cities[i].cityName + '</option>';
                                }
                                el.innerHTML = options;
                            }
                        }
                    };
                    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xmlhttp.send();
                }
            }
        }
    </script>
    <!--[if IE]>
    <link rel='stylesheet' id='ru_RU-ie-css' href='/static/css/admin/ru_RU-ie.css' type='text/css' media='all'/>
    <![endif]-->

    <link rel="stylesheet" id="ru_RU-css" href="/static/css/admin/ru_RU.css" type="text/css" media="all">

</head>
<body>
<div>
    <div>
        <jsp:include page="header.jsp"/>
        <div id="wpbody">
            <div id="wpbody-content">
                <jsp:include page="left_side_bar.jsp"/>
                <div class="wrap">
                    <div class="main_content_header_small">Редактирование события в календаре</div>
                    <br/><br/>

                    <form:form id="post" method="post" action="/admin/save_calendar?calendarId=${calendar.getEventId()}">
                        Введите время начала события - <input type="text" name="datestart" class="datetimepicker"

                                                              id="time_start" value="${calendar.getStartEvent()}"/>
                        <br/>
                        Введите время окончания события - <input type="text" name="datefinish" class="datetimepicker"

                                                                 id="time_end" value="${calendar.getEndEvent()}"/>
                        <br/>
                        Введите место проведения - <input type="text" name="place" value="${calendar.getPlace()}"><br/>
                        Введите название - <input type="text" name="title" value="${calendar.getEventName()}"><br/>
                        Выберите страну - <select onchange="getRegionList(this);" name="countryId">
                        <option value="0">Не выбрана</option>
                        <c:forEach items="${countries}" var="country">
                            <c:choose>
                                <c:when test="${country.getCountryId() == calendar.getCountryId()}">
                                    <option value="${country.getCountryId()}"
                                            selected>${country.getCountryName()}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${country.getCountryId()}">${country.getCountryName()}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                        <br/>

                        <div id="region">
                            Выберите регион - <select onchange="getCityList(this);" class="region_row" id="region_row"
                                                      name="regionId">
                            <option value="0">Не выбрана</option>
                            <c:forEach items="${regions}" var="region">
                                <c:choose>
                                    <c:when test="${region.getRegionId() == calendar.getRegionId()}">
                                        <option value="${region.getRegionId()}"
                                                selected>${region.getRegionName()}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${region.getRegionId()}">${region.getRegionName()}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        </div>
                        <div id="city">
                            <br/>
                            Выберите город - <select class="city_row" id="city_row" name="cityId">
                            <option value="0">Не выбрана</option>
                            <c:forEach items="${cities}" var="city">
                                <c:choose>
                                    <c:when test="${city.getCityId() == calendar.getCityId()}">
                                        <option value="${city.getCityId()}" selected>${city.getCityName()}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${city.getCityId()}">${city.getCityName()}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        </div>
                        <br/>
                        Загрузите изображение - <input type="text" name="pictures" id="pictures"
                                                       value="${calendar.getDefImageId()}"/><br/>
                        <input type="submit" value="Добавить">
                    </form:form>
                </div>
                <div class="clear">
                </div>
            </div>
        </div>
    </div>
</div>
<!-- wpbody-content -->
</body>
</html>