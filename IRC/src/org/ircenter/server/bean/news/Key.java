package org.ircenter.server.bean.news;

import java.io.Serializable;

/**
 * User: Seledkov Kostyantyn
 * Date: 28.03.12
 * Time: 23:34
 */
public class Key implements Serializable {
    long newsId;
    int skipCount;
    int recordCount;

    public Key(long newsId, int skipCount, int recordCount) {
        this.newsId = newsId;
        this.skipCount = skipCount;
        this.recordCount = recordCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Key other = (Key) obj;
        if (this.newsId != other.newsId) {
            return false;
        }
        if (this.skipCount != other.skipCount) {
            return false;
        }
        if (this.recordCount != other.recordCount) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (newsId ^ (newsId >>> 32));
        result = 31 * result + skipCount;
        result = 31 * result + recordCount;
        return result;
    }
}
