package ca.dal.comparify.user.enumeration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserErrorCodeTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testValueOf() {
        assertEquals(2000, UserErrorCode.valueOf("E2000").getCode().intValue());
    }
}

