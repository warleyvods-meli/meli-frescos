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


    private final IPurcharseOrderService purcharseOrderService;

    public PurchaseOrderController(IPurcharseOrderService purcharseOrderService) {
        this.purcharseOrderService = purcharseOrderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<PurchaseOrderResponseDTO> savePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return new ResponseEntity<>(purcharseOrderService.savePurchaseOrder(purchaseOrder), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> listAll() {
        return new ResponseEntity<>(purcharseOrderService.listAll(), HttpStatus.OK);
    }

}
