package org.ircenter.server.dao.location;

import com.googlecode.ehcache.annotations.Cacheable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.bean.location.City;
import org.ircenter.server.bean.location.Country;
import org.ircenter.server.bean.location.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class UserLocationDAO {
    private static final Log LOGGER = LogFactory.getLog(UserLocationDAO.class.getClass());
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private Map<Integer, Country> loadCountries() {
        final Map<Integer, Country> countries = new HashMap<Integer, Country>();
        String selectCountries = "SELECT * FROM countries ORDER BY order_id";
        try {
            jdbcTemplate.query(selectCountries,
                    new RowMapper<Country>() {
                        public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
                            int countryId = rs.getInt("country_id");
                            String countryName = rs.getString("country_name");
                            String shortName = rs.getString("short_name");
                            Country country = new Country(countryId, countryName, shortName);
                            countries.put(countryId, country);
                            return country;
                        }
                    });
        } catch (Exception e) {
            LOGGER.error("error get countries", e);
        }
        return countries;
    }

    private Map<Integer, Region> loadRegions(int countryId) {
        final Map<Integer, Region> regions = new HashMap<Integer, Region>();
        String selectRegions = "SELECT * FROM regions WHERE country_id = ?";
        try {
            jdbcTemplate.query(selectRegions,
                    new Object[]{countryId},
                    new RowMapper<Region>() {
                        public Region mapRow(ResultSet rs, int rowNum) throws SQLException {
                            String timeZoneID = rs.getString("time_zone");
                            Region region = new Region(rs.getInt("region_id"), rs.getString("region_name"), rs.getString("short_name"));
                            regions.put(region.getRegionId(), region);
                            return region;
                        }
                    });
        } catch (Exception e) {
            LOGGER.error("error get regions", e);
        }
        return regions;
    }

    private Map<Integer, City> loadCities(int regionId) {
        final Map<Integer, City> cities = new HashMap<Integer, City>();
        String selectCities = "SELECT * FROM cities WHERE region_id = ?";
        try {
            jdbcTemplate.query(selectCities,
                    new Object[]{regionId},
                    new RowMapper<City>() {
                        public City mapRow(ResultSet rs, int rowNum) throws SQLException {
                            City city = new City(rs.getInt("city_id"), rs.getString("city_name"));
                            city.setLatitude(rs.getDouble("latitude"));
                            city.setLongitude(rs.getDouble("longitude"));
                            cities.put(city.getCityId(), city);
                            return city;
                        }
                    });
        } catch (Exception e) {
            LOGGER.error("error get cities", e);
        }
        return cities;
    }

    @Cacheable(cacheName = "countries")
    public Collection<Country> getAllCountries() {
        return loadCountries().values();
    }

    @Cacheable(cacheName = "regions")
    public Collection<Region> getRegionById(int countryId) {
        return loadRegions(countryId).values();
    }

    @Cacheable(cacheName = "cities")
    public Collection<City> getCityById(int regionId) {
        return loadCities(regionId).values();
    }

    public City getCity(int regionId, int cityId) {
        return loadCities(regionId).get(cityId);
    }

    public Country getCountry(int countryId) {
        return loadCountries().get(countryId);
    }

}
