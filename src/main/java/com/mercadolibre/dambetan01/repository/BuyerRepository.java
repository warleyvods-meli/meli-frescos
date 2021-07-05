package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Buyer;
import com.mercadolibre.dambetan01.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    @Query(value = "SELECT * FROM BUYER where BUYER.email=:username and BUYER.password=:password", nativeQuery = true)
    Optional <Buyer> findByEmailAndPassword(@Param("username") String username, @Param("password") String password);
}
