package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.error.NotFoundException;
import com.mercadolibre.dambetan01.model.Agent;
import com.mercadolibre.dambetan01.repository.AgentRepository;
import com.mercadolibre.dambetan01.service.IAgentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements IAgentService {

    private final AgentRepository repository;

    public AgentServiceImpl(AgentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Agent save(Agent agent) {
        return repository.save(agent);
    }

    @Override
    public Agent findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Agent "+id+ " not found"));
    }

    @Override
    public List <Agent> getAll() {
        return repository.findAll();
    }
}
