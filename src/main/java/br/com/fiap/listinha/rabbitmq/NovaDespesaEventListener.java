package br.com.fiap.listinha.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NovaDespesaEventListener {
    @RabbitListener(queues = "ChangedQueue")
    public void processNovaDespesaEvent(String novaDespesaEventJson){
        try{
            NovaDespesaEvent novaDespesaEvent = new ObjectMapper().readValue(novaDespesaEventJson, NovaDespesaEvent.class);
        }
        catch (Exception e){
            System.out.println("Excessão ocorreu");
        }
    }
}
