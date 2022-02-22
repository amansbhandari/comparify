package ca.dal.comparify.user.model.authentication;

import ca.dal.comparify.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class UserAuthenticationRequestModel implements Serializable {

    private static final String USER_IDENTIFIER = "user_identifier";
    private static final String SECRET = "secret";

    @JsonProperty(USER_IDENTIFIER)
    private String userIdentifier;

    private String secret;

    public UserAuthenticationRequestModel(String userIdentifier, String secret) {
        this.userIdentifier = userIdentifier;
        this.secret = secret;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public boolean isEmpty(){
        return StringUtils.isAnyEmpty(userIdentifier, secret);
    }

    public List<String> getRequiredFields(){
        return Arrays.asList(USER_IDENTIFIER, SECRET);
    }
}
