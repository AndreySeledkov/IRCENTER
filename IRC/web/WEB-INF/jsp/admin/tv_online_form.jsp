<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wpbody-content">
    <div class="wrap">

        <h2>
            ТВ онлайн
            <a href="/admin/tv_online/get_new" class="button add-new-h2">Добавить новую</a>
        </h2>

        <table class="wp-list-table widefat fixed users" cellspacing="0">
            <thead>
            <tr>

                <th scope="col" class="manage-column column-username sortable desc">
                    <a href="javascript:void(0)">
                        <span>ID</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>Date</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>


            </tr>
            </thead>


            <tfoot>
            <tr>
                <th scope="col" class="manage-column column-username sortable desc">
                    <a href="javascript:void(0)">
                        <span>ID</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>Date</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

            </tr>
            </tfoot>


            <tbody id="the-list" class="list:user">
            <c:if test="${onlineTv!=null}">
                <c:forEach var="item" items="${onlineTv}" >
                <tr id="user-1" class="alternate">

                    <td class="username column-username">
                        <strong>
                                ${item.getId()}
                        </strong>
                        <br>

                        <div class="row-actions">
				              <span class="edit">
				                <a href="/admin/tv_online/edit/${item.getId()}">Изменить</a> | <a
                                      href="/admin/tv_online/${item.getId()}"
                                      class="submitdelete">Удалить</a>
				              </span>
                        </div>
                    </td>

                    <td>
                            ${item.getDate()}
                    </td>

                </tr>
                </c:forEach>
            </c:if>


            </tbody>
        </table>

        <br class="clear">
    </div>
</div>
</div>
