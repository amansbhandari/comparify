package ca.dal.comparify.analytics;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.itemcategories.ItemCategoryService;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Harsh Shah
 */
@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * @param itemId
     * @return
     * @author Harsh Shah
     */
    public List<HashModel> getPriceTrend(String itemId) {
        return analyticsRepository.getPriceTrend(itemId);
    }

    /**
     * @param itemId
     * @return
     * @author Harsh Shah
     */
    public HashModel getPriceTrendForDifferentBrands(String itemId) {
        HashModel result = analyticsRepository.getPriceTrendForDifferentBrands(itemId);

        List<Map<String, Object>> brands = (List<Map<String, Object>>) result.get("brands");
        List<String> brand = brands.stream().map(itr -> (String) itr.get("_id")).collect(Collectors.toList());

        List<BrandModel> brandInfo = analyticsRepository.getBrands(brand);
        result.put("brands", brandInfo);
        return result;
    }


    /**
     * @param date
     * @return
     * @author Harsh Shah
     */
    public List<HashModel> getProductCountForCategory(String date) {
        return analyticsRepository.getProductCountForCategory(DateUtils.parse(date));
    }


    /**
     * @param month
     * @return
     * @author Harsh Shah
     */
    public Map<String, Double> getMonthlyTotalPurchaseOfItemCategory(int month) {

        List<HashModel> resultSet = analyticsRepository.getMonthlyTotalPurchaseOfItemCategory(month);

        Map<String, Double> stats = new HashMap<>();
        for (Map<String, Object> resut : resultSet) {
            stats.put((String) resut.get("_id"), (Double) resut.get("totalPurchase"));
        }

        Map<String, String> categories = analyticsRepository.getAllCategories().stream()
            .collect(Collectors.toMap(itr -> itr.getId().toString(), itr -> itr.getCategoryName()));

        Map<String, Double> output = new HashMap<>();

        for (Map.Entry<String, String> entry : categories.entrySet()) {
            output.put(entry.getValue(), stats.getOrDefault(entry.getKey(), 0.0));
        }

        return output;
    }


}
