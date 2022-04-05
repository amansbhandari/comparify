package ca.dal.comparify.notification;

import ca.dal.comparify.framework.exception.handler.GlobalExceptionHandler;
import ca.dal.comparify.notification.model.NotificationModel;
import ca.dal.comparify.notification.model.NotificationReceiverModel;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static ca.dal.comparify.constant.ApplicationConstant.EMPTY_STRING;
import static ca.dal.comparify.constant.ApplicationConstant.STATUS;
import static ca.dal.comparify.utils.ObjectUtils.write;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes = {NotificationController.class, GlobalExceptionHandler.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(NotificationController.class)
@Import(NotificationController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NotificationControllerTest {

    public static final String REQUEST_MAPPING = "/notification";
    public static final String RECEIVER_REGISTER_API = REQUEST_MAPPING + "/receiver/register";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    private MockedStatic<SecurityUtils> securityUtils;

    public static Stream<Arguments> testFetchDatasource() {
        return Stream.of(Arguments.of(OK.value(), emptyList(), emptyList()),
            Arguments.of(OK.value(), null, EMPTY_STRING));
    }

    public static Stream<Arguments> testRegisterReceiverDatasource() {
        return Stream.of(
            Arguments.of(new NotificationReceiverModel("sample"),
                OK.value(), true, singletonMap(STATUS, true)),
            Arguments.of(new NotificationReceiverModel("sample"),
                OK.value(), false, singletonMap(STATUS, false)));
    }

    @BeforeAll
    void setUpForTestSuite() {
        securityUtils = Mockito.mockStatic(SecurityUtils.class);
        securityUtils.when(() -> SecurityUtils.getPrincipal(any())).thenReturn("dummy_id");
    }

    @AfterAll
    void tearDownTestSuite() {
        securityUtils.close();
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
    void testFetch(int expectedStatus, List<NotificationModel> mockResponse,
                   Object expected) throws Exception {

        when(notificationService.fetch(any())).thenReturn(mockResponse);

        mockMvc.perform(get(REQUEST_MAPPING)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(expectedStatus)).andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                if (mockResponse == null) {
                    assertEquals(expected, content);
                } else {
                    assertEquals(write(expected), content);
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
    @ParameterizedTest(name = "{index}: testRegisterReceiver() = {1}")
    @MethodSource("testRegisterReceiverDatasource")
    void testRegisterReceiver(NotificationReceiverModel request, int expectedStatus,
                              boolean mockResponse, Map<String, Boolean> expected) throws Exception {

        when(notificationService.registerReceiver(any(), any())).thenReturn(mockResponse);

        mockMvc.perform(post(RECEIVER_REGISTER_API)
                .contentType(MediaType.APPLICATION_JSON)
                .content(write(request)))
            .andExpect(status().is(expectedStatus)).andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                assertEquals(write(expected), content);
            });
    }
}

