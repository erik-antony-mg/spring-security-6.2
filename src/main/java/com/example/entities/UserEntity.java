package com.example.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuarios")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="usuario_id" )
    @JsonProperty("userId")
    private Long idUserEntity;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(name ="fecha_Nacimiento")
    private LocalDate dateBirth;
    @Column(name ="edad")
    private Integer age;
    // fetch eager para que me traiga todos los roles si es lazy me traera a 1 por 1
    // cascade persist si eliminamos un usuario no se elimien los roles
    @ManyToMany(fetch = FetchType.EAGER,targetEntity = RoleEntity.class,cascade = CascadeType.PERSIST)
    // creamos la tabla intermedia llamada usuarios_roles luego se creara las llaves foraneas
    // usuario_id_fk de usuario y role_id_fk de roles
    @JoinTable(name = "usuarios_Roles",joinColumns = @JoinColumn(name = "usuario_id_fk"),
                                        inverseJoinColumns = @JoinColumn(name = "role_id_fk"))
    private Set<RoleEntity> Roles;

    @PrePersist
    private void generateAge(){
        age = Period.between(dateBirth, LocalDate.now()).getYears();
    }
}
