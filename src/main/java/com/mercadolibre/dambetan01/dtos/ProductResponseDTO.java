package com.mercadolibre.dambetan01.dtos;

import com.mercadolibre.dambetan01.enums.StorageType;
import com.mercadolibre.dambetan01.model.PurchaseOrder;
import com.mercadolibre.dambetan01.model.Seller;
import com.mercadolibre.dambetan01.model.Stock;
import lombok.Data;

import javax.persistence.*;

@Data
public class ProductResponseDTO {


    private String productName;
    private Double price;
    private StorageType category;

}
