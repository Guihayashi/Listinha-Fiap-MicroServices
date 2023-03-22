package br.com.fiap.listinha.Repository;

import br.com.fiap.listinha.Entity.DespesaEntity;
import br.com.fiap.listinha.dto.DespesaDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListinhaMongoRepository extends MongoRepository<DespesaEntity,String> {
    List<DespesaDTO> findByCategoriaContainingIgnoreCase(String categoria);
    List<DespesaDTO> findByNameContainingIgnoreCase(String name);
    List<DespesaDTO> findByStatusContainingIgnoreCase(String status);
    List<DespesaEntity> findAll();
    DespesaDTO deleteByNameContainingIgnoreCase(String name);
    DespesaDTO deleteByCategoriaContainingIgnoreCase(String categoria);

}
