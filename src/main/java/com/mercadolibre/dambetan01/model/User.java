package com.mercadolibre.dambetan01.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;


@Data
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

    public User(Long id, String name, String email, String password, List <UserType> userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List <UserType> getUserType() {
        return userType;
    }

    public void setUserType(List <UserType> userType) {
        this.userType = userType;
    }
}
