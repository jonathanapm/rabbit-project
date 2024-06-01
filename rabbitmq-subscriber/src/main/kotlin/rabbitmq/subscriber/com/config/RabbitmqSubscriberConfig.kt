package rabbitmq.subscriber.com.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitmqSubscriberConfig {

    @Bean
    fun messageConverter(objectMapper: ObjectMapper): MessageConverter =
            Jackson2JsonMessageConverter(objectMapper)

}

