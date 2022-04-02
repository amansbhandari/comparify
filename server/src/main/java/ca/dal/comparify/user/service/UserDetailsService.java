package ca.dal.comparify.user.service;
/**
 * @author aman singh bhandari
 */

import ca.dal.comparify.constant.ApplicationConstant;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.model.iam.UserDetailsRequestModel;
import ca.dal.comparify.user.model.iam.UserIAMRequestModel;
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


    /**
     * @param username
     * @return
     * @author Aman Singh Bhandari
     */
    public Boolean saveUserDetails(UserDetailsRequestModel userDetailsRequestModel) {

        Boolean result = userDetailsRepository.saveUserDetails(userDetailsRequestModel);

        return result;
    }



    /**
     * @param id
     * @return
     *
     * @author Harsh Shah
     */
    public UserDetailsModel findUserById(String id){

        UserDetailsModel userDetailsModel = userDetailsRepository.findUserById(id);

        if (userDetailsModel == null) {
            throw new UsernameNotFoundException(ApplicationConstant.CANNOT_FIND_USER);
        }

        return userDetailsModel;
    }

}