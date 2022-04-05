package ca.dal.comparify.notification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NotificationChannelTypeTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testValueOf() {
        assertEquals("MAIL", NotificationChannelType.valueOf("MAIL").getValue());
    }
}

