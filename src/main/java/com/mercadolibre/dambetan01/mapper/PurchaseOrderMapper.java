package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.request.PurchaseOrderRequestDTO;
import com.mercadolibre.dambetan01.model.PurchaseOrder;
import com.mercadolibre.dambetan01.service.IBuyerService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { IBuyerService.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PurchaseOrderMapper {

    @Mapping(source = "buyerId", target = "buyer")
    PurchaseOrder toPurchaseOrder(PurchaseOrderRequestDTO purchaseOrderRequestDTO);

}
