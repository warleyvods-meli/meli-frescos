package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.response.StockDueDateResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.StockResponseDTO;
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

    public StockDueDateResponseDTO stockDueDateToResponseDTO(Stock stock){
        return StockDueDateResponseDTO.builder()
                .batchNumber(stock.getBatchNumber())
                .productId(stock.getProduct().getId())
                .productType(stock.getProduct().getCategory().name())
                .dueDate(stock.getDueDate())
                .quantity(stock.getCurrentQuantity())
                .build();
    }
}
