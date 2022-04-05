package ca.dal.comparify.user.model.iam.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.dal.comparify.user.model.iam.UserIAMModel;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserPrincipalTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        ArrayList<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        UserPrincipal actualUserPrincipal = new UserPrincipal("42", "42", "Secret", grantedAuthorityList);

        assertSame(grantedAuthorityList, actualUserPrincipal.getAuthorities());
        assertEquals("42", actualUserPrincipal.getId());
        assertEquals("Secret", actualUserPrincipal.getPassword());
        assertEquals("42", actualUserPrincipal.getUsername());
        assertTrue(actualUserPrincipal.isEnabled());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCreate2() {
        UserIAMModel userIAMModel = new UserIAMModel();
        LocalDate accountExpiresOn = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn = LocalDate.ofEpochDay(1L);
        userIAMModel.setAuthentication(
            new UserAuthenticationModel("Secret", true, 10, accountExpiresOn, secretExpiresOn, new ArrayList<>()));
        UserPrincipal actualCreateResult = UserPrincipal.create(userIAMModel);
        assertTrue(actualCreateResult.getAuthorities().isEmpty());
        assertTrue(actualCreateResult.isEnabled());
        assertFalse(actualCreateResult.isAccountNonLocked());
        assertNull(actualCreateResult.getUsername());
        assertEquals("Secret", actualCreateResult.getPassword());
        assertNull(actualCreateResult.getId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCreate3() {
        UserIAMModel userIAMModel = new UserIAMModel();
        LocalDate accountExpiresOn = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn = LocalDate.ofEpochDay(1L);
        userIAMModel.setAuthentication(
            new UserAuthenticationModel("Secret", false, 10, accountExpiresOn, secretExpiresOn, new ArrayList<>()));
        UserPrincipal actualCreateResult = UserPrincipal.create(userIAMModel);
        assertTrue(actualCreateResult.getAuthorities().isEmpty());
        assertFalse(actualCreateResult.isEnabled());
        assertFalse(actualCreateResult.isAccountNonLocked());
        assertNull(actualCreateResult.getUsername());
        assertEquals("Secret", actualCreateResult.getPassword());
        assertNull(actualCreateResult.getId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCreate4() {
        UserIAMModel userIAMModel = new UserIAMModel();
        LocalDate accountExpiresOn = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn = LocalDate.ofEpochDay(1L);
        userIAMModel.setAuthentication(
            new UserAuthenticationModel("Secret", true, 1, accountExpiresOn, secretExpiresOn, new ArrayList<>()));
        UserPrincipal actualCreateResult = UserPrincipal.create(userIAMModel);
        assertTrue(actualCreateResult.getAuthorities().isEmpty());
        assertTrue(actualCreateResult.isEnabled());
        assertTrue(actualCreateResult.isAccountNonLocked());
        assertNull(actualCreateResult.getUsername());
        assertEquals("Secret", actualCreateResult.getPassword());
        assertNull(actualCreateResult.getId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testIsAccountNonExpired() {
        assertTrue((new UserPrincipal("42", "42", "Secret", new ArrayList<>())).isAccountNonExpired());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testIsAccountNonLocked() {
        assertTrue((new UserPrincipal("42", "42", "Secret", new ArrayList<>())).isAccountNonLocked());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testIsCredentialsNonExpired() {
        assertTrue((new UserPrincipal("42", "42", "Secret", new ArrayList<>())).isCredentialsNonExpired());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testEquals() {
        assertNotEquals(new UserPrincipal("42", "42", "Secret", new ArrayList<>()), null);
        assertNotEquals(new UserPrincipal("42", "42", "Secret", new ArrayList<>()), "Different type to UserPrincipal");
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testEquals2() {
        UserPrincipal userPrincipal = new UserPrincipal("42", "42", "Secret", new ArrayList<>());
        assertEquals(userPrincipal, userPrincipal);
        int expectedHashCodeResult = userPrincipal.hashCode();
        assertEquals(expectedHashCodeResult, userPrincipal.hashCode());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testEquals3() {
        UserPrincipal userPrincipal = new UserPrincipal("42", "42", "Secret", new ArrayList<>());
        UserPrincipal userPrincipal1 = new UserPrincipal("42", "42", "Secret", new ArrayList<>());

        assertEquals(userPrincipal, userPrincipal1);
        int expectedHashCodeResult = userPrincipal.hashCode();
        assertEquals(expectedHashCodeResult, userPrincipal1.hashCode());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testEquals4() {
        UserPrincipal userPrincipal = new UserPrincipal("Id", "42", "Secret", new ArrayList<>());
        assertNotEquals(userPrincipal, new UserPrincipal("42", "42", "Secret", new ArrayList<>()));
    }
}

