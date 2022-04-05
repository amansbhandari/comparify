package ca.dal.comparify.framework.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class EntityAlreadyExistsExceptionTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        EntityAlreadyExistsException actualEntityAlreadyExistsException = new EntityAlreadyExistsException(
            "An error occurred", 1, -1);

        assertNull(actualEntityAlreadyExistsException.getCause());
        assertEquals(0, actualEntityAlreadyExistsException.getSuppressed().length);
        assertEquals(1, actualEntityAlreadyExistsException.getStatus().intValue());
        assertEquals("An error occurred", actualEntityAlreadyExistsException.getMessage());
        assertEquals("An error occurred", actualEntityAlreadyExistsException.getLocalizedMessage());
        assertEquals(-1, actualEntityAlreadyExistsException.getErrorCode().intValue());
    }
}

