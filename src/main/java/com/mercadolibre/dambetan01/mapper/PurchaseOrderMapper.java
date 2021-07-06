package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.request.PurchaseOrderRequestDTO;
import com.mercadolibre.dambetan01.model.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class PurchaseOrderMapper {

    public static final  PurchaseOrderMapper INSTANCE = Mappers.getMapper(PurchaseOrderMapper.class);

    @Mapping(target = "buyer.id", source = "buyerId")
    public abstract PurchaseOrder toPurchaseOrder(PurchaseOrderRequestDTO purchaseOrderRequestDTO);


}
