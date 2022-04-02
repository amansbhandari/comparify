package ca.dal.comparify.analytics;

import ca.dal.comparify.model.HashModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     *
     * @author Harsh Shah
     */
    @GetMapping("")
    public List<HashModel> getPriceTrend(@RequestParam("item_id") String itemId){
        return analyticsService.getPriceTrend(itemId);
    }

    @GetMapping("/brands")
    public HashModel getPriceTrendForDifferentBrands(@RequestParam("item_id") String itemId){
        return analyticsService.getPriceTrendForDifferentBrands(itemId);
    }

}
