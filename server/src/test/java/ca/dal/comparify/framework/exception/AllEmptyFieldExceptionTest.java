package ca.dal.comparify.framework.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AllEmptyFieldExceptionTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        ArrayList<String> stringList = new ArrayList<>();
        AllEmptyFieldException actualAllEmptyFieldException = new AllEmptyFieldException(1, -1, stringList);

        assertNull(actualAllEmptyFieldException.getCause());
        assertEquals(0, actualAllEmptyFieldException.getSuppressed().length);
        assertEquals(1, actualAllEmptyFieldException.getStatus().intValue());
        assertEquals("The following fields were declared and at least one field required but could not be resolved: []",
            actualAllEmptyFieldException.getMessage());
        assertEquals("The following fields were declared and at least one field required but could not be resolved: []",
            actualAllEmptyFieldException.getLocalizedMessage());
        assertEquals(-1, actualAllEmptyFieldException.getErrorCode().intValue());
        assertTrue(stringList.isEmpty());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testGetMessage() {
        assertEquals("The following fields were declared and at least one field required but could not be resolved: []",
            (new AllEmptyFieldException(1, -1, new ArrayList<>())).getMessage());
    }
}

