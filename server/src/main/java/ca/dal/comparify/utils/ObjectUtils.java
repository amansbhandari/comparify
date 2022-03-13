package ca.dal.comparify.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectUtils {

    private static ObjectMapper mapper = null;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    private ObjectUtils(){}

    public static <T> T read(String json, Class<T> classOf){
        try {
            return mapper.readValue(json, classOf);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
