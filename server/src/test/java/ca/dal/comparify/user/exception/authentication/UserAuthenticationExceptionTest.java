package ca.dal.comparify.user.exception.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class UserAuthenticationExceptionTest {
    @Test
    void testConstructor() {
        UserAuthenticationException actualUserAuthenticationException = new UserAuthenticationException("An error occurred",
            1, -1);

        assertNull(actualUserAuthenticationException.getCause());
        assertEquals(0, actualUserAuthenticationException.getSuppressed().length);
        assertEquals(1, actualUserAuthenticationException.getStatus().intValue());
        assertEquals("An error occurred", actualUserAuthenticationException.getMessage());
        assertEquals("An error occurred", actualUserAuthenticationException.getLocalizedMessage());
        assertEquals(-1, actualUserAuthenticationException.getErrorCode().intValue());
    }
}

