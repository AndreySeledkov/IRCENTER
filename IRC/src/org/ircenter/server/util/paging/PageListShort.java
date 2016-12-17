package org.ircenter.server.util.paging;

/**
 * generate smth like this: "<a href="1">1</a> <a href="1">2</a> .. <a href="1">5</a> <a href="1">6</a> <b>7</b> <a href="1">8</a> <a href="1">9</a> .. <a href="1">99</a>"
 *
 * @author Anton
 * @date 28.01.2009   16:25:49
 */
public class PageListShort extends PageListGenerator {

    public final static int cnt = 2;

    public PageListShort(String href_prefix, int items_per_page) {
        super(href_prefix, items_per_page);
    }

    public PageListShort(String href_prefix, boolean onclick, int items_per_page) {
        super(href_prefix, onclick, items_per_page);
    }


    public PageListShort(PageListGenerator generator) {
        super(generator.getLinkPrefix(), generator.getCountItemsPerPage());
    }

    private void makeHrefPage(StringBuilder buf, int page, boolean link) {
        if (link) {
            if (onClickPrefix) {
                buf.append("<a class=\"page_link\" href=\"").append(hrefPrefix).append(page + 1).append("\"").append(" onclick=\"").append("return rightSideUpdateMessagesModal('/messages?section=inbox','inbox',").append(page + 1).append(")").append("\" ").append("\">").append(page + 1).append("</a> ");
            } else {
                buf.append("<a class=\"page_link\" href=\"").append(hrefPrefix).append(page + 1).append("\">").append(page + 1).append("</a> ");
            }
        } else {
            buf.append("<span class=\"page_link\">").append(page + 1).append("</span> ");
        }
    }

    @Override
    public void generate(StringBuilder buf) {
        int numberPages = getPagesCount();

        if (currentPage == 0) {
            buf.append("<span class=\"silver_shadow mb_10 disabled\"><a class=\"button gradient_silver_button\"><img src=\"/static/img/arrow_left.png\" /> Назад</a></span> ");
            buf.append("<div class=\"pages\"> ");
        } else {
            if (onClickPrefix) {
                buf.append("<span class=\"silver_shadow mb_10\"><a class=\"button gradient_silver_button\" href=\"").append(hrefPrefix).append(currentPage + 1 - 1).append("\"").append(" onclick=\"").append("return rightSideUpdateMessagesModal('/messages?section=inbox','inbox',").append(currentPage + 1 - 1).append(")").append("\">").append("<img src=\"/static/img/arrow_left.png\" /> Назад</a></span>");
            } else {
                buf.append("<span class=\"silver_shadow mb_10\"><a class=\"button gradient_silver_button\" href=\"").append(hrefPrefix).append(currentPage + 1 - 1).append("\"><img src=\"/static/img/arrow_left.png\" /> Назад</a></span> ");
            }

            buf.append("<div class=\"pages\">");
        }


        if (currentPage < cnt + 2) {
            for (int i = 0; i < currentPage; ++i) {
                makeHrefPage(buf, i, true);
            }
        } else {
            makeHrefPage(buf, 0, true);
            buf.append("... ");
            for (int i = 0; i < cnt; ++i) {
                makeHrefPage(buf, currentPage - (cnt - i), true);
            }
        }

        makeHrefPage(buf, currentPage, false);

        if (currentPage > numberPages - cnt - 3) {
            for (int i = currentPage + 1; i < numberPages; ++i) {
                makeHrefPage(buf, i, true);
            }
        } else {
            for (int i = 0; i < cnt; ++i) {
                makeHrefPage(buf, currentPage + i + 1, true);
            }

            buf.append("... ");
            makeHrefPage(buf, numberPages - 1, true);
        }

        buf.append(" </div>");

        int lastPage = itemsCount / itemsPerPage;
        if (itemsCount % itemsPerPage == 0) {
            lastPage--;
        }
        if (currentPage == lastPage) {
            buf.append(" <span class=\"silver_shadow mb_10 disabled\"><a class=\"gradient_silver_button button\">Вперед <img src=\"/static/img/arrow_right.png\" alt=\"\" /></a></span>");
        } else {
            if (onClickPrefix) {
                buf.append("<span class=\"silver_shadow mb_10\"><a class=\"button gradient_silver_button\" href=\"").append(hrefPrefix).append(currentPage + 1 + 1).append("\"").append(" onclick=\"").append("return rightSideUpdateMessagesModal('/messages?section=inbox','inbox',").append(currentPage + 1 + 1).append(")").append("\">").append("<img src=\"/static/img/arrow_right.png\" /> Вперед</a></span>");
            } else {

                buf.append(" <span class=\"silver_shadow mb_10\"><a class=\"button gradient_silver_button\" href=\"").append(hrefPrefix).append(currentPage + 1 + 1).append("\"><img src=\"/static/img/arrow_right.png\" /> Вперед</a></span>");
            }
        }
    }
}
