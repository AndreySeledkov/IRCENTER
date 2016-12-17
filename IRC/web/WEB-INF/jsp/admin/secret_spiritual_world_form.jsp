<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="wpbody-content">
    <div class="wrap">

        <h2>
            Тайны духовного мира
            <a href="/admin/secret_spiritual_world/get_new " class="button add-new-h2">Добавить новую</a>
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
                        <span>Title</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>Youtube id</span>
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
                        <span>Link</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Count views</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Create date</span>
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
                        <span>Title</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>Youtube id</span>
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
                        <span>Link</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Count views</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Create date</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
            </tr>
            </tfoot>


            <tbody id="the-list" class="list:user">

            <c:forEach items="${secretSpiritualWorldList}" var="secretSpiritualWorld" varStatus="varStatus">
                <tr id="user-1" class="alternate">

                    <td class="username column-username">
                        <strong>
                            <a href="javascript:void(0)">${secretSpiritualWorld.getId()}</a>
                        </strong>
                        <br>

                        <div class="row-actions">
				              <span class="edit">
				                <a href="/admin/secret_spiritual_world/edit/${secretSpiritualWorld.getId()}">Изменить</a> | <a
                                      href="/admin/secret_spiritual_world/${secretSpiritualWorld.getId()}"
                                      class="submitdelete">Удалить</a>
				              </span>
                        </div>
                    </td>

                    <td>
                            ${secretSpiritualWorld.getTitle()}
                    </td>

                    <td>
                            ${secretSpiritualWorld.getYoutubeId()}
                    </td>
                    <td>
                            ${secretSpiritualWorld.getText()}
                    </td>
                    <td>
                            ${secretSpiritualWorld.getLink()}
                    </td>
                    <td>
                            ${secretSpiritualWorld.getCountViews()}
                    </td>
                    <td>
                            ${secretSpiritualWorld.getCreatedDate()}
                    </td>

                </tr>
            </c:forEach>
            <c:if test="${secretSpiritualWorldCount>10}">
                <jsp:include page="../new/pagination.jsp"/>
            </c:if>
            </tbody>
        </table>

        <br class="clear">

    </div>
</div>
