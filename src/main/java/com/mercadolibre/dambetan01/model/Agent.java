package com.mercadolibre.dambetan01.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agent{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    User personalData;

    @ManyToOne
    private Warehouse warehouse;

    @OneToOne
    private InboundOrder inboundOrder;

    public InboundOrder createInboundOrder(InboundOrder inboundOrder){
        return  inboundOrder;
    };
}
