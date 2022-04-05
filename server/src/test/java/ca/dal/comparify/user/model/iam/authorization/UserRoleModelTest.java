package ca.dal.comparify.user.model.iam.authorization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import ca.dal.comparify.model.AuditModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class UserRoleModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        UserRoleModel actualUserRoleModel = new UserRoleModel(UserAuthorizationRoleEnum.USER);
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualUserRoleModel.setAudit(createResult);
        actualUserRoleModel.setId("42");
        actualUserRoleModel.setRoleId(UserAuthorizationRoleEnum.USER);
        assertSame(createResult, actualUserRoleModel.getAudit());
        assertEquals("42", actualUserRoleModel.getId());
        assertEquals(UserAuthorizationRoleEnum.USER, actualUserRoleModel.getRoleId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        UserRoleModel actualUserRoleModel = new UserRoleModel("42", UserAuthorizationRoleEnum.USER,
            AuditModel.create("Jan 1, 2020 8:00am GMT+0100"));
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualUserRoleModel.setAudit(createResult);
        actualUserRoleModel.setId("42");
        actualUserRoleModel.setRoleId(UserAuthorizationRoleEnum.USER);
        assertSame(createResult, actualUserRoleModel.getAudit());
        assertEquals("42", actualUserRoleModel.getId());
        assertEquals(UserAuthorizationRoleEnum.USER, actualUserRoleModel.getRoleId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor3() {
        UserRoleModel actualUserRoleModel = new UserRoleModel("42",
            new UserAuthorizationModel(UserAuthorizationRoleEnum.USER, "Action Type", "By"));

        assertEquals(UserAuthorizationRoleEnum.USER, actualUserRoleModel.getRoleId());
        assertEquals("42", actualUserRoleModel.getId());
    }
}

