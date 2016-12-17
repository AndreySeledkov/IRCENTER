<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="wpbody-content">
    <div class="wrap">
        <div id="icon-users" class="icon32">
            <br>
        </div>
        <h2 id="add-new-user"> Добавить нового пользователя</h2>

        <div id="ajax-response"></div>
        <p>Создать учётную запись нового пользователя и добавить его к этому сайту.</p>

        <form:form id="post" method="post" action="/admin/users/new_user">
            <table class="form-table">
                <tbody>
                <tr class="form-field form-required">
                    <th scope="row">
                        <label for="user_login">
                            Имя пользователя
                            <span class="description">(обязательно)</span>
                        </label>
                    </th>
                    <td>
                        <input name="userLogin" type="text" id="user_login" value="" aria-required="true">
                    </td>
                </tr>
                <tr class="form-field form-required">
                    <th scope="row">
                        <label for="email">
                            E-mail
                            <span class="description">(обязательно)</span>
                        </label>
                    </th>
                    <td>
                        <input name="email" type="text" id="email" value="">
                    </td>
                </tr>

                <tr class="form-field form-required">
                    <th scope="row">
                        <label for="pass1">
                            Пароль
                            <span class="description">(дважды, обязательно)</span>
                        </label>
                    </th>
                    <td>
                        <input name="pass1" type="password" id="pass1" autocomplete="off">
                        <br>
                        <input name="pass2" type="password" id="pass2" autocomplete="off">
                        <br>
                    </td>
                </tr>

                <tr class="form-field">
                    <th scope="row">
                        <label for="role">Роль</label>
                    </th>
                    <td>
                        <select name="role" id="role">
                            <option selected="selected" value="subscriber">Подписчик</option>
                            <option value="administrator">Администратор</option>
                            <option value="editor">Редактор</option>
                            <option value="author">Автор</option>
                            <option value="contributor">Участник</option>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
            <p class="submit">
                <input type="submit" value="Добавить">
            </p>
        </form:form>

    </div>
    <form method="get" action="">
        <table style="display:none;">
            <tbody id="com-reply">
            <tr id="replyrow" style="display:none;">
                <td colspan="2" class="colspanchange">
                    <div id="replyhead" style="display:none;">
                        Ответить на комментарий
                    </div>
                    <div id="edithead" style="display:none;">
                        <div class="inside">
                            <label for="author">Имя</label>
                            <input type="text" name="newcomment_author" size="50" value="" tabindex="101" id="author"/>
                        </div>
                        <div class="inside">
                            <label for="author-email">E-mail</label>
                            <input type="text" name="newcomment_author_email" size="50" value="" tabindex="102"
                                   id="author-email"/>
                        </div>
                        <div class="inside">
                            <label for="author-url">URL</label>
                            <input type="text" id="author-url" name="newcomment_author_url" size="103" value=""
                                   tabindex="103"/>
                        </div>
                        <div style="clear:both;">
                        </div>
                    </div>
                    <div id="replycontainer">
                        <textarea rows="8" cols="40" name="replycontent" tabindex="104" id="replycontent"></textarea>
                    </div>
                    <p id="replysubmit" class="submit">
                        <a href="#comments-form" class="cancel button-secondary alignleft" tabindex="106">Отмена</a>
                        <a href="#comments-form" class="save button-primary alignright" tabindex="104">
                            <span id="savebtn" style="display:none;">Обновить комментарий</span>
                            <span id="replybtn" style="display:none;">Опубликовать ответ</span></a>
                        <img class="waiting" style="display:none;"
                             src="http://bis.dp.ua/wp-admin/images/wpspin_light.gif" alt=""/>
                        <span class="error" style="display:none;"></span>
                        <br class="clear"/>
                    </p>
                    <input type="hidden" name="user_ID" id="user_ID" value="1"/>
                    <input type="hidden" name="action" id="action" value=""/>
                    <input type="hidden" name="comment_ID" id="comment_ID" value=""/>
                    <input type="hidden" name="comment_post_ID" id="comment_post_ID" value=""/>
                    <input type="hidden" name="status" id="status" value=""/>
                    <input type="hidden" name="position" id="position" value="1"/>
                    <input type="hidden" name="checkbox" id="checkbox" value="0"/>
                    <input type="hidden" name="mode" id="mode" value="single"/>
                    <input type="hidden" id="_ajax_nonce-replyto-comment" name="_ajax_nonce-replyto-comment"
                           value="b7ef905ee5"/><input type="hidden" id="_wp_unfiltered_html_comment"
                                                      name="_wp_unfiltered_html_comment" value="11cb83faa0"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <script type="text/javascript">
        try {
            document.post.title.focus();
        } catch (e) {
        }
    </script>
    <div class="clear">
    </div>
</div>
