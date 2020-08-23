package com.apiconcessionaria.filter;

import com.apiconcessionaria.enums.Marcas;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Getter
@Setter
public class VeiculoFilter extends PageFilter implements Serializable {

    private static final long serialVersionUID = -9081259438358192703L;

    @JsonProperty
    private String nome;
    @JsonProperty
    private int ano;
    @JsonProperty
    @Enumerated(EnumType.STRING)
    private Marcas marca;
    @JsonProperty
    private Boolean vendido;
}
