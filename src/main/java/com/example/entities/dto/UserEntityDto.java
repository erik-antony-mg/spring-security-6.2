package com.example.entities.dto;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;


import java.time.LocalDate;
import java.util.Set;

@Getter
public class UserEntityDto {

    @Email
    @NotBlank
    @Size(max = 80)
    private String email;
    @NotBlank
    @Size(max = 30)
    private String username;
    @NotBlank
    private String password;
    @NotNull(message = "la fecha no puede ser nula")
    private LocalDate dateBirth;
    private Set<String> roles;

}
