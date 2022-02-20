package ca.dal.comparify.framework.exception.handler;

import ca.dal.comparify.framework.exception.ApplicationRuntimeException;
import ca.dal.comparify.model.HashModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler({ApplicationRuntimeException.class})
    public ResponseEntity<HashModel> handler(ApplicationRuntimeException exception) {
        HashModel model = new HashModel();
        model.put("message", exception.getMessage());
        model.put("error_code", exception.getErrorCode());

        return ResponseEntity.status(exception.getStatus()).body(model);
    }

}
