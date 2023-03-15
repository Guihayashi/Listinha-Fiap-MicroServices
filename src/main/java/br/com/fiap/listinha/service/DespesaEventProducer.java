package br.com.fiap.listinha.service;

import br.com.fiap.listinha.Entity.DespesaEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class DespesaEventProducer {
    private final RabbitTemplate rabbitTemplate;

    public DespesaEventProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagemNovaDespesa(DespesaEntity despesaEntity){
        rabbitTemplate.convertAndSend("exchange","routingkey",despesaEntity);

    }
}
