package com.mercadolibre.dambetan01.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InboundOrderPutRequest {

    private int orderNumber;
    private LocalDate orderDate;
    private SectionRequest section;
    private List <StockPutRequest> batchStock;

}
