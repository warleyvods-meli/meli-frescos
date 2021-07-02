package com.mercadolibre.dambetan01.service;


import com.mercadolibre.dambetan01.dtos.response.PurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.model.PurchaseOrder;

import java.util.List;

public interface IPurcharseOrderService {

    PurchaseOrderResponseDTO savePurchaseOrder(PurchaseOrder purchaseOrder);

    List<PurchaseOrder> listAll();

}
