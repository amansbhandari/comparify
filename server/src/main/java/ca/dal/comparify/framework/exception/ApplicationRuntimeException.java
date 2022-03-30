package ca.dal.comparify.framework.exception;

/**
 * @author Harsh Shah
 */
public class ApplicationRuntimeException extends RuntimeException {

    private final Integer status;

    private final Integer errorCode;

    public ApplicationRuntimeException(Integer status, Integer errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    public ApplicationRuntimeException(String message, Integer status, Integer errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
