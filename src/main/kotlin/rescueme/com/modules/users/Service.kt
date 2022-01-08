package rescueme.com.modules.users

import rescueme.com.modules.shared.Has
import rescueme.com.modules.shared.ResourceNotFoundException
import java.util.*

suspend fun <R> R.getByUUID(uuid: UUID) where R : Has.UserRepository =
    repository.getByUUID(uuid).toEither {
        ResourceNotFoundException("User with uuid $uuid not found")
    }
