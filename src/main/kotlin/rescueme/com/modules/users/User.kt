package rescueme.com.modules.users

import java.util.*

data class User(val id: UserId) {
    companion object {
        operator fun invoke(id: String) = User(UserId(id))
    }
}

data class UserId(val id: UUID) {
    companion object {
        operator fun invoke(value: String) = UserId(UUID.fromString(value))
    }
}
