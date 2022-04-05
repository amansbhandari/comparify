package ca.dal.comparify.item;

import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.item.model.ItemRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chanpreet Singh
 */
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * @param model
     * @return
     * @author Chanpreet Singh
     */
    public int create(ItemRequestModel model, String createdBy) {
        if (!findItem(model.getName()))
            return itemRepository.save(ItemModel.create(model, createdBy));
        else
            return -2;
    }

    /**
     * @author Chanpreet Singh
     * Sonar fix by Harsh Shah
     */
    public List<Map<String, String>> getAllItems() {
        List<ItemModel> mongoResult = itemRepository.getAll();
        List<Map<String, String>> result = new ArrayList<>();
        for (ItemModel each : mongoResult) {
            Map<String, String> eachObj = new HashMap<>();
            eachObj.put("id", each.getId());
            eachObj.put("itemName", each.getName());
            eachObj.put("itemDescription", each.getDescription());
            result.add(eachObj);
        }
        return result;
    }

    /**
     * @author Chanpreet Singh
     */
    public boolean findItem(String itemName) {
        boolean status = false;
        ItemModel mongoResult = itemRepository.searchItemName(itemName);
        if (mongoResult != null)
            status = true;
        return status;
    }

    /**
     * @author Chanpreet Singh
     * Sonar fix by Harsh Shah
     */
    public Map<String, Object> getItemDetails(String itemId) {
        ItemModel mongoResult = itemRepository.findOneItem(itemId);
        Map<String, Object> result = new HashMap();
        result.put("name", mongoResult.getName());
        result.put("description", mongoResult.getDescription());
        result.put("image", mongoResult.getDefaultImage());
        result.put("categoryId", mongoResult.getItemCategoryId());

        return result;
    }

    /**
     * @param id
     * @return
     * @author Harsh Shah
     */
    public ItemModel findItemById(String id) {
        return itemRepository.findItemById(id);
    }
}
