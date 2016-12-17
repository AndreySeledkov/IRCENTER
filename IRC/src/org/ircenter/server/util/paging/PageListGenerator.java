package org.ircenter.server.util.paging;

/**
 * @author Anton
 * @date 28.01.2009   16:09:39
 */
public abstract class PageListGenerator {

    protected int itemsCount;
    protected int itemsPerPage;
    protected int currentPage = 0;
    protected String hrefPrefix = null;
    protected boolean onClickPrefix;

    public PageListGenerator(String hrefPrefix, int itemsPerPage) {
        this.hrefPrefix = hrefPrefix;
        this.itemsPerPage = itemsPerPage;
    }

    public PageListGenerator(String hrefPrefix, boolean onClickPrefix, int itemsPerPage) {
        this.hrefPrefix = hrefPrefix;
        this.itemsPerPage = itemsPerPage;
        this.onClickPrefix = onClickPrefix;
    }

    public void setItemsCount(int count) {
        this.itemsCount = count;
    }

    public int getItemCount() {
        return this.itemsCount;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getPagesCount() {
        if (itemsPerPage == 0) {
            return 0;
        }

        int count_pages = itemsCount / itemsPerPage + (itemsCount % itemsPerPage > 0 ? 1 : 0);
        return count_pages;
    }

    public void setCurrentPage(int page) {
        this.currentPage = page;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getCountItemsPerPage() {
        return itemsPerPage;
    }

    public void setLinkPrefix(String prefix) {
        this.hrefPrefix = prefix;
    }

    public String getLinkPrefix() {
        return this.hrefPrefix;
    }

    public String generate() {
        StringBuilder buf = new StringBuilder();

        generate(buf);

        return buf.toString();
    }

    public abstract void generate(StringBuilder buf);
}
