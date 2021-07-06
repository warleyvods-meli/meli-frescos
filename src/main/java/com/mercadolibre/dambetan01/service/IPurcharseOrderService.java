package com.mercadolibre.dambetan01.service;


import com.mercadolibre.dambetan01.dtos.ProductResponseDTO;
import com.mercadolibre.dambetan01.dtos.request.PurchaseOrderRequestDTO;
import com.mercadolibre.dambetan01.dtos.response.PurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.PurchaseOrder;

import java.util.List;

public interface IPurcharseOrderService {


    PurchaseOrderResponseDTO savePurchaseOrder(PurchaseOrderRequestDTO purchaseOrderRequestDTO);

    PurchaseOrder findById(Long id);

    List<PurchaseOrder> getAll();

    List<ProductResponseDTO> listAllProductsInOrder(Long idOrder);

    PurchaseOrder editOrder(Long id, PurchaseOrderRequestDTO purchaseOrderRequestDTO);

}
