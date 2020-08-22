package com.apiconcessionaria.service;

import com.apiconcessionaria.entity.Veiculo;
import com.apiconcessionaria.enums.Marcas;
import com.apiconcessionaria.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Map<Marcas, Long> distribuicaoVeiculoFabricante(){
        List<Veiculo> veiculos = veiculoRepository.findAll();

        return veiculos.stream().collect(Collectors.groupingBy(Veiculo::getMarca, Collectors.counting()));
    }
}
