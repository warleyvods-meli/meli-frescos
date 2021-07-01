package com.mercadolibre.dambetan01.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Agent extends User{

    public Agent(Long id, String name, String email, String password, List <UserType> userType) {
        super(id, name, email, password, userType);
    }

    public void orderEntry(){};
}