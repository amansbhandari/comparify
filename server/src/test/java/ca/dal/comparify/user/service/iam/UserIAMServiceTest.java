package ca.dal.comparify.user.service.iam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.dal.comparify.framework.app.ApplicationScope;
import ca.dal.comparify.framework.exception.EntityAlreadyExistsException;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.security.TokenService;
import ca.dal.comparify.user.exception.authentication.UserAuthenticationException;
import ca.dal.comparify.user.model.iam.UserIAMRequestModel;
import ca.dal.comparify.user.repository.iam.UserIAMRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserIAMService.class, ApplicationScope.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class UserIAMServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UserIAMRepository userIAMRepository;

    @Autowired
    private UserIAMService userIAMService;

    /**
     * @throws AuthenticationException
     * @author Harsh Shah
     */
    @Test
    void testAuthenticate() throws AuthenticationException {
        when(this.userIAMRepository.validAuthenticationAttempt((String) any())).thenReturn(true);
        when(this.tokenService.generateToken((Authentication) any())).thenReturn("ABC123");
        when(this.authenticationManager.authenticate((Authentication) any()))
            .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertEquals("ABC123", this.userIAMService.authenticate(new UserIAMRequestModel("42", "Secret")).getToken());
        verify(this.userIAMRepository).validAuthenticationAttempt((String) any());
        verify(this.tokenService).generateToken((Authentication) any());
        verify(this.authenticationManager).authenticate((Authentication) any());
    }

    /**
     * @throws AuthenticationException
     * @author Harsh Shah
     */
    @Test
    void testAuthenticate2() throws AuthenticationException {
        when(this.userIAMRepository.validAuthenticationAttempt((String) any())).thenReturn(true);
        when(this.tokenService.generateToken((Authentication) any())).thenReturn("ABC123");
        when(this.authenticationManager.authenticate((Authentication) any()))
            .thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UserAuthenticationException.class,
            () -> this.userIAMService.authenticate(new UserIAMRequestModel("42", "Secret")));
        verify(this.authenticationManager).authenticate((Authentication) any());
    }

    /**
     * @throws AuthenticationException
     * @author Harsh Shah
     */
    @Test
    void testAuthenticate3() throws AuthenticationException {
        when(this.userIAMRepository.validAuthenticationAttempt((String) any())).thenReturn(true);
        when(this.tokenService.generateToken((Authentication) any())).thenReturn("ABC123");
        when(this.authenticationManager.authenticate((Authentication) any())).thenThrow(new DisabledException("Msg"));
        assertThrows(UserAuthenticationException.class,
            () -> this.userIAMService.authenticate(new UserIAMRequestModel("42", "Secret")));
        verify(this.authenticationManager).authenticate((Authentication) any());
    }

    /**
     * @throws AuthenticationException
     * @author Harsh Shah
     */
    @Test
    void testAuthenticate4() throws AuthenticationException {
        when(this.userIAMRepository.invalidAuthenticationAttempt((String) any())).thenReturn(true);
        when(this.userIAMRepository.validAuthenticationAttempt((String) any())).thenReturn(true);
        when(this.tokenService.generateToken((Authentication) any())).thenReturn("ABC123");
        when(this.authenticationManager.authenticate((Authentication) any())).thenThrow(new BadCredentialsException("Msg"));
        assertThrows(UserAuthenticationException.class,
            () -> this.userIAMService.authenticate(new UserIAMRequestModel("42", "Secret")));
        verify(this.userIAMRepository).invalidAuthenticationAttempt((String) any());
        verify(this.authenticationManager).authenticate((Authentication) any());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCreateUserIAMInfo() {
        when(this.userIAMRepository.isUserExists((String) any())).thenReturn(true);
        when(this.userIAMRepository.save((ca.dal.comparify.user.model.iam.UserIAMModel) any())).thenReturn(1);
        assertThrows(EntityAlreadyExistsException.class, () -> this.userIAMService.createUserIAMInfo("42", "42", "Secret"));
        verify(this.userIAMRepository).isUserExists((String) any());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCreateUserIAMInfo2() {
        when(this.userIAMRepository.isUserExists((String) any())).thenReturn(false);
        when(this.userIAMRepository.save((ca.dal.comparify.user.model.iam.UserIAMModel) any())).thenReturn(1);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertEquals(1, this.userIAMService.createUserIAMInfo("42", "42", "Secret"));
        verify(this.userIAMRepository).isUserExists((String) any());
        verify(this.userIAMRepository).save((ca.dal.comparify.user.model.iam.UserIAMModel) any());
        verify(this.passwordEncoder).encode((CharSequence) any());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCreateUserIAMInfo3() {
        when(this.userIAMRepository.isUserExists((String) any())).thenReturn(false);
        when(this.userIAMRepository.save((ca.dal.comparify.user.model.iam.UserIAMModel) any())).thenReturn(1);
        when(this.passwordEncoder.encode((CharSequence) any())).thenThrow(new UsernameNotFoundException("create"));
        assertThrows(UsernameNotFoundException.class, () -> this.userIAMService.createUserIAMInfo("42", "42", "Secret"));
        verify(this.userIAMRepository).isUserExists((String) any());
        verify(this.passwordEncoder).encode((CharSequence) any());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCheckUserSecretValidity() {
        ArrayList<HashModel> hashModelList = new ArrayList<>();
        when(this.userIAMRepository.checkUserSecretValidity((java.time.LocalDate) any())).thenReturn(hashModelList);
        List<HashModel> actualCheckUserSecretValidityResult = this.userIAMService.checkUserSecretValidity();
        assertSame(hashModelList, actualCheckUserSecretValidityResult);
        assertTrue(actualCheckUserSecretValidityResult.isEmpty());
        verify(this.userIAMRepository).checkUserSecretValidity((java.time.LocalDate) any());
    }
}

