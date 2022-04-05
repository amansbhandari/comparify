package ca.dal.comparify.alerts;

import ca.dal.comparify.alerts.model.AlertRequestModel;
import ca.dal.comparify.alerts.model.AlertResponseModel;
import ca.dal.comparify.framework.exception.handler.GlobalExceptionHandler;
import ca.dal.comparify.utils.ResponseEntityUtils;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static ca.dal.comparify.constant.ApplicationConstant.EMPTY_STRING;
import static ca.dal.comparify.constant.ApplicationConstant.STATUS;
import static ca.dal.comparify.utils.ObjectUtils.write;
import static ca.dal.comparify.utils.ResponseEntityUtils.REQUEST_FAILED_SOMETHING_WENT_WRONG;
import static ca.dal.comparify.utils.ResponseEntityUtils.SUCCESS;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Harsh Shah
 */
@ContextConfiguration(classes = {AlertController.class, GlobalExceptionHandler.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(AlertController.class)
@Import(AlertController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AlertControllerTest {

    public static final String REQUEST_MAPPING = "/alert";
    public static final String DELETE_API = REQUEST_MAPPING + "/delete";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlertService alertService;

    private MockedStatic<SecurityUtils> securityUtils;

    public static Stream<Arguments> testCreateDatasource() {

        return Stream.of(
            Arguments.of(
                new AlertRequestModel(),
                CREATED.value(),
                0,
                Collections.singletonMap(STATUS, ResponseEntityUtils.CREATED)),
            Arguments.of(
                new AlertRequestModel(),
                BAD_REQUEST.value(),
                -1,
                Collections.singletonMap(STATUS, ResponseEntityUtils.INVALID_REQUEST)),
            Arguments.of(
                new AlertRequestModel(),
                UNPROCESSABLE_ENTITY.value(),
                -2,
                Collections.singletonMap(STATUS, ResponseEntityUtils.ENTITY_ALREADY_EXISTS))
        );
    }

    public static Stream<Arguments> testFetchDatasource() {

        return Stream.of(
            Arguments.of(OK.value(), emptyList(), emptyList()),
            Arguments.of(OK.value(), null, EMPTY_STRING)
        );
    }

    public static Stream<Arguments> testDeleteDatasource() {
        return Stream.of(
            Arguments.of(
                singletonMap("id", "sample"), OK.value(),
                true, singletonMap(STATUS, SUCCESS)),
            Arguments.of(
                singletonMap("id", "sample"), 512,
                false, singletonMap(STATUS, REQUEST_FAILED_SOMETHING_WENT_WRONG)));
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
     * @param request
     * @param expectedStatus
     * @param mockResponse
     * @param expected
     * @throws Exception
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testCreate() = {1}")
    @MethodSource("testCreateDatasource")
    void testCreate(AlertRequestModel request, int expectedStatus,
                    int mockResponse, Object expected) throws Exception {

        when(alertService.create(any(), any())).thenReturn(mockResponse);

        mockMvc.perform(post(REQUEST_MAPPING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(write(request)))
            .andExpect(status().is(expectedStatus))
            .andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                assertEquals(write(expected), content);

            });
    }

    /**
     * @param expectedStatus
     * @param mockResponse
     * @param expected
     * @throws Exception
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testFetch() = {0}")
    @MethodSource("testFetchDatasource")
    void testFetch(int expectedStatus, List<AlertResponseModel> mockResponse,
                   Object expected) throws Exception {

        when(alertService.fetch(any())).thenReturn(mockResponse);

        mockMvc.perform(get(REQUEST_MAPPING)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(expectedStatus))
            .andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                if (null == mockResponse) {
                    assertEquals(content, expected);
                } else {
                    assertEquals(content, write(expected));
                }
            });
    }

    /**
     * @param request
     * @param expectedStatus
     * @param mockResponse
     * @param expected
     * @throws Exception
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testDelete() = {1}")
    @MethodSource("testDeleteDatasource")
    void testDelete(Map<String, String> request, int expectedStatus,
                    boolean mockResponse, Object expected) throws Exception {

        when(alertService.delete(any(), any())).thenReturn(mockResponse);

        mockMvc.perform(post(DELETE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("id", request.get("id")))
            .andExpect(status().is(expectedStatus))
            .andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                assertEquals(write(expected), content);
            });

    }
}