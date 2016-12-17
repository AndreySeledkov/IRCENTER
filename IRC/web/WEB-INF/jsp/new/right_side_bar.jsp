<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.ircenter.server.service.user.UserProfile" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="profile_right_column">
    <div class="profile_right_column_header">
        <%= ((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getLoginName()%>
    </div>
    <div class="profile_right_column_content">
        <div class="profile_status">
            Сделай первый шаг и ты поймешь, что не все так страшно!
        </div>
        <div class="profile_info_line">
            <span class="elem_calendar">15 августа 1987 г.</span>
            <span class="elem_place_point">Новомосковск</span>
            <span class="elem_people_sp">женат на <a href="#">Нике Рудаковой</a></span>
            <span class="elem_email"><a href="mailto:netserj@gmail.com">netserj@gmail.com</a></span>
        </div>
        <div class="gallery_header">
            Фотографии
            <a href="#" class="small_link fl_right">добавить фотографии</a>
        </div>
        <div class="gallery_list">
            <ul>
                <li><a href="#"><img src="/static/img/tmp38.jpg" alt=""/></a></li>
                <li><a href="#"><img src="/static/img/tmp39.jpg" alt=""/></a></li>
                <li><a href="#"><img src="/static/img/tmp40.jpg" alt=""/></a></li>
                <li><a href="#"><img src="/static/img/tmp41.jpg" alt=""/></a></li>
            </ul>
        </div>
        <div class="gallery_header">
            Видео
            <a href="#" class="small_link fl_right">добавить видео</a>
        </div>
        <div class="gallery_list">
            <ul>
                <li>
                    <a href="#" class="video_element">
                        <div class="img_abs">
                            <img src="/static/img/tmp42.jpg" alt=""/>
                            <span>1:40:20</span>
                        </div>
                        <span class="title">Cлужение Бенни Хинна в <br/> Днепропетровске...</span>
                    </a><!-- /video_element -->
                </li>
                <li>
                    <a href="#" class="video_element">
                        <div class="img_abs">
                            <img src="/static/img/tmp43.jpg" alt=""/>
                            <span>1:40:20</span>
                        </div>
                        <span class="title">Cлужение Бенни Хинна в <br/> Днепропетровске...</span>
                    </a><!-- /video_element -->
                </li>
                <li>
                    <a href="#" class="video_element">
                        <div class="img_abs">
                            <img src="/static/img/tmp42.jpg" alt=""/>
                            <span>1:40:20</span>
                        </div>
                        <span class="title">Cлужение Бенни Хинна в <br/> Днепропетровске...</span>
                    </a><!-- /video_element -->
                </li>
                <li>
                    <a href="#" class="video_element">
                        <div class="img_abs">
                            <img src="/static/img/tmp43.jpg" alt=""/>
                            <span>1:40:20</span>
                        </div>
                        <span class="title">Cлужение Бенни Хинна в <br/> Днепропетровске...</span>
                    </a><!-- /video_element -->
                </li>
            </ul>
        </div>
    </div>
</div>
