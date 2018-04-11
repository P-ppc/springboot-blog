package com.ppc.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Help {
  @GetMapping("/help")
  public String help() {
    return "springboot-blog is a simple springboot blog rest server!\n"
    + "We support these api as below:\n"  
    + "1. /help <GET>\n"
    + "2. /signUp <POST> \n"
    + "3. /signIn <POST> \n"
    + "4. /signOut <GET> \n"
    + "5. /articles <GET/POST> \n"
    + "6. /articles/{id} <GET/PUT/DELETE> \n"
    + "7. /comments <GET/POST> \n";
  }
}
