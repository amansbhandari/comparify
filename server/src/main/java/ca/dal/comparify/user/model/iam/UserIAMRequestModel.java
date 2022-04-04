package ca.dal.comparify.user.model.iam;

import ca.dal.comparify.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class UserIAMRequestModel implements Serializable {

    private static final String USER_IDENTIFIER = "user_identifier";

    private static final String USER_SECRET = "user_secret";

    private String id;

    @JsonProperty(USER_IDENTIFIER)
    private String userIdentifier;

    @JsonProperty(USER_SECRET)
    private String userSecret;


    public UserIAMRequestModel() {
    }

    public UserIAMRequestModel(String id, String userIdentifier, String userSecret) {
        this.id = id;
        this.userIdentifier = userIdentifier;
        this.userSecret = userSecret;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public boolean isEmpty(){
        return StringUtils.isAnyEmpty(userIdentifier, userSecret);
    }

    @JsonIgnore
    public boolean isAllEmpty(){
        return StringUtils.isAllEmpty(userIdentifier, userSecret);
    }

    @JsonIgnore
    public List<String> getRequiredFields(){
        return Arrays.asList(USER_IDENTIFIER, USER_SECRET);
    }

    @Override
    public String toString() {
        return "UserIAMRequestModel{" +
            "userIdentifier='" + userIdentifier + '\'' +
            ", userSecret='" + userSecret + '\'' +
            '}';
    }
}
