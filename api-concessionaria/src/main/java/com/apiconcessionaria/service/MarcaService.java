package com.apiconcessionaria.service;

import com.apiconcessionaria.dto.QuantidadeVeiculosMarcaDto;
import com.apiconcessionaria.entity.Veiculo;
import com.apiconcessionaria.enums.Marcas;
import com.apiconcessionaria.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<QuantidadeVeiculosMarcaDto> distribuicaoVeiculoFabricante(){
        List<Veiculo> veiculos = veiculoRepository.findAll();

        List<QuantidadeVeiculosMarcaDto> quantidadeVeiculosMarcaDtos = new ArrayList<>();

        Map<Marcas, Long> map = veiculos.stream().collect(Collectors.groupingBy(Veiculo::getMarca, Collectors.counting()));

        map.forEach((marcas, aLong) -> {
            QuantidadeVeiculosMarcaDto dto = new QuantidadeVeiculosMarcaDto(marcas,aLong.intValue());
            quantidadeVeiculosMarcaDtos.add(dto);
        });

        return quantidadeVeiculosMarcaDtos;
    }
}
