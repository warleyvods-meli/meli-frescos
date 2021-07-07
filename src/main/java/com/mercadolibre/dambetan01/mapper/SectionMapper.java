package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.response.SectionResponseDTO;
import com.mercadolibre.dambetan01.model.Section;
import org.springframework.stereotype.Component;

@Component
public class SectionMapper {

    public SectionResponseDTO sectionEntityToResponseDTO(Section section){
         return SectionResponseDTO.builder()
                .sectionId(section.getId())
                .warehouseId(section.getWarehouse().getId())
                 .build();
    }
}
