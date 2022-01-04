package rescueme.com.modules.shared

import rescueme.com.modules.dog.Repository

sealed interface Has {
    interface Logger {
        val logger: rescueme.com.modules.shared.Logger
    }

    interface DogRepository {
        val repository: Repository
    }

    interface NotificationRepository {
        val notificationRepository: rescueme.com.modules.shared.NotificationRepository
    }
}