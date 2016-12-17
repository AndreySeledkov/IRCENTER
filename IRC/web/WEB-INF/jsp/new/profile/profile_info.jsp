<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="profile_info">
    <div class="profile_info_line">
        <span class="profile_info_element"><span class="l">Дата рождения:</span>15 августа 1987 г.</span>
        <span class="profile_info_element"><span class="l">Место жительства:</span>Новомосковск</span>
            <span class="profile_info_element"><span class="l">Семейное положение:</span>женат на <a
                    href="profile_other.html">Нике Рудаковой</a></span>
        <span class="profile_info_element"><span class="l">E-mail:</span><a href="mailto:netserj@gmail.com">netserj@gmail.com</a></span>
        <span class="profile_info_element"><span class="l">Вероисповедание:</span>Протестант</span>
        <span class="profile_info_element"><span class="l">Моб. телефон:</span>050 7 111 333</span>
        <span class="profile_info_element"><span class="l">Интересы:</span>Дизайн, Кино</span>
                    <span class="profile_info_element"><span
                            class="l">О себе:</span>Люблю хорошую компанию костра</span>

        <p></p>
            <span class="profile_info_element"><span class="l">Группы:</span><a href="group.html">Домашняя группа Тети
                Маши</a></span>
    </div>
    <c:if test="${friendCount!=0}">
        <div class="profile_info_friends">

            <div class="profile_info_friends_header">
                <a href="/friends">Друзья</a> <span>${userListFriends.size()}</span>
            </div>
            <div class="profile_info_friends_elements">

                <c:forEach items="${userListFriends}" var="userListFriend" varStatus="i" end="6">
                    <a href="profile_other.html" class="profile_info_friends_element online">
                        <img src="/static/img/tmp94.jpg" alt=""/>
                    </a>
                </c:forEach>

            </div>
            <a href="/friends?section=all" class="fl_right show_all_friends">Посмотреть всех</a>
        </div>
    </c:if>
</div>