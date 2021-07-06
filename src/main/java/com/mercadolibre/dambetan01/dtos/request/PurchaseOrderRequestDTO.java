package com.mercadolibre.dambetan01.dtos.request;

import com.mercadolibre.dambetan01.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PurchaseOrderRequestDTO {

    private Long buyerId;
    private LocalDate dataOrder;
    private OrderStatus orderStatus;
    private List<ProductDTO> products;

    @Data
    public static class ProductDTO {

        private Long id;
        private Integer quantity;

    }

}
