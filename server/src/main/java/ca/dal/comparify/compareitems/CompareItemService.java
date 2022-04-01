package ca.dal.comparify.compareitems;

import ca.dal.comparify.alerts.AlertService;
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

    @Autowired
    private AlertService alertService;

    /**
     * @param model
     * @return
     *
     * @author Chanpreet Singh
     */
    public int create(CompareItemsModel model){

        int status = compareItemRepository.save(CompareItemsModel.create(model));

        if(status == 0){
            alertService.trigger(model.getBrandId(), model.getProductId());
        }

        return status;

    }
}
