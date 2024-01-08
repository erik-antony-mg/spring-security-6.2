# Spring Security 6
# #Spring Boot 3, MySQL, JPA, Rest API, Swagger, Maven,Jwt,SpringSecurity

Create Restful CRUD API as a practice using Spring Boot, Mysql, JPA , Hibernate, SpringSecurity.

## Steps to Setup

**1. Clone the application**

```bash
 git clone https://github.com/erik-antony-mg/spring-security-6.2.git
```

**2. Create Mysql database**
```bash
create database: db_security
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Run the app using maven**


- The app will start running at <http://localhost:8085>
- swagger will start running at <http://localhost:8085/swagger-ui/index.html>

### EndPoints
#### Principal Controller
| Method | Url | Description | Security |
| ------ | --- | ------|-------|
| GET    | /api/usuarios | Get all users |NO
| GET    |  /api/hola | returns a string "hello with no security" |NO
| GET     |  /api/hola2 | returns a string "hello with with  security"| YES
| POST    | /api/createUser | Create user| NO
|DELETE  | /api/deleteUser/{idUser} |  Delete user| YES
| POST    |  /login | you get the token and username| NO

#### Test Controller
| Method | Url | Description | Security |
| ------ | --- | ------|-------|
| <color color=green>GET</color>    | /test/admin  | you get a string only if you belong to the ADMIN role|YES
| <color color=green>GET</color>    | /test/user  | you get a string only if you belong to USER or ADMIN role| YES 
| <color color=green>GET</color>    | /test/invitado  | obtienes una cadena solo si perteneces al rol USER, INVITED o ADMIN|YES



**5. those with a yes in the security box need the bearer token**

#### Postman link 
in the postman link you can find all the end poitns with their respective filling.
```bash
https://documenter.getpostman.com/view/10472566/2s9YsJDDM9
```
