<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="wpbody-content">
    <div class="wrap">

        <h2>
            Программа тв
            <a href="/admin/programm_tv/get_new " class="button add-new-h2">Добавить новую</a>
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
                        <span>Days</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>Date</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>Type</span>
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
                        <span>Days</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>Date</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>Type</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
            </tr>
            </tfoot>


            <tbody id="the-list" class="list:user">

            <c:forEach items="${programmTvList}" var="programmTv" varStatus="varStatus">
                <tr id="user-1" class="alternate">

                    <td class="username column-username">
                        <strong>
                            <a href="javascript:void(0)">${programmTv.getId()}</a>
                        </strong>
                        <br>

                        <div class="row-actions">
				              <span class="edit">
				                <a href="/admin/programm_tv/edit/${programmTv.getId()}">Изменить</a> | <a
                                      href="/admin/programm_tv/${programmTv.getId()}"
                                      class="submitdelete">Удалить</a>
				              </span>
                        </div>
                    </td>

                    <td>
                            ${programmTv.getTitle()}
                    </td>

                    <td>
                            ${programmTv.getDays()}
                    </td>

                    <td>
                            ${programmTv.getDate()}
                    </td>
                    <td>
                            ${programmTv.getProgrammTvType()}
                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>

        <br class="clear">

    </div>
</div>
