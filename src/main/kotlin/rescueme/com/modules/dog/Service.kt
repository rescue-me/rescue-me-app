package rescueme.com.modules.dog

import arrow.core.Either
import arrow.core.computations.either
import rescueme.com.modules.shared.Has

suspend fun <R> R.save(dog: Dog): Either<Throwable, Dog> where R : Has.DogRepository, R : Has.NotificationRepository =
    either {
        val dogSaved = repository.save(dog)
        notificationRepository.publish(DogCreated.apply(dogSaved))
        dogSaved
    }

suspend fun <R> R.getAll(): Either<Throwable, List<Dog>> where R : Has.DogRepository =
    either {
        repository.getAll()
    }

suspend fun <R> R.getByShelter(shelter: String): Either<Throwable, List<Dog>> where R : Has.DogRepository =
    either {
        repository.getAll().filter { it.shelterName.name == shelter }
    }