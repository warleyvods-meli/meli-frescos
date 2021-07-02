package com.mercadolibre.dambetan01.model;

import com.mercadolibre.dambetan01.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataOrder;

    @OneToOne
    public Buyer buyer;

    @Enumerated(EnumType.STRING)
    public OrderStatus orderStatus;

    @OneToMany(mappedBy = "purchaseOrder")
    public List<Product> products;

}
