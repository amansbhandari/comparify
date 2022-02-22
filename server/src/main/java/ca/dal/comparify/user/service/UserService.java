package ca.dal.comparify.user.service;

import ca.dal.comparify.user.repository.UserRepository;
import ca.dal.comparify.user.model.authentication.UserAuthenticationRequestModel;
import ca.dal.comparify.user.model.authentication.UserAuthenticationResponseModel;
import ca.dal.comparify.user.model.authentication.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private UserRepository userRepository;

    /**
     * @param authenticationRequestModel
     * @return
     */
    public UserAuthenticationResponseModel authenticate(UserAuthenticationRequestModel authenticationRequestModel) {
        return userAuthenticationService.authenticate(authenticationRequestModel);
    }

    /**
     * @param userIdentifier
     * @return
     */
    public UserPrincipal fetchUser(String userIdentifier) {
        return userAuthenticationService.fetchUser(userIdentifier);
    }


    /**
     * @param userIdentifier
     * @param secret
     * @return
     */
    public boolean createUserAuthentication(String userIdentifier, String secret){
        return userAuthenticationService.createUserAuthentication(userIdentifier, secret);
    }
}
