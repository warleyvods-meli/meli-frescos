package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.ProductResponseDTO;
import com.mercadolibre.dambetan01.enums.StorageType;
import com.mercadolibre.dambetan01.exceptions.error.NotFoundException;
import com.mercadolibre.dambetan01.mapper.ProductMapper;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.repository.ProductRepository;
import com.mercadolibre.dambetan01.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepostitory) {
        this.productRepository = productRepostitory;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new NotFoundException("Product not found."));
    }

    @Override
    public List <Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductResponseDTO> findProductsCategory(StorageType storageType) {
        return ProductMapper.INSTANCE.productDTOListToProduct(productRepository.findProductByCategory(storageType));
    }
}
