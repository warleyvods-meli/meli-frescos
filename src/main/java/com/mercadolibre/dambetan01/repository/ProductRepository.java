package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.enums.StorageType;
import com.mercadolibre.dambetan01.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByCategory(StorageType storageType);

}
