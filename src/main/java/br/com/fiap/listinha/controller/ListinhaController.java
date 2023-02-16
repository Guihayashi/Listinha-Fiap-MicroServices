package br.com.fiap.listinha.controller;

import br.com.fiap.listinha.dto.DespesaDTO;
import br.com.fiap.listinha.dto.NovaDespesaDTO;
import br.com.fiap.listinha.service.DespesasService;
import com.rabbitmq.client.AMQP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnExpression(value = "#{!${leitura}}")
@RestController
@RequestMapping("despesas")

public class ListinhaController{
	private DespesasService despesaService;
	
	public ListinhaController(DespesasService despesaService) {
		this.despesaService = despesaService;
	
	}

	@CrossOrigin(origins = "*")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DespesaDTO createDespesa(
			@RequestBody NovaDespesaDTO novaDespesaDTO

	) {
		return despesaService.criar(novaDespesaDTO);
	}
	@CrossOrigin(origins = "*")
	@PatchMapping("id/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public DespesaDTO updateDespesa(
			@RequestBody NovaDespesaDTO novaDespesaDTO,
			@PathVariable Integer id
	) {
		return despesaService.patchDespesa(id, novaDespesaDTO);
	}
	@CrossOrigin(origins = "*")
	@PutMapping ("id/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public DespesaDTO updateDaDespesa(
			@RequestBody NovaDespesaDTO novaDespesaDTO,
			@PathVariable Integer id
	) {
		
		return despesaService.atualizar(id, novaDespesaDTO);
	}
	@CrossOrigin(origins = "*")
	@DeleteMapping("id/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDespesa(
			@PathVariable Integer id
	) {
		despesaService.deletarDespesa(id);
	}

}
