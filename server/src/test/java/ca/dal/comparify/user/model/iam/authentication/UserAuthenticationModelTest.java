package ca.dal.comparify.user.model.iam.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserAuthenticationModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        LocalDate accountExpiresOn = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn = LocalDate.ofEpochDay(1L);
        ArrayList<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        UserAuthenticationModel actualUserAuthenticationModel = new UserAuthenticationModel("Secret", true, 10,
            accountExpiresOn, secretExpiresOn, grantedAuthorityList);
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualUserAuthenticationModel.setAccountExpiresOn(ofEpochDayResult);
        actualUserAuthenticationModel.setActive(true);
        ArrayList<GrantedAuthority> grantedAuthorityList1 = new ArrayList<>();
        actualUserAuthenticationModel.setAuthorities(grantedAuthorityList1);
        actualUserAuthenticationModel.setNumberOfIncorrectAttempts(10);
        actualUserAuthenticationModel.setSecret("Secret");
        LocalDate ofEpochDayResult1 = LocalDate.ofEpochDay(1L);
        actualUserAuthenticationModel.setSecretExpiresOn(ofEpochDayResult1);
        assertSame(ofEpochDayResult, actualUserAuthenticationModel.getAccountExpiresOn());
        Collection<GrantedAuthority> authorities = actualUserAuthenticationModel.getAuthorities();
        assertSame(grantedAuthorityList1, authorities);
        assertEquals(grantedAuthorityList, authorities);
        assertTrue(actualUserAuthenticationModel.getIsActive());
        assertEquals(10, actualUserAuthenticationModel.getNumberOfIncorrectAttempts().intValue());
        assertEquals("Secret", actualUserAuthenticationModel.getSecret());
        assertSame(ofEpochDayResult1, actualUserAuthenticationModel.getSecretExpiresOn());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testIsActive() {
        LocalDate accountExpiresOn = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn = LocalDate.ofEpochDay(1L);
        assertTrue((new UserAuthenticationModel("Secret", true, 10, accountExpiresOn, secretExpiresOn, new ArrayList<>()))
            .isActive());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testIsActive2() {
        LocalDate accountExpiresOn = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn = LocalDate.ofEpochDay(1L);
        assertFalse((new UserAuthenticationModel("Secret", false, 10, accountExpiresOn, secretExpiresOn, new ArrayList<>()))
            .isActive());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testIsLocked() {
        LocalDate accountExpiresOn = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn = LocalDate.ofEpochDay(1L);
        assertTrue((new UserAuthenticationModel("Secret", true, 10, accountExpiresOn, secretExpiresOn, new ArrayList<>()))
            .isLocked());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testIsLocked2() {
        LocalDate accountExpiresOn = LocalDate.ofEpochDay(1L);
        LocalDate secretExpiresOn = LocalDate.ofEpochDay(1L);
        assertFalse((new UserAuthenticationModel("Secret", true, 2, accountExpiresOn, secretExpiresOn, new ArrayList<>()))
            .isLocked());
    }
}

