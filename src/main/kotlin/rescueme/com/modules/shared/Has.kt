package rescueme.com.modules.shared

import rescueme.com.modules.users.Repository


sealed interface Has {
    interface Logger {
        val logger: rescueme.com.modules.shared.Logger
    }

    interface DogRepository {
        val repository: rescueme.com.modules.dog.Repository
    }

    interface NotificationRepository {
        val notificationRepository: rescueme.com.modules.shared.NotificationRepository
    }

    interface ShelterRepository {
        val repository: rescueme.com.modules.shelter.Repository
    }

    interface UserRepository {
        val repository: Repository
    }
}