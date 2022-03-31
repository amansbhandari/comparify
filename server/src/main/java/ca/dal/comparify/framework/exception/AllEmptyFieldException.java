package ca.dal.comparify.framework.exception;

import java.util.List;
import java.util.stream.Collectors;

public class AllEmptyFieldException extends ApplicationRuntimeException {

    private final List<String> fields;

    public AllEmptyFieldException(Integer status, Integer errorCode, List<String> fields) {
        super(status, errorCode);
        this.fields = fields;
    }

    @Override
    public String getMessage() {
        return "The following fields were declared and at least one field required but could not be resolved: " +
                fields.stream().collect(Collectors.joining(", ", "[", "]"));
    }
}
