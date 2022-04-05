package ca.dal.comparify.framework.notification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import ca.dal.comparify.notification.model.IconType;
import ca.dal.comparify.notification.model.NotificationTypeEnum;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class WebSocketNotificationModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        ArrayList<String> stringList = new ArrayList<>();
        WebSocketNotificationModel actualWebSocketNotificationModel = new WebSocketNotificationModel(stringList, "Dr",
            "Not all who wander are lost", IconType.ALERT, NotificationTypeEnum.ALERT);
        ArrayList<String> stringList1 = new ArrayList<>();
        actualWebSocketNotificationModel.setReceiverIds(stringList1);
        List<String> receiverIds = actualWebSocketNotificationModel.getReceiverIds();
        assertSame(stringList1, receiverIds);
        assertEquals(stringList, receiverIds);
    }
}

