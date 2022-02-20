package ca.dal.comparify.user.model.authentication;

import ca.dal.comparify.utils.StringUtils;

public class UserAuthenticationRequestModel {

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


}
