package ca.dal.comparify.framework.notification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.notification.model.IconType;
import ca.dal.comparify.notification.model.NotificationTypeEnum;
import org.junit.jupiter.api.Test;

class MailNotificationModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        MailNotificationModel actualMailNotificationModel = new MailNotificationModel("Dr", "Not all who wander are lost",
            IconType.ALERT, NotificationTypeEnum.ALERT, "42", new HashModel());
        HashModel hashModel = new HashModel();
        actualMailNotificationModel.setModel(hashModel);
        actualMailNotificationModel.setReceiverIdentifier("42");
        assertSame(hashModel, actualMailNotificationModel.getModel());
        assertEquals("42", actualMailNotificationModel.getReceiverIdentifier());
    }
}

