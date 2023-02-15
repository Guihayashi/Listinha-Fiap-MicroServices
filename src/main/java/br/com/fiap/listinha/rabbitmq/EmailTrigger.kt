package br.com.fiap.listinha.rabbitmq

import br.com.fiap.listinha.rabbitmq.EmailSender.enviarEmail
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component


@Component
class EmailTrigger {
    @RabbitListener(queues = ["ChangeQueue"])
    fun enviarEmail(mensagem: String?) {
        // Aqui você pode colocar a lógica para enviar o e-mail usando a classe EmailSender do exemplo anterior

        enviarEmail("exemplo@dominio.com", "Assunto do e-mail", mensagem)
    }
}
