package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section,Long> {

    @Query(value = "SELECT distinct stock.id FROM stock join section on stock.section_id=:id", nativeQuery = true)
    List<Long> findStockList(@Param("id") Long id);

}
