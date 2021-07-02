package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.response.PurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.model.PurchaseOrder;
import com.mercadolibre.dambetan01.service.IPurcharseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/fresh-products")
@RestController
public class PurchaseOrderController {


    private final IPurcharseOrderService purchaseOrderService;

    public PurchaseOrderController(IPurcharseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }
}
