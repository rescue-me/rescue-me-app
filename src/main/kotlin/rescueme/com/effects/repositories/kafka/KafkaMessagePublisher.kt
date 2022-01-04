package rescueme.com.effects.repositories.kafka

import rescueme.com.modules.shared.Message
import rescueme.com.modules.shared.NotificationRepository

class KafkaMessagePublisher : NotificationRepository {
    override suspend fun publish(message: Message) {
        println("Publishing message .... $message")
    }
}