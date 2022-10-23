package com.algafood.api.controller;

import com.algafood.AlgafoodApiApplicationTests;
import com.algafood.api.dto.EstadoDTO;
import com.algafood.api.exception.model.Error;
import com.algafood.api.exception.model.ErrorType;
import com.algafood.domain.service.EstadoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {
        "/dataset/delete_all.sql",
        "/dataset/estado_controller_tests.sql"
})
public class EstadoControllerTests extends AlgafoodApiApplicationTests {

    private final String ESTADO_RESOURCE_URI = "/estados/";

    @Autowired
    private EstadoService estadoService;

    @Test
    @DisplayName("must search state by id")
    void MustSearchStateById() throws Exception {
        var requestBuilder = get(ESTADO_RESOURCE_URI.concat(String.valueOf(1)))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(payloadExtractor)
                .andReturn();

        var estado = payloadExtractor.as(EstadoDTO.class);

        Assertions.assertEquals(1L, estado.getId());
        Assertions.assertEquals("Roraima", estado.getNome());
    }

    @Test
    @DisplayName("must fail to search state by nonexistent id")
    void mustFailSearchStateByNonExistentId() throws Exception {
        var requestBuilder = get(ESTADO_RESOURCE_URI.concat(String.valueOf(10)))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andDo(payloadExtractor)
                .andReturn();

        var error = payloadExtractor.as(Error.class);

        Assertions.assertEquals(messageHelper.getMessage("state.not-found"), error.getDetail());
    }

    @Test
    @DisplayName("must list all states")
    void mustListAllStates() throws Exception {
        var requestBuilder = get(ESTADO_RESOURCE_URI)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(payloadExtractor)
                .andReturn();

        var estados = payloadExtractor.asListOf(EstadoDTO.class);

        Assertions.assertEquals(5, estados.size());
    }

    @Test
    @DisplayName("must save state")
    void mustSaveState() throws Exception {
        var nomeEstado = "Pernambuco";
        var estadoDTO = EstadoDTO.builder().nome(nomeEstado).build();

        var requestBuilder = post(ESTADO_RESOURCE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(estadoDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andDo(payloadExtractor)
                .andReturn();

        var location = payloadExtractor.getHeaderAsString("Location");

        Assertions.assertNotNull(location);

        var estado = estadoService.findById(Long.valueOf(location.replaceAll("\\D", "")));

        Assertions.assertEquals(nomeEstado, estado.getNome());
    }

    @Test
    @DisplayName("must fail on save state with duplicated name")
    void mustFailSaveStateDuplicatedName() throws Exception {
        var estadoDTO = EstadoDTO.builder().nome("Roraima").build();

        var requestBuilder = post(ESTADO_RESOURCE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(estadoDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andDo(payloadExtractor)
                .andReturn();

        var error = payloadExtractor.as(Error.class);

        Assertions.assertEquals(messageHelper.getMessage("state.duplicated"), error.getDetail());
    }

    @Test
    @DisplayName("must fail on save state with empty name")
    void mustFailSaveStateEmptyName() throws Exception {
        var estadoDTO = EstadoDTO.builder().nome("").build();

        var requestBuilder = post(ESTADO_RESOURCE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(estadoDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andDo(payloadExtractor)
                .andReturn();

        var error = payloadExtractor.as(Error.class);

        Assertions.assertEquals(ErrorType.DADOS_INVALIDOS.getDescricao(), error.getTitle());
        Assertions.assertEquals(1, error.getValidationFailures().size());

        var qtdFailures = error.getValidationFailures().stream()
                .filter(validationFailure -> validationFailure.getField().equals("nome"))
                .count();

        Assertions.assertEquals(1, qtdFailures);
    }

    @Test
    @DisplayName("must update state")
    void mustUpdateState() throws Exception {
        var nomeEstado = "Rio de Janeiro";
        var estadoDTO = EstadoDTO.builder().nome(nomeEstado).build();

        var requestBuilder = put(ESTADO_RESOURCE_URI.concat(String.valueOf(1)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(estadoDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(payloadExtractor)
                .andReturn();

        var estado = payloadExtractor.as(EstadoDTO.class);

        Assertions.assertEquals(nomeEstado, estado.getNome());
        Assertions.assertEquals(1L, estado.getId());
    }

}
