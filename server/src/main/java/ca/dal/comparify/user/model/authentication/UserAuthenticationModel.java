package ca.dal.comparify.user.model.authentication;

import ca.dal.comparify.utils.DateUtils;
import ca.dal.comparify.utils.UUIDUtils;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.security.core.GrantedAuthority;


import java.time.LocalDate;
import java.util.Collection;

public class UserAuthenticationModel {

    public static final String USER_IDENTIFIER = "user_identifier";

    @BsonId
    private String id;

    @BsonProperty(USER_IDENTIFIER)
    private String userIdentifier;

    private String secret;

    @BsonProperty("is_active")
    private boolean isActive;

    @BsonProperty("number_of_incorrect_attempts")
    private Integer numberOfIncorrectAttempts;

    @BsonProperty("account_expires_on")
    private LocalDate accountExpiresOn;

    @BsonProperty("secret_expires_on")
    private LocalDate secretExpiresOn;

    private Collection<? extends GrantedAuthority> authorities;

    @BsonCreator
    public UserAuthenticationModel(@BsonId String id,
                                   @BsonProperty(USER_IDENTIFIER) String userIdentifier,
                                   @BsonProperty("secret") String secret,
                                   @BsonProperty("is_active") boolean isActive,
                                   @BsonProperty("number_of_incorrect_attempts") Integer numberOfIncorrectAttempts,
                                   @BsonProperty("account_expires_on")  LocalDate accountExpiresOn,
                                   @BsonProperty("secret_expires_on") LocalDate secretExpiresOn,
                                   @BsonProperty("authorities") Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userIdentifier = userIdentifier;
        this.secret = secret;
        this.isActive = isActive;
        this.numberOfIncorrectAttempts = numberOfIncorrectAttempts;
        this.accountExpiresOn = accountExpiresOn;
        this.secretExpiresOn = secretExpiresOn;
        this.authorities = authorities;
    }

    public UserAuthenticationModel(String userIdentifier,
                                   String secret,
                                   boolean isActive,
                                   Integer numberOfIncorrectAttempts,
                                   LocalDate accountExpiresOn,
                                   LocalDate secretExpiresOn,
                                   Collection<? extends GrantedAuthority> authorities) {

        this.id = UUIDUtils.generate();
        this.userIdentifier = userIdentifier;
        this.secret = secret;
        this.isActive = isActive;
        this.numberOfIncorrectAttempts = numberOfIncorrectAttempts;
        this.accountExpiresOn = accountExpiresOn;
        this.secretExpiresOn = secretExpiresOn;
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @BsonIgnore()
    public boolean isActive() {
        return isActive;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
        return numberOfIncorrectAttempts > 3;
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
