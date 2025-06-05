package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.repositories.OwnerRepository;
import com.tecsup.petclinic.entities.Owner;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class VillenaUpdateIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldUpdateOwnerSuccessfully() throws Exception {
        // 1. Traemos el owner actual (ejemplo: ID=2, Betty)
        MvcResult getResult = mockMvc.perform(get("/owners/2"))
                .andExpect(status().isOk())
                .andReturn();

        String getResponse = getResult.getResponse().getContentAsString();
        OwnerDTO owner = objectMapper.readValue(getResponse, OwnerDTO.class);

        // 2. Cambiamos algunos campos
        owner.setFirstName("Beatriz");
        owner.setCity("Caracas");

        String requestJson = objectMapper.writeValueAsString(owner);
        log.info("📨 JSON enviado al endpoint (PUT): {}", requestJson);

        // 3. Ejecutamos el PUT para actualizar el owner
        MvcResult putResult = mockMvc.perform(put("/owners/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Beatriz")))
                .andExpect(jsonPath("$.city", is("Caracas")))
                .andReturn();

        String putResponse = putResult.getResponse().getContentAsString();
        log.info("📥 Respuesta JSON recibida tras actualización: {}", putResponse);

        // 4. Verificamos en la base de datos real
        Owner ownerBD = ownerRepository.findById(2).orElseThrow();
        assertEquals("Beatriz", ownerBD.getFirstName());
        assertEquals("Caracas", ownerBD.getCity());
        log.info("✅ Owner correctamente actualizado en base de datos.");
    }
}
