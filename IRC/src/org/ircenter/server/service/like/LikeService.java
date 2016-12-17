package org.ircenter.server.service.like;

import org.ircenter.server.bean.like.LikeEntity;
import org.ircenter.server.bean.like.LikeQuantity;
import org.ircenter.server.bean.like.ServiceType;
import org.ircenter.server.dao.Database;
import org.ircenter.server.util.Locks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 18.05.12
 * Time: 1:35
 */
@Service
public class LikeService {
    public final static int DEFAULT_RECORDS = 10;

    private Database database;
    private Locks likeLocks = new Locks(1024);

    public LikeService() {
    }

    public enum QueryStatus {

        INSERTED,
        UPDATED_WITHOUT_ROW_EFFECT,
        UPDATED_WITH_ROW_EFFECT;
    }

    private static LikeEntity createLikeEntity(long userId, long clientId, ServiceType itemType, long itemId, byte like) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setUserId(userId);
        likeEntity.setClientId(clientId);
        likeEntity.setType(itemType);
        likeEntity.setContentId(itemId);
        likeEntity.setLike(like);
        likeEntity.setDate(new Date());
        return likeEntity;
    }

    public static String createKey(ServiceType type, long contentId) {
        return new StringBuilder().append(type).append(':').append(contentId).toString();
    }

    public static String createKey(LikeEntity entity) {
        return createKey(entity.getType(), entity.getContentId());
    }

    public QueryStatus likeContent(long userId, long clientId, ServiceType itemType, long itemId) {
        LikeEntity entity = createLikeEntity(userId, clientId, itemType, itemId, (byte) 1);
        return updateLikeItem(entity);
    }


    public List<LikeEntity> getLikeInfo(ServiceType type, long contentId) {
        return database.getLikeDAO().getLikeInfo(type, contentId);
    }

    public LikeQuantity getQuantityInfo(ServiceType type, long contentId) {
        return database.getLikeQuantityDAO().getQuantityInfo(type, contentId);
    }

    private QueryStatus updateLikeItem(LikeEntity likeEntity) {
        QueryStatus status;
        String key = createKey(likeEntity);
        synchronized (likeLocks.getLock(key)) {
            status = database.getLikeDAO().updateLikeItem(likeEntity);
            LikeQuantity likeQuantity = database.getLikeQuantityDAO().getQuantityInfo(likeEntity.getType(), likeEntity.getId());
            updateLikeQuantityObject(likeQuantity, likeEntity, status);
            database.getLikeQuantityDAO().updateLikeItem(likeEntity, status);
        }
        return status;
    }

    private void updateLikeEntityObject(List<LikeEntity> list, LikeEntity entity) {
        boolean found = false;
        for (Iterator<LikeEntity> it = list.iterator(); it.hasNext(); ) {
            LikeEntity likeEntity = it.next();
            if (likeEntity.sameRecord(entity) && likeEntity.getClientId() == entity.getClientId()) {
                found = true;
                if (likeEntity.getLike() != entity.getLike()) {
                    it.remove();
                    found = false;
                }
            }
        }

        if (!found) {
            list.add(0, entity);
        }
        while (list.size() >= DEFAULT_RECORDS) {
            list.remove(list.size() - 1);
        }
    }

    private void updateLikeQuantityObject(LikeQuantity likeQuantity, LikeEntity entity, QueryStatus status) {
        switch (status) {
            case INSERTED: {
                if (entity.getLike() > 0) {
                    likeQuantity.setLikeCount(likeQuantity.getLikeCount() + 1);
                } else {
                    likeQuantity.setDislikeCount(likeQuantity.getDislikeCount() + 1);
                }
                break;
            }
            case UPDATED_WITH_ROW_EFFECT: {
                likeQuantity.setLikeCount(likeQuantity.getLikeCount() + entity.getLike());
                likeQuantity.setDislikeCount(likeQuantity.getDislikeCount() - entity.getLike());
                break;
            }
            default:
                break;
        }
    }


    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }
}
