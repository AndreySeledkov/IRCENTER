package org.ircenter.server.bean.user.options;

/**
 * User: Seledkov Kostyantyn
 * Date: 23.04.12
 * Time: 10:11
 */
public class PrivacyOptions {
    public static enum Type {

        EVERYBODY, FRIENDS, NOBODY;
    }

    private volatile Type writeMessage;      //Кто может писать мне сообщения

    public Type getWriteMessage() {
        return writeMessage;
    }

    public void setWriteMessage(Type writeMessage) {
        this.writeMessage = writeMessage;
    }

    public void setDefaults() {
        writeMessage = Type.EVERYBODY;
    }

    public static int toInt(PrivacyOptions.Type type) {
        switch (type) {
            case EVERYBODY:
                return 0;
            case FRIENDS:
                return 1;
            case NOBODY:
                return 2;
        }
        return -1;
    }

    public static PrivacyOptions.Type fromInt(int t) {
        switch (t) {
            case 0:
                return PrivacyOptions.Type.EVERYBODY;
            case 1:
                return PrivacyOptions.Type.FRIENDS;
            case 2:
                return PrivacyOptions.Type.NOBODY;
        }
        return null;
    }
}
