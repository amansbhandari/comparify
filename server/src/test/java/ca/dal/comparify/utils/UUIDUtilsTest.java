package ca.dal.comparify.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static ca.dal.comparify.utils.StringUtils.isNotEmpty;
import static ca.dal.comparify.utils.UUIDUtils.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Harsh Shah
 */
public class UUIDUtilsTest {

    /**
     * @author Harsh Shah
     */
    @Test
    void testGenerate() {
        assertNotNull(generate());
    }
}
