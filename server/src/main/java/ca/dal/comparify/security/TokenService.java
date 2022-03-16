package ca.dal.comparify.security;

import ca.dal.comparify.framework.security.filters.jwt.AuthenticationProviders;
import ca.dal.comparify.user.model.iam.authentication.UserPrincipal;
import ca.dal.comparify.utils.DateUtils;
import ca.dal.comparify.utils.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static java.util.Collections.emptyList;

@Service
public class TokenService implements AuthenticationProviders {

    @Value("${jwt.token.expiration}")
    private long tokenExpiration;

    @Value("${jwt.token.secret}")
    private String tokenSecret;

    @Value("${jwt.token.issuer}")
    private String issuer;

    /**
     * @param authentication
     * @return
     *
     * @author Harsh Shah
     */
    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return JWT.create()
                    .withSubject(userPrincipal.getId())
                    .withIssuer(issuer)
                    .withIssuedAt(DateUtils.dateNow())
                    .withExpiresAt(DateUtils.addSecondsToDateNow(tokenExpiration))
                    .sign(getAlgorithm(tokenSecret));
    }


    /**
     * @param request
     * @return
     *
     * @author Harsh Shah
     */
    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTHENTICATE_HEADER_STRING);

        if(StringUtils.isEmpty(token)){
            return null;
        }

        String user = getAuthenticationUser(token);
        return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
    }

    /**
     * @param token
     * @return
     *
     * @author Harsh Shah
     */
    public String getAuthenticationUser(String token) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm(tokenSecret))
                    .withIssuer(issuer)
                    .build();

            verifier.verify(token);

            return JWT.decode(token).getSubject();

        } catch (JWTVerificationException e) {
            return null;
        }
    }

    /**
     * @param secret
     * @return
     *
     * @author Harsh Shah
     */
    private Algorithm getAlgorithm(String secret){
        return Algorithm.HMAC256(secret);
    }
}
