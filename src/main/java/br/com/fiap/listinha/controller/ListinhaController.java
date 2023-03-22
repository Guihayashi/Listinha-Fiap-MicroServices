package br.com.fiap.listinha.controller;

import br.com.fiap.listinha.Entity.DespesaEntity;
import br.com.fiap.listinha.Repository.ListinhaMongoRepository;
import br.com.fiap.listinha.dto.DespesaDTO;
import br.com.fiap.listinha.dto.NovaDespesaDTO;
import br.com.fiap.listinha.rabbitmq.NovaDespesaEvent;
import br.com.fiap.listinha.service.DespesasService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@ConditionalOnExpression(value = "#{!${leitura}}")
@RestController
@RequestMapping("despesas")

public class ListinhaController{
	private DespesasService despesaService;
	private DespesaDTO despesaDTO;
	private NovaDespesaDTO novaDespesaDTO;
	@Autowired
	private ListinhaMongoRepository repository;

	@Autowired
	private RabbitTemplate rabbitTemplate = new RabbitTemplate();

	@Autowired
	public ListinhaController(DespesasService despesaService, RabbitTemplate rabbitTemplate){
		this.despesaService = despesaService;
		this.rabbitTemplate = rabbitTemplate;
	}
	public ListinhaController(DespesasService despesaService) {
		this.despesaService = despesaService;
	
	}

	@CrossOrigin(origins = "*")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String salvar(@RequestBody DespesaDTO despesaDTO) {
		DespesaEntity despesaEntity = convertToEntity(despesaDTO);
		repository.save(despesaEntity);
		NovaDespesaEvent novaDespesaEvent = new NovaDespesaEvent();
		novaDespesaEvent.setId(despesaEntity.getId());
		novaDespesaEvent.setNome(despesaEntity.getName());
		novaDespesaEvent.setValor(despesaEntity.getValor());
		novaDespesaEvent.setCategoria(despesaEntity.getCategoria());
		novaDespesaEvent.setDescricao(despesaEntity.getDescricao());
		novaDespesaEvent.setDataVencimento(despesaEntity.getDataVencimento());

		String novaDespesaEventJson = "";
		try {
			novaDespesaEventJson = new ObjectMapper().writeValueAsString(novaDespesaEvent);
		} catch (Exception e) {
			System.out.println("Exceção do POST");
		}

		rabbitTemplate.convertAndSend("exchange", "routingkey", novaDespesaEventJson);

		return novaDespesaEventJson;
	}

	/*
	@CrossOrigin(origins = "*")
	@PatchMapping("id/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public DespesaDTO updateDespesa(
			@RequestBody DespesaDTO despesaDTO,
			@PathVariable ObjectId id
	) {
		return despesaService.patchDespesa(id, despesaDTO);
	}
	@CrossOrigin(origins = "*")
	@PutMapping ("id/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public DespesaDTO updateDaDespesa(
			@RequestBody DespesaDTO despesaDTO,
			@PathVariable ObjectId id
	) {
		
		return despesaService.atualizar(id, despesaDTO);
	}
	@CrossOrigin(origins = "*")
	@DeleteMapping("id/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDespesa(
			@PathVariable ObjectId id
	) {
		despesaService.deletarDespesa(id);
	}
*/
	private DespesaEntity convertToEntity(DespesaDTO despesaDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(despesaDTO, DespesaEntity.class);
	}
	private DespesaEntity convertToEntity(NovaDespesaDTO novaDespesaDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(novaDespesaDTO, DespesaEntity.class);
	}
}
