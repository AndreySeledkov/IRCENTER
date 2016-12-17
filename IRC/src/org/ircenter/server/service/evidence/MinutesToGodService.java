package org.ircenter.server.service.evidence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.MinutesToGod;
import org.ircenter.server.dao.evidence.MinutesToGodDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 16.06.12
 * Time: 16:15
 */
@Service
public class MinutesToGodService {
    private static final Log LOGGER = LogFactory.getLog(MinutesToGodService.class.getClass());
    private MinutesToGodDAO minutesToGodDAO;


    public boolean saveMinutesToGod(MinutesToGod minutesToGod) {
        return minutesToGodDAO.saveMinutesToGod(minutesToGod);
    }

    public List<MinutesToGod> getMinutesToGod(int start, int limit) {
        List<MinutesToGod> list = new ArrayList<MinutesToGod>(minutesToGodDAO.getMinutesToGod().values());
        return MUtil.getSafeSubList(list, start, start + limit);
    }

    public MinutesToGod getMinutesToGod(long id) {
        return minutesToGodDAO.getMinutesToGod().get(id);
    }

    public void removeMinutesToGod(long id) {
        minutesToGodDAO.removeMinutesToGod(id);
    }

    public int getMinutesToGodCount() {
        return minutesToGodDAO.getMinutesToGod().size();
    }

    public boolean updateMinutesToGod(MinutesToGod minutesToGod) {
        return minutesToGodDAO.updateMinutesToGod(minutesToGod);
    }

    public void setViewCount(MinutesToGod minutesToGod, int count) {
        minutesToGodDAO.setViewCount(minutesToGod, count);
    }

    @Autowired
    public void setMinutesToGodDAO(MinutesToGodDAO minutesToGodDAO) {
        this.minutesToGodDAO = minutesToGodDAO;
    }
}
