package rescueme.com.modules.shelter

import arrow.core.Either
import arrow.core.Option
import arrow.core.computations.either
import rescueme.com.modules.shared.Has
import rescueme.com.modules.shared.validate

typealias ShelterOutcome<T> = Either<Throwable, T>

suspend fun <R> R.getAll(): ShelterOutcome<List<Shelter>> where R : Has.ShelterRepository =
    either { repository.getAll() }

suspend fun <R> R.getById(shelterId: String): ShelterOutcome<Option<Shelter>> where R : Has.ShelterRepository =
    either {
        val id = validate { ShelterId(shelterId) }.bind()
        repository.getById(id)
    }

suspend fun <R> R.bindPost(shelter: Shelter): ShelterOutcome<Shelter>
        where R : Has.ShelterRepository, R : Has.NotificationRepository =
    either {
        repository.save(shelter).also {
            notificationRepository.publish(
                ShelterCreated(shelter)
            )
        }
    }