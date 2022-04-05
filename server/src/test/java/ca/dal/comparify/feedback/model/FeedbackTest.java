package ca.dal.comparify.feedback.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

class FeedbackTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        Feedback actualFeedback = new Feedback();
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualFeedback.setDate(ofEpochDayResult);
        actualFeedback.setEmail("jane.doe@example.org");
        ObjectId getResult = ObjectId.get();
        actualFeedback.setId(getResult);
        actualFeedback.setSuggestions("Suggestions");
        actualFeedback.setUsersFeedback("Users Feedback");
        assertSame(ofEpochDayResult, actualFeedback.getDate());
        assertEquals("jane.doe@example.org", actualFeedback.getEmail());
        assertSame(getResult, actualFeedback.getId());
        assertEquals("Suggestions", actualFeedback.getSuggestions());
        assertEquals("Users Feedback", actualFeedback.getUsersFeedback());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        Feedback actualFeedback = new Feedback(ObjectId.get(), "jane.doe@example.org", "Users Feedback", "Suggestions",
            LocalDate.ofEpochDay(1L));
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualFeedback.setDate(ofEpochDayResult);
        actualFeedback.setEmail("jane.doe@example.org");
        ObjectId getResult = ObjectId.get();
        actualFeedback.setId(getResult);
        actualFeedback.setSuggestions("Suggestions");
        actualFeedback.setUsersFeedback("Users Feedback");
        assertSame(ofEpochDayResult, actualFeedback.getDate());
        assertEquals("jane.doe@example.org", actualFeedback.getEmail());
        assertSame(getResult, actualFeedback.getId());
        assertEquals("Suggestions", actualFeedback.getSuggestions());
        assertEquals("Users Feedback", actualFeedback.getUsersFeedback());
    }
}

