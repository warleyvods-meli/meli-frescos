package com.mercadolibre.dambetan01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Seller extends User{

    @Column
    List<String> products;

    public Seller(Long id, String name, String email, String password, List <UserType> userType, List <String> products) {
        super(id, name, email, password, userType);
        this.products = products;
    }
}
