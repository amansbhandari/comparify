package ca.dal.comparify.alerts;

import ca.dal.comparify.alerts.model.AlertModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Harsh Shah
 */
@Service
public class AlertRepository {

    public static final String ALERT_COLLECTION = "alert";

    @Autowired
    private MongoRepository mongoRepository;

    public int save(AlertModel model){
        return mongoRepository.insertOne(ALERT_COLLECTION, model, AlertModel.class);
    }
}
