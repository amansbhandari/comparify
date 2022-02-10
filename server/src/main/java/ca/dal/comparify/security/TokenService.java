package ca.dal.comparify.security;

import ca.dal.comparify.utils.DateUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class TokenService {

    @Value("${jwt.token.expiration}")
    private long tokenExpiration;

    @Value("${jwt.token.secret}")
    private String tokenSecret;

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();

        return JWT.create().withSubject(userPrincipal.getName())
                    .withIssuedAt(DateUtils.now())
                    .withExpiresAt(new Date(now.getTime() + tokenExpiration))
                .sign(Algorithm.HMAC256(tokenSecret));


    }
}
