package ca.dal.comparify.user.service.iam;

import ca.dal.comparify.constant.ApplicationConstant;
import ca.dal.comparify.framework.app.ApplicationScope;
import ca.dal.comparify.framework.exception.EntityAlreadyExistsException;
import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.security.TokenService;
import ca.dal.comparify.user.enumeration.UserErrorCode;
import ca.dal.comparify.user.exception.authentication.UserAuthenticationException;
import ca.dal.comparify.user.model.iam.UserIAMModel;
import ca.dal.comparify.user.model.iam.UserIAMRequestModel;
import ca.dal.comparify.user.model.iam.UserIAMResponseModel;
import ca.dal.comparify.user.model.iam.authentication.UserAuthenticationModel;
import ca.dal.comparify.user.model.iam.authentication.UserPrincipal;
import ca.dal.comparify.user.model.iam.authorization.UserAuthorizationModel;
import ca.dal.comparify.user.model.iam.authorization.UserRoleModel;
import ca.dal.comparify.user.repository.iam.UserIAMRepository;
import ca.dal.comparify.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ca.dal.comparify.constant.ApplicationConstant.SYSTEM;
import static ca.dal.comparify.user.model.iam.authorization.UserAuthorizationModel.CREATE_ACTION;
import static ca.dal.comparify.user.model.iam.authorization.UserAuthorizationRoleEnum.USER;

@Service
public class UserIAMService {

    private static final int ACCOUNT_EXPIRATION_DAYS = 365;
    private static final long SECRET_EXPIRATION_DAYS = 30;
    private static final long ALERT_BEFORE_SECRET_EXPIRATION_DAYS = 15;
    private static final int STATUS = 401;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserIAMRepository userIAMRepository;

    @Autowired
    private ApplicationScope applicationScope;

    /**
     * @param userIdentifier
     * @return
     * @author Harsh Shah
     */
    public UserPrincipal fetchUser(String userIdentifier) {

        UserIAMModel userIAMModel = userIAMRepository
            .fetchUserAuthenticationInfo(userIdentifier);

        if (userIAMModel == null) {
            throw new UsernameNotFoundException(ApplicationConstant.CANNOT_FIND_USERNAME);
        }

        UserPrincipal principal = UserPrincipal.create(userIAMModel);

        applicationScope.setActiveUsers(principal.getId());

        return principal;
    }

    /**
     * @param requestModel
     * @return
     * @author Harsh Shah
     */
    public UserIAMResponseModel authenticate(UserIAMRequestModel requestModel) {

        UsernamePasswordAuthenticationToken userCredAuthToken = new UsernamePasswordAuthenticationToken(
            requestModel.getUserIdentifier(),
            requestModel.getUserSecret());

        Authentication auth = null;
        try {
            auth = authenticationManager.authenticate(userCredAuthToken);
        } catch (DisabledException e) {
            throw new UserAuthenticationException(e.getMessage(), STATUS, UserErrorCode.E2000.getCode());
        } catch (BadCredentialsException e) {
            invalidAuthenticationAttempt(requestModel.getUserIdentifier());
            throw new UserAuthenticationException(e.getMessage(), STATUS, UserErrorCode.E2001.getCode());
        } catch (LockedException e) {
            throw new UserAuthenticationException(e.getMessage(), STATUS, UserErrorCode.E2002.getCode());
        } catch (CredentialsExpiredException e) {
            throw new UserAuthenticationException(e.getMessage(), STATUS, UserErrorCode.E2003.getCode());
        } catch (Exception e) {
            throw new UserAuthenticationException(e.getMessage(), STATUS, UserErrorCode.E2006.getCode());
        }

        validAuthenticationAttempt(requestModel.getUserIdentifier());

        return new UserIAMResponseModel(tokenService.generateToken(auth));
    }

    /**
     * @param userIdentifier
     * @author Harsh Shah
     */
    private void validAuthenticationAttempt(String userIdentifier) {
        userIAMRepository.validAuthenticationAttempt(userIdentifier);
    }

    /**
     * @param userIdentifier
     * @author Harsh Shah
     */
    private void invalidAuthenticationAttempt(String userIdentifier) {
        userIAMRepository.invalidAuthenticationAttempt(userIdentifier);
    }

    /**
     * @param userIdentifier
     * @param secret
     * @return
     * @author Harsh Shah
     */
    public int createUserIAMInfo(String userId, String userIdentifier, String secret) {

        if (isUserExists(userIdentifier)) {
            throw new EntityAlreadyExistsException("User already exists having " + userIdentifier + " User Identifier",
                400, UserErrorCode.E2004.getCode());
        }

        UserAuthorizationModel authorization = new UserAuthorizationModel(USER, CREATE_ACTION, SYSTEM);

        UserAuthenticationModel authentication = new UserAuthenticationModel(
            passwordEncoder.encode(secret),
            true,
            0,
            DateUtils.addDaysToLocalNow(ACCOUNT_EXPIRATION_DAYS),
            DateUtils.addDaysToLocalNow(SECRET_EXPIRATION_DAYS),
            new ArrayList<>());

        return userIAMRepository.save(new UserIAMModel(userId, userIdentifier, authentication, authorization));

    }

    /**
     * @param userIdentifier
     * @return
     * @author Harsh Shah
     */
    private boolean isUserExists(String userIdentifier) {
        return userIAMRepository.isUserExists(userIdentifier);
    }

    /**
     * @param userId
     * @return
     * @author Harsh Shah
     */
    public UserRoleModel getUserRole(String userId) {
        UserIAMModel userIAM = userIAMRepository.getUserRole(userId);

        return new UserRoleModel(userIAM.getId(), userIAM.getAuthorization());
    }

    /**
     * @param userIdentifier
     * @param userSecret
     * @return
     * @author Harsh Shah
     */
    public boolean updateUserSecret(String userIdentifier, String userSecret) {
        userSecret = passwordEncoder.encode(userSecret);
        return userIAMRepository.updateUserIAMInfo(userIdentifier, UserIAMRepository.FIELD_SECRET, userSecret);
    }

    /**
     * @return
     * @author Harsh Shah
     */
    public List<HashModel> checkUserSecretValidity() {

        LocalDate date = DateUtils.addDaysToLocalNow(ALERT_BEFORE_SECRET_EXPIRATION_DAYS);

        return userIAMRepository.checkUserSecretValidity(date);
    }


    /**
     * @param userId
     * @return
     * @author Harsh Shah
     */
    public List<HashModel> getAllUsers(String userId) {
        return userIAMRepository.getAllUsers();
    }

    /**
     * @param userId
     * @author Harsh Shah
     * @return
     */
    public boolean logout(String userId) {
        applicationScope.removeActiveUsers(userId);
        return true;
    }
}
