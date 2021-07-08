package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.response.ProductLocationResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.StockAndSectionResponseDTO;
import com.mercadolibre.dambetan01.mapper.SectionMapper;
import com.mercadolibre.dambetan01.mapper.StockMapper;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.repository.ProductRepository;
import com.mercadolibre.dambetan01.repository.SectionRepository;
import com.mercadolibre.dambetan01.repository.StockRepository;
import com.mercadolibre.dambetan01.util.BuilderProductServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private final StockRepository stockRepository = Mockito.mock(StockRepository.class);
    private final SectionRepository sectionRepository = Mockito.mock(SectionRepository.class);
    StockMapper stockMapper = new StockMapper();
    SectionMapper sectionMapper = new SectionMapper();
    ProductServiceImpl productService;
    BuilderProductServiceTest helper;

    @BeforeEach
    void setup(){
        this.productService = new ProductServiceImpl(productRepository, stockRepository, sectionRepository, stockMapper, sectionMapper);
        this.helper =  BuilderProductServiceTest.builder().build();
    }

    @Test
    void getProductLocationWithoutOrderingWithoutWarehouse(){

        helper.createObjects();

        when(productRepository.findById(1L)).thenReturn(Optional.of(helper.getProduct()));
        when(stockRepository.findAllById(Mockito.anyIterable())).thenReturn(helper.getProduct().getStockList());
        when(sectionRepository.findById(1L)).thenReturn(Optional.of(helper.getSection()));

        ProductLocationResponseDTO response =  this.productService.getProductLocation(helper.getProduct().getId(), null, null);
        ProductLocationResponseDTO expected = helper.getAllProductLocationResponse();

        assertEquals(expected, response);
    }
}