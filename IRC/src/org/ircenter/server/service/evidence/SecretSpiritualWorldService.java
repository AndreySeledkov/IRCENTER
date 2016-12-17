package org.ircenter.server.service.evidence;

import org.ircenter.server.MUtil;
import org.ircenter.server.bean.evidence.SecretSpiritualWorld;
import org.ircenter.server.dao.evidence.SecretSpiritualWorldDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 16.06.12
 * Time: 16:16
 */
@Service
public class SecretSpiritualWorldService {
    private SecretSpiritualWorldDAO secretSpiritualWorldDAO;

    public boolean saveSecretSpiritualWorld(SecretSpiritualWorld minutesToGod) {
        return secretSpiritualWorldDAO.saveSecretSpiritualWorld(minutesToGod);
    }

    public List<SecretSpiritualWorld> getSecretSpiritualWorld(int start, int limit) {
        List<SecretSpiritualWorld> list = new ArrayList<SecretSpiritualWorld>(secretSpiritualWorldDAO.getSecretSpiritualWorld().values());
        return MUtil.getSafeSubList(list, start, start + limit);
    }

    public SecretSpiritualWorld getSecretSpiritualWorld(long id) {
        return secretSpiritualWorldDAO.getSecretSpiritualWorld().get(id);
    }

    public void removeSecretSpiritualWorld(long id) {
        secretSpiritualWorldDAO.removeSecretSpiritualWorld(id);
    }

    public int getSecretSpiritualWorldCount() {
        return secretSpiritualWorldDAO.getSecretSpiritualWorld().size();
    }

    public boolean updateSecretSpiritualWorld(SecretSpiritualWorld secretSpiritualWorld) {
        return secretSpiritualWorldDAO.updateSecretSpiritualWorld(secretSpiritualWorld);
    }

    public void setViewCount(SecretSpiritualWorld secretSpiritualWorld, int count) {
        secretSpiritualWorldDAO.setViewCount(secretSpiritualWorld, count);
    }

    @Autowired
    public void setSecretSpiritualWorldDAO(SecretSpiritualWorldDAO secretSpiritualWorldDAO) {
        this.secretSpiritualWorldDAO = secretSpiritualWorldDAO;
    }
}
