package com.apiconcessionaria.controller;

import com.apiconcessionaria.dto.QuantidadeVeiculosMarcaDto;
import com.apiconcessionaria.enums.Marcas;
import com.apiconcessionaria.service.MarcaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/marcas")
@Api(value = "MarcaController - Controller responsável por funções da Marca dos Veículos")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @GetMapping
    @ApiOperation("Retorna a lista de Marcas cadastradas no sistema.")
    public ResponseEntity<List<Marcas>> listarMarcas(){
        return ResponseEntity.ok(Arrays.asList(Marcas.values()));
    }

    @GetMapping("/veiculos-por-fabricante")
    @ApiOperation("Lista a quantidade de veiculo cadastrado por marca")
    public ResponseEntity<List<QuantidadeVeiculosMarcaDto>> veiculosPorFabricante(){
        return ResponseEntity.ok(service.distribuicaoVeiculoFabricante());
    }
}
