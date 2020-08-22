package com.apiconcessionaria.dto;

import com.apiconcessionaria.enums.Marcas;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDto implements Serializable {

    private Integer id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Marcas marca;
    private int ano;
    private String descricao;
    private Boolean vendido;
}
