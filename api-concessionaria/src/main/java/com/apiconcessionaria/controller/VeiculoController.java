package com.apiconcessionaria.controller;

import com.apiconcessionaria.dto.QuantidadeVeiculosDecadaDto;
import com.apiconcessionaria.dto.VeiculoDto;
import com.apiconcessionaria.dto.VeiculoVendidoDto;
import com.apiconcessionaria.entity.Veiculo;
import com.apiconcessionaria.filter.PageFilter;
import com.apiconcessionaria.service.VeiculoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/veiculos")
@Api(value = "VeiculoController - Controller responsável por funções dos Veículos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/{id}")
    @ApiOperation("Busca um veículo pelo id.")
    public ResponseEntity<Veiculo> buscar(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.buscar(id));

    }

    @PostMapping
    @ApiOperation("Insere veículo enviando formulário pelo corpo da requisição.")
    public ResponseEntity<Veiculo> inserir(@RequestBody VeiculoDto form){
        Veiculo veiculo = service.salvar(form);
        return new ResponseEntity<>(veiculo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Edita veículo enviando formulário pelo corpo da requisição e o id pela url.")
    public ResponseEntity<Veiculo> editar(@PathVariable("id") Integer id, @RequestBody VeiculoDto form){
        Veiculo veiculo = service.editar(form, id);
        return new ResponseEntity<>(veiculo, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Edita o campo vendido do veiculo enviando formulário pelo corpo da requisição e o id pela url.")
    public ResponseEntity<Veiculo> editarVendido(@PathVariable("id") Integer id, @RequestBody VeiculoVendidoDto form){
        Veiculo veiculo = service.editarParcial(form, id);
        return new ResponseEntity<>(veiculo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta veículo enviando o id pela url.")
    public ResponseEntity deletar(@PathVariable("id") Integer id){
        service.deletar(id);
        return ResponseEntity.ok().body(messageSource.getMessage("veiculo.sucesso.deletar", null, Locale.ROOT));
    }

    @GetMapping
    @ApiOperation("Lista veiculos paginado passando a informação e o tamanho da pagina pelo corpo da requisição")
    public ResponseEntity<Page<Veiculo>> listarPaginado(final PageFilter filter){
        return ResponseEntity.ok(this.service.listar(filter));
    }

    @GetMapping("/quantidade-por-decada")
    @ApiOperation("Lista a quantidade de veiculo cadastrado por decada")
    public ResponseEntity<List<QuantidadeVeiculosDecadaDto>> veiculosPorDecada(){
        return ResponseEntity.ok(service.distribuicaoVeiculoDecada());
    }

    @GetMapping("/registrados-ultima-semana")
    @ApiOperation("Lista os veiculos que foram cadastrados na ultima semana com paginação passando a informação e o tamanho da pagina pelo corpo da requisição")
    public ResponseEntity<Page<Veiculo>> veiculosRegistradosUltimaSemana(final PageFilter filter){
        return ResponseEntity.ok(service.veiculosRegistradosUltimaSemana(filter));
    }

    @GetMapping("/quantidade-nao-vendidos")
    @ApiOperation("Retorna a quantidade de veiculos cadastrados que não foram vendidos")
    public ResponseEntity<Integer> veiculosNaoVendidos(){
        return ResponseEntity.ok(service.quantidadeVeiculosNaoVendidos());
    }
}
