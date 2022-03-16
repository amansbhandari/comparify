package ca.dal.comparify.user.model.iam.authentication;

import ca.dal.comparify.utils.DateUtils;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;

public class UserAuthenticationModel {

    private String secret;

    @BsonProperty("is_active")
    private boolean isActive;

    @BsonProperty("number_of_incorrect_attempts")
    private Integer numberOfIncorrectAttempts;

    @BsonProperty("account_expires_on")
    private LocalDate accountExpiresOn;

    @BsonProperty("secret_expires_on")
    private LocalDate secretExpiresOn;

    private Collection<GrantedAuthority> authorities;

    @BsonCreator
    public UserAuthenticationModel(@BsonProperty("secret") String secret,
                                   @BsonProperty("is_active") boolean isActive,
                                   @BsonProperty("number_of_incorrect_attempts") Integer numberOfIncorrectAttempts,
                                   @BsonProperty("account_expires_on") LocalDate accountExpiresOn,
                                   @BsonProperty("secret_expires_on") LocalDate secretExpiresOn,
                                   @BsonProperty("authorities") Collection<GrantedAuthority> authorities) {
        this.secret = secret;
        this.isActive = isActive;
        this.numberOfIncorrectAttempts = numberOfIncorrectAttempts;
        this.accountExpiresOn = accountExpiresOn;
        this.secretExpiresOn = secretExpiresOn;
        this.authorities = authorities;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @BsonIgnore()
    public boolean isActive() {
        return getIsActive();
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public Integer getNumberOfIncorrectAttempts() {
        return numberOfIncorrectAttempts;
    }

    public void setNumberOfIncorrectAttempts(Integer numberOfIncorrectAttempts) {
        this.numberOfIncorrectAttempts = numberOfIncorrectAttempts;
    }

    public LocalDate getAccountExpiresOn() {
        return accountExpiresOn;
    }

    public void setAccountExpiresOn(LocalDate accountExpiresOn) {
        this.accountExpiresOn = accountExpiresOn;
    }

    public LocalDate getSecretExpiresOn() {
        return secretExpiresOn;
    }

    public void setSecretExpiresOn(LocalDate secretExpiresOn) {
        this.secretExpiresOn = secretExpiresOn;
    }

    @BsonIgnore()
    public boolean isLocked() {
        return numberOfIncorrectAttempts > 2;
    }

    @BsonIgnore()
    public boolean isAccountExpired() {
        return DateUtils.isAfterNow(accountExpiresOn);
    }

    @BsonIgnore()
    public boolean isSecretExpired() {
        return DateUtils.isAfterNow(secretExpiresOn);
    }

}
