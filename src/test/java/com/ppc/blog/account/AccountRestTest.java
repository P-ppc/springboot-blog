package com.ppc.blog.account;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ppc.blog.account.AccountDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
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

  @After
  public void tearDown() throws Exception {
    accountDAO.deleteAll();
  }
  
  @Test
  public void shouldReturnError() throws Exception {
    mockMvc.perform(post("/signUp"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("$.error.code", is("COMM_ERROR_UNKNOWN")));
  }

  @Test
  public void shouldNotPassValid() throws Exception {
    JSONObject params = new JSONObject();
    params.put("userName", "ppc");

    mockMvc.perform(post("/signUp")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(params.toString()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.error.code", is("COMM_ERROR_VALID")));
  }

  @Test
  public void shouldSuccess() throws Exception {
    mockMvc.perform(post("/signUp")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(params.toString()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.id").exists());
    
  }

  @Test
  public void shouldOccupy() throws Exception {
    mockMvc.perform(post("/signUp")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(params.toString()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.id").exists());

    mockMvc.perform(post("/signUp")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(params.toString()))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.error.code", is("ACCOUNT_KEY_OCCUPY")));
  }
}
