package com.mercadolibre.dambetan01.model;

import com.mercadolibre.dambetan01.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;


@Data
@Builder
@Embeddable
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;

    @Email
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

}
