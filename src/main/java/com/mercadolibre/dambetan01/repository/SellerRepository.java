package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Agent;
import com.mercadolibre.dambetan01.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query(value = "SELECT * FROM SELLER where SELLER.email=:username and SELLER.password=:password", nativeQuery = true)
    Optional<Seller> findByEmailAndPassword(@Param("username") String username, @Param("password") String password);
}
