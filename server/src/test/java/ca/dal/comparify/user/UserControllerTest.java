package ca.dal.comparify.user;

import ca.dal.comparify.framework.exception.MissingRequiredFieldException;
import ca.dal.comparify.framework.exception.handler.GlobalExceptionHandler;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.user.model.iam.UserIAMRequestModel;
import ca.dal.comparify.user.model.iam.UserIAMResponseModel;
import ca.dal.comparify.user.model.iam.authorization.UserAuthorizationRoleEnum;
import ca.dal.comparify.user.model.iam.authorization.UserRoleModel;
import ca.dal.comparify.user.service.UserDetailsService;
import ca.dal.comparify.user.service.UserService;
import ca.dal.comparify.utils.MapUtils;
import ca.dal.comparify.utils.ResponseEntityUtils;
import ca.dal.comparify.utils.SecurityUtils;
import org.junit.jupiter.api.*;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static ca.dal.comparify.constant.ApplicationConstant.EMPTY_STRING;
import static ca.dal.comparify.constant.ApplicationConstant.STATUS;
import static ca.dal.comparify.utils.ObjectUtils.write;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Harsh Shah
 */
@ContextConfiguration(classes = {UserController.class, GlobalExceptionHandler.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    public static final String REQUEST_MAPPING = "/user";
    public static final String GET_ALL_USER_API = REQUEST_MAPPING + "/all";
    public static final String AUTHENTICATION_API = REQUEST_MAPPING + "/authentication";
    public static final String ROLE_API = REQUEST_MAPPING + "/role";
    public static final String LOGOUT_API = REQUEST_MAPPING + "/logout";
    public static final String UPDATE_SECRET_API = REQUEST_MAPPING + "/iam";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDetailsService userDetailsService;

    private MockedStatic<SecurityUtils> securityUtils;


    public static Stream<Arguments> testGetAllUsersDatasource() {
        return Stream.of(
            Arguments.of(Collections.emptyList(), Collections.emptyList()),
            Arguments.of(null, EMPTY_STRING)
        );
    }

    public static Stream<Arguments> testAuthenticationDatasource() {

        MissingRequiredFieldException mrf = new MissingRequiredFieldException(400, 1000, new UserIAMRequestModel().getRequiredFields());

        return Stream.of(
            Arguments.of(
                new UserIAMRequestModel("sample", "sample"),
                OK.value(),
                new UserIAMResponseModel("dummy-token"),
                new UserIAMResponseModel("dummy-token")),
            Arguments.of(
                new UserIAMRequestModel("sample", null),
                HttpStatus.BAD_REQUEST.value(),
                new UserIAMResponseModel(null),
                MapUtils.of("message", mrf.getMessage(), "error_code", mrf.getErrorCode())),
            Arguments.of(
                new UserIAMRequestModel(null, "sample"),
                HttpStatus.BAD_REQUEST.value(),
                new UserIAMResponseModel(null),
                MapUtils.of("message", mrf.getMessage(), "error_code", mrf.getErrorCode())),
            Arguments.of(
                new UserIAMRequestModel(null, null),
                HttpStatus.BAD_REQUEST.value(),
                new UserIAMResponseModel(null),
                MapUtils.of("message", mrf.getMessage(), "error_code", mrf.getErrorCode()))
        );
    }

    public static Stream<Arguments> testGetUserRoleDatasource() {
        return Stream.of(
            Arguments.of(new UserRoleModel(UserAuthorizationRoleEnum.USER),
                OK.value(),
                new UserRoleModel(UserAuthorizationRoleEnum.USER)),
            Arguments.of(new UserRoleModel(UserAuthorizationRoleEnum.ADMIN),
                OK.value(),
                new UserRoleModel(UserAuthorizationRoleEnum.ADMIN)),
            Arguments.of(null, OK.value(), EMPTY_STRING)
        );
    }

    public static Stream<Arguments> testLogoutDatasource() {
        return Stream.of(Arguments.of(true, OK.value(), Collections.singletonMap(STATUS, true)),
            Arguments.of(false, OK.value(), Collections.singletonMap(STATUS, false)));
    }

    public static Stream<Arguments> testUpdateDatasource() {

        MissingRequiredFieldException mrf = new MissingRequiredFieldException(400, 1000,
            new UserIAMRequestModel().getRequiredFields());

        return Stream.of(
            Arguments.of(
                new UserIAMRequestModel("sample", "sample"),
                CREATED.value(),
                true,
                Collections.singletonMap(STATUS, ResponseEntityUtils.CREATED)),
            Arguments.of(
                new UserIAMRequestModel("sample", "sample"),
                512,
                false,
                Collections.singletonMap(STATUS, ResponseEntityUtils.REQUEST_FAILED_SOMETHING_WENT_WRONG)),
            Arguments.of(
                new UserIAMRequestModel("sample", null),
                HttpStatus.BAD_REQUEST.value(),
                false,
                MapUtils.of("message", mrf.getMessage(), "error_code", mrf.getErrorCode())),
            Arguments.of(
                new UserIAMRequestModel(null, "sample"),
                HttpStatus.BAD_REQUEST.value(),
                false,
                MapUtils.of("message", mrf.getMessage(), "error_code", mrf.getErrorCode())),
            Arguments.of(
                new UserIAMRequestModel(null, null),
                HttpStatus.BAD_REQUEST.value(),
                false,
                MapUtils.of("message", mrf.getMessage(), "error_code", mrf.getErrorCode()))
        );
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

    @BeforeEach
    void setUpForTestCase() {}

    @AfterEach
    void tearDownTestCase() {}

    /**
     * @param mockResponse
     * @param expected
     * @throws Exception
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testGetAllUsers() = {0}")
    @MethodSource("testGetAllUsersDatasource")
    public void testGetAllUsers(List<HashModel> mockResponse, Object expected) throws Exception {
        when(userService.getAllUsers(any())).thenReturn(mockResponse);
        mockMvc.perform(get(GET_ALL_USER_API)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(result -> {
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
     * @param status
     * @param mockResponse
     * @param expected
     * @throws Exception
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testAuthentication({0}) = {1}")
    @MethodSource("testAuthenticationDatasource")
    public void testAuthentication(UserIAMRequestModel request, int status,
                                   UserIAMResponseModel mockResponse, Object expected) throws Exception {

        when(userService.authenticate(any()))
            .thenReturn(mockResponse);

        mockMvc.perform(post(AUTHENTICATION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .content(write(request)))
            .andExpect(status().is(status))
            .andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                if (mockResponse == null) {
                    assertEquals(expected, content);
                } else {
                    assertEquals(write(expected), content);
                }
            });
    }


    /**
     * @param mockResponse
     * @param status
     * @param expected
     * @throws Exception
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testGetUserRole({0}) = {1}")
    @MethodSource("testGetUserRoleDatasource")
    public void testGetUserRole(UserRoleModel mockResponse, int status, Object expected) throws Exception {
        when(userService.getUserRole(any())).thenReturn(mockResponse);
        mockMvc.perform(get(ROLE_API)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(status))
            .andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                if (mockResponse == null) {
                    assertEquals(expected, content);
                } else {
                    assertEquals(write(expected), content);
                }
            });
    }

    /**
     * @param mockResponse
     * @param status
     * @param expected
     * @throws Exception
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testLogout() = {0}")
    @MethodSource("testLogoutDatasource")
    public void testLogout(Object mockResponse, int status, Object expected) throws Exception {
        when(userService.logout(any())).thenReturn((Boolean) mockResponse);

        mockMvc.perform(get(LOGOUT_API)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(status))
            .andExpect(result -> {
                String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                assertEquals(write(expected), content);
            });
    }


    /**
     * @param request
     * @param status
     * @param mockResponse
     * @param expected
     * @throws Exception
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testUpdate() = {0}")
    @MethodSource("testUpdateDatasource")
    public void testUpdate(UserIAMRequestModel request, int status,
                           boolean mockResponse, Object expected) throws Exception {

        when(userService.updateUserSecret(any(), any())).thenReturn(mockResponse);

        mockMvc.perform(put(UPDATE_SECRET_API)
                .contentType(MediaType.APPLICATION_JSON)
                .content(write(request)))
            .andExpect(status().is(status))
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