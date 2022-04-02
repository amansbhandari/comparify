package ca.dal.comparify.compareitems;

import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import org.bson.conversions.Bson;
import java.util.Date;
import java.util.List;

import static ca.dal.comparify.mongo.MongoUtils.and;
import static ca.dal.comparify.mongo.MongoUtils.eq;

/**
 * @author Chanpreet Singh
 */
@Service
public class CompareItemRepository {

    public static final String ITEM_COLLECTION = "compareItems";

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @author Chanpreet Singh
     */
    public int save(CompareItemsModel model){
        return mongoRepository.insertOne(ITEM_COLLECTION, model, CompareItemsModel.class);
    }

    /**
     * @author Chanpreet Singh
     */
    public List<CompareItemsModel> fetchCompare(String ItemId, String date){
        Bson query;
        if(date==null)
            query = and(eq("productId", ItemId), eq("status", "verified"));
        else
            query = and(eq("productId", ItemId), eq("dateOfPurchase", DateUtils.parse(date)), eq("status", "verified"));
        List<CompareItemsModel> result = mongoRepository.find(ITEM_COLLECTION, query, CompareItemsModel.class);
        return result;
    }
}