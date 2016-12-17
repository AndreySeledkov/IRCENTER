<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="ru-RU">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Редактирование</title>
    <link rel='stylesheet' href='/static/css/admin/calendar.css' type='text/css'>
    <link rel='stylesheet' href='/static/css/admin/load-styles.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='akismet.css-css' href='/static/css/admin/akismet.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='colors-css' href='/static/css/admin/colors-fresh.css' type='text/css' media='all'/>
    <script type="text/javascript" src="/static/js/admin/action.js"></script>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
</head>
<body class="wp-admin no-js post-new-php">
<div id="wpwrap">
    <div id="wpcontent">
        <div id="wphead">
            <h1 id="site-heading" class="long-title">
                <a href="http://ircenter.eu/" title="Перейти на сайт">
                    <span id="site-title">IrCenter.eu</span>
                </a>
            </h1>

            <div id="wphead-info">
                <div id="user_info">
                    <p>
                        Привет, <a href="#" title="Изменить профиль">admin</a> | <a href="" title="Выйти">Выйти</a>
                    </p>
                </div>
            </div>
        </div>
        <div id="wpbody">
            <jsp:include page="left_side_bar.jsp"/>
            <div id="wpbody-content">
                <h2>
                    Редактирование
                    <%--<a href="/admin/tv/add_new" class="button add-new-h2">Добавить новую</a>--%>
                </h2>

                <form:form action="/admin/submit_edit_molodejhka_v">
                <div>Ссылка - <textarea id="link" name="link">${item.getLink()}</textarea>

                    <div>Заголовок - <input id="description" name="description" value="${item.getDescription()}"></div>
                    <div>Описание - <input id="text" name="text" value="${item.getText()}"></div>
                    <input id="id" name="id" type="hidden" value="${item.getId()}">
                    <input type="submit" value="Изменить">
                    </form:form>
                    <div class="clear">
                    </div>
                </div>
                <!-- wpbody-content -->
                <div class="clear">
                </div>
            </div>
            <!-- wpbody -->
            <div class="clear">
            </div>
        </div>
        <!-- wpcontent -->
    </div>
    <!-- wpwrap -->
</body>
</html>
