package ca.dal.comparify.item;

import ca.dal.comparify.item.model.ItemModel;
import ca.dal.comparify.item.model.ItemRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Harsh Shah
 */
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public int create(ItemRequestModel model, String createdBy){
        return itemRepository.save(ItemModel.create(model, createdBy));
    }
}
