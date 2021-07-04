package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.request.InboundOrderRequest;
import com.mercadolibre.dambetan01.dtos.response.InboundOrderResponse;
import com.mercadolibre.dambetan01.model.InboundOrder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InboundOrderMapper {

    private final ModelMapper mapper;

    public InboundOrderMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public InboundOrder requestToEntity(InboundOrderRequest request){
        return mapper.map(request, InboundOrder.class);
    }

    public InboundOrderResponse EntityToResponse(InboundOrder entity){
        return mapper.map(entity, InboundOrderResponse.class);
    }
}
