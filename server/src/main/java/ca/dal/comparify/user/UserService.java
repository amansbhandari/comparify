package ca.dal.comparify.user;

import ca.dal.comparify.user.model.authentication.UserAuthenticationModel;
import ca.dal.comparify.user.model.authentication.UserAuthenticationRequestModel;
import ca.dal.comparify.user.model.authentication.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public UserPrincipal fetchUser(String userIdentifier) {


        UserAuthenticationModel authenticateModel = userRepository.fetchUserAuthenicationInfo(userIdentifier);

        if (authenticateModel == null) {
            throw new UsernameNotFoundException("Cannot find username");
        }

        return UserPrincipal.create(authenticateModel);
    }

    public void authenticate(UserAuthenticationRequestModel userAuthenticationRequestModel) {



    }
}
