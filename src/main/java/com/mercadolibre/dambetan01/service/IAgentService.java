package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Agent;
import com.mercadolibre.dambetan01.model.Buyer;

import java.util.List;

public interface IAgentService {
    Agent save(Agent agent);

    Agent findById(Long id);

    List <Agent> getAll();
}
