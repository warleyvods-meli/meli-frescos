package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.repository.SectionRepository;
import com.mercadolibre.dambetan01.service.ISectionService;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements ISectionService {

    private final SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Section findById(Long id) {
        return sectionRepository.findById(id).orElseThrow(()-> new NotFoundException("Section "+id+ " not found."));
    }
}
