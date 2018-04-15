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
    + "5. /profile/${id} <GET/PUT> \n"
    + "6. /profile/${id}/password <PUT>\n"
    + "7. /profile/${id}/passwordreset <POST>\n"
    + "8. /articles <GET/POST> \n"
    + "9. /articles/{id} <GET/PUT/DELETE> \n"
    + "10. /articles/${id}/comments <GET/POST> \n";
  }
}
