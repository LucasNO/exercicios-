package com.apiconcessionaria.controller;

import com.apiconcessionaria.entity.Veiculo;
import com.apiconcessionaria.enums.Marcas;
import com.apiconcessionaria.service.MarcaService;
import com.apiconcessionaria.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MarcaControllerTest {

    @Autowired
    private MarcaController marcaController;

    @MockBean
    private MarcaService marcaService;

    @MockBean
    private VeiculoService veiculoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(marcaController)
                .build();
    }

    @Test
    void listarMarcas() throws Exception {
        mockMvc.perform(get("/marcas/listar"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void veiculosPorFabricante() throws Exception {

        Map<Marcas, Long> map = null;
        when(this.marcaService.distribuicaoVeiculoFabricante()).thenReturn(map);

        mockMvc.perform(get("/marcas/veiculos-por-fabricante"))
                .andExpect(status().isOk());

        verify(marcaService, times(1)).distribuicaoVeiculoFabricante();
    }
}