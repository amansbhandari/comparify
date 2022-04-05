package ca.dal.comparify.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AlertTypeEnumTest {
    @Test
    void testGetValueLowerCase() {
        assertEquals("price_drop", AlertTypeEnum.PRICE_DROP.getValueLowerCase());
    }

    @Test
    void testValueOf() {
        assertEquals("PRICE_DROP", AlertTypeEnum.valueOf("PRICE_DROP").getValue());
    }
}

