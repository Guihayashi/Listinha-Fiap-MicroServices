package br.com.fiap.listinha.rabbitmq

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.MessageProperties


object RabbitMQPublisher {
    private const val EXCHANGE_NAME = "exchange"
    private const val ROUTING_KEY = "routingkey"
    @Throws(Exception::class)
    @JvmStatic
    fun main(argv: Array<String>) {
        val factory = ConnectionFactory()
        factory.host = "localhost"
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        // Declaração do tópico
        channel.exchangeDeclare(EXCHANGE_NAME, "topic")
        val mensagem = "Alteração realizada!"
        val body = mensagem.toByteArray(charset("UTF-8"))

        // Configuração das propriedades da mensagem
        val props = AMQP.BasicProperties.Builder()
            .contentType("text/plain")
            .deliveryMode(MessageProperties.PERSISTENT_TEXT_PLAIN.deliveryMode)
            .priority(MessageProperties.PERSISTENT_TEXT_PLAIN.priority)
            .build()

        // Publicação da mensagem no tópico
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, props, body)
        println("Mensagem enviada: $mensagem")
        channel.close()
        connection.close()
    }
}
