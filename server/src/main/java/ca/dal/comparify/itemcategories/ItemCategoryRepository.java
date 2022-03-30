package ca.dal.comparify.itemcategories;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.itemcategories.model.ItemCategoryModel;
import com.mongodb.client.model.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemCategoryRepository {
    public static final String BRAND_COLLECTION = "itemCategories";

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @return
     *
     * @author Meghna Rupchandani
     */
    public List<ItemCategoryModel> getAllCategories(){
        List<ItemCategoryModel> result = mongoRepository.find(BRAND_COLLECTION, Filters.empty(), ItemCategoryModel.class);
        return result;
    }
}