package ca.dal.comparify.user.model.iam;

import ca.dal.comparify.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class UserIAMRequestModel implements Serializable {

    private static final String USER_IDENTIFIER = "user_identifier";

    private static final String SECRET = "secret";

    @JsonProperty(USER_IDENTIFIER)
    private String userIdentifier;

    private String userSecret;

    public UserIAMRequestModel(String userIdentifier, String secret) {
        this.userIdentifier = userIdentifier;
        this.userSecret = secret;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
    }

    public boolean isEmpty(){
        return StringUtils.isAnyEmpty(userIdentifier, userSecret);
    }

    public List<String> getRequiredFields(){
        return Arrays.asList(USER_IDENTIFIER, SECRET);
    }
}
