package org.ircenter.server.bean.forum;

public interface BanFilterable {

    long getAuthorId();

    boolean isBanned();

    boolean isDeleted();
}
