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

    ResponseEntityUtils(){}

    public static ResponseEntity<Map<String, String>> returnStatus(int status){
        HttpStatus httpStatus = null;
        String message = null;

        switch (status){
            case 0:
                httpStatus = HttpStatus.CREATED;
                message = "Created";
                break;
            case -2:
                httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
                message = "Entity already exists";
                break;
            case -1:
            default:
                httpStatus = HttpStatus.BAD_REQUEST;
                message = "Invalid Request";
        }

        return ResponseEntity.status(httpStatus).body(Collections.singletonMap(ApplicationConstant.STATUS, message));
    }

}
