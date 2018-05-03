package com.ppc.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Help {
  @GetMapping("/help")
  public String help() {
    return "springboot-blog is a simple springboot blog rest server!\n"
    + "Now the api version is v1! \n"
    + "We support these api as below:\n"  
    + "1. /help <GET>\n"
    + "2. /signUp <POST> \n"
    + "3. /signIn <POST> \n"
    + "4. /signOut <GET> \n"
    + "5. /accounts/{id} <GET/PUT> \n"
    + "6. /accounts/{id}/password <PUT>\n"
    + "7. /articles <POST> \n"
    + "8. /articles/{id} <GET/PUT/DELETE> \n"
    + "9. /articles/queryAll <POST> \n"
    + "10. /articles/queryPage <POST> \n"
    + "11. /articles/{id}/comments <GET> \n"
    + "12. /comments <POST> \n"
    + "13. /comments/{id} <GET> \n";
  }
}
