package ca.dal.comparify.user.model.authentication;

import ca.dal.comparify.utils.DateUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

public class UserAuthenticationModel implements Serializable {

    private String id;

    private String userIdentifier;

    private String secret;

    private Collection<? extends GrantedAuthority> authorities;

    private boolean isActive;

    private Integer numberOfIncorrectAttempts;

    private LocalDate accountExpiresOn;

    private LocalDate secretExpiresOn;

    public UserAuthenticationModel() {}

    public UserAuthenticationModel(String id, String userIdentifier, String secret,
                                   Collection<? extends GrantedAuthority> authorities,
                                   boolean isActive, Integer numberOfIncorrectAttempts,
                                   LocalDate accountExpiresOn, LocalDate secretExpiresOn) {
        this.id = id;
        this.userIdentifier = userIdentifier;
        this.secret = secret;
        this.authorities = authorities;
        this.isActive = isActive;
        this.numberOfIncorrectAttempts = numberOfIncorrectAttempts;
        this.accountExpiresOn = accountExpiresOn;
        this.secretExpiresOn = secretExpiresOn;
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

    public boolean isActive() {
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

    public boolean isLocked() {
        return numberOfIncorrectAttempts > 3;
    }

    public boolean isAccountExpired() {
        return DateUtils.isAfterNow(accountExpiresOn);
    }

    public boolean isSecretExpired() {
        return DateUtils.isAfterNow(secretExpiresOn);
    }

}
