package com.mercadolibre.dambetan01.service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.mercadolibre.dambetan01.model.Section;

import com.mercadolibre.dambetan01.dtos.ProductResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.ProductLocationResponseDTO;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.repository.ProductRepository;
import com.mercadolibre.dambetan01.repository.StockRepository;
import com.mercadolibre.dambetan01.service.impl.ProductServiceImpl;
import org.apache.commons.collections.ArrayStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class IProductServiceTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private IProductService productService;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveAProduct() {
        Product p = new Product();
        when(productRepository.save(anyObject())).thenReturn(p);
        assertThat(productServiceImpl.save(p)).isEqualTo(p);
    }

    @Test
    void findById() {
        Product p = new Product();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(p));
        var result = productServiceImpl.findById(anyLong());
        assertThat(p).isEqualTo(result);
    }

    @Test
    void getAllProductsResponse() {
        List<ProductResponseDTO> pp = new ArrayList<>();
        List<Product> po = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(po);

        var allProductsResponse = productService.getAllProductsResponse();

        assertThat(po).isEqualTo(allProductsResponse);
    }

    @Test
    void findProductsCategory() {
        List<ProductResponseDTO> list = new ArrayList<>();
        List<Product> po = new ArrayList<>();
        when(productRepository.findProductByCategory(any())).thenReturn(po);
        var productsCategory = productService.findProductsCategory(any());
        assertThat(po).isEqualTo(productsCategory);
    }

}
