package rescueme.com.modules.dog

import rescueme.com.modules.shelter.ShelterId
import java.util.*

data class Dog(
    val id: DogId,
    val name: DogName,
    val shelterId: ShelterId
) {
    companion object {
        fun apply(id: String, name: String, shelterId: String) =
            Dog(DogId.apply(id), DogName(name), ShelterId.apply(shelterId))
    }
}

data class DogId(val value: UUID) {
    companion object {
        fun apply(value: String) = DogId(UUID.fromString(value))
    }
}

@JvmInline
value class DogName(val name: String)
