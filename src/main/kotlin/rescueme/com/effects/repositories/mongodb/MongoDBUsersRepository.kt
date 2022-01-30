package rescueme.com.effects.repositories.mongodb

import arrow.core.Either
import arrow.core.Option
import arrow.core.Some
import rescueme.com.effects.shared.HasLive
import rescueme.com.modules.shared.DomainException
import rescueme.com.modules.users.Repository
import rescueme.com.modules.users.User
import java.util.*

interface LiveUserContext : HasLive.DatabaseClient

class MongoDBUsersRepository(ctx: LiveUserContext) : Repository {
    override suspend fun getByUUID(uuid: UUID): Option<User> = Some(User.apply(uuid.toString()))
    override suspend fun save(user: User): Either<DomainException, User> = Either.Right(user)
}