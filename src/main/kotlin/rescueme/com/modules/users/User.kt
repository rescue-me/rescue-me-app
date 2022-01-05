package rescueme.com.modules.users

import java.util.*

data class User(val id: UserId) {
    companion object {
        fun apply(id: String) = User(UserId.apply(id))
    }
}

data class UserId(val id: UUID) {
    companion object {
        fun apply(value: String) = UserId(UUID.fromString(value))
    }
}
