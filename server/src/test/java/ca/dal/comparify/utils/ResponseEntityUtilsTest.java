package ca.dal.comparify.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class ResponseEntityUtilsTest {

    @Test
    void testReturnStatus() {
        ResponseEntity<Map<String, String>> actualReturnStatusResult = ResponseEntityUtils.returnStatus(1);
        assertTrue(actualReturnStatusResult.hasBody());
        assertEquals(200, actualReturnStatusResult.getStatusCodeValue());
        assertTrue(actualReturnStatusResult.getHeaders().isEmpty());
    }

    @Test
    void testReturnStatus2() {
        ResponseEntity<Map<String, String>> actualReturnStatusResult = ResponseEntityUtils.returnStatus(-3);
        assertTrue(actualReturnStatusResult.hasBody());
        assertEquals(512, actualReturnStatusResult.getStatusCodeValue());
        assertTrue(actualReturnStatusResult.getHeaders().isEmpty());
    }

    @Test
    void testReturnStatus3() {
        ResponseEntity<Map<String, String>> actualReturnStatusResult = ResponseEntityUtils.returnStatus(-2);
        assertTrue(actualReturnStatusResult.hasBody());
        assertEquals(422, actualReturnStatusResult.getStatusCodeValue());
        assertTrue(actualReturnStatusResult.getHeaders().isEmpty());
    }

    @Test
    void testReturnStatus4() {
        ResponseEntity<Map<String, String>> actualReturnStatusResult = ResponseEntityUtils.returnStatus(-1);
        assertTrue(actualReturnStatusResult.hasBody());
        assertEquals(400, actualReturnStatusResult.getStatusCodeValue());
        assertTrue(actualReturnStatusResult.getHeaders().isEmpty());
    }

    @Test
    void testReturnStatus5() {
        ResponseEntity<Map<String, String>> actualReturnStatusResult = ResponseEntityUtils.returnStatus(0);
        assertTrue(actualReturnStatusResult.hasBody());
        assertEquals(201, actualReturnStatusResult.getStatusCodeValue());
        assertTrue(actualReturnStatusResult.getHeaders().isEmpty());
    }
}

