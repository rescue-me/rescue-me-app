package rescueme.com.effects.repositories.kafka

import rescueme.com.modules.shared.Message
import rescueme.com.modules.shared.NotificationRepository

object NotificationTestRepository : NotificationRepository {
    override suspend fun publish(message: Message) = println("Sending message .... $message")
}