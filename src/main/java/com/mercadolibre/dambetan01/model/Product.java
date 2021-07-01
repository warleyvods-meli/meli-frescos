package com.mercadolibre.dambetan01.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String category;

    @ManyToOne
    private Seller seller;



}
