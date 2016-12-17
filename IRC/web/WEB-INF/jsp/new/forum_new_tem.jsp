<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Форум</title>
    <link rel="icon" href="/static/img/favicon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/static/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/forum.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/profile.css" type="text/css"/>
    <script src="/static/js/jquery.js" type="text/javascript"></script>
    <script src="/static/js/json2.js" type="text/javascript"></script>
    <script src="/static/js/countdown.min.js" type="text/javascript"></script>
    <script src="/static/js/demo.sj" type="text/javascript"></script>
    <script src="/static/js/widget.js" type="text/javascript"></script>
    <script src="/static/js/smoothDivScroll.js" type="text/javascript"></script>
    <script src="/static/js/common.js" type="text/javascript"></script>
    <script type="text/javascript" src="/static/protoplasm/protoplasm.js"></script>
    <script language="javascript">
        Protoplasm.use('rte').transform('textarea.richtext');
    </script>
    <script type="text/javascript">
        (function (d, w, c) {
            (w[c] = w[c] || []).push(function() {
                try {
                    w.yaCounter15559465 = new Ya.Metrika({id:15559465, enableAll: true, webvisor:true});
                } catch(e) {}
            });

            var n = d.getElementsByTagName("script")[0],
                    s = d.createElement("script"),
                    f = function () { n.parentNode.insertBefore(s, n); };
            s.type = "text/javascript";
            s.async = true;
            s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";

            if (w.opera == "[object Opera]") {
                d.addEventListener("DOMContentLoaded", f);
            } else { f(); }
        })(document, window, "yandex_metrika_callbacks");
    </script>
</head>
<body>
<sec:authorize access="isAnonymous()">
    <jsp:include page="anonymous_header_forum.jsp"/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="authenticated_header_forum.jsp"/>
</sec:authorize>


<div id="content">

    <div class="clear_fix">&nbsp;</div>

    <div class="main_content top bottom">
        <div class="forum_navig"><a href="/forum" class="forum_navig_link">Форум</a> <span class="forum_arrow_to_right">&nbsp;</span>
            <a href="/forum/${part.id}" class="forum_navig_link">${part.name}</a> <span
                    class="forum_arrow_to_right">&nbsp;</span> <span
                    class="forum_navig_last">Начать новую тему</span>
        </div>
        <div class="forum_element ma10">
            <div class="forum_element_header">Начать новую тему</div>
            <div class="forum_new_block">
                <form:form id="post" method="post" action="/forum/newtheme">

                    <input type="hidden" id="partId" name="partId" value="${part.id}">

                    <div class="forum_new_block_header">
                        Название темы <input type="text" name="nameTheme" id="theme_title" size="30" tabindex="1"
                                             value=""
                                             id="title"
                                             autocomplete="off"/>
                    </div>
                    <div class="forum_new_block_content">
                        <textarea name="text" class="richtext" rows="20" cols="80">

                        </textarea>

                        <div class="editor_block tl">
                            <input type="submit" value="Отправить">

                        </div>
                    </div>
                </form:form>
            </div>


        </div>
    </div>
    <!-- /main_content -->


    <jsp:include page="footer.jsp"/>
    <!-- /footer_out -->
</body>
</html>