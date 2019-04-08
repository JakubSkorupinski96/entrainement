package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import model.User;

@Service
public class UserServices implements UserDetailsService  {

  @Autowired
  UserDAO userDAO;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDAO.showUser(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new UserPrincipal(user);
  }

}
