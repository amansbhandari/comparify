package ca.dal.comparify.compareitems;

import ca.dal.comparify.alerts.AlertService;
import ca.dal.comparify.compareitems.CompareItemRepository;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.brand.BrandService;
import ca.dal.comparify.item.ItemService;
import ca.dal.comparify.itemcategories.ItemCategoryService;
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
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemCategoryService itemCategoryService;

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
     * @author Chanpreet Singh
     */
    public Map<String, Object> fetchComparisions(String ItemId, String date){
        List<CompareItemsModel> mongoResult = compareItemRepository.fetchCompare(ItemId, date);
        Map<String, Object> brandResults = this.getBrandDetails(mongoResult);
        Map<String, Object> storeResults = this.getStoreDetails(mongoResult);
        Map<String, Object> itemResults = this.getItemDetails(ItemId);
        Map<String, String> categoryDetails = this.getCategoryDetails(String.valueOf(itemResults.get("categoryId")));
        ArrayList<String> taken = new ArrayList<String>();
        Map<String, Object> result = new HashMap(){
            {
                put("itemId", ItemId);
                put("itemCategoryId", itemResults.get("categoryId"));
                put("itemImage", itemResults.get("image"));
                put("itemName", itemResults.get("name"));
                put("itemDescription", itemResults.get("description"));
                put("itemCategoryName", categoryDetails.get("categoryName"));
            }};
        ArrayList storeBrandList = new ArrayList();
        for(CompareItemsModel each : mongoResult) {
            String uniqueKey = each.getBrandId() + each.getStoreId();
            if(!taken.contains(uniqueKey))
            {
                Map<String, Object> dataDict = new HashMap<String, Object>();
                if(each.getUnit() > 0)
                {
                    dataDict.put("pricePerQuantity", each.getPrice()/each.getUnit());
                    dataDict.put("storeId", each.getStoreId());
                    dataDict.put("storeDetails", storeResults.get(each.getStoreId()));
                    dataDict.put("brandId", each.getBrandId());
                    dataDict.put("brandDetails", brandResults.get(each.getBrandId()));
                    storeBrandList.add(dataDict);
                    taken.add(uniqueKey);
                }
            }
        }
        result.put("storeBrandList", storeBrandList);
        return result;
    }
    /**
     * @author Chanpreet Singh
     */
    public Map<String, Object> getBrandDetails(List<CompareItemsModel> mongoResult){
        ArrayList<String> brandIds = new ArrayList<String>();
        for(CompareItemsModel each : mongoResult)
            brandIds.add(each.getBrandId());
        Map<String, Object> brandResults = brandService.getSpecificBrandDetails(brandIds);
        return brandResults;
    }

    /**
     * @author Chanpreet Singh
     */
    public Map<String, Object> getStoreDetails(List<CompareItemsModel> mongoResult){
        ArrayList<String> storeIds = new ArrayList<String>();
        for(CompareItemsModel each : mongoResult)
            storeIds.add(each.getStoreId());
        Map<String, Object> storeResults = storeService.getSpecificStoreDetails(storeIds);
        return storeResults;
    }

    /**
     * @author Chanpreet Singh
     */
    public Map<String, Object> getItemDetails(String itemId) {
        return itemService.getItemDetails(itemId);
    }

    /**
     * @author Chanpreet Singh
     */
    public Map<String, String> getCategoryDetails(String categoryId) {
        if(categoryId != null)
            return itemCategoryService.getItemCategory(categoryId);
        return new HashMap(){{put("categoryName", null);}};
    }
}