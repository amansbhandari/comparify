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
     *
     * @author Chanpreet Singh
     */
    public int create(ItemRequestModel model, String createdBy){
        if(!findItem(model.getName()))
            return itemRepository.save(ItemModel.create(model, createdBy));
        else
            return -2;
    }

    /**
     * @author Chanpreet Singh
     */
    public ArrayList<Map> getAllItems(){
        List<ItemModel> mongoResult = itemRepository.getAll();
        ArrayList<Map> result = new ArrayList<>();
        for(ItemModel each:mongoResult) {
            Map<String, String> eachObj = new HashMap<String, String>(){{
                put("id", each.getId());
                put("itemName", each.getName());
                put("itemDescription", each.getDescription());
            }};
            result.add(eachObj);
        }
        return result;
    }

    public boolean findItem(String itemName){
        boolean status = false;
        ItemModel mongoResult = itemRepository.searchItemName(itemName);
        if(mongoResult != null)
            status = true;
        return status;
    }

}
