package ca.dal.comparify.user.model.iam.authentication;

import ca.dal.comparify.user.model.iam.UserIAMModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserPrincipal implements UserDetails {

    private String id;

    private String userIdentifier;

    private String secret;

    private Collection<GrantedAuthority> authorities;

    private boolean isLocked;

    private boolean isActive;

    private boolean isAccountExpired;

    private boolean isSecretExpired;


    /**
     * @param id
     * @param userIdentifier
     * @param secret
     * @param authorities
     *
     * @author Harsh Shah
     */
    public UserPrincipal(String id, String userIdentifier, String secret,
                         Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.userIdentifier = userIdentifier;
        this.secret = secret;
        this.authorities = authorities;
        this.isLocked = false;
        this.isActive = true;
        this.isAccountExpired = false;
        this.isSecretExpired = false;
    }

    /**
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public static UserPrincipal create(UserIAMModel model) {

        UserAuthenticationModel authentication = model.getAuthentication();

        return new UserPrincipal(model.getId(), model.getUserIdentifier(),
                authentication.getSecret(), authentication.getAuthorities())
                    .setLocked(authentication.isLocked())
                    .setActive(authentication.isActive())
                    .setAccountExpired(authentication.isAccountExpired())
                    .setSecretExpired(authentication.isSecretExpired());
    }

    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return userIdentifier;
    }

    @Override
    public String getPassword() {
        return secret;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isSecretExpired;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private UserPrincipal setLocked(boolean locked) {
        isLocked = locked;
        return this;
    }

    private UserPrincipal setActive(boolean active) {
        isActive = active;
        return this;
    }

    private UserPrincipal setAccountExpired(boolean accountExpired) {
        isAccountExpired = accountExpired;
        return this;
    }

    private UserPrincipal setSecretExpired(boolean secretExpired) {
        isSecretExpired = secretExpired;
        return this;
    }
}
