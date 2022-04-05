package ca.dal.comparify.compareitems;

import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.conversions.Bson;
import java.util.List;

import static ca.dal.comparify.mongo.MongoUtils.*;
import static ca.dal.comparify.mongo.MongoUtils.set;

/**
 * @author Chanpreet Singh
 */
@Service
public class CompareItemRepository {

    public static final String ITEM_COLLECTION = "compareItems";
    private final String brandId = "brandId";
    private final String storeId = "storeId";
    private final String productId = "productId";
    private String dateOfPurchase = "dateOfPurchase";
    private final String price = "price";

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
            query = and(eq(productId, ItemId), eq("status", "verified"));
        else
            query = and(eq(productId, ItemId), eq("dateOfPurchase", DateUtils.parse(date)), eq("status", "verified"));
        List<CompareItemsModel> result = mongoRepository.find(ITEM_COLLECTION, query, CompareItemsModel.class);
        return result;
    }

    /**
     * @author aman singh bhandari
     */
    public List<CompareItemsModel> getSameItems(CompareItemsModel comparifyItemsModel)
    {
        Bson query = and(eq(brandId,comparifyItemsModel.getBrandId()),
                eq(storeId,comparifyItemsModel.getStoreId()),
                eq(productId, comparifyItemsModel.getProductId()),
                eq(dateOfPurchase, comparifyItemsModel.getDateOfPurchase()),
                eq(price, comparifyItemsModel.getPrice()));

        List<CompareItemsModel> list = mongoRepository.find(ITEM_COLLECTION, query, CompareItemsModel.class);

        return list;
    }

    /**
     * @param compareItemsModel
     * @return
     * @author Aman Singh Bhandari
     */
    public Boolean updateItem(CompareItemsModel compareItemsModel) {
        Bson query = eq(CompareItemsModel.ID, compareItemsModel.getId());
        Bson[] values = {set(CompareItemsModel.STATUS_KEY,compareItemsModel.getStatus())};
        Boolean result = mongoRepository.updateOne(ITEM_COLLECTION,query, values);

        return result;
    }
}