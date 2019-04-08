package spring;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import model.User;

public class UserPrincipal implements UserDetails{

  private User user;
  
  public UserPrincipal(User user) {
    this.user = user;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //This is useless
    return null;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    //this is useless
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    //useless
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    //useless
    return true;
  }

  @Override
  public boolean isEnabled() {
    //muda muda muda
    return true;
  }

}
