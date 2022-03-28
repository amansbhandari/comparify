package ca.dal.comparify.securityQuestionTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.securityQuestion.model.Question;
import ca.dal.comparify.securityQuestion.repository.QuestionRepository;


public class TestQuestionRepository {

    
   
    QuestionRepository questionRepository = mock(QuestionRepository.class);
    String collectionName = "securityQuestion";
  

    // @Test
    // public void testAddQuestionSuccess() {
    //     Question question = new Question("What is your mother's maiden name?", "Doe",(long) 1);    
    //     when(questionRepository.addQuestion(question)).thenReturn(0);
    //     assertEquals(0, questionRepository.addQuestion(question));
    // }
    
    // @Test
    // public void testAddQuestionFail() {
    //     Question question = new Question("What is your mother's maiden name?", "Doe",(long) 2);
    //     when(questionRepository.addQuestion(question)).thenReturn(-1);   
    //     assertEquals(-1, questionRepository.addQuestion(question));
    // }
   

}
