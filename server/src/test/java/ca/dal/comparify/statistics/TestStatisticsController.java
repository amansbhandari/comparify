package ca.dal.comparify.statistics;

import ca.dal.comparify.searchProduct.SearchProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Chanpreet Singh
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TestStatisticsController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SearchProductController searchProductController;
    @MockBean
    private StatisticsService statisticsService;

    @Test
    public void testGetStats() throws Exception {
        Map dataDict = new HashMap(){{
            put("users", 1L);
            put("feedback", 1L);
            put("stores", 1L);
            put("brand", 1L);
            put("product", 1L);
        }};
        when(statisticsService.getTotalStats()).thenReturn(dataDict);
        this.mockMvc.perform(get("/statistics/"))
                .andExpect(status().isOk());
    }
}
