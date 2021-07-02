package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Section;

import java.util.Optional;

public interface ISectionService {

    Section findById(Long id);
}
