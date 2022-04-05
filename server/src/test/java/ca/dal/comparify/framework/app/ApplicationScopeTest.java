package ca.dal.comparify.framework.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

class ApplicationScopeTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testSetActiveUsers() {
        ApplicationScope applicationScope = new ApplicationScope();
        applicationScope.setActiveUsers("42");
        assertEquals(1, applicationScope.getActiveUsers().size());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testSetActiveUsers2() {
        ApplicationScope applicationScope = new ApplicationScope();
        applicationScope.removeActiveUsers("42");
        applicationScope.setActiveUsers("42");
        assertEquals(1, applicationScope.getActiveUsers().size());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testRemoveActiveUsers() {
        ApplicationScope applicationScope = new ApplicationScope();
        applicationScope.removeActiveUsers("42");
        assertTrue(applicationScope.getActiveUsers().isEmpty());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testRemoveActiveUsers2() {
        ApplicationScope applicationScope = new ApplicationScope();
        applicationScope.setActiveUsers(new HashSet<>());
        applicationScope.removeActiveUsers("42");
        assertTrue(applicationScope.getActiveUsers().isEmpty());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testContainsActiveUsers() {
        assertFalse((new ApplicationScope()).containsActiveUsers("42"));
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testContainsActiveUsers2() {
        ApplicationScope applicationScope = new ApplicationScope();
        applicationScope.setActiveUsers(new HashSet<>());
        assertTrue(applicationScope.containsActiveUsers("42"));
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testGetUserToReceiverToken() {
        assertNull((new ApplicationScope()).getUserToReceiverToken("42"));
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testGetUserToReceiverToken2() {
        ApplicationScope applicationScope = new ApplicationScope();
        applicationScope.setUserToReceiverToken(new HashMap<>());
        assertNull(applicationScope.getUserToReceiverToken("42"));
    }

    @Test
    void testSetUserToReceiverToken() {
        ApplicationScope applicationScope = new ApplicationScope();
        applicationScope.setUserToReceiverToken("42", "ABC123");
        assertEquals(1, applicationScope.getUserToReceiverToken().size());
    }

    @Test
    void testConstructor() {
        ApplicationScope actualApplicationScope = new ApplicationScope();
        HashSet<String> stringSet = new HashSet<>();
        actualApplicationScope.setActiveUsers(stringSet);
        HashMap<String, String> stringStringMap = new HashMap<>();
        actualApplicationScope.setUserToReceiverToken(stringStringMap);
        assertSame(stringSet, actualApplicationScope.getActiveUsers());
        assertSame(stringStringMap, actualApplicationScope.getUserToReceiverToken());
    }
}

