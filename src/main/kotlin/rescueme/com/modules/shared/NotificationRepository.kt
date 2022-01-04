package rescueme.com.modules.shared

interface NotificationRepository {
    suspend fun publish(message: Message)
}