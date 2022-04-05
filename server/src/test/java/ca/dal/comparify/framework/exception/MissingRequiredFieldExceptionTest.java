package ca.dal.comparify.framework.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MissingRequiredFieldExceptionTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        ArrayList<String> stringList = new ArrayList<>();
        MissingRequiredFieldException actualMissingRequiredFieldException = new MissingRequiredFieldException(1, -1,
            stringList);

        assertNull(actualMissingRequiredFieldException.getCause());
        assertEquals(0, actualMissingRequiredFieldException.getSuppressed().length);
        assertEquals(1, actualMissingRequiredFieldException.getStatus().intValue());
        assertEquals("The following fields were declared as required but could not be resolved: []",
            actualMissingRequiredFieldException.getMessage());
        assertEquals("The following fields were declared as required but could not be resolved: []",
            actualMissingRequiredFieldException.getLocalizedMessage());
        assertEquals(-1, actualMissingRequiredFieldException.getErrorCode().intValue());
        assertTrue(stringList.isEmpty());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testGetMessage() {
        assertEquals("The following fields were declared as required but could not be resolved: []",
            (new MissingRequiredFieldException(1, -1, new ArrayList<>())).getMessage());
    }
}

