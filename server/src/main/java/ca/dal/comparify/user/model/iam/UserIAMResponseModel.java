package ca.dal.comparify.user.model.iam;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UserIAMResponseModel {

    private String token;

    @JsonProperty("refresh_token")
    private String refreshToken;

    public UserIAMResponseModel() {}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserIAMResponseModel)) return false;
        UserIAMResponseModel that = (UserIAMResponseModel) o;
        return Objects.equals(getToken(), that.getToken()) && Objects.equals(getRefreshToken(), that.getRefreshToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getRefreshToken());
    }

    @Override
    public String toString() {
        return "UserIAMResponseModel{" +
            "token='" + token + '\'' +
            '}';
    }
}
