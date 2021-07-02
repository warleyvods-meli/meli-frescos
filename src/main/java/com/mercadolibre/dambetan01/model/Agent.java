package com.mercadolibre.dambetan01.model;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
public class Agent extends User{

    public void orderEntry(){};
}
