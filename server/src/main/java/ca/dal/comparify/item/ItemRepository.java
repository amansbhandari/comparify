package ca.dal.comparify.item;

import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Harsh Shah
 */
@Service
public class ItemRepository {

    public static final String ITEM_COLLECTION = "item";

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public int save(ItemModel model){
        return mongoRepository.insertOne(ITEM_COLLECTION, model, ItemModel.class);
    }

}
