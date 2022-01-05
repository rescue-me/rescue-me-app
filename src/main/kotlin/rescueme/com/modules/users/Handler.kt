package rescueme.com.modules.users

import arrow.core.computations.ResultEffect.bind
import arrow.core.computations.either
import rescueme.com.modules.shared.DomainException
import rescueme.com.modules.shared.Has
import rescueme.com.modules.shared.validate
import java.util.*

interface Context : Has.UserRepository

suspend fun Context.bindGet(uuidString: String) = either<DomainException, User> {
    val uuid = validate { UUID.fromString(uuidString) }.bind()
    retrieveIfExists(uuid).bind()
}