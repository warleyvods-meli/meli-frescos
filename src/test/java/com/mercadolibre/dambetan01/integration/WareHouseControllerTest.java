package com.mercadolibre.dambetan01.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.dambetan01.exceptions.error.BadRequestException;
import com.mercadolibre.dambetan01.mapper.InboundOrderMapper;
import com.mercadolibre.dambetan01.service.IAgentService;
import com.mercadolibre.dambetan01.service.IInboundOrderService;
import com.mercadolibre.dambetan01.util.BuilderInboundOrderTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
public class WareHouseControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private  IAgentService agentService;

    @Autowired
    private  InboundOrderMapper inboundOrderMapper;

    @Autowired
    private  IInboundOrderService inboundOrderService;


    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void shouldCreateInboundOrder() throws Exception {
        var request = BuilderInboundOrderTestHelper.getInboundOrderRequest();
        request.getBatchStock().get(0).setProductId(3);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fresh-products/inboundorder")
        .contentType(MediaType.APPLICATION_JSON).header("AgentId","1")
        .content(mapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void shouldUpdateInboundOrder() throws Exception {
        var request = BuilderInboundOrderTestHelper.getInboundOrderRequest();
        request.getBatchStock().get(0).setProductId(3);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/fresh-products/inboundorder")
                .contentType(MediaType.APPLICATION_JSON).header("AgentId","1").header("OrderId","1")
                .content(mapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void shouldFailedCauseProductIsIncompatible() throws Exception {
        var request = BuilderInboundOrderTestHelper.getInboundOrderRequest();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fresh-products/inboundorder")
                .contentType(MediaType.APPLICATION_JSON).header("AgentId","1").header("OrderId","1")
                .content(mapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof BadRequestException));
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void shouldFailedCauseAgentIsIncompatible() throws Exception {
        var request = BuilderInboundOrderTestHelper.getInboundOrderRequest();
        request.getBatchStock().get(0).setProductId(3);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fresh-products/inboundorder")
                .contentType(MediaType.APPLICATION_JSON).header("AgentId","3").header("OrderId","1")
                .content(mapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof BadRequestException));
    }

    @Test //TODO rever este teste, esta dando certo pelo motivo errado
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void shouldFailedCauseSectionNoHaveSpace() throws Exception {
        var request = BuilderInboundOrderTestHelper.getInboundOrderRequest();
        request.getBatchStock().get(0).setProductId(2);
        request.getSection().setSectionId(3);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fresh-products/inboundorder")
                .contentType(MediaType.APPLICATION_JSON).header("AgentId","3").header("OrderId","1")
                .content(mapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof BadRequestException));
    }

}
