package ca.dal.comparify.utils;

import ca.dal.comparify.constant.ApplicationConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;

/**
 * @author Harsh Shah
 */
public class ResponseEntityUtils {

    public static final String SUCCESS = "Success";
    public static final String CREATED = "Created";
    public static final String ENTITY_ALREADY_EXISTS = "Entity already exists";
    public static final String REQUEST_FAILED_SOMETHING_WENT_WRONG = "Request Failed! Something went wrong...";
    public static final String INVALID_REQUEST = "Invalid Request";
    private static final int CUSTOM_STATUS = 512;


    private ResponseEntityUtils() {
    }

    /**
     * @param status
     * @return
     * @author Harsh Shah
     */
    public static ResponseEntity<Map<String, String>> returnStatus(int status) {
        HttpStatus httpStatus = null;
        int customStatus = HttpStatus.BAD_REQUEST.value();
        String message = null;

        switch (status) {

            case 1:
                httpStatus = HttpStatus.OK;
                message = SUCCESS;
                break;

            case 0:
                httpStatus = HttpStatus.CREATED;
                message = CREATED;
                break;

            case -2:
                httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
                message = ENTITY_ALREADY_EXISTS;
                break;

            case -3:
                customStatus = CUSTOM_STATUS;
                message = REQUEST_FAILED_SOMETHING_WENT_WRONG;
                break;

            case -1:
            default:
                httpStatus = HttpStatus.BAD_REQUEST;
                message = INVALID_REQUEST;
        }

        int responseStatus = httpStatus == null ? customStatus : httpStatus.value();

        return ResponseEntity.status(responseStatus).body(Collections.singletonMap(ApplicationConstant.STATUS, message));
    }

}
