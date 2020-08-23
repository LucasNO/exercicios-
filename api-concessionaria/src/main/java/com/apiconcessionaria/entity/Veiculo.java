package com.apiconcessionaria.entity;

import com.apiconcessionaria.enums.Marcas;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "veiculo")
public class Veiculo extends Auditable implements Serializable {

    private static final long serialVersionUID = 3663260299874049107L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "marca", nullable = false)
    private Marcas marca;

    @Column(name = "ano", nullable = false , length = 4)
    private Integer ano;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "vendido")
    private Boolean vendido;
}
