package org.ircenter.server.service.user;

/**
 * UserProfile: Seledkov Kostyantyn
 * Date: 28.01.12
 * Time: 23:12
 */
public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_BAN_POST("ROLE_BAN_POST"),
    ROLE_BAN_USER("ROLE_BAN_USER"),
    ROLE_BAN_COMMENT("ROLE_BAN_COMMENT"),
    ROLE_MODERATOR("ROLE_MODERATOR");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getValue() {
        return this.role;
    }

    public boolean equals(String role) {
        if (name().equals(role)) {
            return true;
        }
        return false;
    }
}
