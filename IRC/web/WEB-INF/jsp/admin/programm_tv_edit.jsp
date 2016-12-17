<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" SYSTEM "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="ru-RU">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Админ-панель</title>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/admin/load-styles.css" type="text/css" media="all">
    <link rel="stylesheet" id="akismet.css-css" href="/static/css/admin/akismet.css" type="text/css" media="all">
    <link rel="stylesheet" id="colors-css" href="/static/css/admin/colors-fresh.css" type="text/css" media="all">
    <!--[if lte IE 7]>
    <link rel='stylesheet' id='ie-css' href='/static/css/admin/ie.css' type='text/css' media='all'/>
    <![endif]-->
    <link rel="stylesheet" id="nggmenu-css" href="/static/css/admin/menu.css" type="text/css" media="all">
    <script type="text/javascript" src="/static/js/admin/l10n.js"></script>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/admin/load-scripts.js"></script>
    <script type="text/javascript" src="/static/js/admin/akismet.js"></script>
    <script type="text/javascript" src="/static/js/admin/load-scripts1.js"></script>
    <script type="text/javascript" src="/static/js/admin/update_right_side.js"></script>
    <script type="text/javascript" src="/static/protoplasm/protoplasm.js"></script>
    <link rel="stylesheet" id="ru_RU-css" href="/static/css/admin/ru_RU.css" type="text/css" media="all">
    <!--[if IE]>
    <link rel='stylesheet' id='ru_RU-ie-css' href='/static/css/admin/ru_RU-ie.css' type='text/css' media='all'/>
    <![endif]-->
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

        function sendEditProgrammTv() {
            xmlhttp = getXMLObject();
            if (xmlhttp) {
                var title = document.getElementById("title");
                var date = document.getElementById("date");
                var type = document.getElementById("type");


                xmlhttp.open("GET", "/admin/programm_tv/submit_edit_programm_tv?title=" + title.value + "&date=" + date.value + "&type=" + type.value+"&id="+${programmTv.getId()}, true);
                xmlhttp.onreadystatechange = function response() {
                    if (xmlhttp.readyState == 4) {
                        if (xmlhttp.status == 200) {
                            window.alert("Сохранено");
                        }
                    }
                };
                xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xmlhttp.send();
            }
        }

    </script>
</head>
<body>
<div id="wpwrap">
    <div id="wpcontent">

        <jsp:include page="header.jsp"/>

        <div id="wpbody">
            <jsp:include page="left_side_bar.jsp"/>

            <jsp:include page="programm_tv_edit_form.jsp"/>

            <div class="clear"></div>
        </div>
        <!-- wpbody -->
        <div class="clear"></div>
    </div>
    <!-- wpcontent -->
</div>
<!-- wpwrap -->
</body>
</html>