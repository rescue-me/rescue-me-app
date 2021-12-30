package rescueme.com.modules.dog

import arrow.core.Either
import rescueme.com.modules.shared.DomainException
import rescueme.com.modules.shared.Has
import rescueme.com.modules.shared.RepositoryGenericException

interface Context : Has.Logger, Has.DogRepository

suspend fun Context.bindPost(dog: Dog): Either<DomainException, Dog> =
    save(dog).mapLeft { RepositoryGenericException(it) }

suspend fun Context.bindGet(): Either<DomainException, List<Dog>> =
    getAll().mapLeft { RepositoryGenericException(it) }

suspend fun Context.bindGetByShelter(shelter: String): Either<DomainException, List<Dog>> =
    getByShelter(shelter).mapLeft { RepositoryGenericException(it) }