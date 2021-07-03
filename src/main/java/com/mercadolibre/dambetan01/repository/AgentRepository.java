package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    @Query(value = "SELECT * FROM AGENT where AGENT.email=:username and AGENT.password=:password", nativeQuery = true)
    Agent findByEmailAndPassword(@Param("username") String username, @Param("password") String password);

}
