package rabbitmq.subscriber.com

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableRabbit
@SpringBootApplication
class RabbitmqSubscriberApplication

fun main(args: Array<String>) {
	runApplication<RabbitmqSubscriberApplication>(*args)
}
