package com.apiconcessionaria.specification;

import com.apiconcessionaria.entity.Veiculo;
import com.apiconcessionaria.enums.Marcas;
import com.apiconcessionaria.filter.VeiculoFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoSpecification implements Specification<Veiculo> {

    private static final String NOME = "nome";
    private static final String ANO = "ano";
    private static final String MARCA = "marca";
    private static final String VENDIDO = "vendido";

    private VeiculoFilter filter;

    public VeiculoSpecification(final VeiculoFilter filter){
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        final Path<String> nome = root.get(NOME);
        final Path<Integer> ano = root.get(ANO);
        final Path<Marcas> marca = root.get(MARCA);
        final Path<Boolean> vendido = root.get(VENDIDO);

        final List<Predicate> predicates = new ArrayList<>();

        if(filter != null){

            String nomeFilter = filter.getNome();
            if (!ObjectUtils.isEmpty(nomeFilter)){
                predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(nome), "%"+nomeFilter.toUpperCase().trim()+"%"));
            }

            int anoFilter = filter.getAno();
            if (!ObjectUtils.isEmpty(anoFilter)){
                predicates.add(criteriaBuilder.equal(ano, anoFilter));
            }

            Marcas marcaFilter = filter.getMarca();
            if (!ObjectUtils.isEmpty(marcaFilter)){
                predicates.add(criteriaBuilder.equal(marca, marcaFilter));
            }

            Boolean vendidoFilter = filter.getVendido();
            if (!ObjectUtils.isEmpty(vendidoFilter)) {
                predicates.add(criteriaBuilder.equal(vendido, vendidoFilter));
            }

            criteriaQuery.orderBy(criteriaBuilder.asc(nome));
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
