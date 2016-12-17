package org.ircenter.server.bean.location;

public class Region {

    private int regionId;
    private String regionName;
    private String shortName;
    private Country country;

    public Region(int regionId, String regionName, String shortName) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.shortName = shortName;
    }

    public int getRegionId() {
        return regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getShortName() {
        return shortName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
