package ca.dal.comparify.notification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NotificationReceiverModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        NotificationReceiverModel actualNotificationReceiverModel = new NotificationReceiverModel();
        actualNotificationReceiverModel.setIdentifier("42");
        assertEquals("42", actualNotificationReceiverModel.getIdentifier());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        NotificationReceiverModel actualNotificationReceiverModel = new NotificationReceiverModel("42");
        actualNotificationReceiverModel.setIdentifier("42");
        assertEquals("42", actualNotificationReceiverModel.getIdentifier());
    }
}

