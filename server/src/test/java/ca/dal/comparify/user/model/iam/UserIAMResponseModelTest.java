package ca.dal.comparify.user.model.iam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class UserIAMResponseModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        UserIAMResponseModel actualUserIAMResponseModel = new UserIAMResponseModel();
        actualUserIAMResponseModel.setRefreshToken("ABC123");
        actualUserIAMResponseModel.setToken("ABC123");
        assertEquals("ABC123", actualUserIAMResponseModel.getRefreshToken());
        assertEquals("ABC123", actualUserIAMResponseModel.getToken());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        UserIAMResponseModel actualUserIAMResponseModel = new UserIAMResponseModel("ABC123");
        actualUserIAMResponseModel.setRefreshToken("ABC123");
        actualUserIAMResponseModel.setToken("ABC123");
        assertEquals("ABC123", actualUserIAMResponseModel.getRefreshToken());
        assertEquals("ABC123", actualUserIAMResponseModel.getToken());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor3() {
        UserIAMResponseModel actualUserIAMResponseModel = new UserIAMResponseModel("ABC123", "ABC123");
        actualUserIAMResponseModel.setRefreshToken("ABC123");
        actualUserIAMResponseModel.setToken("ABC123");
        assertEquals("ABC123", actualUserIAMResponseModel.getRefreshToken());
        assertEquals("ABC123", actualUserIAMResponseModel.getToken());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testEquals() {
        assertNotEquals(new UserIAMResponseModel("ABC123"), null);
        assertNotEquals(new UserIAMResponseModel("ABC123"), "Different type to UserIAMResponseModel");
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testEquals2() {
        UserIAMResponseModel userIAMResponseModel = new UserIAMResponseModel("ABC123");
        assertEquals(userIAMResponseModel, userIAMResponseModel);
        int expectedHashCodeResult = userIAMResponseModel.hashCode();
        assertEquals(expectedHashCodeResult, userIAMResponseModel.hashCode());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testEquals3() {
        UserIAMResponseModel userIAMResponseModel = new UserIAMResponseModel("ABC123");
        UserIAMResponseModel userIAMResponseModel1 = new UserIAMResponseModel("ABC123");
        assertEquals(userIAMResponseModel, userIAMResponseModel1);
        int expectedHashCodeResult = userIAMResponseModel.hashCode();
        assertEquals(expectedHashCodeResult, userIAMResponseModel1.hashCode());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testEquals4() {
        UserIAMResponseModel userIAMResponseModel = new UserIAMResponseModel("Token");
        assertNotEquals(userIAMResponseModel, new UserIAMResponseModel("ABC123"));
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testEquals5() {
        UserIAMResponseModel userIAMResponseModel = new UserIAMResponseModel("ABC123", "ABC123");
        assertNotEquals(userIAMResponseModel, new UserIAMResponseModel("ABC123"));
    }
}

