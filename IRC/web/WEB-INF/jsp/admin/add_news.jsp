<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="ru-RU">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Создание</title>
    <link rel='stylesheet' href='/static/css/admin/calendar.css' type='text/css'>
    <link rel='stylesheet' href='/static/css/admin/load-styles.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='akismet.css-css' href='/static/css/admin/akismet.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='colors-css' href='/static/css/admin/colors-fresh.css' type='text/css' media='all'/>
    <script type="text/javascript">
        var CKEDITOR_BASEPATH = '/static/ckeditor/';
    </script>
    <script type='text/javascript' src='/static/js/admin/calendar.js'></script>
    <script type="text/javascript" src="/static/js/admin/action.js"></script>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
</head>
<body class="wp-admin no-js post-new-php">
<div id="wpwrap">
    <div id="wpcontent">
        <div id="wphead">
            <h1 id="site-heading" class="long-title">
                <a href="http://ircenter.eu/" title="Перейти на сайт">
                    <span id="site-title">IrCenter.eu</span>
                </a>
            </h1>

            <div id="wphead-info">
                <div id="user_info">
                    <p>
                        Привет, <a href="#" title="Изменить профиль">admin</a> | <a href="" title="Выйти">Выйти</a>
                    </p>
                </div>
            </div>
        </div>
        <div id="wpbody">
            <jsp:include page="left_side_bar.jsp"/>
            <div id="wpbody-content">
                <div class="wrap">
                    <div id="icon-edit" class="icon32 icon32-posts-post">
                        <br/>
                    </div>
                    <h2>Добавить запись</h2><br/>

                    <div id="poststuff" class="metabox-holder has-right-sidebar">
                        <div id="side-info-column" class="inner-sidebar">
                            <div id="side-sortables" class="meta-box-sortables">
                                <div id="submitdiv" class="postbox ">
                                    <div class="handlediv" title="Нажмите, чтобы переключить">
                                        <br/>
                                    </div>
                                    <h3 class='hndle'><span>Опубликовать</span></h3>

                                    <div class="inside">
                                        <div class="submitbox" id="submitpost">
                                            <div id="minor-publishing">
                                                <div style="display:none;">
                                                    <p class="submit">
                                                        <input type="submit" name="save" id="save" class="button"
                                                               value="Сохранить"/>
                                                    </p>
                                                </div>
                                                <div id="minor-publishing-actions">
                                                    <div id="save-action">
                                                        <input type="button" name="save" id="save-post"
                                                               value="Сохранить" tabindex="4"
                                                               class="button button-highlighted"
                                                               onclick="action.saveNews()"/>
                                                    </div>
                                                    <div id="preview-action">
                                                        <a class="preview button" href="#" id="post-preview"
                                                           tabindex="4">Просмотреть</a>
                                                    </div>
                                                    <div class="clear">
                                                    </div>
                                                </div>
                                                <div class="clear">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div id="categorydiv" class="postbox">
                                    <div class="handlediv" title="Нажмите, чтобы переключить">
                                        <br>
                                    </div>
                                    <h3 class="hndle">
                                        <span>Рубрики</span>
                                    </h3>

                                    <div class="inside">
                                        <div id="taxonomy-category" class="categorydiv">
                                            <ul id="category-tabs" class="category-tabs">
                                                <li class="tabs">
                                                    <a href="#" tabindex="3">Все рубрики</a>
                                                </li>
                                            </ul>
                                            <div id="category-all" class="tabs-panel" style="">
                                                <input type="hidden" name="post_category[]" value="0">
                                                <ul id="categorychecklist"
                                                    class="list:category categorychecklist form-no-clear">
                                                    <c:forEach items="${parts}" var="part">
                                                        <li>
                                                            <label class="selectit">
                                                                <input value="${part.getId()}" type="checkbox"
                                                                       name="post_category[]">
                                                                <c:out value="${part.getName()}"/>
                                                            </label>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                            <form:form action="./add_category" method="post"
                                                       modelAttribute="newsChapters">
                                                <div id="category-adder" class="wp-hidden-children">
                                                    <h4>
                                                        <a id="category-add-toggle" href="#" class="hide-if-no-js"
                                                           tabindex="3"> +
                                                            Добавить
                                                            новую рубрику </a>
                                                    </h4>

                                                    <p id="category-add" class="category-add ">
                                                        <label class="screen-reader-text" for="newcategory">Добавить
                                                            новую
                                                            рубрику</label>
                                                        <input type="text" name="newcategory" id="newcategory"
                                                               class="form-required form-input-tip"
                                                               tabindex="3" aria-required="true">
                                                        <label class="screen-reader-text" for="newcategory_parent">
                                                            Родительская
                                                            рубрика: </label>
                                                        <form:select path="chapterId" cssClass="postform" tabindex="3">
                                                            <form:option value="-1" label="— Родительская рубрика —"/>
                                                            <form:options items="${chapters}" itemLabel="chapter"
                                                                          itemValue="chapterId"/>
                                                        </form:select>
                                                        <input type="submit"
                                                               class="categorychecklist:category-add button category-add-sumbit"
                                                               value="Добавить новую рубрику" tabindex="3"/>
                                                        <span id="category-ajax-response"></span>
                                                    </p>
                                                </div>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                        <form:form action="./save_news" method="post" modelAttribute="newsParts">
                            <div id="post-body">
                                <div id="post-body-content">
                                    <div id="titlediv">
                                        <div id="titlewrap">
                                            <label class="hide-if-no-js" style="visibility:hidden"
                                                   id="title-prompt-text" for="title">Введите
                                                заголовок</label>
                                            <input type="text" name="post_title" size="30" tabindex="1" value=""
                                                   id="title"
                                                   autocomplete="off"/>
                                        </div>
                                        <div class="inside">
                                            <div id="edit-slug-box">
                                            </div>
                                        </div>
                                        Рубрика - <form:select path="name">
                                        <form:options items="${parts}" itemValue="id" itemLabel="name"/>
                                    </form:select>
                                        <div>Дата начала новости - <input class="date" id="date_start"></div>
                                            <%--<div>Дата конца новости - <input class="date" id="date_stop"></div>--%>
                                        <div>Номер картинки по умолчанию - №<input type="text" id="image"></div>
                                        <div class="inside">
                                            <div id="edit-slug-box1">
                                            </div>
                                        </div>
                                    </div>
                                    <textarea id="area3" style="width: 300px; height: 100px;">
                                    </textarea>
                                    <ckeditor:replace replace="area3" basePath="/static/ckeditor/"/>
                                </div>
                            </div>
                        </form:form>
                        <br class="clear"/>
                    </div>
                    <!-- /poststuff -->

                </div>
                <script type="text/javascript">
                    try {
                        document.post.title.focus();
                    } catch (e) {
                    }
                </script>
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
