package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.response.PurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.PurchaseOrder;
import com.mercadolibre.dambetan01.repository.PurchaseOrderRepository;
import com.mercadolibre.dambetan01.service.IPurcharseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements IPurcharseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public PurchaseOrderResponseDTO savePurchaseOrder(PurchaseOrder purchaseOrder) {

        return PurchaseOrderResponseDTO.builder().totalPrice(purchaseOrderRepository.save(purchaseOrder).getProducts()
                .stream().mapToDouble(Product::getPrice).sum()).build();
    }

    @Override
    public List<PurchaseOrder> listAll() {
        return purchaseOrderRepository.findAll();
    }

}
