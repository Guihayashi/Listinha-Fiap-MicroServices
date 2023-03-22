package br.com.fiap.listinha.service;

import br.com.fiap.listinha.dto.DespesaDTO;
import br.com.fiap.listinha.Entity.DespesaEntity;
import br.com.fiap.listinha.Repository.ListinhaMongoRepository;
import br.com.fiap.listinha.dto.NovaDespesaDTO;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespesasServiceImpl implements DespesasService {

	private final ListinhaMongoRepository listinhaMongoRepository;
	private final ModelMapper modelMapper;

	public DespesasServiceImpl(ListinhaMongoRepository listinhaMongoRepository, ModelMapper modelMapper) {
		this.listinhaMongoRepository = listinhaMongoRepository;
		this.modelMapper = modelMapper;
	}

	private DespesaDTO convertToDto(DespesaEntity despesaEntity) {
		return modelMapper.map(despesaEntity, DespesaDTO.class);
	}

	@Override
	public List<DespesaDTO> listarDespesas() {
		List<DespesaEntity> despesaList = listinhaMongoRepository.findAll();
		return despesaList.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private DespesaEntity convertToEntity(DespesaDTO despesaDTO) {
		return modelMapper.map(despesaDTO, DespesaEntity.class);
	}

	private DespesaEntity convertToEntity(NovaDespesaDTO novaDespesaDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(novaDespesaDTO, DespesaEntity.class);
	}


		public List<DespesaDTO> buscarDespesasPorCategoria(String categoria) {
		List<DespesaDTO> despesas = listinhaMongoRepository.findByCategoriaContainingIgnoreCase(categoria);
		return despesas;
	}



	public List<DespesaDTO> buscarDespesasPorNome(String name) {
		List<DespesaDTO> despesas = listinhaMongoRepository.findByNameContainingIgnoreCase(name);
		return despesas;
	}


	public List<DespesaDTO> buscarDespesasPorStatus(String status) {
		List<DespesaDTO> despesas = listinhaMongoRepository.findByStatusContainingIgnoreCase(status);
		return despesas;
	}


	@Override
	public DespesaDTO buscarDespesaPorId(String id) {
		DespesaEntity despesaEntity = listinhaMongoRepository.findById(id).orElse(null);
		if (null == despesaEntity) {
			throw new RuntimeException("Despesa não encontrada");
		}
		return convertToDto(despesaEntity);
	}

	@Override
	public void deletarDespesa(String id) {
		listinhaMongoRepository.deleteById(id);
	}

	@Override
	public void deletarDespesaPorId(String id) {
	listinhaMongoRepository.deleteByNameContainingIgnoreCase(id);
	}

	@Override
	public void deletarDespesaPorCategoria(String categoria) {

	}

}
