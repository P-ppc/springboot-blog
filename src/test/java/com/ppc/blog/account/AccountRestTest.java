package com.ppc.blog.account;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppc.blog.account.AccountDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountRestTest {
  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  private JSONObject params;

  @Autowired
  private AccountDAO accountDAO;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();  
    params = new JSONObject();
    params.put("userName", "ppc");
    params.put("email", "ppc@test.com");
    params.put("password", "password");
    params.put("repeatPassword", "password");
  }

  @Test
  public void AShouldReturnError() throws Exception {
    System.out.println("1: -------------------------");
    mockMvc.perform(post("/signUp"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("$.error.code", is("COMM_ERROR_UNKNOWN")));
  }

  @Test
  public void BShouldNotPassValid() throws Exception {
    System.out.println("2: -------------------------");
    JSONObject params = new JSONObject();
    params.put("userName", "ppc");

    mockMvc.perform(post("/signUp")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(params.toString()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.error.code", is("COMM_ERROR_VALID")));
  }

  @Test
  public void CShouldSuccess() throws Exception {
    System.out.println("3: -------------------------");
    mockMvc.perform(post("/signUp")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(params.toString()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.id").exists());
  }

  @Test
  public void DShouldOccupy() throws Exception {
    System.out.println("4: -------------------------");
    mockMvc.perform(post("/signUp")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(params.toString()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.error.code", is("ACCOUNT_KEY_OCCUPY")));
  }

  @Test
  public void EShouldSignInSuccess() throws Exception {
    System.out.println("5: -------------------------");
    JSONObject params = new JSONObject();
    params.put("userName", "ppc");
    params.put("password", "password");

    mockMvc.perform(post("/signIn")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(params.toString()))
      .andExpect(status().isOk())
      .andDo(print())
      .andExpect(jsonPath("$.data.id").exists());
  }

  @Test
  public void FShouldSignOutSuccess() throws Exception {
    System.out.println("6: -------------------------");
    mockMvc.perform(get("/signOut"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data", is(true)));
  }

  @Test
  public void ZTearDown() throws Exception {
    System.out.println("TearDown: -------------------------");
    accountDAO.deleteAll();
  }
}
