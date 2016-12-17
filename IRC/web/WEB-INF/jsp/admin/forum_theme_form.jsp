<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wpbody-content">
<div class="wrap">

<h2>
    Темы форума
</h2>

<table class="wp-list-table widefat fixed users" cellspacing="0">
<thead>
<tr>

    <th scope="col" class="manage-column column-username sortable desc">
        <a href="javascript:void(0)">
            <span>ID theme</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>
    <th scope="col" class="manage-column column-name sortable desc">
        <a href="javascript:void(0)">
            <span>ID part</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>
    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>Last edit</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>
    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>Number views</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>Author ID</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>themename</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>deleted</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>banned</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>messages</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>checked</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>
</tr>
</thead>


<tfoot>
<tr>

    <th scope="col" class="manage-column column-username sortable desc">
        <a href="javascript:void(0)">
            <span>ID theme</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>
    <th scope="col" class="manage-column column-name sortable desc">
        <a href="javascript:void(0)">
            <span>ID part</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>
    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>Last edit</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>Number views</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>Author ID</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>themename</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>deleted</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>banned</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>messages</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>

    <th scope="col" class="manage-column column-email sortable desc">
        <a href="javascript:void(0)">
            <span>checked</span>
            <span class="sorting-indicator"></span>
        </a>
    </th>
</tr>
</tfoot>


<tbody id="the-list" class="list:user">

<c:forEach items="${themeList}" var="theme" varStatus="varStatus">
    <tr id="user-1" class="alternate">

        <td class="username column-username">
            <strong>
                <a href="javascript:void(0)">${theme.getId()}</a>
            </strong>
            <br>

            <div class="row-actions">
				              <span class="edit">
				                <a href="/admin/forum_theme/approve/${theme.getId()}">Проверил</a> | <a
                                      href="/admin/forum_theme/delete/${theme.getId()}"
                                      class="submitdelete">Удалить</a>
				              </span>
            </div>
        </td>

        <td>
                ${theme.getPartId()}
        </td>
        <td>
                ${theme.getLastEdit()}
        </td>
        <td>
                ${theme.getNumberViews()}
        </td>
        <td>
                ${theme.getAuthorId()}
        </td>
        <td>
                ${theme.getName()}
        </td>
        <td>
                ${theme.isDeleted()}
        </td>
        <td>
                ${theme.isBanned()}
        </td>
        <td>
                ${theme.getMessagesCount()}
        </td>
        <td>
                ${theme.isChecked()}
        </td>

    </tr>
</c:forEach>
<c:if test="${themeUncheckedCount>10}">
    <jsp:include page="../new/pagination.jsp"/>
</c:if>


</tbody>
</table>

<br class="clear">
</div>
</div>
</div>
