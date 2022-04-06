package ca.dal.comparify.analytics;

import ca.dal.comparify.framework.exception.handler.GlobalExceptionHandler;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.utils.SecurityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static ca.dal.comparify.constant.ApplicationConstant.EMPTY_STRING;
import static ca.dal.comparify.utils.ObjectUtils.write;
import static ca.dal.comparify.utils.StringUtils.valueOf;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {AnalyticsController.class, GlobalExceptionHandler.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(AnalyticsController.class)
@Import(AnalyticsController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnalyticsControllerTest {

    private static final String REQUEST_MAPPING = "/analytics";
    private static final String PRICE_TREND_FOR_DIFFERENT_BRANDS = REQUEST_MAPPING + "/brands";
    private static final String PRODUCT_COUNT_FOR_CATEGORY = REQUEST_MAPPING + "/categories";
    private static final String MONTHLY_TOTAL_PURCHASE_OF_ITEM_CATEGORY = REQUEST_MAPPING + "/monthly";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnalyticsService analyticsService;

    private MockedStatic<SecurityUtils> securityUtils;

    public static Stream<Arguments> testGetProductCountForCategoryDatasource() {

        return Stream.of(
            Arguments.of(
                "04/04/2022",
                emptyList(),
                OK.value(),
                emptyList()),
            Arguments.of(
                "04/04/2022",
                singletonList(singletonMap("Key", "Sample")),
                OK.value(),
                singletonList(singletonMap("Key", "Sample"))),
            Arguments.of(EMPTY_STRING, emptyList(), OK.value(), emptyList())
        );
    }


    public static Stream<Arguments> testGetMonthlyTotalPurchaseOfItemCategoryDatasource() {

        return Stream.of(
            Arguments.of(
                1,
                singletonMap("Key", 1.5),
                OK.value(),
                singletonMap("Key", 1.5)),
            Arguments.of(
                0,
                singletonMap("Key", 1.5),
                OK.value(),
                emptyMap()),
            Arguments.of(15,
                singletonMap("Key", 1.5),
                OK.value(),
                emptyMap()));
    }

    public static Stream<Arguments> testGetPriceTrendDatasource() {

        return Stream.of(
            Arguments.of(
                "Sample_ID",
                emptyList(),
                OK.value(),
                emptyList()),
            Arguments.of(
                "Sample_ID",
                singletonList(singletonMap("Key", "Sample")),
                OK.value(),
                singletonList(singletonMap("Key", "Sample"))),
            Arguments.of(EMPTY_STRING, emptyList(), OK.value(), emptyList())
        );
    }

    public static Stream<Arguments> testGetPriceTrendForDifferentBrandsDatasource() {

        HashModel model = new HashModel();
        model.put("Key", 1.5);

        return Stream.of(
            Arguments.of("Sample_ID", model, OK.value(), model),
            Arguments.of("Sample_ID", model, OK.value(), model),
            Arguments.of(EMPTY_STRING, model, OK.value(), new HashModel()));
    }

    @BeforeAll
    void setUpForTestSuite() {
        securityUtils = Mockito.mockStatic(SecurityUtils.class);
        securityUtils.when(() -> SecurityUtils.getPrincipal(any()))
            .thenReturn("dummy_id");
    }

    @AfterAll
    void tearDownTestSuite() {
        securityUtils.close();
    }

    /**
     * @param mockResponse
     * @param expectedStatus
     * @param expected
     * @throws Exception
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testGetProductCountForCategory() = {1}")
    @MethodSource("testGetProductCountForCategoryDatasource")
    void testGetProductCountForCategory(String request, List<HashModel> mockResponse,
                                        int expectedStatus, List<HashModel> expected) throws Exception {

        when(analyticsService.getProductCountForCategory(any())).thenReturn(mockResponse);

        mockMvc.perform(get(PRODUCT_COUNT_FOR_CATEGORY)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("date", request))
            .andExpect(status().is(expectedStatus))
            .andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                assertEquals(write(expected), content);
            });
    }

    /**
     * @param request
     * @param mockResponse
     * @param expectedStatus
     * @param expected
     * @throws Exception
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testGetMonthlyTotalPurchaseOfItemCategory() = {1}")
    @MethodSource("testGetMonthlyTotalPurchaseOfItemCategoryDatasource")
    void testGetMonthlyTotalPurchaseOfItemCategory(int request, Map<String, Double> mockResponse,
                                                   int expectedStatus, Map<String, Double> expected) throws Exception {

        when(analyticsService.getMonthlyTotalPurchaseOfItemCategory(anyInt())).thenReturn(mockResponse);

        mockMvc.perform(get(MONTHLY_TOTAL_PURCHASE_OF_ITEM_CATEGORY)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("month", valueOf(request)))
            .andExpect(status().is(expectedStatus))
            .andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                assertEquals(write(expected), content);
            });
    }

    @ParameterizedTest(name = "{index}: testGetPriceTrend() = {1}")
    @MethodSource("testGetPriceTrendDatasource")
    void testGetPriceTrend(String request, List<HashModel> mockResponse,
                           int expectedStatus, List<HashModel> expected) throws Exception {

        when(analyticsService.getPriceTrend(any())).thenReturn(mockResponse);

        mockMvc.perform(get(REQUEST_MAPPING)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("item_id", request))
            .andExpect(status().is(expectedStatus))
            .andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                assertEquals(write(expected), content);
            });
    }

    @ParameterizedTest(name = "{index}: testGetPriceTrendForDifferentBrands() = {1}")
    @MethodSource("testGetPriceTrendForDifferentBrandsDatasource")
    void testGetPriceTrendForDifferentBrands(String request, HashModel mockResponse,
                                             int expectedStatus, HashModel expected) throws Exception {

        when(analyticsService.getPriceTrendForDifferentBrands(any())).thenReturn(mockResponse);

        mockMvc.perform(get(PRICE_TREND_FOR_DIFFERENT_BRANDS)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("item_id", request))
            .andExpect(status().is(expectedStatus))
            .andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                assertEquals(write(expected), content);
            });
    }

    @Configuration
    @EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
    static class ContextConfiguration {
    }
}

