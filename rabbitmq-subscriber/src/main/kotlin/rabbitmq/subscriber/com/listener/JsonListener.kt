package rabbitmq.subscriber.com.listener

import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.stereotype.Service

@Service
class JsonListener(private val messageConverter: MessageConverter) {

    @RabbitListener(queues = ["JSON-QUEUE-TEST"])
    fun receiveMessageFromJsonQueue(message: Message) {
        Thread.sleep(300)
        println("message receive: ${message.messageProperties.consumerQueue}")
        val person = messageConverter.fromMessage(message)
        println("body $person")
    }

    @RabbitListener(queues = ["FIRST-QUEUE-TEST"])
    fun receiveMessageFromFirstQueue(message: Message) {
        Thread.sleep(100)
        println("message receive: ${message.messageProperties.consumerQueue}")
        val person = messageConverter.fromMessage(message)
        println("body $person")
        Thread.sleep(100)
    }

    @RabbitListener(queues = ["SECOND-QUEUE-TEST"])
    fun receiveMessageFromSecondQueue(message: Message) {
        Thread.sleep(150)
        println("message receive: ${message.messageProperties.consumerQueue}")
        val person = messageConverter.fromMessage(message)
        println("body $person")

    }

}