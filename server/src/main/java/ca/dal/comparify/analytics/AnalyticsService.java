package ca.dal.comparify.analytics;

import ca.dal.comparify.brand.model.BrandModel;
import ca.dal.comparify.model.HashModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * @param itemId
     * @return
     *
     * @author Harsh Shah
     */
    public List<HashModel> getPriceTrend(String itemId){
        return analyticsRepository.getPriceTrend(itemId);
    }

    /**
     * @param itemId
     * @return
     *
     * @author Harsh Shah
     */
    public HashModel getPriceTrendForDifferentBrands(String itemId){
        HashModel result = analyticsRepository.getPriceTrendForDifferentBrands(itemId);

        List<Map<String, Object>> brands = (List<Map<String, Object>>) result.get("brands");
        List<String> brand = brands.stream().map(itr -> (String) itr.get("_id")).collect(Collectors.toList());

        List<BrandModel> brandInfo = analyticsRepository.getBrands(brand);

        result.put("brands", brandInfo);

        return result;

    }

}
