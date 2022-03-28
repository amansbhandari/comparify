package ca.dal.comparify.alerts;

import ca.dal.comparify.alerts.model.AlertModel;
import ca.dal.comparify.alerts.model.AlertResponseModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static ca.dal.comparify.mongo.MongoUtils.*;

/**
 * @author Harsh Shah
 */
@Service
public class AlertRepository {

    public static final String ALERT_COLLECTION = "alert";

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public int save(AlertModel model){
        return mongoRepository.insertOne(ALERT_COLLECTION, model, AlertModel.class);
    }


    /**
     * @param userIdentifier
     * @return
     *
     * @author Harsh Shah
     */
    public List<AlertResponseModel> getAlerts(String userIdentifier) {
        List<Bson> pipeline = Arrays.asList(

            match(new Document("audit.created_by", userIdentifier)),

            lookup("item", "item.entity_id", "_id",
                Arrays.asList(new Document("$project",
                new Document("audit", 0L)
                    .append("_id", 0L))), "item"),

            unwind("$item"),

            lookup("brand", "brand.entity_id", "_id",
                Arrays.asList(new Document("$project",
                    new Document("audit", 0L)
                        .append("_id", 0L))), "brand"),

            unwind("$brand"));

        return mongoRepository.aggregate(ALERT_COLLECTION, pipeline, AlertResponseModel.class);
    }
}
