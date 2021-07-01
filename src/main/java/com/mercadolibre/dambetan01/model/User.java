package com.mercadolibre.dambetan01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    @Email
    String email;

    @Column
    String password;

    @Column
    @Enumerated(EnumType.STRING)
    @OneToMany(fetch = FetchType.EAGER)
    List <UserType> userType;
}
