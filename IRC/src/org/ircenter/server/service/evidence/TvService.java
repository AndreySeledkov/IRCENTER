package org.ircenter.server.service.evidence;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.Tv;
import org.ircenter.server.bean.evidence.TvType;
import org.ircenter.server.dao.evidence.TvDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: Seledkov Kostyantyn
 * Date: 16.06.12
 * Time: 16:13
 */
@Service
public class TvService {
    private TvDAO tvDAO;

    public boolean saveTv(Tv tv) {
        return tvDAO.saveTv(tv);
    }

    public List<Tv> getTV(int start, int limit, TvType tvType) {
        Map<Long, Tv> map = tvDAO.getTV();
        List<Tv> list = new ArrayList<Tv>();
        for (Map.Entry<Long, Tv> entry : map.entrySet()) {
            Tv value = entry.getValue();
            if (value.getTvType() == tvType) {
                list.add(value);
            }
        }
        return MUtil.getSafeSubList(list, start, start + limit);
    }

    public List<Tv> getTV(int start, int limit) {
        List<Tv> list = new ArrayList<Tv>(tvDAO.getTV().values());
        return MUtil.getSafeSubList(list, start, start + limit);
    }

    public void removeTv(long id) {
        tvDAO.removeTv(id);
    }

    public int getTvCount(TvType tvType) {
        int count = 0;
        Map<Long, Tv> map = tvDAO.getTV();
        for (Map.Entry<Long, Tv> entry : map.entrySet()) {
            Tv value = entry.getValue();
            if (value.getTvType() == tvType) {
                count++;
            }
        }
        return count;
    }

    public int getTvCount() {
        return tvDAO.getTV().size();
    }

    public Tv getTv(long id) {
        return tvDAO.getTV().get(id);
    }

    public boolean updateTv(Tv tv) {
        return tvDAO.updateTv(tv);
    }

    @Autowired
    public void setTvDAO(TvDAO tvDAO) {
        this.tvDAO = tvDAO;
    }
}
