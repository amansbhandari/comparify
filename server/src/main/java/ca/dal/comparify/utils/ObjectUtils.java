package ca.dal.comparify.utils;

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
    }

    private ObjectUtils(){}

    /**
     * @param json
     * @param classOf
     * @param <T>
     * @return
     */
    public static <T> T read(String json, Class<T> classOf){
        try {
            return mapper.readValue(json, classOf);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * @param object
     * @param classOf
     * @param <T>
     * @return
     *
     * @author Harsh Shah
     */
    public static <T> T convert(Object object, Class<T> classOf){
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
     *
     * @author Harsh Shah
     */
    public static <T> List<T> convert(List<Object> objects, Class<T> classOf){
        return objects.stream().map(object -> convert(object, classOf)).collect(Collectors.toList());
    }
}
