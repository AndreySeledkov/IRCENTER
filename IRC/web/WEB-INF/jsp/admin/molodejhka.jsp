<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" SYSTEM "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="ru-RU">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Молодежка</title>
    <link rel="stylesheet" href="/static/css/admin/load-styles.css" type="text/css" media="all">
    <link rel="stylesheet" id="akismet.css-css" href="/static/css/admin/akismet.css" type="text/css" media="all">
    <link rel="stylesheet" id="colors-css" href="/static/css/admin/colors-fresh.css" type="text/css" media="all">
    <!--[if lte IE 7]>
    <link rel='stylesheet' id='ie-css' href='/static/css/admin/ie.css' type='text/css' media='all'/>
    <![endif]-->
    <link rel="stylesheet" id="nggmenu-css" href="/static/css/admin/menu.css" type="text/css" media="all">
    <script type="text/javascript" src="/static/js/admin/l10n.js"></script>
    <link type="image/x-icon" rel="shortcut icon" href="/static/img/favicon.ico">
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
<body>
<div>
<div>
<jsp:include page="header.jsp"/>

<div id="wpbody">
<jsp:include page="left_side_bar.jsp"/>
<div id="wpbody-content">

<div class="wrap">
<div id="icon-edit" class="icon32 icon32-posts-post">
    <br>
</div>
<h2>
    Молодежка
    <a href="/admin/add_molodejhka_video" class="button add-new-h2">Добавить новую</a>
</h2>
<ul class="subsubsub">
    <li class="all">
        <a href="#" class="current">
            Все
            <span class="count">(${countItems})</span>
        </a>
        |
    </li>
    <li class="publish">
        <a href="#">
            Опубликованные
            <span class="count">(?)</span>
        </a>
        |
    </li>
    <li class="draft">
        <a href="#">
            Черновик
            <span class="count">(?)</span>
        </a>
    </li>
</ul>
<p class="search-box">
    <label class="screen-reader-text" for="post-search-input">Поиск записей:</label>
    <input type="text" id="post-search-input" name="s" value="">
    <input type="submit" name="" id="search-submit" class="button" value="Поиск записей">
</p>

<div class="tablenav top">
    <div class="alignleft actions">
        <select name="action">
            <option value="-1" selected="selected">Действия</option>
            <option value="edit">Изменить</option>
            <option value="trash">Удалить</option>
        </select>
        <input type="submit" name="" id="doaction" class="button-secondary action" value="Применить">
    </div>
    <div class="alignleft actions">
        <select name="m">
            <option selected="selected" value="0">Все даты</option>
        </select>
        <select name="cat" id="cat" class="postform">
            <option value="0">Все рубрики</option>
        </select>
        <input type="submit" name="" id="post-query-submit" class="button-secondary" value="Фильтр">
    </div>
    <div class="tablenav-pages">
        <span class="displaying-num">${countItems} элементов</span>
        <a class="first-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на первую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin/molodejhka?p=0"/></c:if>>«</a>
        <a class="prev-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на предыдущую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin/molodejhka?p=${page - 1}"/></c:if>
                >‹</a>
        <span class="paging-input">
          <input class="current-page" title="Текущая страница" type="text" name="paged" value="${page + 1}" size="1">
           из
          <span class="total-pages">${countPage}</span>
        </span>
        <a class="next-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на следующую страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin/molodejhka?p=${page + 1}"/></c:if>
                >›</a>
        <a class="last-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на последнюю страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin/molodejhka?p=${countPage - 1}"/></c:if>
                >»</a>
    </div>
    <br class="clear">
</div>
<table class="wp-list-table widefat fixed posts" cellspacing="0">
    <thead>
    <tr>
        <th scope="col" id="cb" class="manage-column column-cb check-column">
            <input type="checkbox">
        </th>
        <th scope="col" id="title" class="manage-column column-title sortable desc">
            <a href="#">
                <span>Заголовок</span>
                <span class="sorting-indicator"></span>
            </a>
        </th>
        <th scope="col" id="author" class="manage-column column-author sortable desc">
            <a href="#">
                <span>Автор</span>
                <span class="sorting-indicator"></span>
            </a>
        </th>
        <th scope="col" id="categories" class="manage-column column-categories">Линка</th>
        <th scope="col" class="manage-column column-categories">Описание</th>
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
        <th scope="col" class="manage-column column-title sortable desc">
            <a href="#">
                <span>Заголовок</span>
                <span class="sorting-indicator"></span>
            </a>
        </th>
        <th scope="col" class="manage-column column-author sortable desc">
            <a href="#">
                <span>Автор</span>
                <span class="sorting-indicator"></span>
            </a>
        </th>
        <th scope="col" class="manage-column column-categories">Линка</th>
        <th scope="col" class="manage-column column-categories">Описание</th>
        <th scope="col" class="manage-column column-date sortable asc">
            <a href="#">
                <span>Дата</span>
                <span class="sorting-indicator"></span>
            </a>
        </th>
    </tr>
    </tfoot>
    <tbody id="the-list">
    <c:forEach items="${molod}" var="item" varStatus="status">
        <tr class="author-self status-publish format-default iedit" valign="top">
            <th scope="row" class="check-column">
                <input type="checkbox" name="post[]" value="396">
            </th>
            <td class="post-title page-title column-title">
                <strong>
                    <a class="row-title" href="#"
                       title="${item.getDescription()}">${item.getDescription()}</a>
                </strong>

                <div class="row-actions">
              <span class="edit">
                <a href="/admin/edit_molodejhka_v?id=${item.getId()}"
                   title="Редактировать этот элемент">Редактировать</a>
                 |
              </span>
              <span class="trash">
                  <a class="submitdelete" title="Переместить этот элемент в корзину"
                     href="/admin/remove_molodejhka_v?id=${item.getId()}" onclick="">Удалить</a>
              </span>
                </div>
            </td>
            <td class="author column-author">
                <a href="#">${item.getUserId()}</a>
            </td>
            <td class="categories column-categories">
                    ${item.getLink()}
            </td>
            <td class="categories column-categories">
                    ${item.getDescription()}
            </td>
            <td class="date column-date">
                <abbr title="${item.getDate()}">${item.getDate()}</abbr>
                <br>
                Опубликовано
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="tablenav bottom">
    <div class="alignleft actions">
        <select name="action2">
            <option value="-1" selected="selected">Действия</option>
            <option value="edit">Изменить</option>
            <option value="trash">Удалить</option>
        </select>
        <input type="submit" name="" id="doaction2" class="button-secondary action" value="Применить">
    </div>
    <div class="alignleft actions">
    </div>
    <div class="tablenav-pages">
        <span class="displaying-num">${countItems} элементов</span>
        <a class="first-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на первую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin/molodejhka?p=0"/></c:if>>«</a>
        <a class="prev-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на предыдущую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin/molodejhka?p=${page - 1}"/></c:if>
                >‹</a>
        <span class="paging-input">
          <input class="current-page" title="Текущая страница" type="text" name="paged" value="${page + 1}" size="1">
           из
          <span class="total-pages">${countPage}</span>
        </span>
        <a class="next-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на следующую страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin/molodejhka?p=${page + 1}"/></c:if>
                >›</a>
        <a class="last-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на последнюю страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin/molodejhka?p=${countPage - 1}"/></c:if>
                >»</a>
    </div>

    <br class="clear">
</div>
<div id="ajax-response"></div>
<br class="clear">
</div>
<div class="clear">
</div>
</div>
<!-- wpbody-content -->
</div>
</div>
</div>
</body>
</html>