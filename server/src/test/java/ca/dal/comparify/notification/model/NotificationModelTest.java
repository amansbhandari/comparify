package ca.dal.comparify.notification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import ca.dal.comparify.model.AuditModel;
import ca.dal.comparify.model.HashModel;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class NotificationModelTest {
    @Test
    void testConstructor() {
        NotificationModel actualNotificationModel = new NotificationModel();
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualNotificationModel.setAudit(createResult);
        actualNotificationModel.setForUser("For User");
        actualNotificationModel.setId("42");
        actualNotificationModel.setMessage("Not all who wander are lost");
        HashMap<String, Object> stringObjectMap = new HashMap<>();
        actualNotificationModel.setModel(stringObjectMap);
        actualNotificationModel.setTitle("Dr");
        actualNotificationModel.setType(NotificationTypeEnum.ALERT);
        assertSame(createResult, actualNotificationModel.getAudit());
        assertEquals("For User", actualNotificationModel.getForUser());
        assertEquals("42", actualNotificationModel.getId());
        assertEquals("Not all who wander are lost", actualNotificationModel.getMessage());
        assertSame(stringObjectMap, actualNotificationModel.getModel());
        assertEquals("Dr", actualNotificationModel.getTitle());
        assertEquals(NotificationTypeEnum.ALERT, actualNotificationModel.getType());
    }

    @Test
    void testConstructor2() {
        HashModel model = new HashModel();
        NotificationModel actualNotificationModel = new NotificationModel("42", NotificationTypeEnum.ALERT, model,
                AuditModel.create("Jan 1, 2020 8:00am GMT+0100"));
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualNotificationModel.setAudit(createResult);
        actualNotificationModel.setForUser("For User");
        actualNotificationModel.setId("42");
        actualNotificationModel.setMessage("Not all who wander are lost");
        HashMap<String, Object> stringObjectMap = new HashMap<>();
        actualNotificationModel.setModel(stringObjectMap);
        actualNotificationModel.setTitle("Dr");
        actualNotificationModel.setType(NotificationTypeEnum.ALERT);
        assertSame(createResult, actualNotificationModel.getAudit());
        assertEquals("For User", actualNotificationModel.getForUser());
        assertEquals("42", actualNotificationModel.getId());
        assertEquals("Not all who wander are lost", actualNotificationModel.getMessage());
        assertSame(stringObjectMap, actualNotificationModel.getModel());
        assertEquals("Dr", actualNotificationModel.getTitle());
        assertEquals(NotificationTypeEnum.ALERT, actualNotificationModel.getType());
    }

    @Test
    void testConstructor3() {
        HashModel hashModel = new HashModel();
        NotificationModel actualNotificationModel = new NotificationModel("For User", "Dr", "Not all who wander are lost",
                NotificationTypeEnum.ALERT, hashModel);

        assertEquals(NotificationTypeEnum.ALERT, actualNotificationModel.getType());
        assertEquals("For User", actualNotificationModel.getForUser());
        assertEquals("Not all who wander are lost", actualNotificationModel.getMessage());
        assertSame(hashModel, actualNotificationModel.getModel());
        assertEquals("Dr", actualNotificationModel.getTitle());
        assertEquals("SYSTEM", actualNotificationModel.getAudit().getCreatedBy());
    }
}

