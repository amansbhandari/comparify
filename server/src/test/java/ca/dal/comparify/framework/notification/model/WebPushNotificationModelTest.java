package ca.dal.comparify.framework.notification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.dal.comparify.notification.model.IconType;
import ca.dal.comparify.notification.model.NotificationTypeEnum;
import org.junit.jupiter.api.Test;

class WebPushNotificationModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        WebPushNotificationModel actualWebPushNotificationModel = new WebPushNotificationModel("42", "Dr",
            "Not all who wander are lost", IconType.ALERT, NotificationTypeEnum.ALERT);
        actualWebPushNotificationModel.setReceiverIdentifier("42");
        assertEquals("42", actualWebPushNotificationModel.getReceiverIdentifier());
    }
}

