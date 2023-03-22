package br.com.fiap.listinha.controller;

import br.com.fiap.listinha.Entity.DespesaEntity;
import br.com.fiap.listinha.Repository.ListinhaMongoRepository;
import br.com.fiap.listinha.dto.DespesaDTO;
//import br.com.fiap.listinha.dto.NovaDespesaDTO;
import br.com.fiap.listinha.service.DespesasService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@ConditionalOnExpression(value = "${leitura}")
@RestController
@RequestMapping("despesas")
public class ListinhaControllerReading {


	private DespesasService despesaService;
	@Autowired
	private ListinhaMongoRepository repository;
	public ListinhaControllerReading(DespesasService despesaService) {
		this.despesaService = despesaService;

	}
	@CrossOrigin(origins = "*")
	@GetMapping("/despesa/listar")
	@ResponseStatus(HttpStatus.OK)
	public List<DespesaEntity> getDespesas(){
		List<DespesaEntity> despesas = repository.findAll();
		return despesas;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{categoria}")
	@ResponseStatus(HttpStatus.OK)
	public List<DespesaDTO> buscarDespesaPorCategoria(@PathVariable String categoria) {

		return despesaService.buscarDespesasPorCategoria(categoria);
	}
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DespesaDTO buscarDespesaPorId(@RequestParam String id) {

		return despesaService.buscarDespesaPorId(id);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<DespesaDTO> buscarDespesasPorNome(@PathVariable String name) {

		return despesaService.buscarDespesasPorNome(name);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{status}")
	@ResponseStatus(HttpStatus.OK)
	public List<DespesaDTO> buscarDespesasPorStatus(@PathVariable String status) {

		return despesaService.buscarDespesasPorStatus(status);
	}


}
