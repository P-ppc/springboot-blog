package com.ppc.blog.account;

import org.hibernate.validator.constraints.ScriptAssert;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.Size;

@ScriptAssert(lang = "javascript",
  script = "com.ppc.blog.account.AccountValidator.checkRepeatPassword(_this.password, _this.repeatPassword)",
  message = "repeatPassword must be same with password")

public class AccountValidator {
  @NotBlank(message = "userName must not be empty")
  @Size(min = 3, max = 10, message = "userName length must between 3 ~ 10")
  private String userName;

  @NotBlank(message = "email must not be empty")
  @Email(message = "email must be a validate email")
  @Size(min = 6, max = 15, message = "email length must between 6 ~ 15")
  private String email;

  @NotBlank(message = "password must not be empty")
  @Size(min = 6, max = 15, message = "password length must between 6 ~ 15")
  private String password;

  private String repeatPassword;
  
  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setPassword(String password) {
    this.password = password; 
  }

  public String getPassword() {
    return password;
  }

  public void setRepeatPassword(String repeatPassword) {
    this.repeatPassword = repeatPassword;
  }

  public String getRepeatPassword() {
    return repeatPassword;
  }

  public static boolean checkRepeatPassword(String password, String repeatPassword) {
    if (password != null && password.equals(repeatPassword)) {
      return true;
    }
    return false;
  }
}
