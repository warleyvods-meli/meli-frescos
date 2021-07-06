package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.request.PurchaseOrderRequestDTO;
import com.mercadolibre.dambetan01.dtos.response.PurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.model.Product;
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

    @GetMapping
    public List<Product> listAllFreshProduct() {
        return null;
    }

    @PostMapping("/orders")
    public PurchaseOrderResponseDTO registerOrder(@RequestBody PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        return purchaseOrderService.savePurchaseOrder(purchaseOrderRequestDTO);
    }

    @GetMapping("/orders/{idOrder}")
    public List<Product> listAllProductsInOrder(@PathVariable Long idOrder) {
        return null ;
    }

    @GetMapping("/orders")
    public PurchaseOrder editOrder(@RequestParam Long id) {
        return purchaseOrderService.findById(id);
    }



}
