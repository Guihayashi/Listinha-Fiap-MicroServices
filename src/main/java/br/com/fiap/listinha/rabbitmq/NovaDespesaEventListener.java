package br.com.fiap.listinha.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NovaDespesaEventListener {

    @Autowired
    private EmailSender emailSender;

    @RabbitListener(queues = "routingkey")
    public void processNovaDespesaEvent(String novaDespesaEventJson){
        try{
            NovaDespesaEvent novaDespesaEvent = new ObjectMapper().readValue(novaDespesaEventJson, NovaDespesaEvent.class);
            System.out.println(novaDespesaEventJson);
            emailSender.enviarEmail("guilherme-hayashi@hotmail.com","Listinha - Atualização de despesa","Olá! Temos uma atualização em suas despesas!");

        }
        catch (Exception e){
            System.out.println("Excessão ocorreu");
        }
    }
}
