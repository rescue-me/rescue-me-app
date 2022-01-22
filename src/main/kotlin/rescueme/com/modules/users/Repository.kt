package rescueme.com.modules.users

import arrow.core.Either
import arrow.core.Option
import rescueme.com.modules.shared.DomainException
import java.util.*

interface Repository {
    suspend fun getByUUID(uuid: UUID): Option<User>
    suspend fun save(user: User): Either<DomainException, User>
}