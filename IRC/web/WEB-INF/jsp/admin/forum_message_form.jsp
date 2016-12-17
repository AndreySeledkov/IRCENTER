<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wpbody-content">
    <div class="wrap">

        <h2>
            Сообщения форума
        </h2>

        <table class="wp-list-table widefat fixed users" cellspacing="0">
            <thead>
            <tr>

                <th scope="col" class="manage-column column-username sortable desc">
                    <a href="javascript:void(0)">
                        <span>ID message</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>ID theme</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>ID user</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Create date</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Text</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Checked</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Deleted</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Banned</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

            </tr>
            </thead>


            <tfoot>
            <tr>

                <th scope="col" class="manage-column column-username sortable desc">
                    <a href="javascript:void(0)">
                        <span>ID message</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>ID theme</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>ID user</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Create date</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Text</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Checked</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Deleted</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Banned</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
            </tr>
            </tfoot>


            <tbody id="the-list" class="list:user">

            <c:forEach items="${messageList}" var="message" varStatus="varStatus">
                <tr id="user-1" class="alternate">

                    <td class="username column-username">
                        <strong>
                            <a href="javascript:void(0)">${message.getId()}</a>
                        </strong>
                        <br>

                        <div class="row-actions">
				              <span class="edit">
				                <a href="/admin/forum_messages/approve/${message.getId()}">Проверил</a> | <a
                                      href="/admin/forum_messages/delete/${message.getId()}"
                                      class="submitdelete">Удалить</a>
				              </span>
                        </div>
                    </td>

                    <td>
                            ${message.getThemeId()}
                    </td>
                    <td>
                            ${message.getAuthorId()}
                    </td>
                    <td>
                            ${message.getCreateDate()}
                    </td>
                    <td>
                            ${message.getText()}
                    </td>
                    <td>
                            ${message.isChecked()}
                    </td>
                    <td>
                            ${message.isDeleted()}
                    </td>
                    <td>
                            ${message.isBanned()}
                    </td>

                </tr>
            </c:forEach>
            <c:if test="${messageUncheckedCount>10}">
                <jsp:include page="../new/pagination.jsp"/>
            </c:if>
            </tbody>
        </table>

        <br class="clear">
    </div>
</div>
</div>
