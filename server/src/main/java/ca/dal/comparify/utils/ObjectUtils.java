package ca.dal.comparify.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectUtils {

    private static ObjectMapper mapper = null;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private ObjectUtils() {
    }

    /**
     * @param json
     * @param classOf
     * @param <T>
     * @return
     * @author Harsh Shah
     */
    public static <T> T read(String json, Class<T> classOf) {
        try {
            return mapper.readValue(json, classOf);
        } catch (JsonProcessingException e) {
            return null;
        }
    }


    /**
     * @param object
     * @return
     * @author Harsh Shah
     */
    public static String write(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * @param object
     * @param classOf
     * @param <T>
     * @return
     * @author Harsh Shah
     */
    public static <T> T convert(Object object, Class<T> classOf) {
        try {
            return read(mapper.writeValueAsString(object), classOf);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * @param objects
     * @param classOf
     * @param <T>
     * @return
     * @author Harsh Shah
     */
    public static <T> List<T> convert(List<Object> objects, Class<T> classOf) {
        return objects.stream().map(object -> convert(object, classOf)).collect(Collectors.toList());
    }
}
