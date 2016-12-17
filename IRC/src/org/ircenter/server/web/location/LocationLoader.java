package org.ircenter.server.web.location;

import org.ircenter.server.bean.location.City;
import org.ircenter.server.bean.location.Country;
import org.ircenter.server.bean.location.Region;
import org.ircenter.server.dao.location.UserLocationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Collection;

/**
 * User: Seledkov Kostyantyn
 * Date: 22.02.12
 * Time: 8:09
 */
@Controller
@RequestMapping("/location")
public class LocationLoader {

    private UserLocationDAO userLocationDAO;

    @RequestMapping(value = "/getcountries", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<Country> getCountries() {
        return userLocationDAO.getAllCountries();
    }

    @RequestMapping(value = "/getregions", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<Region> getRegions(@RequestParam int countryId) {
        return userLocationDAO.getRegionById(countryId);
    }


    @RequestMapping(value = "/getcities", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<City> getCities(@RequestParam int regionId) {
        return userLocationDAO.getCityById(regionId);
    }

    @Autowired
    public void setUserLocationDAO(UserLocationDAO userLocationDAO) {
        this.userLocationDAO = userLocationDAO;
    }

}
