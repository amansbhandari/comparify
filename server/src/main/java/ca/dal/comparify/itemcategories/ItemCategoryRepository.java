package ca.dal.comparify.itemcategories;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.itemcategories.model.ItemCategoryModel;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static ca.dal.comparify.mongo.MongoUtils.eq;

@Service
public class ItemCategoryRepository {
    public static final String CATEGORY_COLLECTION = "itemCategories";

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @return
     *
     * @author Meghna Rupchandani
     */
    public List<ItemCategoryModel> getAllCategories(){
        List<ItemCategoryModel> result = mongoRepository.find(CATEGORY_COLLECTION, Filters.empty(), ItemCategoryModel.class);
        return result;
    }

    /**
     * @author Chanpreet Singh
     */
    public ItemCategoryModel getCategoryName(String itemCategoryId){
        ItemCategoryModel result = mongoRepository.findOne(CATEGORY_COLLECTION, eq("_id", new ObjectId(itemCategoryId)), ItemCategoryModel.class);
        return result;
    }
}