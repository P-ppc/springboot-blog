# springboot-blog

## introduce
Simple springboot blog rest server

## what did I do?
```
springboot-blog is a simple springboot blog rest server!
Now the api version is v1!
We support these api as below:
1. /help <GET>
2. /signUp <POST>
3. /signIn <POST>
4. /signOut <GET>
5. /accounts/{id} <GET/PUT>
6. /accounts/{id}/password <PUT>
7. /articles <POST>
8. /articles/{id} <GET/PUT/DELETE>
9. /articles/queryAll <POST>
10. /articles/queryPage <POST>
11. /articles/{id}/comments <GET>
12. /comments <POST>
13. /comments/{id} <GET>
```

## quick start
1. change the src/main/resources/application.properties config to your own, especially your mysql config
2. `mvn compile` to install the dependencies and compile the source code
3. `mvn spring-boot:run` to run this project

## what did I use?

I just use this dependencies as below:
1. spring-boot-starter-parent
2. spring-boot-starter-web
3. spring-boot-devtools
4. junit
5. spring-boot-starter-data-jpa
6. mysql-connector-java
7. fastjson
8. jackson-core
9. spring-security-crypto
10. commons-lang3

## feedback
You can create a issue or send Email for me.

