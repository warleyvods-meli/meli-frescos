package com.mercadolibre.dambetan01.model;

import com.mercadolibre.dambetan01.enums.StorageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "section")
@Builder
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacity;

    @Enumerated(EnumType.STRING)
    private StorageType sectionName;

    @ManyToOne
    private Warehouse warehouse;

    @OneToMany(mappedBy = "section")
    private List<Stock> stockList;

}
