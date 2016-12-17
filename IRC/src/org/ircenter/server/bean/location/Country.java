package org.ircenter.server.bean.location;

public class Country {
    private int countryId;
    private String countryName;
    private String shortName;

    public Country(int countryId, String countryName, String shortName) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.shortName = shortName;
    }
    
    public int getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getShortName() {
        return shortName;
    }

}
