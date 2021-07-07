package com.mercadolibre.dambetan01.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductLocationResponseDTO {

    private Long productId;

    private List<StockAndSectionResponseDTO> batchStock;


}
