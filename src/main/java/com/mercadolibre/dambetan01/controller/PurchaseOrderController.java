package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.ProductResponseDTO;
import com.mercadolibre.dambetan01.dtos.request.PurchaseOrderRequestDTO;
import com.mercadolibre.dambetan01.dtos.response.PurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.enums.StorageType;
import com.mercadolibre.dambetan01.model.PurchaseOrder;
import com.mercadolibre.dambetan01.service.IProductService;
import com.mercadolibre.dambetan01.service.IPurcharseOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v1")
@RestController
public class PurchaseOrderController {

    private final IProductService productService;
    private final IPurcharseOrderService purchaseOrderService;

    public PurchaseOrderController(IProductService productService, IPurcharseOrderService purchaseOrderService) {
        this.productService = productService;
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("buyer/fresh-products")
    public ResponseEntity<List<ProductResponseDTO>> listAllFreshProduct() {
        return new ResponseEntity<>(productService.getAllProductsResponse(), HttpStatus.OK);
    }

    @GetMapping("buyer/fresh-products/list")
    public ResponseEntity<List<ProductResponseDTO>> listAllFreshProductFromCategory(@RequestParam StorageType storageType) {
        return new ResponseEntity<>(productService.findProductsCategory(storageType), HttpStatus.OK);
    }

    @PostMapping("buyer/fresh-products/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PurchaseOrderResponseDTO> registerOrder(@RequestBody PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        return new ResponseEntity<>(purchaseOrderService.savePurchaseOrder(purchaseOrderRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("buyer/fresh-products/orders/find")
    public ResponseEntity<List<ProductResponseDTO>> listAllProductsInOrder(@RequestParam Long idOrder) {
        return new ResponseEntity<>(purchaseOrderService.listAllProductsInOrder(idOrder), HttpStatus.OK);
    }

    @PutMapping("buyer/fresh-products/orders")
    public ResponseEntity<PurchaseOrder> editOrder(@RequestParam Long id, @RequestBody @Valid PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        return new ResponseEntity<>(purchaseOrderService.editOrder(id, purchaseOrderRequestDTO), HttpStatus.OK);
    }


}
