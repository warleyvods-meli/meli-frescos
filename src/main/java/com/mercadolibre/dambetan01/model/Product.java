package com.mercadolibre.dambetan01.model;

import com.mercadolibre.dambetan01.enums.StorageType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @ManyToMany(mappedBy = "products")
    private List<PurchaseOrder> purchaseOrder;

    @OneToMany(mappedBy = "product")
    private List<Stock> stockList;

}
