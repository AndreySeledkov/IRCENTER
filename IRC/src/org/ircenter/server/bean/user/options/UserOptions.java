package org.ircenter.server.bean.user.options;

/**
 * User: Seledkov Kostyantyn
 * Date: 23.04.12
 * Time: 10:26
 */
public class UserOptions {

    public static enum MessageNotification {

        NONE(0), VIBRATION(1), SOUND(2);
        private final int value;

        private MessageNotification(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }


        public static MessageNotification getMessageNotification(int value) {
            if (value == SOUND.value) {
                return SOUND;
            }
            if (value == VIBRATION.value) {
                return VIBRATION;
            }
            return NONE;
        }
    }

    private final long ownerId;
    private volatile boolean existsInDb;

    private MessageNotification messageNotification;
    private final PrivacyOptions privacyOptions;

    public UserOptions(long ownerId) {
        this.ownerId = ownerId;
        this.privacyOptions = new PrivacyOptions();
    }

    public void setDefault() {
        messageNotification = MessageNotification.NONE;
        privacyOptions.setDefaults();
    }

    public MessageNotification getMessageNotification() {
        return messageNotification;
    }

    public void setMessageNotification(int value) {
        this.messageNotification = MessageNotification.getMessageNotification(value);
    }

    public boolean isExistsInDb() {
        return existsInDb;
    }

    public void setExistsInDb(boolean existsInDb) {
        this.existsInDb = existsInDb;
    }

    public PrivacyOptions getPrivacyOptions() {
        return privacyOptions;
    }

    public long getOwnerId() {
        return ownerId;
    }
}
