package rabbitmq.publisher.com

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RabbitmqPublisherApplication

fun main(args: Array<String>) {
	runApplication<RabbitmqPublisherApplication>(*args)
}
