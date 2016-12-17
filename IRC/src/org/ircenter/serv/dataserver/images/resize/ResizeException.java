package org.ircenter.serv.dataserver.images.resize;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 7:59
 */
public class ResizeException extends Exception {

    public ResizeException(Throwable cause) {
        super(cause);
    }

    public ResizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResizeException(String message) {
        super(message);
    }

    public ResizeException() {
    }

}
