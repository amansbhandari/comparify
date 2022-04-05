package ca.dal.comparify.analytics;

import ca.dal.comparify.model.HashModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Harsh Shah
 */
@RequestMapping("/analytics")
@RestController
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    /**
     * @param itemId
     * @return
     * @author Harsh Shah
     */
    @GetMapping("")
    public List<HashModel> getPriceTrend(@RequestParam("item_id") String itemId) {
        return analyticsService.getPriceTrend(itemId);
    }

    /**
     * @param itemId
     * @return
     * @author Harsh Shah
     */
    @GetMapping("/brands")
    public HashModel getPriceTrendForDifferentBrands(@RequestParam("item_id") String itemId) {
        return analyticsService.getPriceTrendForDifferentBrands(itemId);
    }

    /**
     * @param date
     * @return
     * @author Harsh Shah
     */
    @GetMapping("/categories")
    public List<HashModel> getProductCountForCategory(@RequestParam("date") String date) {
        return analyticsService.getProductCountForCategory(date);
    }

    /**
     * @param month
     * @return
     * @author Harsh Shah
     */
    @GetMapping("/monthly")
    public Map<String, Double> getMonthlyTotalPurchaseOfItemCategory(@RequestParam("month") int month) {
        return analyticsService.getMonthlyTotalPurchaseOfItemCategory(month);
    }
}
