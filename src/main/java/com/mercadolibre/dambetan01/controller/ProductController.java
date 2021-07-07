package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.response.ProductLocationResponseDTO;
import com.mercadolibre.dambetan01.service.IProductService;
import org.springframework.web.bind.annotation.*;


@RequestMapping(path = "/api/v1")
@RestController
public class ProductController {

    private final IProductService productService;


    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/fresh-products-location/{productId}")
    @ResponseBody
    public ProductLocationResponseDTO getProductLocation(@PathVariable Long productId){
       return productService.getProductLocation(productId, 0L);

    }

    @GetMapping("/fresh-products-location/{productId}/{warehouseId}")
    @ResponseBody
    public ProductLocationResponseDTO getProductLocation(@PathVariable Long productId, @PathVariable Long warehouseId){
        return productService.getProductLocation(productId, warehouseId);

    }
}
