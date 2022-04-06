package ca.dal.comparify.alerts;

import ca.dal.comparify.alerts.model.AlertModel;
import ca.dal.comparify.alerts.model.AlertResponseModel;
import ca.dal.comparify.alerts.model.AlertTypeEnum;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static ca.dal.comparify.mongo.MongoUtils.*;
import static java.util.Arrays.asList;

/**
 * @author Harsh Shah
 */
@Service
public class AlertRepository {

    private static final String ALERT_COLLECTION = "alert";

    private static final String FIELD_BRAND = "brand.entity_id";
    private static final String FIELD_ITEM = "item.entity_id";
    private static final String TYPE = "type";

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @param model
     * @return
     * @author Harsh Shah
     */
    public int save(AlertModel model) {
        return mongoRepository.insertOne(ALERT_COLLECTION, model, AlertModel.class);
    }


    /**
     * @param userIdentifier
     * @return
     * @author Harsh Shah
     */
    public List<AlertResponseModel> getAlerts(String userIdentifier) {
        List<Bson> pipeline = asList(

            match(new Document("audit.created_by", userIdentifier)),

            lookup("item", FIELD_ITEM, "_id",
                asList(new Document("$project",
                    new Document("audit", 0L)
                        .append("_id", 0L))), "item"),

            unwind("$item"),

            lookup("brand", FIELD_BRAND, "_id",
                asList(new Document("$project",
                    new Document("audit", 0L)
                        .append("_id", 0L))), "brand"),

            unwind("$brand"));

        return mongoRepository.aggregate(ALERT_COLLECTION, pipeline, AlertResponseModel.class);
    }

    /**
     * @param userId
     * @param id
     * @return
     * @author Harsh Shah
     */
    public boolean delete(String userId, String id) {

        return mongoRepository.deleteOne(ALERT_COLLECTION, and(eq("_id", id),
            eq("audit.created_by", userId)));

    }

    /**
     * @param itemId
     * @param brandId
     * @return
     * @author Harsh Shah
     */
    public HashModel checkForAlerts(String brandId, String itemId) {

        String productInformationAvailable = AlertTypeEnum.PRODUCT_INFORMATION_AVAILABLE.getValue();
        String priceRange = AlertTypeEnum.PRICE_RANGE.getValue();
        String priceDrop = AlertTypeEnum.PRICE_DROP.getValue();

        List<Bson> pipeline = asList(match(new Document(FIELD_BRAND, brandId)
                .append(FIELD_ITEM, itemId)),
            facet(new Document(priceDrop.toLowerCase(),
                asList(match(new Document(TYPE, priceDrop))))
                .append(priceRange.toLowerCase(),
                    asList(match(new Document(TYPE, priceRange))))
                .append(productInformationAvailable.toLowerCase(),
                    asList(match(new Document(TYPE, productInformationAvailable)))
                )));

        return mongoRepository.aggregateOne(ALERT_COLLECTION, pipeline, HashModel.class);
    }

    /**
     * @param alertId
     * @return
     * @author Harsh Shah
     */
    public AlertModel fetchAlertById(String alertId) {
        return mongoRepository.findOne(ALERT_COLLECTION, eq("_id", alertId), AlertModel.class);
    }
}
