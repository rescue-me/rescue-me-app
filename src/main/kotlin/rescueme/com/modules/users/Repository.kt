package rescueme.com.modules.users

import arrow.core.Option
import java.util.*

interface Repository {
    suspend fun getByUUID(uuid: UUID): Option<User>
}