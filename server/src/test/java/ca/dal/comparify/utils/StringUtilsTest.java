package ca.dal.comparify.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static ca.dal.comparify.constant.ApplicationConstant.EMPTY_STRING;
import static ca.dal.comparify.utils.StringUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Harsh Shah
 */
public class StringUtilsTest {

    /**
     * @return
     *
     * @author Harsh Shah
     */
    public static Stream<Arguments> testIsNotEmptyDatasource() {
        return Stream.of(
                Arguments.of("sample", true),
                Arguments.of(null, false),
                Arguments.of(EMPTY_STRING, false),
                Arguments.of("   ", false)
        );
    }

    /**
     * @return
     *
     * @author Harsh Shah
     */
    public static Stream<Arguments> testIsEmptyDatasource() {
        return Stream.of(
                Arguments.of("sample", false),
                Arguments.of(null, true),
                Arguments.of(EMPTY_STRING, true),
                Arguments.of("   ", true)
        );
    }

    /**
     * @return
     *
     * @author Harsh Shah
     */
    public static Stream<Arguments> testIsAnyEmptyDatasource() {
        return Stream.of(
                Arguments.of(false, new String[]{"sample1", "sample2","3", "@"}),
                Arguments.of(true, new String[]{"sample1", "","3", "@"}),
                Arguments.of(true, new String[]{"sample1", "sample2", null, "@"}),
                Arguments.of(true, new String[]{"sample1", "sample2","3", "   "})
        );
    }

    /**
     * @param str
     * @param expected
     *
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testIsNotEmptyDatasource({0}) = {1}")
    @MethodSource("testIsNotEmptyDatasource")
    void testIsNotEmpty(String str, boolean expected) {
        assertEquals(expected, isNotEmpty(str));
    }

    @ParameterizedTest(name = "{index}: testIsNotEmptyDatasource({0}) = {1}")
    @MethodSource("testIsEmptyDatasource")
    void testIsEmpty(String str, boolean expected) {
        assertEquals(expected, isEmpty(str));
    }

    @ParameterizedTest(name = "{index}: testIsAnyEmptyDatasource() = {0}")
    @MethodSource("testIsAnyEmptyDatasource")
    void testIsAnyEmpty(boolean expected, String... str) {
        assertEquals(expected, isAnyEmpty(str));
    }

}
