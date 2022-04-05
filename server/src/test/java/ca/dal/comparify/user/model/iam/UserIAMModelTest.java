package ca.dal.comparify.user.model.iam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import ca.dal.comparify.user.model.iam.authentication.UserAuthenticationModel;
import ca.dal.comparify.user.model.iam.authorization.UserAuthorizationModel;
import ca.dal.comparify.user.model.iam.authorization.UserAuthorizationRoleEnum;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UserIAMModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        UserIAMModel actualUserIAMModel = new UserIAMModel();
        LocalDate accountExpiresOn = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn = LocalDate.ofEpochDay(1L);
        UserAuthenticationModel userAuthenticationModel = new UserAuthenticationModel("Secret", true, 10, accountExpiresOn,
            secretExpiresOn, new ArrayList<>());

        actualUserIAMModel.setAuthentication(userAuthenticationModel);
        UserAuthorizationModel userAuthorizationModel = new UserAuthorizationModel(UserAuthorizationRoleEnum.USER,
            "Action Type", "By");

        actualUserIAMModel.setAuthorization(userAuthorizationModel);
        actualUserIAMModel.setId("42");
        actualUserIAMModel.setUserIdentifier("42");
        assertSame(userAuthenticationModel, actualUserIAMModel.getAuthentication());
        assertSame(userAuthorizationModel, actualUserIAMModel.getAuthorization());
        assertEquals("42", actualUserIAMModel.getId());
        assertEquals("42", actualUserIAMModel.getUserIdentifier());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        LocalDate accountExpiresOn = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn = LocalDate.ofEpochDay(1L);
        UserAuthenticationModel authentication = new UserAuthenticationModel("Secret", true, 10, accountExpiresOn,
            secretExpiresOn, new ArrayList<>());

        UserIAMModel actualUserIAMModel = new UserIAMModel("42", "42", authentication,
            new UserAuthorizationModel(UserAuthorizationRoleEnum.USER, "Action Type", "By"));
        LocalDate accountExpiresOn1 = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn1 = LocalDate.ofEpochDay(1L);
        UserAuthenticationModel userAuthenticationModel = new UserAuthenticationModel("Secret", true, 10, accountExpiresOn1,
            secretExpiresOn1, new ArrayList<>());

        actualUserIAMModel.setAuthentication(userAuthenticationModel);
        UserAuthorizationModel userAuthorizationModel = new UserAuthorizationModel(UserAuthorizationRoleEnum.USER,
            "Action Type", "By");

        actualUserIAMModel.setAuthorization(userAuthorizationModel);
        actualUserIAMModel.setId("42");
        actualUserIAMModel.setUserIdentifier("42");
        assertSame(userAuthenticationModel, actualUserIAMModel.getAuthentication());
        assertSame(userAuthorizationModel, actualUserIAMModel.getAuthorization());
        assertEquals("42", actualUserIAMModel.getId());
        assertEquals("42", actualUserIAMModel.getUserIdentifier());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor3() {
        LocalDate accountExpiresOn = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn = LocalDate.ofEpochDay(1L);
        UserAuthenticationModel userAuthenticationModel = new UserAuthenticationModel("Secret", true, 10, accountExpiresOn,
            secretExpiresOn, new ArrayList<>());

        UserAuthorizationModel userAuthorizationModel = new UserAuthorizationModel(UserAuthorizationRoleEnum.USER,
            "Action Type", "By");

        UserIAMModel actualUserIAMModel = new UserIAMModel("42", userAuthenticationModel, userAuthorizationModel);

        assertSame(userAuthenticationModel, actualUserIAMModel.getAuthentication());
        assertEquals("42", actualUserIAMModel.getUserIdentifier());
        assertSame(userAuthorizationModel, actualUserIAMModel.getAuthorization());
    }
}

