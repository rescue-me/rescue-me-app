package rescueme.com.modules.shared

sealed interface Has {
    interface Logger {
        val logger: rescueme.com.modules.shared.Logger
    }

    interface DogRepository {
        val repository: DogRepository
    }

    interface NotificationRepository {
        val notificationRepository: rescueme.com.modules.shared.NotificationRepository
    }

    interface ShelterRepository {
        val repository: ShelterRepository
    }
}