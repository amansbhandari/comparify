package ca.dal.comparify.statistics;

import ca.dal.comparify.brand.BrandRepository;
import ca.dal.comparify.feedback.services.FeedbackService;
import ca.dal.comparify.item.ItemRepository;
import ca.dal.comparify.store.StoreRepository;
import ca.dal.comparify.user.repository.iam.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chanpreet Singh
 */
@Service
public class StatisticsService {
    @Autowired
    private UserDetailsRepository userService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ItemRepository itemRepository;

    public Map<String, Long> getTotalStats() {
        Map<String, Long> result = new HashMap<>();
        result.put("users", userService.getUserCount());
        result.put("feedback", feedbackService.getFeedbackCount());
        result.put("stores", storeRepository.getStoreCount());
        result.put("brand", brandRepository.getBrandCount());
        result.put("product", itemRepository.getItemCount());
        return result;
    }
}
