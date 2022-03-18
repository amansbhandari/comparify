package ca.dal.comparify.user.exception.authentication;

import ca.dal.comparify.framework.exception.ApplicationRuntimeException;

public class UserAuthenticationException extends ApplicationRuntimeException {

    public UserAuthenticationException(String message, Integer status, Integer errorCode){
        super(message, status, errorCode);
    }

}
