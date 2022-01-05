package rescueme.com.modules.users

import arrow.core.Either
import arrow.core.Option
import rescueme.com.modules.shared.DomainException
import rescueme.com.modules.shared.ResourceNotFoundException
import java.util.*

interface Repository {
    suspend fun retrieveIfExists(uuid: UUID): Option<User>
}

suspend fun Repository.getByUUID(uuid: UUID): Either<DomainException, User> =
    retrieveIfExists(uuid).toEither { ResourceNotFoundException("User with uuid $uuid not found") }