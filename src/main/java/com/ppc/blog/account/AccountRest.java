package com.ppc.blog.account;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ppc.blog.account.AccountEntity;
import com.ppc.blog.common.Response;
import com.ppc.blog.account.AccountValidator;

@RestController
public class AccountRest {
  @Autowired
  private AccountDAO accountDAO;
  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  
  @RequestMapping(value = "/signUp", method = RequestMethod.POST)
  public Response signUp(
    @RequestBody
    @Validated(value = AccountValidator.SignUpGroup.class) 
    AccountValidator account) {
    List<AccountEntity> accountList = accountDAO.findByUserNameOrEmail(account.getUserName(), account.getEmail());
    if (accountList.size() > 0) {
      return new Response("ACCOUNT_KEY_OCCUPY", "userName or email has been used");
    }
    AccountEntity entity = new AccountEntity();
    entity.setUserName(account.getUserName());
    entity.setEmail(account.getEmail());
    entity.setPassword(passwordEncoder.encode(account.getPassword()));
    entity.setCreatedTime(new Date());
    accountDAO.save(entity);
    return new Response(entity);
  }

  @RequestMapping(value = "/signIn", method = RequestMethod.POST)
  public Response signIn(
    @RequestBody
    @Validated(value = AccountValidator.SignInGroup.class)
    AccountValidator account,
    HttpServletRequest request) {
    List<AccountEntity> accountList = accountDAO.findByUserName(account.getUserName());
    if (accountList.size() != 1) {
      return new Response("COMM_ERROR_VALID", "userName or password error");
    }
    AccountEntity accountEntity = accountList.get(0);
    if (!passwordEncoder.matches(account.getPassword(), accountEntity.getPassword())) {
      return new Response("COMM_ERROR_VALID", "userName or password error");
    }
    request.getSession().setAttribute("currentUser", accountEntity);
    return new Response(accountEntity);
  }

  @RequestMapping(value = "/signOut", method = RequestMethod.GET)
  public Response signOut(HttpServletRequest request) {
    request.getSession().invalidate();
    return new Response(true);
  }

  @RequestMapping(value = "/accounts/:id", method = RequestMethod.GET)
  public Response getAccount() {
    return new Response("COMM_API_UNIMPLEMENT", "this api is unimplement");
  }
  
  @RequestMapping(value = "/accounts/:id", method = RequestMethod.PUT)
  public Response updateAccount() {
    return new Response("COMM_API_UNIMPLEMENT", "this api is unimplement");
  }

  @RequestMapping(value = "/accounts/:id/password", method = RequestMethod.PUT)
  public Response updatePassword() {
    return new Response("COMM_API_UNIMPLEMENT", "this api is unimplement");
  }

  @RequestMapping(value = "/accounts/:id/passwordreset", method = RequestMethod.POST)
  public Response resetPassword() {
    return new Response("COMM_API_UNIMPLEMENT", "this api is unimplement");
  }
}
