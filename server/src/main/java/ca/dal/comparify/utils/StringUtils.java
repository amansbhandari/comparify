package ca.dal.comparify.utils;

public class StringUtils {

    private StringUtils(){}

    public static boolean isEmpty(String str){
        return org.springframework.util.StringUtils.hasLength(str);
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
