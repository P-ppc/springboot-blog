package com.ppc.blog.account;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_account")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class AccountEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "uuid2")
  @Column(length = 36)
  private String id;

  @Column(nullable = false, updatable = false, length = 10) 
  private String userName;

  @Column(nullable = false, length = 60) 
  @JsonIgnore
  private String password;

  @Column(nullable = false, length = 25)
  private String email;

  @Column(nullable = false, updatable = false)
  private Date createdTime;

  private Date updatedTime;  

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password; 
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }
  
  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

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
