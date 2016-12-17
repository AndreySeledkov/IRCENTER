<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="adminmenu">
<li class="wp-first-item wp-has-submenu wp-not-current-submenu menu-top menu-top-first menu-icon-post wp-menu-open"
    id="menu-posts">
    <div class="wp-menu-image">
        <a href="index.html">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin"
       class="wp-first-item wp-has-submenu wp-not-current-submenu menu-top menu-top-first menu-icon-post"
       tabindex="1">Новости</a>

    <div class="wp-submenu" style="display: block">
        <div class="wp-submenu-head">Новости</div>
        <ul>
            <li class="wp-first-item current">
                <a href="/admin" class="wp-first-item current" tabindex="1">Записи</a>
            </li>
            <li>
                <a href="/admin/add_news" tabindex="1">Добавить новую</a>
            </li>
        </ul>
    </div>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-appearance" id="menu-posts">
    <div class="wp-menu-image">
        <a href="/admin/event_calendar">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/event_calendar" class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-appearance"
       tabindex="1">Календарь</a>

    <div class="wp-submenu" style="display: none">
        <div class="wp-submenu-head">Календарь событий</div>
        <ul>
            <li>
                <a href="/admin/event_calendar" tabindex="1">Календарь событий</a>
            </li>
            <li>
                <a href="/admin/add_calendar" tabindex="1">Добавить новую</a>
            </li>
        </ul>
    </div>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-appearance" id="menu-posts">
    <div class="wp-menu-image">
        <a href="/admin/molodejhka">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/molodejhka" class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-appearance"
       tabindex="1">Молодежка</a>

    <div class="wp-submenu" style="display: none">
        <div class="wp-submenu-head">Молодежка</div>
        <ul>
            <li>
                <a href="/admin/add_molodejhka_photo" tabindex="1">Добавить фото</a>
            </li>
            <li>
                <a href="/admin/add_molodejhka_video" tabindex="1">Добавить видео</a>
            </li>
        </ul>
    </div>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-settings" id="menu-posts">
    <div class="wp-menu-image">
        <a href="widgets.html">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="widgets.html" class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-settings" tabindex="1">Виджеты</a>

    <div class="wp-submenu" style="display: none">
        <div class="wp-submenu-head">Виджеты</div>
        <ul>
            <li>
                <a href="widget_.html" tabindex="1">Программа ТВ</a>
            </li>
            <li>
                <a href="widget_.html" tabindex="1">Начало эфира</a>
            </li>
            <li>
                <a href="widget_.html" tabindex="1">Баннеры</a>
            </li>
            <li>
                <a href="widget_.html" tabindex="1">Социальные виджеты</a>
            </li>
        </ul>
    </div>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-links" id="menu-posts">
    <div class="wp-menu-image">
        <a href="/admin/slider">
            <br>
        </a>
    </div>
    <a href="/admin/slider" class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-links"
       tabindex="1">Слайдер</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-users" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/users" class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-users" tabindex="1">Пользователи</a>

    <div class="wp-submenu">
        <div class="wp-submenu-head">Пользователи</div>
        <ul>
            <li class="wp-first-item">
                <a href="/admin/users?user='simple'" class="wp-first-item" tabindex="1">Обычные пользователи</a>
            </li>
            <li class="wp-first-item">
                <a href="/admin/users?user='our'" class="wp-first-item" tabindex="1">Наши пользователи</a>
            </li>
            <li>
                <a href="/admin/users/new_user" tabindex="1">Добавить нового</a>
            </li>

            <%--<li>--%>
            <%--<a href="profile.html" tabindex="1">Ваш профиль</a>--%>
            <%--</li>--%>
        </ul>
    </div>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-page" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/text_evidence" class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-page" tabindex="1">Свидетельства</a>

    <div class="wp-submenu">
        <div class="wp-submenu-head">Свидетельства</div>
        <ul>
            <li class="wp-first-item">
                <a href="/admin/text_evidence" class="wp-first-item" tabindex="1">Текстовые</a>
            </li>
            <li>
                <a href="/admin/video_evidence" tabindex="1">Видео</a>
            </li>
        </ul>
    </div>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-media" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/media" class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-media"
       tabindex="1">Медиафайлы</a>

    <div class="wp-submenu">
        <div class="wp-submenu-head">Медиафайлы</div>
        <ul>
            <li class="wp-first-item">
                <a href="/admin/media" class="wp-first-item" tabindex="1">Медиафайлы</a>
            </li>
            <li>
                <a href="/admin/media_add" tabindex="1">Добавить медиафайл</a>
            </li>
        </ul>
    </div>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/evidence_online"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Свидетельства онлайн</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="admin/comment" class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Коментарии</a>
</li>

<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/forum_theme"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Темы форума</a>
</li>

<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/forum_messages"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Сообщения форума</a>
</li>

<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/tv_online"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Онлайн тв</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/tv"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Телепередачи</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/minutes_to_god"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">5 минут для бога</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/secret_spiritual_world"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Тайны духовного мира</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/partner"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Партнеры</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/partner_prayer"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Молитвы Партнеров</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/feedback"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Обратная связь</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/pastor"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Обращения пастеря</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/bus"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Автобусы</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/programm_tv"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Программа тв</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/evidence_healing"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Свидетельство исцеления</a>
</li>
<li class="wp-has-submenu wp-not-current-submenu menu-top menu-icon-comments menu-top-last" id="menu-users">
    <div class="wp-menu-image">
        <a href="users.php">
            <br>
        </a>
    </div>
    <div class="wp-menu-toggle">
        <br>
    </div>
    <a href="/admin/statistic"
       class="wp-has-submenu wp-has-current-submenu wp-menu-open menu-top menu-icon-comments"
       tabindex="1">Статистика</a>
</li>
</ul>