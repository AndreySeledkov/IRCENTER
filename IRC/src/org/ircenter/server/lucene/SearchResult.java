package org.ircenter.server.lucene;

import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 11.06.12
 * Time: 19:38
 */
public class SearchResult {
    private IndexType type;
    private List<Long> result;
    private int totalHits;

    public SearchResult(IndexType type, List<Long> result,int totalHits) {
        this.type = type;
        this.result = result;
        this.totalHits = totalHits;
    }

    public IndexType getType() {
        return type;
    }

    public List<Long> getResult() {
        return result;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setType(IndexType type) {
        this.type = type;
    }

    public void setResult(List<Long> result) {
        this.result = result;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }
}
