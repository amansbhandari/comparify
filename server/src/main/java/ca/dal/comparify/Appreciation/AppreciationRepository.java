package ca.dal.comparify.Appreciation;

import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.model.AppreciationModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static ca.dal.comparify.mongo.MongoUtils.*;

/**
 * @author amansinghbhandari
 */
@Service
public class AppreciationRepository {

    @Autowired
    private MongoRepository mongoRepository;

    private final String USERNAME = "username";
    private final String BRAND_ID= "brandId";
    private final String STORE_ID= "storeId";
    private final String PRODUCT_ID= "productId";

    private final String COMPARE_ITEMS_COLLECTION = "compareItems";
    public static final String USER_DETAILS_COLLECTION = "user";
    public static final String USER_ID = "userId";
    private String DATE_OF_PURCHASE= "dateOfPurchase";

    /**
     * @param saveUserAppreciation
     * @return
     * @author Aman Singh Bhandari
     */
    public Boolean saveUserAppreciation(AppreciationModel appreciationModel) {
        Bson query = eq(USERNAME, appreciationModel.getUsername());
        Bson[] values = {set(AppreciationModel.POINTS,appreciationModel.getPoints()), set(AppreciationModel.TYPE, appreciationModel.getType())};
        Boolean result = mongoRepository.updateOne(USER_DETAILS_COLLECTION,query, values);

        return result;
    }

    public List<CompareItemsModel> billItemsOfSameProducts(CompareItemsModel comparifyItemsModel)
    {
        Bson query = and(eq(BRAND_ID,comparifyItemsModel.getBrandId()),
                            eq(STORE_ID,comparifyItemsModel.getStoreId()),
                                eq(PRODUCT_ID, comparifyItemsModel.getProductId()),
                                    eq(DATE_OF_PURCHASE, comparifyItemsModel.getDateOfPurchase()));

        List<CompareItemsModel> list = mongoRepository.find(COMPARE_ITEMS_COLLECTION, query, CompareItemsModel.class);

        return list;
    }
}
