<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="header_out">
    <div id="header">
        <div id="header_top">
            <a href="/" id="logo" class="fl_left">&nbsp;</a>

            <div id="header_top_right">
                <span class="header_shadow_e"><a href="/signup/" class="gradient_green_button reg_main_button p6">Регистрация</a></span>
                <span class="header_shadow_e"><a href="javascript:void(0);" onClick="createModal('/auth/auth_form');" class="gradient_silver_button auth_main_button p6">Войти</a></span>
            </div>
        </div>

        <div id="header_bottom">
            <div id="header_menu">
                <a class="link null"></a>
                <a href="/" class="link">Главная</a>
                <a id="news_button" href="/news" class="link select" onMouseOver="show_pad_menu('news', 'news_button')" onMouseOut="hide_pad_menu('news', 'news_button')"><span class="l">&nbsp;</span>Новости<span class="r">&nbsp;</span></a>
                <a href="/partner" class="link">Партнерство</a>
                <a id="svidet_button" href="/text_evidence" class="link" onMouseOver="show_pad_menu('svidet', 'svidet_button')" onMouseOut="hide_pad_menu('svidet', 'svidet_button')">Свидетельства</a>
                <a id="media_button" href="/tv_online" class="link" onMouseOver="show_pad_menu('media', 'media_button')" onMouseOut="hide_pad_menu('media', 'media_button')">Телевидение</a>
                <a class="link" href="/molodejhka">Молодежка</a>
                <%--<a href="/forum" class="link">Форум</a>--%>
                <a href="http://vo.org.ua/shop/index.php?option=com_jshopping&controller=category&task=view&category_id=5&Itemid=157" class="link">Магазин</a>
            </div>
            <div id="news" class="out_menu" onMouseOver="show_pad_menu('news', 'news_button')" onMouseOut="hide_pad_menu('news', 'news_button')">
                <div class="menu">
                    <div class="in_block">
                        <a href="/news?partId=2">Днепропетровск</a>
                        <hr>
                        <a href="/news?partId=3">Киев</a>
                    </div>
                </div>
            </div>
            <div id="media" class="out_menu" onMouseOver="show_pad_menu('media', 'media_button')" onMouseOut="hide_pad_menu('media', 'media_button')">
                <div class="menu">
                    <div class="in_block">
                        <a href="/tv_online">Прямой эфир</a>
                        <hr>
                        <a href="/tv">Телепередачи</a>
                        <hr>
                        <a href="/minutes_to_god">Пять минут для Бога</a>
                        <hr>
                        <a href="/secret_spiritual_world">Тайны Духовного мира</a>
                        <%--<hr>--%>
                        <%--<a href="tv.html">Разное</a>--%>
                    </div>
                </div>
            </div>
            <div id="svidet" class="out_menu" onMouseOver="show_pad_menu('svidet', 'svidet_button')" onMouseOut="hide_pad_menu('svidet', 'svidet_button')">
                <div class="menu">
                    <div class="in_block">
                        <a href="/text_evidence">Текстовые</a>
                        <hr>
                        <a href="/video_evidence">Видео</a>
                    </div>
                </div>
            </div>
            <form accept-charset="UTF-8" action="/search" method="get">
                <div id="main_serach" class="search fl_right">
                    <div class="search_bg">
                        <input type="text" name="q" class="srarch_input" placeholder="Поиск по сайту">
                        <a class="search_button" href="javascript:void(0)" onclick="$('#searchpanel').submit();">&nbsp;</a>
                        <input type="hidden" name="s" value="0"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
