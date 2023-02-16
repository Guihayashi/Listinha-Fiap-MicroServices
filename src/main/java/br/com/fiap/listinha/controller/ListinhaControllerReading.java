package br.com.fiap.listinha.controller;

import br.com.fiap.listinha.dto.DespesaDTO;
import br.com.fiap.listinha.dto.NovaDespesaDTO;
import br.com.fiap.listinha.service.DespesasService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@ConditionalOnExpression(value = "${leitura}")
@RestController
@RequestMapping("despesas")
public class ListinhaControllerReading {


	private DespesasService despesaService;

	public ListinhaControllerReading(DespesasService despesaService) {
		this.despesaService = despesaService;
	
	}
	@CrossOrigin(origins = "*")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<DespesaDTO> getDespesas(){
		return despesaService.listarDespesas();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/categoria/{categoria}")
	@ResponseStatus(HttpStatus.OK)
	public List<DespesaDTO> buscarDespesaPorCategoria(@PathVariable String categoria) {

		return despesaService.listarDespesasPorCategoria(categoria);
	}
	@CrossOrigin(origins = "*")
	@GetMapping("id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DespesaDTO buscarDespesaPorId(@RequestParam Integer id) {

		return despesaService.buscarDespesaPorId(id);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("name/{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<DespesaDTO> buscarDespesasPorNome(@PathVariable String name) {

		return despesaService.buscarDespesasPorNome(name);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("status/{status}")
	@ResponseStatus(HttpStatus.OK)
	public List<DespesaDTO> buscarDespesasPorStatus(@PathVariable String status) {

		return despesaService.buscarDespesasPorStatus(status);
	}


}
