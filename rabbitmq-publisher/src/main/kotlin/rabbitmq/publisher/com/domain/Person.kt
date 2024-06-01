package rabbitmq.publisher.com.domain

import java.time.LocalDate

data class Person(
        val name: String,
        val bornDate: LocalDate,
        val active: Boolean
)
