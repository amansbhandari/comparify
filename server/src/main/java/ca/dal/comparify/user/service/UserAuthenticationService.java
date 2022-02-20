package ca.dal.comparify.user.service;

import ca.dal.comparify.constant.ApplicationConstant;
import ca.dal.comparify.framework.exception.EntityAlreadyExistsException;
import ca.dal.comparify.security.TokenService;
import ca.dal.comparify.user.enumeration.UserErrorCode;
import ca.dal.comparify.user.exception.authentication.UserAuthenticationException;
import ca.dal.comparify.user.model.authentication.UserAuthenticationModel;
import ca.dal.comparify.user.model.authentication.UserAuthenticationRequestModel;
import ca.dal.comparify.user.model.authentication.UserAuthenticationResponseModel;
import ca.dal.comparify.user.model.authentication.UserPrincipal;
import ca.dal.comparify.user.repository.UserAuthenticationRepository;
import ca.dal.comparify.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    /**
     * @param userIdentifier
     * @return
     */
    public UserPrincipal fetchUser(String userIdentifier) {

        UserAuthenticationModel authenticateModel = userAuthenticationRepository
                                .fetchUserAuthenticationInfo(userIdentifier);

        if (authenticateModel == null) {
            throw new UsernameNotFoundException(ApplicationConstant.CANNOT_FIND_USERNAME);
        }

        return UserPrincipal.create(authenticateModel);
    }

    /**
     * @param requestModel
     * @return
     */
    public UserAuthenticationResponseModel authenticate(UserAuthenticationRequestModel requestModel) {

        UsernamePasswordAuthenticationToken userCredAuthToken = new UsernamePasswordAuthenticationToken(
                requestModel.getUserIdentifier(),
                requestModel.getSecret());

        Authentication auth = null;
        try{
            auth = authenticationManager.authenticate(userCredAuthToken);
        } catch (DisabledException e) {
            throw new UserAuthenticationException(e.getMessage(), 401, UserErrorCode.E2000.getCode());
        } catch (BadCredentialsException e) {
            throw new UserAuthenticationException(e.getMessage(), 401, UserErrorCode.E2001.getCode());
        } catch (LockedException e){
            throw new UserAuthenticationException(e.getMessage(), 401, UserErrorCode.E2002.getCode());
        } catch (CredentialsExpiredException e){
            throw new UserAuthenticationException(e.getMessage(), 401, UserErrorCode.E2003.getCode());
        }


        return new UserAuthenticationResponseModel(tokenService.generateToken(auth));
    }

    /**
     * @param userIdentifier
     * @param secret
     * @return
     */
    public boolean createUserAuthentication(String userIdentifier, String secret) {

        if(isUserExists(userIdentifier)){
            throw new EntityAlreadyExistsException("User already exists having "+ userIdentifier +" User Identifier",
                    400, UserErrorCode.E2004.getCode());
        }

        return userAuthenticationRepository.save(new UserAuthenticationModel(
                    userIdentifier,
                    passwordEncoder.encode(secret),
                    true,
                    0,
                    DateUtils.addDaysToLocalNow(30),
                    DateUtils.addDaysToLocalNow(30),
                    new ArrayList<>()));

    }

    private boolean isUserExists(String userIdentifier){
        return userAuthenticationRepository.isUserExists(userIdentifier);
    }
}
