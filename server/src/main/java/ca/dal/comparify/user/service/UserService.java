package ca.dal.comparify.user.service;

import ca.dal.comparify.user.model.iam.UserIAMRequestModel;
import ca.dal.comparify.user.model.iam.UserIAMResponseModel;
import ca.dal.comparify.user.model.iam.authentication.UserPrincipal;
import ca.dal.comparify.user.service.iam.UserIAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserIAMService userIAMService;

    /**
     * @param authenticationRequestModel
     * @return
     *
     * @author Harsh Shah
     */
    public UserIAMResponseModel authenticate(UserIAMRequestModel authenticationRequestModel) {
        return userIAMService.authenticate(authenticationRequestModel);
    }

    /**
     * @param userIdentifier
     * @return
     *
     * @author Harsh Shah
     */
    public UserPrincipal fetchUser(String userIdentifier) {
        return userIAMService.fetchUser(userIdentifier);
    }


    /**
     * @param userIdentifier
     * @param secret
     * @return
     *
     * @author Harsh Shah
     */
    public boolean createUserIAMInfo(String userIdentifier, String secret){
        return userIAMService.createUserIAMInfo(userIdentifier, secret);
    }
}
