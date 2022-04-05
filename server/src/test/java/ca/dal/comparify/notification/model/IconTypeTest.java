package ca.dal.comparify.notification.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IconTypeTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testValueOf() {
        assertEquals("ALERT", IconType.valueOf("ALERT").getValue());
    }
}

