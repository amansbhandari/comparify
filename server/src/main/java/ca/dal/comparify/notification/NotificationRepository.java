package ca.dal.comparify.notification;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.notification.model.NotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static ca.dal.comparify.mongo.MongoUtils.eq;

/**
 * @author Harsh Shah
 */
@Service
public class NotificationRepository {

    public static final String NOTIFICATION_COLLECTION = "notification";

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @param notificationModel
     * @return
     *
     * @author Harsh Shah
     */
    public int add(NotificationModel notificationModel) {
        return mongoRepository.insertOne(NOTIFICATION_COLLECTION, notificationModel, NotificationModel.class);
    }

    /**
     * @param userId
     * @return
     *
     * @author Harsh Shah
     */
    public List<NotificationModel> fetch(String userId) {
        return mongoRepository.find(NOTIFICATION_COLLECTION,
            eq("for", userId), NotificationModel.class);
    }
}
