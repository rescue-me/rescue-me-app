package rescueme.com.modules.dog

import arrow.core.Either
import arrow.core.computations.either
import rescueme.com.modules.shared.Has

suspend fun <R> R.save(dog: Dog): Either<Throwable, Dog> where R : Has.DogRepository, R : Has.Logger =
    either {
        repository.save(dog).also { logger.info("### Saved dog with id ${dog.id}") }
    }

suspend fun <R> R.getAll(): Either<Throwable, List<Dog>> where R : Has.DogRepository, R : Has.Logger =
    either {
        repository.getAll().also { logger.info("### Retrieved dogs $it") }
    }

suspend fun <R> R.getByShelter(shelter: String): Either<Throwable, List<Dog>> where R : Has.DogRepository, R : Has.Logger =
    either {
        repository.getAll().filter { it.shelterName == shelter }
    }