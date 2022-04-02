package ca.dal.comparify.itemcategories;

import ca.dal.comparify.itemcategories.model.ItemCategoryModel;
import ca.dal.comparify.itemcategories.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemCategoryService {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    /**
     * @author Meghna Rupchandani
     */
    public ArrayList<Map> getAllCategories(){
        List<ItemCategoryModel> mongoResult = itemCategoryRepository.getAllCategories();
        ArrayList<Map> result = new ArrayList<>();
        for(ItemCategoryModel each:mongoResult) {
            Map<String, String> eachObj = new HashMap<String, String>(){{
                put("id", each.getId().toString());
                put("categoryName", each.getCategoryName());
            }};
            result.add(eachObj);
        }
        return result;
    }

    public Map<String, String> getItemCategory(String itemCategoryId){
        ItemCategoryModel mongoResult = itemCategoryRepository.getCategoryName(itemCategoryId);
        Map result = new HashMap(){{put("categoryName", mongoResult.getCategoryName());}};
        return result;
    }
}
