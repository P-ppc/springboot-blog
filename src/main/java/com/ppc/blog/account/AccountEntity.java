package com.ppc.blog.account;

import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_account")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class AccountEntity {
  private String id;
  private String userName;
  private String password;
  private String email;
  private Date createdTime;
  private Date updatedTime;  

  public void setId(String id) {
    this.id = id;
  }

  @Id
  @GeneratedValue(generator = "uuid2")
  @Column(length = 32)
  public String getId() {
    return id;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Column(nullable = false, updatable = false, length = 10) 
  public String getUserName() {
    return userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(nullable = false, length = 15) 
  public String getPassword() {
    return password; 
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(nullable = false, length = 25)
  public String getEmail() {
    return email;
  }
  
  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  @Column(nullable = false, updatable = false)
  public Date getCreatedTime() {
    return createdTime;
  }

  public void setUpdatedTime(Date updatedTime) {
    this.updatedTime = updatedTime;
  }

  public Date getUpdatedTime() {
    return updatedTime;
  }

  @Override
  public String toString() {
    return "[ id: " + id + ", userName: " + userName + " ]";
  }
}
