package com.mercadolibre.dambetan01.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="order_in")
public class OrderIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "id_section_fk", nullable = false)
    private Section section;

    //@OneToMany
    //private List<BatchStock> batchStock;



}
