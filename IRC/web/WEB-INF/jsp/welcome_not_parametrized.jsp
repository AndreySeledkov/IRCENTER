<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="r-star block_themes">
    <div class="cn tr"></div>
    <div class="cn tl"></div>
    <h1>Новости</h1>
    <div class="clear"></div>
    <div class="content themes_wrap">
        <c:forEach var="news" items="${news}" varStatus="i" end="10">
            <div class="r_star_white r_star_white_hover">
                <div class="cn_white tl_white"></div>
                <div class="cn_white tr_white"></div>
                <div class="content_white one_mes">
                    <div class="wrap_mes_form">
                        <div class="float_helper">
                            <p>
                                <fmt:formatDate value="${news.created}" pattern="dd.MM.yy"/> в <fmt:formatDate
                                    value="${news.created}" pattern="HH:mm"/>
                                <br>
                                <span style="color: #a9a9a9;">${news.author}</span>
                            </p>

                            <div class="mess_text" style="cursor: pointer; cursor: hand;"
                                 onclick="window.open('http://${news.link}')">
                                <p style="background: transparent; font: 16px/30px 'PT Sans','Trebuchet MS',Arial,sans-serif; border-bottom: none; float: none; text-align: left; width: 100%; text-decoration: underline; height: auto;">
                                    <c:out value="${news.title}"/>
                                </p>

                                <p style="background: transparent; font: 12px 'PT Sans','Trebuchet MS',Arial,sans-serif; border-bottom: none; float: none; text-align: left; width: 100%; text-decoration: none; height: auto; font-weight: bold;">
                                    ${news.description}
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="cn_white bl_white"></div>
                <div class="cn_white br_white"></div>
            </div>
        </c:forEach>
        <c:forEach var="news" items="${news}" varStatus="i" begin="11" end="20">
            <div id="news2" style="display: none;" class="r_star_white r_star_white_hover">
                <div class="cn_white tl_white"></div>
                <div class="cn_white tr_white"></div>
                <div class="content_white one_mes">
                    <div class="wrap_mes_form">
                        <div class="float_helper">
                            <p>
                                <fmt:formatDate value="${news.created}" pattern="dd.MM.yy"/> в <fmt:formatDate
                                    value="${news.created}" pattern="HH:mm"/>
                                <br>
                                <span style="color: #a9a9a9;">${news.author}</span>
                            </p>

                            <div class="mess_text" style="cursor: pointer; cursor: hand;"
                                 onclick="window.open('http://${news.link}')">
                                <p style="background: transparent; font: 16px/30px 'PT Sans','Trebuchet MS',Arial,sans-serif; border-bottom: none; float: none; text-align: left; width: 100%; text-decoration: underline; height: auto;">
                                    <c:out value="${news.title}"/>
                                </p>

                                <p style="background: transparent; font: 12px 'PT Sans','Trebuchet MS',Arial,sans-serif; border-bottom: none; float: none; text-align: left; width: 100%; text-decoration: none; height: auto; font-weight: bold;">
                                    ${news.description}
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="cn_white bl_white"></div>
                <div class="cn_white br_white"></div>
            </div>
        </c:forEach>
        <c:forEach var="news" items="${news}" varStatus="i" begin="21">
            <div id="news3" style="display: none;" class="r_star_white r_star_white_hover">
                <div class="cn_white tl_white"></div>
                <div class="cn_white tr_white"></div>
                <div class="content_white one_mes">
                    <div class="wrap_mes_form">
                        <div class="float_helper">
                            <p>
                                <fmt:formatDate value="${news.created}" pattern="dd.MM.yy"/> в <fmt:formatDate
                                    value="${news.created}" pattern="HH:mm"/>
                                <br>
                                <span style="color: #a9a9a9;">${news.author}</span>
                            </p>

                            <div class="mess_text" style="cursor: pointer; cursor: hand;"
                                 onclick="window.open('http://${news.link}')">
                                <p style="background: transparent; font: 16px/30px 'PT Sans','Trebuchet MS',Arial,sans-serif; border-bottom: none; float: none; text-align: left; width: 100%; text-decoration: underline; height: auto;">
                                    <c:out value="${news.title}"/>
                                </p>

                                <p style="background: transparent; font: 12px 'PT Sans','Trebuchet MS',Arial,sans-serif; border-bottom: none; float: none; text-align: left; width: 100%; text-decoration: none; height: auto; font-weight: bold;">
                                    ${news.description}
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="cn_white bl_white"></div>
                <div class="cn_white br_white"></div>
            </div>
        </c:forEach>
    </div>
    <div id="more_news" class="pagination">
        <span style="color: #555555; background: transparent; cursor: pointer; cursor: hand;" onclick="showMoreNews(); return false;">Дальше</span>
    </div>
    <div id="more_news_div" style="height: 12px; display: none;"></div>
    <div class="clear"></div>
    <div class="cn br"></div>
    <div class="cn bl"></div>
</div>