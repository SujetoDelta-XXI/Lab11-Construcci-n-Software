package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper; //Json a objeto
import com.tecsup.petclinic.dtos.OwnerDTO; //objeto - dueño
import com.tecsup.petclinic.repositories.OwnerRepository; //acceso bd

import lombok.extern.slf4j.Slf4j; //soporte a log.info
import org.junit.jupiter.api.Test; //prueba automatizada

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc //simular peticiones http
@Slf4j //habilita logg para imprimir mensajes
public class Lopez {

    @Autowired
    private MockMvc mockMvc;   //simular peticiones
    @Autowired
    private ObjectMapper objectMapper;  //obj json en class
    @Autowired
    private OwnerRepository ownerRepository; //accder bd
    @Test
    void FindOwnerById() throws Exception { //metod
        int id = 2;  //ID conocido en la bd

        //ejecuta get - id 2
        MvcResult result = mockMvc.perform(get("/owners/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Betty")))
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        log.info("Respuesta JSON del GET: {}", responseJson);

        //validar
        OwnerDTO owner = objectMapper.readValue(responseJson, OwnerDTO.class);

        assertEquals("Betty", owner.getFirstName());
        assertEquals("Davis", owner.getLastName());
        log.info("Owner verificado correctamente");
    }
}



