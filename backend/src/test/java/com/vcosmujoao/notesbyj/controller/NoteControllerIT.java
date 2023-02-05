package com.vcosmujoao.notesbyj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcosmujoao.notesbyj.DTO.NoteDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class NoteControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findAllNotesShouldReturnAllResources() throws Exception {

        ResultActions result =
                mockMvc.perform(get("/notes")
                        .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$", hasSize(2)));
        result.andExpect(jsonPath("$[0].id").exists());
        result.andExpect(jsonPath("$[0].titulo").exists());
        result.andExpect(jsonPath("$[0].descricao").exists());
        result.andExpect(jsonPath("$[0].dataCriacao").exists());
    }

    @Test
    public void createShouldInsertResource() throws Exception {
        NoteDTO dto = new NoteDTO(null,"Titulo Teste", "Descricão Teste",null);
        String jsonBody = objectMapper.writeValueAsString(dto);
        ResultActions result =
                mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.titulo").value("Titulo Teste"));
        result.andExpect(jsonPath("$.descricao").value("Descricão Teste"));
        result.andExpect(jsonPath("$.dataCriacao").exists());
    }


}