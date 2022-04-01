package ca.dal.comparify.compareitems;

import ca.dal.comparify.alerts.AlertService;
import ca.dal.comparify.compareitems.CompareItemRepository;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.brand.BrandService;
import ca.dal.comparify.store.StoreService;
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
public class CompareItemService {

    @Autowired
    private CompareItemRepository compareItemRepository;
    @Autowired
    private BrandService brandService;
    @Autowired
    private StoreService storeService;

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

    /**
     * @return
     *
     * @author Chanpreet Singh
     */
    public ArrayList<Map<String, Object>> fetchComparisions(String ItemId, String date){
        List<CompareItemsModel> mongoResult = compareItemRepository.fetchCompare(ItemId, date);
        Map<String, Object> brandResults = this.getBrandDetails(mongoResult);
        Map<String, Object> storeResults = this.getStoreDetails(mongoResult);
        ArrayList<String> taken = new ArrayList<String>();
        ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for(CompareItemsModel each : mongoResult) {
            String uniqueKey = each.getBrandId() + each.getStoreId();
            if(!taken.contains(uniqueKey))
            {
                Map<String, Object> dataDict = new HashMap<String, Object>();
                dataDict.put("itemId", ItemId);
                dataDict.put("price", each.getPrice());
                dataDict.put("storeId", each.getStoreId());
                dataDict.put("storeDetails", storeResults.get(each.getStoreId()));
                dataDict.put("brandId", each.getBrandId());
                dataDict.put("brandDetails", brandResults.get(each.getBrandId()));
                result.add(dataDict);
                taken.add(uniqueKey);
            }
        }
        return result;
    }

    public Map<String, Object> getBrandDetails(List<CompareItemsModel> mongoResult){
        ArrayList<String> brandIds = new ArrayList<String>();
        for(CompareItemsModel each : mongoResult)
            brandIds.add(each.getBrandId());
        Map<String, Object> brandResults = brandService.getSpecificBrandDetails(brandIds);
        return brandResults;
    }

    public Map<String, Object> getStoreDetails(List<CompareItemsModel> mongoResult){
        ArrayList<String> storeIds = new ArrayList<String>();
        for(CompareItemsModel each : mongoResult)
            storeIds.add(each.getStoreId());
        Map<String, Object> storeResults = storeService.getSpecificStoreDetails(storeIds);
        return storeResults;
    }
}
