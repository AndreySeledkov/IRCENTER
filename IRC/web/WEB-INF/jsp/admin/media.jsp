<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="ru-RU">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Медиафайлы</title>
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
    <link rel="stylesheet" id="ru_RU-css" href="/static/css/admin/ru_RU.css" type="text/css" media="all">
    <!--[if IE]>
    <link rel='stylesheet' id='ru_RU-ie-css' href='/static/css/admin/ru_RU-ie.css' type='text/css' media='all'/>
    <![endif]-->
</head>
<body class="wp-admin no-js post-new-php">
<div id="wpwrap">
<div id="wpcontent">
<jsp:include page="header.jsp"/>
<div id="wpbody">
<jsp:include page="left_side_bar.jsp"/>
<div id="wpbody-content">
<div class="wrap">
<div id="icon-upload" class="icon32">
    <br>
</div>
<h2>
    Библиотека файлов
    <a href="/admin/media_add" class="button add-new-h2">Добавить новый</a>
</h2>
<ul class="subsubsub">
    <li class="all">
        <a href="#" class="current">
            Все
            <span class="count">(17)</span>
        </a>
        |
    </li>
    <li class="image">
        <a href="#">
            Изображения
            <span class="count">(17)</span>
        </a>
        |
    </li>
    <li class="detached">
        <a href="#">
            Неприкреплённые
            <span class="count">(4)</span>
        </a>
    </li>
</ul>
<p class="search-box">
    <label class="screen-reader-text" for="media-search-input">Поиск медиафайлов:</label>
    <input type="text" id="media-search-input" name="s" value="">
    <input type="submit" name="" id="search-submit" class="button" value="Поиск медиафайлов">
</p>

<div class="tablenav top">
    <div class="alignleft actions">
        <select name="action">
            <option value="-1" selected="selected">Действия</option>
            <option value="delete">Удалить навсегда</option>
        </select>
        <input type="submit" name="" id="doaction" class="button-secondary action" value="Применить">
    </div>
    <div class="alignleft actions">
        <select name="m">
            <option selected="selected" value="0">Все даты</option>
        </select>
        <input type="submit" name="" id="post-query-submit" class="button-secondary" value="Фильтр">
    </div>
    <div class="tablenav-pages">
        <span class="displaying-num">${countElements} элементов</span>
        <a class="first-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на первую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin/media?p=0"/></c:if>>«</a>
        <a class="prev-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на предыдущую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin/media?p=${page - 1}"/></c:if>
                >‹</a>
        <span class="paging-input">
          <input class="current-page" title="Текущая страница" type="text" name="paged" value="${page + 1}" size="1">
           из
          <span class="total-pages">${countPage}</span>
        </span>
        <a class="next-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на следующую страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin/media?p=${page + 1}"/></c:if>
                >›</a>
        <a class="last-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на последнюю страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin/media?p=${countPage - 1}"/></c:if>
                >»</a>
    </div>
    <br class="clear">
