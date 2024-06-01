package rabbitmq.publisher.com

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import rabbitmq.publisher.com.domain.Person

@RestController
class ExchangeController(
        private val rabbitTemplate: RabbitTemplate
) {

    @PostMapping("/{exchange}/{routingKey}")
    fun postOnExchange(
            @PathVariable exchange: String,
            @PathVariable routingKey: String,
            @RequestBody person: Person
    ): HttpEntity<Any?> {

        rabbitTemplate.convertAndSend(exchange, "", person)
        return ResponseEntity.ok().build()
    }
}