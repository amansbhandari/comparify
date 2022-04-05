package ca.dal.comparify.statistics;

import ca.dal.comparify.brand.BrandRepository;
import ca.dal.comparify.feedback.services.FeedbackService;
import ca.dal.comparify.item.ItemRepository;
import ca.dal.comparify.store.StoreRepository;
import ca.dal.comparify.user.repository.iam.UserDetailsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Chanpreet Singh
 */
@SpringBootTest
@ActiveProfiles("test")
public class TestStatisticsService {
    @MockBean
    private BrandRepository brandRepository;
    @MockBean
    private UserDetailsRepository userRepository;
    @MockBean
    private FeedbackService feedbackRepository;
    @MockBean
    private StoreRepository storeRepository;
    @MockBean
    private ItemRepository itemRepository;
    @Autowired
    private StatisticsService statisticsService;

    @Test
    void getTotalStats(){
        when(brandRepository.getBrandCount()).thenReturn(1L);
        when(userRepository.getUserCount()).thenReturn(1L);
        when(feedbackRepository.getFeedbackCount()).thenReturn(1L);
        when(storeRepository.getStoreCount()).thenReturn(1L);
        when(itemRepository.getItemCount()).thenReturn(1L);

        Map expectedResult = statisticsService.getTotalStats();
        assertEquals(expectedResult.get("users"), 1L);
        assertEquals(expectedResult.get("feedback"), 1L);
        assertEquals(expectedResult.get("stores"), 1L);
        assertEquals(expectedResult.get("brand"), 1L);
        assertEquals(expectedResult.get("product"), 1L);
    }
}
