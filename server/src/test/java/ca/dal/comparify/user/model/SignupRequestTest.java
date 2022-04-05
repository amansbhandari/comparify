package ca.dal.comparify.user.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SignupRequestTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testSetSecurityQuestion2() {
        SignupRequest signupRequest = new SignupRequest("Jane", "Doe", "janedoe", "jane.doe@example.org",
            "Security Question1", "Security Question2", "Security Question3", "Answer1", "Answer2", "Answer3", "iloveyou",
            "iloveyou", 10.0d, "Type");
        signupRequest.setSecurityQuestion2("Security Question2");
        assertEquals("Answer1", signupRequest.getAnswer1());
        assertEquals("janedoe", signupRequest.getUsername());
        assertEquals("Type", signupRequest.getType());
        assertEquals("Security Question3", signupRequest.getSecurityQuestion3());
        assertEquals("Security Question2", signupRequest.getSecurityQuestion2());
        assertEquals("Security Question1", signupRequest.getSecurityQuestion1());
        assertEquals(10.0d, signupRequest.getPoints());
        assertEquals("iloveyou", signupRequest.getPassword());
        assertEquals("Doe", signupRequest.getLastName());
        assertEquals("Jane", signupRequest.getFirstName());
        assertEquals("jane.doe@example.org", signupRequest.getEmail());
        assertEquals("iloveyou", signupRequest.getConfirmPassword());
        assertEquals("Answer3", signupRequest.getAnswer3());
        assertEquals("Answer2", signupRequest.getAnswer2());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testSetSecurityQuestion3() {
        SignupRequest signupRequest = new SignupRequest("Jane", "Doe", "janedoe", "jane.doe@example.org",
            "Security Question1", "Security Question2", "Security Question3", "Answer1", "Answer2", "Answer3", "iloveyou",
            "iloveyou", 10.0d, "Type");
        signupRequest.setSecurityQuestion3("Security Question3");
        assertEquals("Answer1", signupRequest.getAnswer1());
        assertEquals("janedoe", signupRequest.getUsername());
        assertEquals("Type", signupRequest.getType());
        assertEquals("Security Question3", signupRequest.getSecurityQuestion3());
        assertEquals("Security Question2", signupRequest.getSecurityQuestion2());
        assertEquals("Security Question1", signupRequest.getSecurityQuestion1());
        assertEquals(10.0d, signupRequest.getPoints());
        assertEquals("iloveyou", signupRequest.getPassword());
        assertEquals("Doe", signupRequest.getLastName());
        assertEquals("Jane", signupRequest.getFirstName());
        assertEquals("jane.doe@example.org", signupRequest.getEmail());
        assertEquals("iloveyou", signupRequest.getConfirmPassword());
        assertEquals("Answer3", signupRequest.getAnswer3());
        assertEquals("Answer2", signupRequest.getAnswer2());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        SignupRequest actualSignupRequest = new SignupRequest("Jane", "Doe", "janedoe", "jane.doe@example.org",
            "Security Question1", "Security Question2", "Security Question3", "Answer1", "Answer2", "Answer3", "iloveyou",
            "iloveyou", 10.0d, "Type");
        actualSignupRequest.setAnswer1("Answer1");
        actualSignupRequest.setAnswer2("Answer2");
        actualSignupRequest.setAnswer3("Answer3");
        actualSignupRequest.setConfirmPassword("iloveyou");
        actualSignupRequest.setEmail("jane.doe@example.org");
        actualSignupRequest.setFirstName("Jane");
        actualSignupRequest.setId("42");
        actualSignupRequest.setLastName("Doe");
        actualSignupRequest.setPassword("iloveyou");
        actualSignupRequest.setPoints(10.0d);
        actualSignupRequest.setSecurityQuestion1("Security Question1");
        actualSignupRequest.setType("Type");
        actualSignupRequest.setUsername("janedoe");
        assertEquals("Answer1", actualSignupRequest.getAnswer1());
        assertEquals("Answer2", actualSignupRequest.getAnswer2());
        assertEquals("Answer3", actualSignupRequest.getAnswer3());
        assertEquals("iloveyou", actualSignupRequest.getConfirmPassword());
        assertEquals("jane.doe@example.org", actualSignupRequest.getEmail());
        assertEquals("Jane", actualSignupRequest.getFirstName());
        assertEquals("42", actualSignupRequest.getId());
        assertEquals("Doe", actualSignupRequest.getLastName());
        assertEquals("iloveyou", actualSignupRequest.getPassword());
        assertEquals(10.0d, actualSignupRequest.getPoints());
        assertEquals("Security Question1", actualSignupRequest.getSecurityQuestion1());
        assertEquals("Security Question2", actualSignupRequest.getSecurityQuestion2());
        assertEquals("Security Question3", actualSignupRequest.getSecurityQuestion3());
        assertEquals("Type", actualSignupRequest.getType());
        assertEquals("janedoe", actualSignupRequest.getUsername());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        SignupRequest actualSignupRequest = new SignupRequest("Jane", "Doe", "janedoe", "jane.doe@example.org",
            "Security Question1", "Security Question2", "Security Question3", "Answer1", "Answer2", "Answer3", "iloveyou",
            "iloveyou", 10.0d, "Type");

        assertEquals("Answer1", actualSignupRequest.getAnswer1());
        assertEquals("janedoe", actualSignupRequest.getUsername());
        assertEquals("Type", actualSignupRequest.getType());
        assertEquals("Security Question3", actualSignupRequest.getSecurityQuestion3());
        assertEquals("Security Question2", actualSignupRequest.getSecurityQuestion2());
        assertEquals("Security Question1", actualSignupRequest.getSecurityQuestion1());
        assertEquals(10.0d, actualSignupRequest.getPoints());
        assertEquals("iloveyou", actualSignupRequest.getPassword());
        assertEquals("Doe", actualSignupRequest.getLastName());
        assertEquals("Jane", actualSignupRequest.getFirstName());
        assertEquals("jane.doe@example.org", actualSignupRequest.getEmail());
        assertEquals("iloveyou", actualSignupRequest.getConfirmPassword());
        assertEquals("Answer3", actualSignupRequest.getAnswer3());
        assertEquals("Answer2", actualSignupRequest.getAnswer2());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testValidateEmail() {
        assertTrue(
            (new SignupRequest("Jane", "Doe", "janedoe", "jane.doe@example.org", "Security Question1", "Security Question2",
                "Security Question3", "Answer1", "Answer2", "Answer3", "iloveyou", "iloveyou", 10.0d, "Type"))
                .validateEmail());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testValidate() {
        assertTrue(
            (new SignupRequest("Jane", "Doe", "janedoe", "jane.doe@example.org", "Security Question1", "Security Question2",
                "Security Question3", "Answer1", "Answer2", "Answer3", "iloveyou", "iloveyou", 10.0d, "Type")).validate());
        assertFalse(
            (new SignupRequest("", "Doe", "janedoe", "jane.doe@example.org", "Security Question1", "Security Question2",
                "Security Question3", "Answer1", "Answer2", "Answer3", "iloveyou", "iloveyou", 10.0d, "Type")).validate());
        assertFalse(
            (new SignupRequest("Jane", "Doe", "janedoe", "jane.doe@example.org", "Security Question1", "Security Question2",
                "Security Question3", "Answer1", "Answer2", "Answer3", "Password", "iloveyou", 10.0d, "Type")).validate());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testHasValidPasswordPattern() {
        assertTrue(
            (new SignupRequest("Jane", "Doe", "janedoe", "jane.doe@example.org", "Security Question1", "Security Question2",
                "Security Question3", "Answer1", "Answer2", "Answer3", "iloveyou", "iloveyou", 10.0d, "Type"))
                .HasValidPasswordPattern("iloveyou"));
    }
}

