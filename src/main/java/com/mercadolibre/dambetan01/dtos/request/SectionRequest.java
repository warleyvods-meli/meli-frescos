package com.mercadolibre.dambetan01.dtos.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionRequest {

    private String sectionCode;
    private String warehouseCode;

}
