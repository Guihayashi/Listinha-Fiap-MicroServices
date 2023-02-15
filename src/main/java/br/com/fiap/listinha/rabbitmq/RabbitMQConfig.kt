package br.com.fiap.listinha.rabbitmq

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMQConfig {
    @Autowired
    private val rabbitTemplate: RabbitTemplate? = null
    @Bean
    fun filaDeEmails(): Queue {
        return Queue("ChangeQueue")
    }

    fun enviarMensagemParaFilaDeEmails(mensagem: String?) {
        rabbitTemplate!!.convertAndSend("ChangeQueuea", mensagem)
    }
}
