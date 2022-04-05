package ca.dal.comparify.securityQuestion;


import org.assertj.core.util.Arrays;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.securityQuestion.model.Question;
import ca.dal.comparify.securityQuestion.repository.QuestionRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class TestQuestionRepository {
    @MockBean
    private MongoRepository mongoRepository;

    @Autowired
    private QuestionRepository questionRepository;
    Question question = new Question();
    @Test
    public void addQuestionSuccessful() {
       
        assertEquals(questionRepository.addQuestion(question), 0);   
    }
    @Test
    public void addQuestionUnsuccessful() {
        when(mongoRepository.insertOne(anyString(), any(), any())).thenReturn(-1);
        Question question = new Question();
        assertEquals(questionRepository.addQuestion(question), -1);   
    }

    @Test   
    public void getOneQuestionByIdSuccessful() {
        when(mongoRepository.findOne(anyString(), any(), any())).thenReturn(question);
        assertEquals(questionRepository.getOneQuestionById("userIdentifier"), question);
    }
   
    @Test
    public void getAllQuestionSuccessful() {
        Question q1 = new Question();
        Question q2 = new Question();
        List<Object> questions = new ArrayList<Object>();
        questions.add((Object)q1);
        questions.add((Object)q2);
        when(mongoRepository.find(anyString(), any(), any())).thenReturn(questions);
        assertEquals(questionRepository.getAllQuestion("userIdentifier"), questions);
    }
    @Test
    public void updateAnswerSuccessful() {
        when(mongoRepository.updateOne(any(), any(), any(Bson.class))).thenReturn(true);
        assertEquals(questionRepository.updateAnswer("userIdentifier", "question", "answer"), true);
    }
    @Test
    public void deleteQuestionSuccessful() {
        when(mongoRepository.deleteOne(anyString(), any())).thenReturn(true);
        assertEquals(questionRepository.deleteQuestion("userIdentifier", "question"), true);
    }
    @Test
    public void updateAnswerUnsuccessful() {
        when(mongoRepository.updateOne(any(), any(), any(Bson.class))).thenReturn(false);
        assertEquals(questionRepository.updateAnswer("userIdentifier", "question", "answer"), false);
    }
    @Test
    public void deleteQuestionUnsuccessful() {
        when(mongoRepository.deleteOne(anyString(), any())).thenReturn(false);
        assertEquals(questionRepository.deleteQuestion("userIdentifier", "question"), false);
    }
}
