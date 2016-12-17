package org.ircenter.serv.dataserver.images.impl.cache;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 2:12
 */
public class SPCacheException extends RuntimeException {

    public SPCacheException(Throwable cause) {
        super(cause);
    }

    public SPCacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public SPCacheException(String message) {
        super(message);
    }

    public SPCacheException() {
    }
}