</div>
<table class="wp-list-table widefat fixed media" cellspacing="0">
    <thead>
    <tr>
        <th scope="col" id="cb" class="manage-column column-cb check-column">
            <input type="checkbox">
        </th>
        <th scope="col" id="icon" class="manage-column column-icon"></th>
        <th scope="col" id="title" class="manage-column column-title sortable desc">
            <a href="#">
                <span>Файл</span>
                <span class="sorting-indicator"></span>
            </a>
        </th>
        <th scope="col" id="parent" class="manage-column column-parent sortable desc">
            <a href="#">
                <span>№ картинки</span>
            </a>
        </th>
        <th scope="col" id="author" class="manage-column column-author sortable desc">
            <a href="#">
                <span>Автор</span>
                <span class="sorting-indicator"></span>
            </a>
        </th>

        <th scope="col" id="date" class="manage-column column-date sortable asc">
            <a href="#">
                <span>Дата</span>
                <span class="sorting-indicator"></span>
            </a>
        </th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th scope="col" class="manage-column column-cb check-column">
            <input type="checkbox">
        </th>
        <th scope="col" class="manage-column column-icon"></th>
        <th scope="col" class="manage-column column-title sortable desc">
            <a href="#">
                <span>Файл</span>
                <span class="sorting-indicator"></span>
            </a>
        </th>
        <th scope="col" id="parent" class="manage-column column-parent sortable desc">
            <a href="#">
                <span>№ картинки</span>
            </a>
        </th>
        <th scope="col" class="manage-column column-author sortable desc">
            <a href="#">
                <span>Автор</span>
                <span class="sorting-indicator"></span>
            </a>
        </th>

        <th scope="col" class="manage-column column-date sortable asc">
            <a href="#">
                <span>Дата</span>
                <span class="sorting-indicator"></span>
            </a>
        </th>
    </tr>
    </tfoot>
    <tbody id="the-list">
    <c:forEach items="${files}" var="file">
        <tr class="author-self status-inherit" valign="top">
            <th scope="row" class="check-column">
                <input type="checkbox" name="media[]" value="360">
            </th>
            <td class="column-icon media-icon">
                <a href="/image/getimage/1/${file.getImageId()}">
                    <img src="/image/getimage/1/${file.getImageId()}"/>
                </a>
            </td>
            <td class="title column-title">
                <strong>
                    <a href="#"
                       title="${file.getTitle()}">${file.getTitle()}</a>
                </strong>

                <div class="row-actions">
              <span class="delete">

                <a class="submitdelete" href="/image/media_remove?imageId=${file.getImageId()}">Удалить</a>
                 |
              </span>
              <span class="view">
                <a href="/image/media_show?imageId=${file.getImageId()}"
                   title="" rel="permalink">Перейти</a>
              </span>
                </div>
            </td>
            <td class="parent column-parent">
                <strong>
                    № ${file.getImageId()}
                </strong>
            </td>
            <td class="author column-author">${file.getUserId()}</td>
            <td class="date column-date">${file.getDate()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="tablenav bottom">
    <div class="alignleft actions">
        <select name="action2">
            <option value="-1" selected="selected">Действия</option>
            <option value="delete">Удалить навсегда</option>
        </select>
        <input type="submit" name="" id="doaction2" class="button-secondary action" value="Применить">
    </div>
    <div class="alignleft actions">
    </div>
    <div class="tablenav-pages">
        <span class="displaying-num">${countElements} элементов</span>
        <a class="first-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на первую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin/media?p=0"/></c:if>>«</a>
        <a class="prev-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на предыдущую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin/media?p=${page - 1}"/></c:if>
                >‹</a>
        <span class="paging-input">
          <input class="current-page" title="Текущая страница" type="text" name="paged" value="${page + 1}" size="1">
           из
          <span class="total-pages">${countPage}</span>
        </span>
        <a class="next-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на следующую страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin/media?p=${page + 1}"/></c:if>
                >›</a>
        <a class="last-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на последнюю страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin/media?p=${countPage - 1}"/></c:if>
                >»</a>
    </div>
    <br class="clear">
</div>
<div id="ajax-response"></div>
<div id="find-posts" class="find-box" style="display:none;">
    <div id="find-posts-head" class="find-box-head">Найти записи или страницы</div>
    <div class="find-box-inside">
        <div class="find-box-search">
            <input type="hidden" name="affected" id="affected" value="">
            <input type="hidden" id="_ajax_nonce" name="_ajax_nonce" value="42a7d2200c">
            <label class="screen-reader-text" for="find-posts-input">Поиск</label>
            <input type="text" id="find-posts-input" name="ps" value="">
            <input type="button" id="find-posts-search" value="Поиск" class="button">
            <br>
            <input type="radio" name="find-posts-what" id="find-posts-post" value="post" checked="checked">
            <label for="find-posts-post">Записи</label>
            <input type="radio" name="find-posts-what" id="find-posts-page" value="page">
            <label for="find-posts-page">Страницы</label>
        </div>
        <div id="find-posts-response"></div>
    </div>
    <div class="find-box-buttons">
        <input id="find-posts-close" type="button" class="button alignleft" value="Закрыть">
        <input type="submit" name="find-posts-submit" id="find-posts-submit" class="button-primary alignright"
               value="Выбрать">
    </div>
</div>
<br class="clear">
</div>
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

