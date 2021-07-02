package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.PurchaseOrder;
import com.mercadolibre.dambetan01.model.Section;

import java.util.List;

public interface ISectionService {

    Section save(Section section);

    Section findById(Long id);

    List<Section> getAll();
}
