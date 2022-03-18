package ca.dal.comparify.user.service;

import ca.dal.comparify.constant.ApplicationConstant;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.repository.iam.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    /**
     * @param username
     * @return
     * @author Aman Singh Bhandari
     */
    public UserDetailsModel fetchUser(String username) {

        UserDetailsModel userDetailsModel = userDetailsRepository.fetchUserDetails(username);

        if (userDetailsModel == null) {
            throw new UsernameNotFoundException(ApplicationConstant.CANNOT_FIND_USERNAME);
        }

        return userDetailsModel;
    }

}