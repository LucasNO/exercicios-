package com.apiconcessionaria.dto;

import com.apiconcessionaria.enums.Marcas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuantidadeVeiculosMarcaDto {

    private Marcas marca;
    private Integer quantidade;
}
