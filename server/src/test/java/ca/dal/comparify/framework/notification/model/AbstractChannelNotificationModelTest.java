package ca.dal.comparify.framework.notification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.dal.comparify.notification.model.IconType;
import ca.dal.comparify.notification.model.NotificationTypeEnum;
import org.junit.jupiter.api.Test;

class AbstractChannelNotificationModelTest {
    @Test
    void testConstructor() {
        AbstractChannelNotificationModel actualAbstractChannelNotificationModel = new AbstractChannelNotificationModel("Dr",
            "Not all who wander are lost", IconType.ALERT, NotificationTypeEnum.ALERT);
        actualAbstractChannelNotificationModel.setIconType(IconType.ALERT);
        actualAbstractChannelNotificationModel.setMessage("Not all who wander are lost");
        actualAbstractChannelNotificationModel.setTitle("Dr");
        assertEquals(IconType.ALERT, actualAbstractChannelNotificationModel.getIconType());
        assertEquals("Not all who wander are lost", actualAbstractChannelNotificationModel.getMessage());
        assertEquals("Dr", actualAbstractChannelNotificationModel.getTitle());
    }

    @Test
    void testConstructor2() {
        AbstractChannelNotificationModel actualAbstractChannelNotificationModel = new AbstractChannelNotificationModel("Dr",
            "Not all who wander are lost", IconType.ALERT, NotificationTypeEnum.ALERT);
        actualAbstractChannelNotificationModel.setIconType(IconType.ALERT);
        actualAbstractChannelNotificationModel.setMessage("Not all who wander are lost");
        actualAbstractChannelNotificationModel.setTitle("Dr");
        assertEquals(IconType.ALERT, actualAbstractChannelNotificationModel.getIconType());
        assertEquals("Not all who wander are lost", actualAbstractChannelNotificationModel.getMessage());
        assertEquals("Dr", actualAbstractChannelNotificationModel.getTitle());
    }
}

