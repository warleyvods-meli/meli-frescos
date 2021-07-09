package com.mercadolibre.dambetan01.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDueDateResponseDTO {

    private int batchNumber;
    private long productId;
    private String productType;
    private LocalDate dueDate;
    private int quantity;

}
