package ca.dal.comparify.framework.exception.handler;

import ca.dal.comparify.framework.exception.ApplicationRuntimeException;
import ca.dal.comparify.model.HashModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
