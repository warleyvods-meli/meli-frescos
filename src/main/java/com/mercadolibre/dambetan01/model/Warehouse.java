package com.mercadolibre.dambetan01.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "warehouse")
    private List<Section> sectionList;

    @OneToMany(mappedBy = "warehouse")
    private List<Agent> agents;






}
