package ca.dal.comparify.securityQuestion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ca.dal.comparify.securityQuestion.model.Question;
import ca.dal.comparify.securityQuestion.repository.QuestionRepository;
import net.minidev.json.JSONObject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TestQuestionControler {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private QuestionController questionController;

    @MockBean
    private QuestionRepository questionRepository;

    String questionText = "What is your favourite color?";
    String answer = "Blue";
    String username = "test";
    Question question = new Question(questionText, answer, username);
    JSONObject json = new JSONObject();

    @Test
    public void addQuestionFailMongoException() throws Exception {

        when(questionRepository.addQuestion(any(Question.class))).thenReturn(-2);

        this.mockMvc.perform(post("/securityQuestion/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string("-2"));
    }

    @Test
    public void addQuestionFailCollectionCanNotfind() throws Exception {

        when(questionRepository.addQuestion(any(Question.class))).thenReturn(-1);

        this.mockMvc.perform(post("/securityQuestion/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string("-1"));
    }

    @Test
    public void addQuestionSuccessful() throws Exception {

        when(questionRepository.addQuestion(question)).thenReturn(0);
        this.mockMvc.perform(post("/securityQuestion/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
                .header("Authorization", "Bearer")
                .characterEncoding("utf-8")).andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void getQuestionSucceful() throws Exception {
        when(questionRepository.getAllQuestion(username)).thenReturn(Arrays.asList(question));
        this.mockMvc.perform(get("/securityQuestion/getAll?userIdentifier=test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userIdentifier", is(username)))
                .andExpect(jsonPath("$[0].question", is(questionText)))
                .andExpect(jsonPath("$[0].answer", is(answer)));

    }

    @Test
    public void getQuestionNoRecordFound() throws Exception {
        when(questionRepository.getAllQuestion(any())).thenReturn(Arrays.asList());
        this.mockMvc.perform(get("/securityQuestion/getAll?userIdentifier=tes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getQuestionFailBadRequest() throws Exception {
        when(questionRepository.getAllQuestion(username)).thenReturn(Arrays.asList(question));
        this.mockMvc.perform(get("/securityQuestion/getAll?xyz=test"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getOneQuestionSuccess() throws Exception {
        when(questionRepository.getOneQuestionById(username)).thenReturn(question);
        this.mockMvc.perform(get("/securityQuestion/getOne?userIdentifier=test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userIdentifier", is(username)))
                .andExpect(jsonPath("$.question", is(questionText)))
                .andExpect(jsonPath("$.answer", is(answer)));

    }

    @Test
    public void getOneQuestionNoRecordFound() throws Exception {
        when(questionRepository.getOneQuestionById(any())).thenReturn(question);
        this.mockMvc.perform(get("/securityQuestion/getOne?userIdentifier=test"))
                .andExpect(status().isOk())
                .equals(null);
            }

    @Test
    public void getOneQuestionFailBadRequest() throws Exception {
        when(questionRepository.getOneQuestionById(username)).thenReturn(question);
        this.mockMvc.perform(get("/securityQuestion/getOne?xyz=test"))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void updateAnswerSuccessFull() throws Exception {
       when(questionRepository.updateAnswer(anyString(), anyString(),anyString())).thenReturn(true);
         this.mockMvc.perform(put("/securityQuestion/update?userIdentifier=test&question="+questionText+"&answer=Red"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
    @Test
    public void updateAnswerFail4xxError() throws Exception {
        when(questionRepository.updateAnswer(anyString(), anyString(),anyString())).thenReturn(false);
        this.mockMvc.perform(put("/securityQuestion/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
                .header("Authorization", "Bearer")
                .characterEncoding("utf-8")).andExpect(status().is4xxClientError());
    }
    @Test   
    public void deleteQuestionSuccess() throws Exception {
        when(questionRepository.deleteQuestion(anyString(),anyString())).thenReturn(true);
        this.mockMvc.perform(delete("/securityQuestion/delete?userIdentifier=test&question="+questionText))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
    @Test
    public void deleteQuestionFail() throws Exception {
        when(questionRepository.deleteQuestion(anyString(),anyString())).thenReturn(false);
        this.mockMvc.perform(delete("/securityQuestion/delete?userIdentifier=test"))
                .andExpect(status().is4xxClientError());
    }

}
