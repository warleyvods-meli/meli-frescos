package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.response.ProductLocationResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.StockDueDateResponseDTO;
import com.mercadolibre.dambetan01.mapper.StockMapper;
import com.mercadolibre.dambetan01.service.IProductService;
import com.mercadolibre.dambetan01.service.IStockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping(path = "/api/v1")
@RestController
public class ProductController {

    private final IProductService productService;
    private final IStockService stockService;
    private final StockMapper stockMapper;

    public ProductController(IProductService productService, IStockService stockService, StockMapper stockMapper) {
        this.productService = productService;
        this.stockService = stockService;
        this.stockMapper = stockMapper;
    }

    @GetMapping("/fresh-products-location/")
    @ResponseBody
    public ProductLocationResponseDTO getProductLocation(@RequestParam Long productId, @RequestParam(required = false) Long warehouseId,
                                                         @RequestParam(required = false) String orderBy){
        System.out.println(warehouseId);
        return productService.getProductLocation(productId, warehouseId, orderBy);

    }

    @GetMapping("/fresh-products/due-date")
    public ResponseEntity<List<StockDueDateResponseDTO>> getStockDueDate(@RequestParam Long id_section, @RequestParam Long day){
        List<StockDueDateResponseDTO> listStock = new ArrayList<>();
        var list = stockService.findStockFromSectionDueDate(id_section, day);
        list.forEach(l-> listStock.add(stockMapper.stockDueDateToResponseDTO(l)));

        return ResponseEntity.status(HttpStatus.OK).body(listStock);
    }

}
