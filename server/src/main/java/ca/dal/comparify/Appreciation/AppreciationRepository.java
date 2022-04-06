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

    private final String username = "username";
    private final String brandId = "brandId";
    private final String storeId = "storeId";
    private final String productId = "productId";

    private final String compareItemsCollection = "compareItems";
    private static final String userDetailsCollection = "user";
    private static final String userId = "userId";

    private String dateOfPurchase = "dateOfPurchase";

    /**
     * @param saveUserAppreciation
     * @return
     * @author Aman Singh Bhandari
     */
    public Boolean saveUserAppreciation(AppreciationModel appreciationModel) {
        Bson query = eq(username, appreciationModel.getUsername());
        Bson[] values = {set(AppreciationModel.POINTS_KEY,appreciationModel.getPoints()), set(AppreciationModel.TYPE_KEY, appreciationModel.getType())};
        Boolean result = mongoRepository.updateOne(userDetailsCollection,query, values);

        return result;
    }

    public List<CompareItemsModel> billItemsOfSameProducts(CompareItemsModel comparifyItemsModel)
    {
        Bson query = and(eq(brandId,comparifyItemsModel.getBrandId()),
                            eq(storeId,comparifyItemsModel.getStoreId()),
                                eq(productId, comparifyItemsModel.getProductId()),
                                    eq(dateOfPurchase, comparifyItemsModel.getDateOfPurchase()));

        List<CompareItemsModel> list = mongoRepository.find(compareItemsCollection, query, CompareItemsModel.class);

        return list;
    }
}
