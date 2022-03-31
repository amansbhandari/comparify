package ca.dal.comparify.framework.exception;

public class InvalidFormatException extends ApplicationRuntimeException{
    public InvalidFormatException(String message, Integer status, Integer errorCode) {
        super(message, status, errorCode);
    }
}
