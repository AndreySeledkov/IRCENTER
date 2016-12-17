package org.ircenter.server.bean.like;

public enum ServiceType {

    NEWS(1);
    private int dbType;

    private ServiceType(int dbType) {
        this.dbType = dbType;
    }

    public int getDbType() {
        return dbType;
    }

    public static ServiceType valueOfDbType(int dbType) {
        for (ServiceType ct : values()) {
            if (ct.dbType == dbType) {
                return ct;
            }
        }
        throw new IllegalArgumentException("wrong dbType: " + dbType);
    }
}
