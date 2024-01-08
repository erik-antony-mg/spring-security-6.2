package com.example.controllers;


import com.example.entities.dto.UserEntityDto;
import com.example.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PrincipalController {

    private UserService userService;

    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllUsers(){
        return  ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/hola")
    public String hello(){
        return  "hola sin seguridad";
    }
    @GetMapping("/hola2")
    public String hello2(){
        return  "hola con seguridad";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Validated @RequestBody UserEntityDto userEntityDto){
        return  ResponseEntity.ok(userService.createUser(userEntityDto));
    }

    @DeleteMapping("/deleteUser/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable Long idUser){
        userService.deleteUser(idUser);
        Map<String,String> resp = new HashMap<>();
        resp.put("mensaje","el usuario con el  id : "+idUser+" ha sido eliminado!!");
        return ResponseEntity.ok(resp);
    }
}
