package ca.dal.comparify.framework.security.filters.jwt;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationProviders {

    public final String AUTHENTICATE_TOKEN_PREFIX = "Bearer";
    public final String AUTHENTICATE_HEADER_STRING = "Authorization";

    public Authentication getAuthentication(HttpServletRequest request);
}
