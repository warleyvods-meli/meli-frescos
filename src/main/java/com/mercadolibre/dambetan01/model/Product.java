package com.mercadolibre.dambetan01.model;

import com.mercadolibre.dambetan01.enums.StorageType;
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
    private Double price;

    @Enumerated(EnumType.STRING)
    private StorageType category;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @OneToOne
    private Stock stock;



}
