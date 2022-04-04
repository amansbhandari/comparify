package ca.dal.comparify.user.model.iam;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserIAMResponseModel {

    private String token;

    @JsonProperty("refresh_token")
    private String refreshToken;

    public UserIAMResponseModel(String token) {
        this.token = token;
    }

    public UserIAMResponseModel(String token, String refreshToken) {
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

    @Override
    public String toString() {
        return "UserIAMResponseModel{" +
            "token='" + token + '\'' +
            '}';
    }
}
