<%@ page import="org.ircenter.server.bean.feedback.FeedBackTheme" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wpbody-content">
    <div class="wrap">

        <h2>
            Обратная связь
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
                <th scope="col" class="manage-column column-username sortable desc">
                    <a href="javascript:void(0)">
                        <span>First name</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>Last name</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Mail</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Theme</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Info</span>
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
                <th scope="col" class="manage-column column-username sortable desc">
                    <a href="javascript:void(0)">
                        <span>First name</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-name sortable desc">
                    <a href="javascript:void(0)">
                        <span>Last name</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>
                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Mail</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Theme</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

                <th scope="col" class="manage-column column-email sortable desc">
                    <a href="javascript:void(0)">
                        <span>Info</span>
                        <span class="sorting-indicator"></span>
                    </a>
                </th>

            </tr>
            </tfoot>


            <tbody id="the-list" class="list:user">

            <c:forEach items="${feedBackList}" var="feedBack" varStatus="varStatus">
                <tr id="user-1" class="alternate">

                    <td class="username column-username">
                        <strong>
                            <a href="javascript:void(0)">${feedBack.getId()}</a>
                        </strong>
                        <br>

                        <div class="row-actions">
				              <span class="edit">
				                <a href="javascript:void(0)">Изменить</a> | <a
                                      href="/admin/feedback/${feedBack.getId()}"
                                      class="submitdelete">Удалить</a>
				              </span>
                        </div>
                    </td>

                    <td>
                            ${feedBack.getFirstName()}
                    </td>

                    <td>
                            ${feedBack.getLastName()}
                    </td>
                    <td>
                            ${feedBack.getMail()}
                    </td>
                    <td>
                            ${feedBack.getTheme()}
                    </td>
                    <td>
                            ${feedBack.getInfo()}
                    </td>

                </tr>
            </c:forEach>
            <c:if test="${feedBackCount>10}">
                <jsp:include page="../new/pagination.jsp"/>
            </c:if>
            </tbody>
        </table>

        <br class="clear">
    </div>
</div>
</div>
