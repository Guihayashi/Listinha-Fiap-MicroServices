package br.com.fiap.listinha.rabbitmq

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMQConfig {
    @Value("\${spring.rabbitmq.host}")
    private var host: String? = "localhost"

    @Value("\${spring.rabbitmq.port}")
    private var port = 5672

    @Value("\${spring.rabbitmq.username}")
    private var username: String? = "guest"

    @Value("\${spring.rabbitmq.password}")
    private var password: String? = "guest"
    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory()
        connectionFactory.host = host
        connectionFactory.port = port
        connectionFactory.setUsername(username)
        connectionFactory.setPassword(password)
        return connectionFactory
    }

    @Bean
    fun rabbitTemplate(): RabbitTemplate {
        return RabbitTemplate(connectionFactory())
    }
}
