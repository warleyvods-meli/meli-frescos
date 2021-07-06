package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.ProductResponseDTO;
import com.mercadolibre.dambetan01.dtos.request.PurchaseOrderRequestDTO;
import com.mercadolibre.dambetan01.dtos.response.PurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.enums.StorageType;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.PurchaseOrder;
import com.mercadolibre.dambetan01.service.IProductService;
import com.mercadolibre.dambetan01.service.IPurcharseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/fresh-products")
@RestController
public class PurchaseOrderController {

    private final IProductService productService;
    private final IPurcharseOrderService purchaseOrderService;

    public PurchaseOrderController(IProductService productService, IPurcharseOrderService purchaseOrderService) {
        this.productService = productService;
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping
    public List<ProductResponseDTO> listAllFreshProduct(@RequestParam StorageType storageType) {
        return productService.findProductsCategory(storageType);
    }

    @PostMapping("/orders")
    public PurchaseOrderResponseDTO registerOrder(@RequestBody PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        return purchaseOrderService.savePurchaseOrder(purchaseOrderRequestDTO);
    }

    @GetMapping("/orders/{idOrder}")
    public List<Product> listAllProductsInOrder(@PathVariable Long idOrder) {
        return null;
    }

    @GetMapping("/orders")
    public PurchaseOrder editOrder(@RequestParam Long id) {
        return purchaseOrderService.findById(id);
    }



}
