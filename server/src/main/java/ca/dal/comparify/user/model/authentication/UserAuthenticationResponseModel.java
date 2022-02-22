package ca.dal.comparify.user.model.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAuthenticationResponseModel {

    private String token;

    @JsonProperty("refresh_token")
    private String refreshToken;

    public UserAuthenticationResponseModel(String token) {
        this.token = token;
    }

    public UserAuthenticationResponseModel(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
