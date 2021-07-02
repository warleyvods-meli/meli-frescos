package com.mercadolibre.dambetan01.service;


import com.mercadolibre.dambetan01.dtos.response.PurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.PurchaseOrder;

import java.util.List;

public interface IPurcharseOrderService {


    PurchaseOrder save(PurchaseOrder purchaseOrder);

    PurchaseOrder findById(Long id);

    List<PurchaseOrder> getAll();

}
