package com.mercadolibre.dambetan01.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="seller")
public class Seller extends User{

    @OneToMany(mappedBy = "seller")
    private List<Product> productList;

}
