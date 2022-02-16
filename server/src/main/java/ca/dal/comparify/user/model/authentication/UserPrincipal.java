package ca.dal.comparify.user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserPrincipal implements UserDetails {

  private String id;

  private String userIdentifier;

  private String secret;

  private Collection<? extends GrantedAuthority> authorities;

  private boolean isLocked;

  private boolean isActive;

  private boolean isAccountExpired;

  private boolean isSecretExpired;


  public UserPrincipal(String id, String userIdentifier, String secret,
                       Collection<? extends GrantedAuthority> authorities,
                       boolean isLocked, boolean isActive, boolean isAccountExpired,
                       boolean isSecretExpired) {
    this.id = id;
    this.userIdentifier = userIdentifier;
    this.secret = secret;
    this.authorities = authorities;
    this.isLocked = isLocked;
    this.isActive = isActive;
    this.isAccountExpired = isAccountExpired;
    this.isSecretExpired = isSecretExpired;
  }

  public static UserPrincipal create(UserAuthenticationModel model) {

    return new UserPrincipal(model.getId(),
            model.getUserIdentifier(),
            model.getSecret(),
            model.getAuthorities(),
            model.isLocked(),
            model.isActive(),
            model.isAccountExpired(),
            model.isSecretExpired());
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
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isAccountExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isSecretExpired;
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
}
