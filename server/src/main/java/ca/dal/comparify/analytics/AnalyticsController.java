package ca.dal.comparify.analytics;

import ca.dal.comparify.model.HashModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static ca.dal.comparify.utils.StringUtils.isEmpty;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

/**
 * @author Harsh Shah
 */
@RequestMapping("/analytics")
@RestController
public class AnalyticsController {

    private static final int START_MONTH = 1;
    private static final int END_MONTH = 12;

    @Autowired
    private AnalyticsService analyticsService;

    /**
     * @param itemId
     * @return
     * @author Harsh Shah
     */
    @GetMapping("")
    public List<HashModel> getPriceTrend(@RequestParam("item_id") String itemId) {
        if (isEmpty(itemId)) {
            return emptyList();
        }
        return analyticsService.getPriceTrend(itemId);
    }

    /**
     * @param itemId
     * @return
     * @author Harsh Shah
     */
    @GetMapping("/brands")
    public HashModel getPriceTrendForDifferentBrands(@RequestParam("item_id") String itemId) {
        if (isEmpty(itemId)) {
            return new HashModel();
        }
        return analyticsService.getPriceTrendForDifferentBrands(itemId);
    }

    /**
     * @param date
     * @return
     * @author Harsh Shah
     */
    @GetMapping("/categories")
    public List<HashModel> getProductCountForCategory(@RequestParam("date") String date) {
        if (isEmpty(date)) {
            return emptyList();
        }
        return analyticsService.getProductCountForCategory(date);
    }

    /**
     * @param month
     * @return
     * @author Harsh Shah
     */
    @GetMapping("/monthly")
    public Map<String, Double> getMonthlyTotalPurchaseOfItemCategory(@RequestParam("month") int month) {
        if (month < START_MONTH || month > END_MONTH) {
            return emptyMap();
        }
        return analyticsService.getMonthlyTotalPurchaseOfItemCategory(month);
    }
}
