package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.request.InboundOrderRequest;
import com.mercadolibre.dambetan01.dtos.request.SectionRequest;
import com.mercadolibre.dambetan01.dtos.request.StockRequest;
import com.mercadolibre.dambetan01.dtos.response.InboundOrderResponse;
import com.mercadolibre.dambetan01.exceptions.error.BadRequestException;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.service.IInboundOrderService;
import com.mercadolibre.dambetan01.service.IProductService;
import com.mercadolibre.dambetan01.service.ISectionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InboundOrderMapper {

    private final ModelMapper mapper;
    private final ISectionService sectionService;
    private final IProductService productServiceService;
    private final IInboundOrderService inboundOrderService;

    public InboundOrderMapper(ModelMapper mapper, ISectionService sectionService, IProductService productServiceService, IInboundOrderService inboundOrderService) {
        this.mapper = mapper;
        this.sectionService = sectionService;
        this.productServiceService = productServiceService;
        this.inboundOrderService = inboundOrderService;
    }

    public InboundOrder requestToEntity(InboundOrderRequest request){
        return InboundOrder.builder()
                .orderDate(request.getOrderDate())
                .section(this.sectionRequestToSection(request.getSection()))
                .batchStock(this.batchStockRequestToBatchStock(request))
                .orderNumber(request.getOrderNumber())
                .build();
    }

    private List<Stock> batchStockRequestToBatchStock(InboundOrderRequest inboundOrderRequests) {
        List<Stock> stockList = new ArrayList <>();
        inboundOrderRequests.getBatchStock().forEach(bsr -> stockList.add(Stock.builder()
                .currentTemperature(bsr.getCurrentTemperature())
                .dueDate(bsr.getDueDate())
                .manufacturingDate(bsr.getManufacturingDate())
                .minimumTemperature(bsr.getMinimumTemperature())
                .currentQuantity(bsr.getCurrentQuantity())
                .initialQuantity(bsr.getInitialQuantity())
                .product(productServiceService.findById((long) bsr.getProductId()))
                .manufacturingTime(bsr.getManufacturingTime())
                .orderNumber(bsr.getOrderNumber())
                .batchNumber(bsr.getBatchNumber())
                .section(this.sectionRequestToSection(inboundOrderRequests.getSection()))
                .build()
        ));

        return stockList;
    }

    private Section sectionRequestToSection(SectionRequest sectionRequest) {
        Section section = sectionService.findById((long)sectionRequest.getSectionId());
        if (section.getWarehouse().getId() == sectionRequest.getSectionId()){
            return section;
        }
        throw  new BadRequestException("this section id doesn't match with warehouse id");
    }

    public InboundOrderResponse EntityToResponse(InboundOrder entity){
        List<StockRequest> stockRequestList = new ArrayList <>();
        entity.getBatchStock().forEach(bs -> stockRequestList.add( StockRequest.builder()
                .currentQuantity(bs.getCurrentQuantity())
                .currentTemperature(bs.getCurrentTemperature())
                .dueDate(bs.getDueDate())
                .initialQuantity(bs.getInitialQuantity())
                .manufacturingDate(bs.getManufacturingDate())
                .manufacturingTime(bs.getManufacturingTime())
                .orderNumber(bs.getOrderNumber())
                .batchNumber(bs.getBatchNumber())
                .productId(bs.getProduct().getId().byteValue())
                .minimumTemperature(bs.getMinimumTemperature())
                .build()
        ));
        return InboundOrderResponse.builder().batchStock(stockRequestList).build();
    }

    public InboundOrder requestToEntityForPut(InboundOrderRequest request, int orderId) {
        var savedOrder = inboundOrderService.findById((long)orderId);
        var newOrder = requestToEntity(request);
        savedOrder.setOrderDate(newOrder.getOrderDate());
        savedOrder.setOrderNumber(newOrder.getOrderNumber());
        savedOrder.setSection(newOrder.getSection());
        savedOrder.setBatchStock(newOrder.getBatchStock());
        return savedOrder;
    }
}
