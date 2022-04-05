package ca.dal.comparify.user.model.iam.authorization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import ca.dal.comparify.model.AuditModel;
import org.junit.jupiter.api.Test;

class UserAuthorizationModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        UserAuthorizationModel actualUserAuthorizationModel = new UserAuthorizationModel();
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualUserAuthorizationModel.setAudit(createResult);
        actualUserAuthorizationModel.setRoleId(UserAuthorizationRoleEnum.USER);
        assertSame(createResult, actualUserAuthorizationModel.getAudit());
        assertEquals(UserAuthorizationRoleEnum.USER, actualUserAuthorizationModel.getRoleId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        UserAuthorizationModel actualUserAuthorizationModel = new UserAuthorizationModel(UserAuthorizationRoleEnum.USER,
            AuditModel.create("Jan 1, 2020 8:00am GMT+0100"));
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualUserAuthorizationModel.setAudit(createResult);
        actualUserAuthorizationModel.setRoleId(UserAuthorizationRoleEnum.USER);
        assertSame(createResult, actualUserAuthorizationModel.getAudit());
        assertEquals(UserAuthorizationRoleEnum.USER, actualUserAuthorizationModel.getRoleId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor3() {
        UserAuthorizationModel actualUserAuthorizationModel = new UserAuthorizationModel(UserAuthorizationRoleEnum.USER,
            "Action Type", "By");

        assertEquals(UserAuthorizationRoleEnum.USER, actualUserAuthorizationModel.getRoleId());
        assertEquals("By", actualUserAuthorizationModel.getAudit().getUpdatedBy());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor4() {
        UserAuthorizationModel actualUserAuthorizationModel = new UserAuthorizationModel(UserAuthorizationRoleEnum.USER,
            (String) UserAuthorizationModel.CREATE_ACTION, "foo");

        assertEquals(UserAuthorizationRoleEnum.USER, actualUserAuthorizationModel.getRoleId());
        assertEquals("foo", actualUserAuthorizationModel.getAudit().getCreatedBy());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor5() {
        UserAuthorizationModel actualUserAuthorizationModel = new UserAuthorizationModel(UserAuthorizationRoleEnum.ADMIN,
            "Action Type", "By");

        assertEquals(UserAuthorizationRoleEnum.ADMIN, actualUserAuthorizationModel.getRoleId());
        assertEquals("By", actualUserAuthorizationModel.getAudit().getUpdatedBy());
    }
}

