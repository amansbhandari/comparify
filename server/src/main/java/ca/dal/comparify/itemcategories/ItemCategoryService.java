package ca.dal.comparify.itemcategories;

import ca.dal.comparify.itemcategories.model.ItemCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemCategoryService {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    /**
     * @author Meghna Rupchandani
     * Sonar fix by Harsh Shah
     */
    public List<Map<String, String>> getAllCategories() {
        List<ItemCategoryModel> mongoResult = itemCategoryRepository.getAllCategories();
        List<Map<String, String>> result = new ArrayList<>();
        for (ItemCategoryModel each : mongoResult) {
            Map<String, String> eachObj = new HashMap<>();
            eachObj.put("id", each.getId().toString());
            eachObj.put("categoryName", each.getCategoryName());
            result.add(eachObj);
        }
        return result;
    }

    /**
     * @param itemCategoryId
     * @return
     * Sonar fix by Harsh Shah
     */
    public Map<String, String> getItemCategory(String itemCategoryId) {
        ItemCategoryModel mongoResult = itemCategoryRepository.getCategoryName(itemCategoryId);
        return Collections.singletonMap("categoryName", mongoResult.getCategoryName());
    }
}
