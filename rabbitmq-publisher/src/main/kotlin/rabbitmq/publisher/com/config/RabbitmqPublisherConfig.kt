package rabbitmq.publisher.com.config

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.Exchange
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.QueueBuilder
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import rabbitmq.publisher.com.domain.QueueDefinition

@Configuration
class RabbitmqPublisherConfig(
        private val connectionFactory: ConnectionFactory
) {

    @PostConstruct
    fun createRabbitElements() {
        val rabbitAdmin = RabbitAdmin(connectionFactory)
        createFanoutExchange(rabbitAdmin)
        createFirstQueue(rabbitAdmin)
        createSecondQueue(rabbitAdmin)
        createJsonQueue(rabbitAdmin)
    }

    @Bean
    fun messageConverter(objectMapper: ObjectMapper): MessageConverter =
            Jackson2JsonMessageConverter(objectMapper)


    private fun createFanoutExchange(rabbitAdmin: RabbitAdmin) {
        val exchange = ExchangeBuilder
            .fanoutExchange(QueueDefinition.FANNOUT_EXCHANGE)
            .durable(true)
            .build<Exchange>()
        rabbitAdmin.declareExchange(exchange)
    }

    private fun createJsonQueue(rabbitAdmin: RabbitAdmin) {

        val queue = QueueBuilder.durable(QueueDefinition.JSON_QUEUE)
                .build()

        val binding = Binding(
                QueueDefinition.JSON_QUEUE,
                Binding.DestinationType.QUEUE,
                QueueDefinition.FANNOUT_EXCHANGE,
                "", //is not necessary
                null
        )

        rabbitAdmin.declareQueue(queue)
        rabbitAdmin.declareBinding(binding)
    }

    private fun createSecondQueue(rabbitAdmin: RabbitAdmin) {
        val queue = QueueBuilder.durable(QueueDefinition.SECOND_QUEUE)
                .build()

        val binding = Binding(
                QueueDefinition.SECOND_QUEUE,
                Binding.DestinationType.QUEUE,
                QueueDefinition.FANNOUT_EXCHANGE,
                "",
                null
        )

        rabbitAdmin.declareQueue(queue)
        rabbitAdmin.declareBinding(binding)
    }

    private fun createFirstQueue(rabbitAdmin: RabbitAdmin) {
        val queue = QueueBuilder.durable(QueueDefinition.FIRST_QUEUE)
                .build()

        val binding = Binding(
                QueueDefinition.FIRST_QUEUE,
                Binding.DestinationType.QUEUE,
                QueueDefinition.FANNOUT_EXCHANGE,
                "",
                null
        )

        rabbitAdmin.declareQueue(queue)
        rabbitAdmin.declareBinding(binding)
    }
}

