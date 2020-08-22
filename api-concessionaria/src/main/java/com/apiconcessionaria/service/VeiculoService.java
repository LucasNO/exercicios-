package com.apiconcessionaria.service;

import com.apiconcessionaria.dto.VeiculoDto;
import com.apiconcessionaria.dto.VeiculoVendidoDto;
import com.apiconcessionaria.entity.Veiculo;
import com.apiconcessionaria.exception.BusinessException;
import com.apiconcessionaria.filter.PageFilter;
import com.apiconcessionaria.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private MessageSource messageSource;

    public Veiculo buscar(Integer id){
        Optional<Veiculo> veiculo = repository.findById(id);
        if(veiculo.isEmpty()){
            Object parametros[] = {id.toString()};
            throw new BusinessException(messageSource.getMessage("veiculo.erro.buscar", parametros, Locale.ROOT));
        }
        return veiculo.get();
    }

    public Veiculo salvar(VeiculoDto dto){
        try {
            return repository.save(dtoToVeiculo(dto));
        }catch (Exception e){
            throw new BusinessException(messageSource.getMessage("veiculo.error.salvar", null, Locale.ROOT));
        }
    }

    public Veiculo editar(VeiculoDto dto, Integer id) {
            Veiculo veiculo = buscar(id);
            dto.setId(veiculo.getId());
            return salvar(dto);
    }

    public Veiculo editarParcial(VeiculoVendidoDto dto, Integer id) {
            Veiculo veiculo = buscar(id);
            veiculo.setVendido(dto.getVendido());
            return repository.save(veiculo);
    }

    public void deletar(Integer id) {
            try {
                Veiculo veiculo = buscar(id);
                repository.delete(veiculo);
            }catch (Exception e){
                Object parametros[] = {id.toString()};
                throw new BusinessException(messageSource.getMessage("veiculo.error.deletar", parametros, Locale.ROOT));
            }
    }

    public Page<Veiculo> listar(PageFilter filter) {
        final Pageable pageable = PageRequest.of(filter.getPageNumber(), filter.getPageSize());
        final Page<Veiculo> veiculos = this.repository.findAll(pageable);
        if(veiculos.isEmpty()){
            throw new BusinessException(messageSource.getMessage("veiculo.nao.encontrado", null, Locale.ROOT));
        }
        return veiculos;
    }


    private Veiculo dtoToVeiculo(VeiculoDto dto){
        return Veiculo.builder()
                .ano(dto.getAno())
                .descricao(dto.getDescricao())
                .id(dto.getId())
                .marca(dto.getMarca())
                .nome(dto.getNome())
                .vendido(dto.getVendido())
                .build();
    }

    public Map<String, Long> distribuicaoVeiculoDecada(){
        List<Veiculo> veiculos = repository.findAll();
        return veiculos.stream().collect(Collectors.groupingBy(v -> v.getAno().toString().substring(0, v.getAno().toString().length()-1)+"0", Collectors.counting()));
    }

    public Page<Veiculo> veiculosRegistradosUltimaSemana(PageFilter filter) {
        LocalDateTime data = LocalDate.now().atStartOfDay();
        final Pageable pageable = PageRequest.of(filter.getPageNumber(), filter.getPageSize());
        final Page<Veiculo> veiculos = this.repository.findByCreatedAfter(data.minusWeeks(1),pageable);
        return veiculos;
    }

    public Integer quantidadeVeiculosNaoVendidos(){
        return repository.countByVendidoFalse();
    }
}
