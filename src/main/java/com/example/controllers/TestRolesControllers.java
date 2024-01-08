package com.example.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestRolesControllers {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin(){
        return "hola tienes el roll de ADMIN";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    public String accessUser(){
        return "hola tienes el roll de USER";
    }

    @GetMapping("/invitado")
    @PreAuthorize("hasAnyRole('ADMIN','USER','INVITED') ")
    public String accessInvited(){
        return "hola tienes el roll de Invited";
    }
}
