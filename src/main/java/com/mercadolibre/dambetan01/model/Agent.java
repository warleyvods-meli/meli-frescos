package com.mercadolibre.dambetan01.model;

import com.mercadolibre.dambetan01.service.chain.InboundedOrder.*;
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
    private User personalData;

    @ManyToOne
    private Warehouse warehouse;

    @OneToOne
    private InboundOrder inboundOrder;

    public InboundOrder validateInboundOrder(InboundOrder inboundOrder){
        InboundOrderChecker chain =
                new CheckerByWarehouse(new CheckerByAgent(new CheckerByProductSection(new CheckerByStockTemperature(new CheckerBySectionSpace(new CheckerFinalyze())))));
        boolean resultChecker = chain.verify(inboundOrder);
        return  inboundOrder;
    };
}
