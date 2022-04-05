package ca.dal.comparify.utils;

import ca.dal.comparify.user.model.iam.UserIAMResponseModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static ca.dal.comparify.utils.ObjectUtils.read;
import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.*;

class ObjectUtilsTest {


    public static Stream<Arguments> testReadDatasource() {

        return Stream.of(
            Arguments.of("{\"token\": \"AAAA\", \"refresh_token\": \"BBBB\"}",
                UserIAMResponseModel.class, new UserIAMResponseModel("AAAA", "BBBB")),
            Arguments.of("{\"token\": \"AAAA\"}",
                UserIAMResponseModel.class, new UserIAMResponseModel("AAAA")),
            Arguments.of("{\"token\": \"AAAA\"", UserIAMResponseModel.class, null)
        );
    }

    public static Stream<Arguments> testWriteDatasource() {
        return Stream.of(
            Arguments.of(new UserIAMResponseModel("AAAA", "BBBB"),
                "{\"token\":\"AAAA\",\"refresh_token\":\"BBBB\"}"),
            Arguments.of(new UserIAMResponseModel("AAAA"),
                "{\"token\":\"AAAA\"}")
        );
    }


    public static Stream<Arguments> testConvertDatasource() {

        return Stream.of(
            Arguments.of(
                MapUtils.of("token", "AAAA", "refresh_token", "BBBB"),
                UserIAMResponseModel.class,
                new UserIAMResponseModel("AAAA", "BBBB")),
            Arguments.of(
                singletonMap("token", "AAAA"),
                UserIAMResponseModel.class,
                new UserIAMResponseModel("AAAA")),
            Arguments.of(
                singletonMap("demo", "AAAA"),
                UserIAMResponseModel.class,
                null)
        );
    }

    public static Stream<Arguments> testConvertsDatasource() {

        return Stream.of(
            Arguments.of(
                singletonList(MapUtils.of("token", "AAAA", "refresh_token", "BBBB")),
                UserIAMResponseModel.class,
                singletonList(new UserIAMResponseModel("AAAA", "BBBB"))),
            Arguments.of(
                singletonList(singletonMap("token", "AAAA")),
                UserIAMResponseModel.class,
                singletonList(new UserIAMResponseModel("AAAA")))
        );
    }


    /**
     * @param json
     * @param classOf
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testRead({0})")
    @MethodSource("testReadDatasource")
    <T> void testRead(String json, Class<T> classOf, T expected) {
        if (null == expected) {
            assertNull(read(json, classOf));
        } else {
            assertEquals(expected, read(json, classOf));
        }

    }

    /**
     * @param request
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testWrite() = {1}")
    @MethodSource("testWriteDatasource")
    <T> void testWrite(Object request, String expected) {
        if (null == expected) {
            assertNull(ObjectUtils.write(request));
        } else {
            assertEquals(expected, ObjectUtils.write(request));
        }
    }


    /**
     * @param request
     * @param classOf
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testConvert() = {1}")
    @MethodSource("testConvertDatasource")
    <T> void testConvert(Object request, Class<T> classOf, T expected) {
        assertEquals(expected, ObjectUtils.convert(request, classOf));
    }

    /**
     * @param request
     * @param classOf
     * @param expected
     * @param <T>
     * @author Harsh Shah
     */
    @ParameterizedTest(name = "{index}: testConverts()")
    @MethodSource("testConvertsDatasource")
    <T> void testConverts(List<Object> request, Class<T> classOf, List<T> expected) {
        assertArrayEquals(expected.toArray(), ObjectUtils.convert(request, classOf).toArray());
    }

}

