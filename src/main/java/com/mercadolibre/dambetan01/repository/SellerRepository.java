package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
