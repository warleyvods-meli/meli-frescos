package com.mercadolibre.dambetan01.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends User{

    @Column
    List<String> products;
}
