package com.mercadolibre.dambetan01.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
public class Agent extends User{

    public void orderEntry(){};
}
