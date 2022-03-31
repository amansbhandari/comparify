package ca.dal.comparify.compareitems;

import ca.dal.comparify.compareitems.CompareItemRepository;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chanpreet Singh
 */
@Service
public class CompareItemService {

    @Autowired
    private CompareItemRepository compareItemRepository;

    /**
     * @param model
     * @return
     *
     * @author Chanpreet Singh
     */
    public int create(CompareItemsModel model){
        return compareItemRepository.save(CompareItemsModel.create(model));
    }
}
