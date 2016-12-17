<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Статистика</title>
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
                .transform('.datetimepicker', { locale:'ru_RU',dateTimeFormat: 'yyyy-MM-dd HH:mm', timePicker:true, use24hrs:true });
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
Онлайн = ${loggedCount}
<br>
Кол-во зарег-х пол-ей = ${usersCount}
<br>
Кол-во зарег-черз VK = ${vkUsers}
<br>
Кол-во зарег-черз Fb = ${fBUsers}
<br>
</body>
</html>