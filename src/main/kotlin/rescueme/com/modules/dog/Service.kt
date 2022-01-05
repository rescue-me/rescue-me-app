package rescueme.com.modules.dog

import arrow.core.Either
import arrow.core.computations.either
import rescueme.com.modules.shared.Has
import rescueme.com.modules.shared.validate
import java.util.*

typealias DogOutcome<R> = Either<Throwable, R>

suspend fun <R> R.save(dog: Dog): DogOutcome<Dog> where R : Has.DogRepository, R : Has.NotificationRepository =
    either {
        val dogSaved = repository.save(dog)
        notificationRepository.publish(DogCreated.apply(dogSaved))
        dogSaved
    }

suspend fun <R> R.getAll(): DogOutcome<List<Dog>> where R : Has.DogRepository =
    either {
        repository.getAll()
    }

suspend fun <R> R.getByShelter(shelter: String): DogOutcome<List<Dog>> where R : Has.DogRepository =
    either {
        val shelterId = validate { UUID.fromString(shelter) }.bind()
        repository.getByShelter(shelterId)
    }

