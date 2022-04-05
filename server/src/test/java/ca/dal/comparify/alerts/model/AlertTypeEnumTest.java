package ca.dal.comparify.alerts.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlertTypeEnumTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testGetValueLowerCase() {
        assertEquals("price_drop", AlertTypeEnum.PRICE_DROP.getValueLowerCase());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testValueOf() {
        assertEquals("PRICE_DROP", AlertTypeEnum.valueOf("PRICE_DROP").getValue());
    }
}

