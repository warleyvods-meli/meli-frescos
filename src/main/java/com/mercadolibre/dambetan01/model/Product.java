package com.mercadolibre.dambetan01.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String manufacturer;
    private String composition;
    private String category;
    private Double weight;
    private Double price;
    private String status;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private Double minimumTemp;
    private Double maximumTemp;
    private LocalDate dueDate;


}
