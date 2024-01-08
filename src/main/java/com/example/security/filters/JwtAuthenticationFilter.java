package com.example.security.filters;

import com.example.entities.UserEntity;
import com.example.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtils jwtUtils;

    // Su objetivo principal es realizar el proceso de autenticación basado en los datos presentes en la solicitud HTTP
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        UserEntity userEntity;
        String username;
        String password;
        try{
            // de la peticion (request) uso jackson para convertirlo a una clase java
            userEntity=new ObjectMapper().readValue(request.getInputStream(),UserEntity.class);
            username=userEntity.getUsername();
            password=userEntity.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //crea un token de autenticación con el username y contraseña específicos,
        // que se utiliza para iniciar el proceso de autenticación
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username,password);

        return getAuthenticationManager()
                .authenticate(authenticationToken);
    }
    //verifica la autentificiacion
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateToken(user.getUsername());
        response.addHeader("Authorization","Bearer "+token);

        Map<String, Object> resp= new HashMap<>();
        resp.put("token",token);
        resp.put("message","Autentificacion Correcta");
        resp.put("Username",user.getUsername());

        //que valores llevara nuestra respuesta
        response.getWriter().write(new ObjectMapper().writeValueAsString(resp));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
