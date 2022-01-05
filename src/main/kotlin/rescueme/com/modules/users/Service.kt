package rescueme.com.modules.users

import arrow.core.computations.either
import rescueme.com.modules.shared.DomainException
import rescueme.com.modules.shared.Has
import java.util.*

suspend fun <R> R.retrieveIfExists(uuid: UUID) where R : Has.UserRepository =
    either<DomainException, User> {
        repository.getByUUID(uuid).bind()
    }