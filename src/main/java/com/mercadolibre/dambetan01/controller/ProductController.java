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

    @GetMapping("/fresh-products-location/")
    @ResponseBody
    public ProductLocationResponseDTO getProductLocation(@RequestParam Long productId, @RequestParam(required = false) Long warehouseId,
                                                         @RequestParam(required = false) String orderBy){
        System.out.println(warehouseId);
        return productService.getProductLocation(productId, warehouseId, orderBy);

    }

}
