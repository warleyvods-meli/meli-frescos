package com.mercadolibre.dambetan01.dtos.request;

import com.mercadolibre.dambetan01.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class PurchaseOrderRequestDTO {

    @Schema(description = "Id Purchase Order", example = "1", required = true)
    private Long id;

    @NotBlank
    @Schema(description = "Id from buyer", example = "1", required = true)
    private Long buyerId;

    @Schema(description = "Date order", example = "2021-01-01")
    private LocalDate dataOrder;

    @NotNull
    @Schema(description = "Status Order", example = "INPROGRESS")
    private OrderStatus orderStatus;

    @NotNull
    @Schema(description = "Product List")
    private List<ProductDTO> products;

    @Getter
    @Setter
    public static class ProductDTO {

        @Schema(description = "Id Product", example = "1")
        private Long id;

        @Schema(description = "Quantity of product", example = "10")
        private Integer quantity;

    }

}
