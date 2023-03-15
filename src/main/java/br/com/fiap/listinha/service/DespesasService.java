package br.com.fiap.listinha.service;

import br.com.fiap.listinha.dto.DespesaDTO;
import org.bson.types.ObjectId;
//import br.com.fiap.listinha.dto.NovaDespesaDTO;

import java.util.List;

public interface DespesasService {
	List<DespesaDTO> listarDespesas ();
	List<DespesaDTO> buscarDespesasPorCategoria (String categoria);
	List<DespesaDTO> buscarDespesasPorNome(String name);
	List<DespesaDTO> buscarDespesasPorStatus(String status);

	DespesaDTO buscarDespesaPorId(ObjectId id);
	//DespesaDTO criar (NovaDespesaDTO novaDespesaDTO);
//	DespesaDTO atualizar (Integer id, NovaDespesaDTO novaDespesaDTO);
//	DespesaDTO patchDespesa (Integer id, NovaDespesaDTO novaDespesaDTO);
	void deletarDespesa(ObjectId id);

	void deletarDespesaPorNome(String name);
	void deletarDespesaPorCategoria(String categoria);


}
