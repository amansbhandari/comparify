package ca.dal.comparify.utils;

import java.util.UUID;

public class UUIDUtils {

    private UUIDUtils(){}

    public static String generate(){
        return UUID.randomUUID().toString();
    }
}
