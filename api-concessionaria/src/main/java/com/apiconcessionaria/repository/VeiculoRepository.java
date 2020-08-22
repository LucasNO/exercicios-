package com.apiconcessionaria.repository;

import com.apiconcessionaria.entity.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> , JpaSpecificationExecutor<Veiculo> {

    Page<Veiculo> findByCreatedAfter(LocalDateTime data, Pageable pageable);

    Integer countByVendidoFalse();
}
