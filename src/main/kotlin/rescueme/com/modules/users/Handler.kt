package rescueme.com.modules.users

import arrow.core.computations.either
import rescueme.com.modules.shared.DomainException
import rescueme.com.modules.shared.Has
import rescueme.com.modules.shared.validate
import java.util.*

interface Context : Has.UserRepository, Has.NotificationRepository

suspend fun Context.bindGet(uuidString: String) = either<DomainException, User> {
    val uuid = validate { UUID.fromString(uuidString) }.bind()
    getByUUID(uuid).bind()
}

suspend fun Context.bindPost(uuidString: String) = either<DomainException, User> {
    val uuid = validate { UUID.fromString(uuidString) }.bind()
    save(uuid).bind()
}