package com.apiconcessionaria.controller;

import com.apiconcessionaria.dto.QuantidadeVeiculosDecadaDto;
import com.apiconcessionaria.dto.VeiculoDto;
import com.apiconcessionaria.entity.Veiculo;
import com.apiconcessionaria.enums.Marcas;
import com.apiconcessionaria.filter.PageFilter;
import com.apiconcessionaria.service.MarcaService;
import com.apiconcessionaria.service.VeiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest
class VeiculoControllerTest {

    @Autowired
    private  VeiculoController veiculoController;

    @MockBean
    private VeiculoService service;

    @MockBean
    private MarcaService marcaService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(veiculoController)
                .build();
    }

    @Test
    void buscar() throws Exception {
        when(this.service.buscar(1)).thenReturn(new Veiculo(1,"TORO", Marcas.FIAT,2019,"", false));

        mockMvc.perform(get("/veiculos/buscar/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).buscar(1);

    }

    @Test
    void inserir() throws Exception {
        Veiculo veiculo = new Veiculo(1,"TORO", Marcas.FIAT,2019,"", false);
        VeiculoDto dto = new VeiculoDto(1,"TORO", Marcas.FIAT,2019,"", false);

        when(service.salvar(any(VeiculoDto.class))).thenReturn(veiculo);

        mockMvc.perform(post("/veiculos")
                .content(objectMapper.writeValueAsString(dto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("TORO")))
                .andExpect(jsonPath("$.marca", is("FIAT")))
                .andExpect(jsonPath("$.ano", is(2019)))
                .andExpect(jsonPath("$.descricao", is("")))
                .andExpect(jsonPath("$.vendido", is(false)));

        verify(service, times(1)).salvar(any(VeiculoDto.class));
    }

    @Test
    void editar() throws Exception{
        Veiculo veiculo = new Veiculo(1,"TORO", Marcas.FIAT,2019,"SEM DESCRICAO", false);
        VeiculoDto dto = new VeiculoDto(1,"TORO", Marcas.FIAT,2019,"SEM DESCRICAO", false);

        when(service.editar(any(VeiculoDto.class),any(Integer.class))).thenReturn(veiculo);

        mockMvc.perform(put("/veiculos/1")
                .content(objectMapper.writeValueAsString(dto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("TORO")))
                .andExpect(jsonPath("$.marca", is("FIAT")))
                .andExpect(jsonPath("$.ano", is(2019)))
                .andExpect(jsonPath("$.descricao", is("SEM DESCRICAO")))
                .andExpect(jsonPath("$.vendido", is(false)));

    }

    @Test
    void editarVendido() throws Exception {

        Veiculo veiculo = new Veiculo(1,"TORO", Marcas.FIAT,2019,"SEM DESCRICAO", true);
        VeiculoDto dto = new VeiculoDto(1,"TORO", Marcas.FIAT,2019,"SEM DESCRICAO", true);

        when(service.editar(any(VeiculoDto.class),any(Integer.class))).thenReturn(veiculo);

        mockMvc.perform(put("/veiculos/1")
                .content(objectMapper.writeValueAsString(dto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.vendido", is(true)));
    }

    @Test
    void deletar() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(1);
        mockMvc.perform(delete("/veiculos/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).deletar(1);
    }

    @Test
    void listarPaginado() throws Exception {
        PageFilter pageFilter = new PageFilter();
        pageFilter.setPageNumber(0);
        pageFilter.setPageSize(10);
        Page<Veiculo> veiculos = null;


        when(this.service.listar(pageFilter)).thenReturn(veiculos);


        mockMvc.perform(get("/veiculos?pageNumber=0&pageSize=10"))
                .andExpect(status().isOk());

        verify(service, times(1)).listar(any(PageFilter.class));

    }

    @Test
    void veiculosPorDecada() throws Exception {

        List<QuantidadeVeiculosDecadaDto> list = null;

        when(this.service.distribuicaoVeiculoDecada()).thenReturn(list);

        mockMvc.perform(get("/veiculos/quantidade-por-decada"))
                .andExpect(status().isOk());

        verify(service, times(1)).distribuicaoVeiculoDecada();
    }

    @Test
    void veiculosRegistradosUltimaSemana() throws Exception {
        PageFilter pageFilter = new PageFilter();
        pageFilter.setPageNumber(0);
        pageFilter.setPageSize(10);
        Page<Veiculo> veiculos = null;

        when(this.service.listar(pageFilter)).thenReturn(veiculos);

        mockMvc.perform(get("/veiculos/registrados-ultima-semana?pageNumber=0&pageSize=10"))
                .andExpect(status().isOk());

        verify(service, times(1)).veiculosRegistradosUltimaSemana(any(PageFilter.class));
    }

    @Test
    void veiculosNaoVendidos() throws Exception {
        when(this.service.quantidadeVeiculosNaoVendidos()).thenReturn(0);

        mockMvc.perform(get("/veiculos/quantidade-nao-vendidos"))
                .andExpect(status().isOk());

        verify(service, times(1)).quantidadeVeiculosNaoVendidos();
    }
}