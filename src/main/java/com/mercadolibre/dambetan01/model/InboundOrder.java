package com.mercadolibre.dambetan01.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="inbound_order")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;

    private int orderNumber;

    @OneToOne
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "id_section_fk", nullable = false)
    private Section section;

    @Transient
    private List<Stock> batchStock;

}
