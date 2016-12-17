<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">

    function fbClick() {
        FB.login(post_fb, {
            scope:'user_about_me,user_birthday,user_interests,email'
        });
    }

    function post_fb(response) {
        if (response.authResponse) {
            $.getJSON("/fbauth", response.authResponse, function (response) {
                $(window.location).attr('href', "/");
            });
        }
        else {
            return false;
        }
    }

    function vkClick() {
        VK.Auth.login(post_vk);
    }
    function post_vk(response) {
        if (response.session) {
            $.getJSON("/vkauth", response.session, function (response) {
                $(window.location).attr('href', "/");
            });
        }
        else {
            return false;
        }
    }
    function post_mailRu(session) {
        $.getJSON("/mailRuAuth/", session[0], function (response) {
            $(window.location).attr('href', "/");
        });
    }

</script>
<div id="api_scripts">
    <div id="fb-root"></div>
    <script src="/static/js/openapi.js" type="text/javascript"></script>
    <script src="/static/js/all.js" type="text/javascript"></script>
    <script type="text/javascript" src="/static/js/loader.js"></script>
    <script type="text/javascript" language="javascript">
        FB.init({appId:'8331309681', status:true, cookie:true, xfbml:true});
        VK.init({
            apiId:20168308
        });
        mailru.loader.require('api', function () {
            mailru.connect.init('667673', 'dd84d229e2addcde1c1f3eefbc0b231c');
            mailru.events.listen(mailru.connect.events.login, function (session) {
                mailru.common.users.getInfo(function (result) {
                    post_mailRu(result);
                });
                //window.location.reload();
            });
            mailru.events.listen(mailru.connect.events.logout, function () {
                window.location.reload();
            });
            mailru.connect.getLoginStatus(function (result) {
                if (result.is_app_user != 1) {
                    mailru.connect.initButton();
                } else {
                    mailru.common.users.getInfo(function (result) {
                        // post_mailRu(result);
                    });
                }
            });
        });


    </script>


</div>
<div class="reg_right_block">
    <a href="javascript:void(0)" onclick="fbClick()" class="social facebook">Войти через
        <span>Facebook</span></a>
    <a href="javascript:void(0)" onclick="vkClick()" class="social vkontakte">Войти через
        <span>Vkontakte</span></a>

    <%--<a class="mrc__connectButton">вход@mail.ru</a>--%>
    <%--<a href="https://connect.mail.ru/oauth/authorize?client_id=667673&response_type=token&redirect_uri=http%3A%2F%2Fconnect.mail.ru%2Fproxy%3Fapp_id%3D667673&host=http://localhost:8080" class="social odnoklassniki">Войти через <span>Одноклассники</span></a>--%>
</div>
