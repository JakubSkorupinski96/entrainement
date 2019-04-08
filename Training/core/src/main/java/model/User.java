package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;
  
//  @Column(name="sso_id", unique=true, nullable=false)
//  private String ssoId;

  @Column(nullable = false, unique = true)
  private String username;
  
  @Column(name = "password", nullable = false)
  private String password;

//  @Column(name = "role", nullable = false)
//  private ERole role;
  
  
//  public Set<UserProfile> getUserProfiles() {
//    return userProfiles;
//  }
//
//  public void setUserProfiles(Set<UserProfile> userProfiles) {
//    this.userProfiles = userProfiles;
//  }

  public int getId() {
    return userId;
  }

  public void setId(int userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
//  public String getSsoId() {
//    return ssoId;
//  }
//
//  public void setSsoId(String ssoId) {
//    this.ssoId = ssoId;
//  }

}
