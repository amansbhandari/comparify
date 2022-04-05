package ca.dal.comparify.notification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NotificationTypeEnumTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testValueOf() {
        assertEquals("ALERT", NotificationTypeEnum.valueOf("ALERT").getValue());
    }
}

