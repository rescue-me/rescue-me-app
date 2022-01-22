package rescueme.com.modules.users

import arrow.core.computations.either
import rescueme.com.modules.shared.DomainException
import rescueme.com.modules.shared.Has
import rescueme.com.modules.shared.ResourceNotFoundException
import java.util.*

suspend fun <R> R.getByUUID(uuid: UUID) where R : Has.UserRepository =
    repository.getByUUID(uuid).toEither {
        ResourceNotFoundException("User with uuid $uuid not found")
    }

suspend fun <R> R.save(uuid: UUID) where R : Has.UserRepository, R : Has.NotificationRepository =
    either<DomainException, User> {
        val user = User.apply(uuid.toString())
        repository.save(user).bind()
            .also { notificationRepository.publish(UserCreated.apply(user)) }
    }
