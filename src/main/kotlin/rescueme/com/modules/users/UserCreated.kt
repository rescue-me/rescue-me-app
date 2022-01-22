package rescueme.com.modules.users

import rescueme.com.modules.shared.Message

data class UserCreated(
    val userId: UserId
) : Message {
    override val subType = "user_created"

    companion object {
        fun apply(user: User) = UserCreated(user.id)
    }
}
