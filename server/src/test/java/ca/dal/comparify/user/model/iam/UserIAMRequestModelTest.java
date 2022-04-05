package ca.dal.comparify.user.model.iam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class UserIAMRequestModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        UserIAMRequestModel actualUserIAMRequestModel = new UserIAMRequestModel();
        actualUserIAMRequestModel.setId("42");
        actualUserIAMRequestModel.setUserIdentifier("42");
        actualUserIAMRequestModel.setUserSecret("User Secret");
        assertEquals("42", actualUserIAMRequestModel.getId());
        assertEquals("42", actualUserIAMRequestModel.getUserIdentifier());
        assertEquals("User Secret", actualUserIAMRequestModel.getUserSecret());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        UserIAMRequestModel actualUserIAMRequestModel = new UserIAMRequestModel("42", "Secret");
        actualUserIAMRequestModel.setId("42");
        actualUserIAMRequestModel.setUserIdentifier("42");
        actualUserIAMRequestModel.setUserSecret("User Secret");
        assertEquals("42", actualUserIAMRequestModel.getId());
        assertEquals("42", actualUserIAMRequestModel.getUserIdentifier());
        assertEquals("User Secret", actualUserIAMRequestModel.getUserSecret());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor3() {
        UserIAMRequestModel actualUserIAMRequestModel = new UserIAMRequestModel("42", "42", "User Secret");
        actualUserIAMRequestModel.setId("42");
        actualUserIAMRequestModel.setUserIdentifier("42");
        actualUserIAMRequestModel.setUserSecret("User Secret");
        assertEquals("42", actualUserIAMRequestModel.getId());
        assertEquals("42", actualUserIAMRequestModel.getUserIdentifier());
        assertEquals("User Secret", actualUserIAMRequestModel.getUserSecret());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testIsEmpty() {
        assertFalse((new UserIAMRequestModel("42", "Secret")).isEmpty());
        assertTrue((new UserIAMRequestModel("", "Secret")).isEmpty());
        assertTrue((new UserIAMRequestModel()).isEmpty());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testIsAllEmpty() {
        assertFalse((new UserIAMRequestModel("42", "Secret")).isAllEmpty());
        assertFalse((new UserIAMRequestModel("", "Secret")).isAllEmpty());
        assertTrue((new UserIAMRequestModel()).isAllEmpty());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testGetRequiredFields() {
        List<String> actualRequiredFields = (new UserIAMRequestModel("42", "Secret")).getRequiredFields();
        assertEquals(2, actualRequiredFields.size());
        assertEquals(UserIAMModel.USER_IDENTIFIER, actualRequiredFields.get(0));
        assertEquals("user_secret", actualRequiredFields.get(1));
    }
}

