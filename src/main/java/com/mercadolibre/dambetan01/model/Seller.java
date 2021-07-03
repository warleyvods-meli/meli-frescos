package com.mercadolibre.dambetan01.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="seller")
public class Seller{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    User personalData;

    @OneToMany(mappedBy = "seller")
    private List<Product> productList;

}
