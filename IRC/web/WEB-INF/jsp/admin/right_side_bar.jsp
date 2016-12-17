<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <script type="text/javascript" src="/static/js/admin/action.js"></script>
</head>
<div id="wpbody-content">
<div class="wrap">
<div id="icon-edit" class="icon32 icon32-posts-post">
    <br>
</div>
<h2>
    Новости
    <a href="/admin/add_news" class="button add-new-h2">Добавить новую</a>
</h2>
<ul class="subsubsub">
    <li class="all">
        <a href="#" class="current">
            Все
            <span class="count">(${countNews})</span>
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
        <span class="displaying-num">${countNews} элементов</span>
        <a class="first-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на первую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin?p=0"/></c:if>>«</a>
        <a class="prev-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на предыдущую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin?p=${page - 1}"/></c:if>
                >‹</a>
        <span class="paging-input">
          <input class="current-page" title="Текущая страница" type="text" name="paged" value="${page + 1}" size="1">
           из
          <span class="total-pages">${countPage}</span>
        </span>
        <a class="next-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на следующую страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin?p=${page + 1}"/></c:if>
                >›</a>
        <a class="last-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на последнюю страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin?p=${countPage - 1}"/></c:if>
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
        <th scope="col" id="categories" class="manage-column column-categories">Рубрики</th>
        <th scope="col" id="comments" class="manage-column column-comments num sortable desc">
            <a href="#">
              <span>
                <div class="vers">
                    <img alt="Комментарии" src="/static/img/admin/comment-grey-bubble.png">
                </div>
              </span>
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
        <th scope="col" class="manage-column column-categories">Рубрики</th>
        <th scope="col" class="manage-column column-comments num sortable desc">
            <a href="#">
              <span>
                <div class="vers">
                    <img alt="Комментарии" src="/static/img/admin/comment-grey-bubble.png">
                </div>
              </span>
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
    <c:forEach items="${news}" var="item" varStatus="status">
        <tr class="author-self status-publish format-default iedit" valign="top">
            <th scope="row" class="check-column">
                <input type="checkbox" name="post[]" value="396">
            </th>
            <td class="post-title page-title column-title">
                <strong>
                    <a class="row-title" href="#"
                       title="${item.getTitle()}">${item.getTitle()}</a>
                </strong>

                <div class="row-actions">
              <span class="edit">
                <a href="/admin/edit_news?id=${item.getId()}" title="Редактировать этот элемент">Редактировать</a>
                 |
              </span>
              <span class="trash">
                  <a class="submitdelete" title="Переместить этот элемент в корзину"
                     href="javascript:void(0)" onclick="action.removeNews(${item.getId()})">Удалить</a>
              </span>
                </div>
            </td>
            <td class="author column-author">
                <a href="#">${item.getAuthorId()}</a>
            </td>
            <td class="categories column-categories">
                <a href="#">${item.getPartId()}</a>
            </td>
            <td class="comments column-comments">
                <div class="post-com-count-wrapper">
                    <a href="#"
                       class="post-com-count">
                        <span class="comment-count">${item.getCommentsCount()}</span>
                    </a>
                </div>
            </td>
            <td class="date column-date">
                <abbr title="${item.getStartDate()}">${item.getStartDate()}</abbr>
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
        <span class="displaying-num">${countNews} элементов</span>
        <a class="first-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на первую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin?p=0"/></c:if>>«</a>
        <a class="prev-page <c:if test="${page == 0}"><c:out value="disabled"/></c:if>"
           title="Перейти на предыдущую страницу"
                <c:if test="${page > 0}"><c:out value="href=/admin?p=${page - 1}"/></c:if>
                >‹</a>
        <span class="paging-input">
          <input class="current-page" title="Текущая страница" type="text" name="paged" value="${page + 1}" size="1">
           из
          <span class="total-pages">${countPage}</span>
        </span>
        <a class="next-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на следующую страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin?p=${page + 1}"/></c:if>
                >›</a>
        <a class="last-page <c:if test="${countPage - 1 <= page}"><c:out value="disabled"/></c:if>"
           title="Перейти на последнюю страницу"
                <c:if test="${countPage - 1 > page}"><c:out value="href=/admin?p=${countPage - 1}"/></c:if>
                >»</a>
    </div>

    <br class="clear">
</div>
<div id="ajax-response"></div>
<br class="clear">
</div>
<div class="clear"></div>
</div>