package ca.dal.comparify.framework.exception;

public class EntityAlreadyExistsException extends ApplicationRuntimeException {

    public EntityAlreadyExistsException(String message, Integer status, Integer errorCode) {
        super(message, status, errorCode);
    }
}
