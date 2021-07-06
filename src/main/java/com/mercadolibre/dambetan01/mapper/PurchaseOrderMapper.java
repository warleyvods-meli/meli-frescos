package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.request.PurchaseOrderRequestDTO;
import com.mercadolibre.dambetan01.model.PurchaseOrder;
import com.mercadolibre.dambetan01.service.IBuyerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { IBuyerService.class })
public abstract class PurchaseOrderMapper {

    public static final  PurchaseOrderMapper INSTANCE = Mappers.getMapper(PurchaseOrderMapper.class);

    @Mapping(source = "buyerId", target = "buyer")
    public abstract PurchaseOrder toPurchaseOrder(PurchaseOrderRequestDTO purchaseOrderRequestDTO);

}
