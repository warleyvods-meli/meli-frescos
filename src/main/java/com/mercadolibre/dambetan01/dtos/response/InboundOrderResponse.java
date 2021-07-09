package com.mercadolibre.dambetan01.dtos.response;

import com.mercadolibre.dambetan01.dtos.request.StockRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InboundOrderResponse {

    List <StockRequest> batchStock;

}
