package ca.dal.comparify.utils;

public class StringUtils {

    private StringUtils(){}

    public static boolean isNotEmpty(String str){
        if(str == null){
            return false;
        }
        return org.springframework.util.StringUtils.hasLength(str.trim());
    }

    public static boolean isEmpty(String str){
        return !isNotEmpty(str);
    }

    public static boolean isAnyEmpty(String... strs) {
        for(String str: strs){
            if(isEmpty(str)){
                return true;
            }
        }

        return false;
    }
}
