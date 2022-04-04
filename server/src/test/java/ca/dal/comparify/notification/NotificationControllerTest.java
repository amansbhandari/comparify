package ca.dal.comparify.notification;

import ca.dal.comparify.notification.model.NotificationReceiverModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {NotificationController.class})
@ExtendWith(SpringExtension.class)
class NotificationControllerTest {

    @Autowired
    private NotificationController notificationController;

    @MockBean
    private NotificationService notificationService;

    @Test
    void testFetch() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.notificationController)
            .build()
            .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterReceiver() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/notification/receiver/register");
        postResult.characterEncoding("Encoding");
        MockHttpServletRequestBuilder contentTypeResult = postResult.contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
            .content(objectMapper.writeValueAsString(new NotificationReceiverModel()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.notificationController)
            .build()
            .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }
}

