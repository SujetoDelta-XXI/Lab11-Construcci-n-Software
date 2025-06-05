package com.tecsup.petclinic.webs;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class Lopez {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindOwnerById() throws Exception {
        mockMvc.perform(get("/owners/3") // ID existente
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //verifica
                .andExpect(jsonPath("$.firstName").value("Eduardo")); //verifica el nombre
    }
}
