package vn.itz.plansync.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.itz.plansync.model.Permission;
import vn.itz.plansync.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomUserDetails implements UserDetails {
  private User user;
  private List<GrantedAuthority> authorities;

  public CustomUserDetails(User user) {
    this.user = user;
    this.authorities = mapPermissionsToAuthorities(user.getRole().getPermissions());
  }

  private List<GrantedAuthority> mapPermissionsToAuthorities(Set<Permission> permissions) {
    return permissions.stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPcode()))
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
