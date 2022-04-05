package ca.dal.comparify.securityQuestion.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class QuestionTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        Question actualQuestion = new Question();
        actualQuestion.setAnswer("Answer");
        actualQuestion.setQuestion("Question");
        actualQuestion.setUserIdentifier("42");
        assertEquals("Answer", actualQuestion.getAnswer());
        assertEquals("Question", actualQuestion.getQuestion());
        assertEquals("42", actualQuestion.getUserIdentifier());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        Question actualQuestion = new Question("Question", "Answer", "42");
        actualQuestion.setAnswer("Answer");
        actualQuestion.setQuestion("Question");
        actualQuestion.setUserIdentifier("42");
        assertEquals("Answer", actualQuestion.getAnswer());
        assertEquals("Question", actualQuestion.getQuestion());
        assertEquals("42", actualQuestion.getUserIdentifier());
    }
}

