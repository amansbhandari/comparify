package ca.dal.comparify.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectUtils {

    private ObjectUtils(){}

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T read(String json, Class<T> classOf){
        try {
            return mapper.readValue(json, classOf);
        } catch (JsonProcessingException e) {

        }
        return null;
    }


}
