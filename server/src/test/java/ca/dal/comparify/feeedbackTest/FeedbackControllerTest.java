package ca.dal.comparify.feeedbackTest;
import ca.dal.comparify.feedback.controller.RestUpdateController;
import ca.dal.comparify.feedback.model.Feedback;
import ca.dal.comparify.feedback.services.FeedbackService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ca.dal.comparify.utils.ObjectUtils.write;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Meghna Rupchandani
 */

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)

public class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedbackService feedbackService;

    @MockBean
    private RestUpdateController controller;

    @Test
    void TestUserfeedback() throws Exception{

        LocalDate date=LocalDate.now();
        Feedback model =new Feedback();
        model.setDate(date);
        model.setUsersFeedback("dummydata");
        model.setSuggestions("dummydata");
        model.setId(new ObjectId("6247c13f28da7f42a60913e2"));
        model.setEmail("dummydata");

        Map dataDict = new HashMap(){{
            put("email", "abjkhjc@gmail.com");
            put("Usersfeedback", "stupid app");
            put("suggestions", "good!");
        }};

        String data="dummydata";

        when(feedbackService.addFeedback(model)).thenReturn(true);
        this.mockMvc.perform(post("/feedback").contentType("application/json").content(write(dataDict))).andExpect(status().isOk());
    }
}
