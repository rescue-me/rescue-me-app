package rescueme.com.modules.dog

import arrow.core.Either
import arrow.core.computations.either
import rescueme.com.modules.shared.DomainException
import rescueme.com.modules.shared.Has
import rescueme.com.modules.shared.RepositoryGenericException

suspend fun <R> R.save(dog: Dog) where R : Has.DogRepository, R : Has.Logger =
    either<DomainException, Dog> {
        Either.catch { repository.save(dog).also { logger.info("### Saved dog with id ${dog.id}") } }
            .mapLeft { RepositoryGenericException(it) }.bind()
    }
