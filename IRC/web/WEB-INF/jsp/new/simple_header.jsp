<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header_out">
    <div id="header">
        <div id="header_top">
            <a href="/" id="logo" class="fl_left">&nbsp;</a>
        </div>
        <div id="header_bottom">
            <div id="header_menu">
                <%--<a href="" class=" link null"></a>--%>
                <a href="/" class="link">Главная</a>
                <a id="media_button" href="" class="link" onMouseOver="show_pad_menu('media', 'media_button')"
                   onMouseOut="hide_pad_menu('media', 'media_button')">Свидетельства</a>
                <a href="" class="link">Сила Святого Духа</a>
                <a href="" class="link">Хвала и Поклонение</a>
                <a href="" class="link">Новая Жизнь</a>
                <a href="" class="link">Магазин</a>
                <%--<a href="" class="link">Форум</a>--%>
            </div>
            <div id="media" class="out_menu" onMouseOver="show_pad_menu('media', 'media_button')"
                 onMouseOut="hide_pad_menu('media', 'media_button')">
                <div class="menu">
                    <div class="in_block">
                        <a href="tv.html" class="online">Онлайн служение</a>
                        <hr>
                        <a href="blank.html" class="arhive_video">Видео архив</a>
                        <hr>
                        <a href="blank.html" class="arhive_photo">Фото архив</a>
                        <hr>
                        <a href="blank.html" class="arhive_audio">Аудио архив</a>
                    </div>
                </div>
            </div>
            <div id="main_serach" class="search fl_right">
                <div class="search_bg">
                    <input type="text" class="srarch_input" title="Поиск по сайту">
                    <a href="" class="search_button">&nbsp;</a>
                </div>
            </div>
        </div>
    </div>
</div>
