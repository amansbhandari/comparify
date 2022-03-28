package ca.dal.comparify.utils;

import org.springframework.security.core.context.SecurityContext;

/**
 * @author Harsh Shah
 */
public class SecurityUtils {

    private SecurityUtils(){}

    /**
     * @param context
     * @return
     *
     * @author Harsh Shah
     */
    public static String getPrincipal(SecurityContext context) {
        return (String) context.getAuthentication().getPrincipal();
    }
}
