package spring;

import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  UserServices userServices;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests().anyRequest().authenticated()
          .and()
          .authorizeRequests().antMatchers("/login**").permitAll()
          .and()
          .formLogin()
          .loginProcessingUrl("/signin")
          .defaultSuccessUrl("/Dashboard?search=", true)
          .failureUrl("/login?error")
          .permitAll()
          .and()
          .logout()
          .logoutUrl("/logout")
          .logoutSuccessUrl("/login")
          .permitAll()
          .and()
          .csrf()
          .disable();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Override
  public void configure(AuthenticationManagerBuilder authentication) throws Exception {
    authentication.authenticationProvider(authenticationProvider());
}
  
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
      authenticationProvider.setUserDetailsService(userServices);
      authenticationProvider.setPasswordEncoder(passwordEncoder());
      return authenticationProvider;
  }
  
  @Bean
  public AuthenticationTrustResolver getAuthenticationTrustResolver() {
      return new AuthenticationTrustResolverImpl();
  }

}
