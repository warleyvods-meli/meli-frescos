package com.mercadolibre.dambetan01.mappers;

import com.mercadolibre.dambetan01.dtos.request.InboundOrderRequest;
import com.mercadolibre.dambetan01.dtos.request.SectionRequest;
import com.mercadolibre.dambetan01.dtos.request.StockRequest;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.model.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InboundOrderMapper {

    public InboundOrder RequestToEntity(InboundOrderRequest request){
        return InboundOrder
                .builder()
                .orderDate(request.getOrderDate())
                .section(sectionRequestToSection(request.getSection()))
                .batchStock(batchStockRequestToBathStock(request.getBatchStock()))
                .build();
    }

    private List<Stock> batchStockRequestToBathStock(List<StockRequest> bathStock) {
        List<Stock> stokcList =  new ArrayList<>();
        bathStock.forEach(s ->
                stokcList.add(Stock.builder()
                        .currentTemperature(s.getCurrentTemperature())
                        .manufacturingDate(s.getManufacturingDate())
                        .minimumTemperature(s.getMinimumTemperature())
                        .currentQuantity(s.getCurrentQuantity())
                        .initialQuantity(s.getInitialQuantity())
                        .manufacturingTime(s.getManufacturingTime())
                        .dueDate(s.getDueDate())
                        .productId(s.getProductId())
                        .build()));
        return  stokcList;
    }

    private Section sectionRequestToSection(SectionRequest sectionRequest) {
        return Section
                .builder()
                .sectionCode(sectionRequest.getSectionCode())
                .warehouseCode(sectionRequest.getWarehouseCode())
                .build();
    }
}
