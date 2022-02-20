package ca.dal.comparify.user;

import ca.dal.comparify.user.model.authentication.UserAuthenticationRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/authentication")
    public void authentication(@RequestBody UserAuthenticationRequestModel authenticationRequestModel){

        if(authenticationRequestModel.isEmpty()){
            return;
        }

        userService.authenticate(authenticationRequestModel);



    }


}
