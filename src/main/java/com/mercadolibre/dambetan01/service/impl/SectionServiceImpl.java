package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.error.NotFoundException;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.repository.SectionRepository;
import com.mercadolibre.dambetan01.service.ISectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements ISectionService {

    private final SectionRepository repository;

    public SectionServiceImpl(SectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Section save(Section section) {
        return repository.save(section);
    }

    @Override
    public Section findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new NotFoundException("Section not found."));
    }

    @Override
    public List <Section> getAll() {
        return repository.findAll();
    }
}
