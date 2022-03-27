package ca.dal.comparify.user;

import ca.dal.comparify.framework.exception.MissingRequiredFieldException;
import ca.dal.comparify.user.model.iam.UserDetailsModel;
import ca.dal.comparify.user.model.iam.UserDetailsRequestModel;
import ca.dal.comparify.user.model.iam.UserIAMRequestModel;
import ca.dal.comparify.user.model.iam.UserIAMResponseModel;
import ca.dal.comparify.user.service.UserDetailsService;
import ca.dal.comparify.user.service.UserService;
import ca.dal.comparify.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Harsh Shah
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * @param userIAMRequestModel
     * @return
     * @author Harsh Shah
     */
    @PostMapping("/authentication")
    public UserIAMResponseModel authentication(@RequestBody UserIAMRequestModel userIAMRequestModel) {

        if (userIAMRequestModel.isEmpty()) {
            throw new MissingRequiredFieldException(400, 1000, userIAMRequestModel.getRequiredFields());
        }

        return userService.authenticate(userIAMRequestModel);
    }

    /**
     * @param userIAMRequestModel
     * @return
     * @author Harsh Shah
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserIAMRequestModel userIAMRequestModel) {

        if (userIAMRequestModel.isEmpty()) {
            throw new MissingRequiredFieldException(400, 1000, userIAMRequestModel.getRequiredFields());
        }

        int status = userService.createUserIAMInfo(userIAMRequestModel.getUserIdentifier(),
                userIAMRequestModel.getUserSecret());

        return ResponseEntityUtils.returnStatus(status);
    }


    /**
     * @param username
     * @return
     * @author Aman Singh Bhandari
     */
    @GetMapping("/details")
    public UserDetailsModel getUserDetails(@RequestParam(name = "username") String username) {

        if (username.isEmpty()) {
            throw new MissingRequiredFieldException(400, 1000, UserDetailsModel.getRequiredFields());
        }

        return userDetailsService.fetchUser(username);
    }


    /**
     * @param UserDetailsRequestModel
     * @return
     * @author Aman Singh Bhandari
     */
    @PostMapping("/details")
    public Boolean setUserDetails(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {

        if (userDetailsRequestModel.isEmpty()) {
            throw new MissingRequiredFieldException(400, 1000, userDetailsRequestModel.getRequiredFields());
        }

        return userDetailsService.saveUserDetails(userDetailsRequestModel);
    }


}
