package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.response.SectionResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.StockResponseDTO;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockResponseDTO stockEntityToResponseDTO(Stock stock){
        return StockResponseDTO.builder()
                .batchNumber(stock.getBatchNumber())
                .dueDate(stock.getDueDate())
                .currentQuantity(stock.getCurrentQuantity())
                .build();
    }
}
