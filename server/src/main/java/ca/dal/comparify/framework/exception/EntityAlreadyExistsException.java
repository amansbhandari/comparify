package ca.dal.comparify.framework.exception;

/**
 * @author Harsh Shah
 */
public class EntityAlreadyExistsException extends ApplicationRuntimeException {

    public EntityAlreadyExistsException(String message, Integer status, Integer errorCode) {
        super(message, status, errorCode);
    }
}
