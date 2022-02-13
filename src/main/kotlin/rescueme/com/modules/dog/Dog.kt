package rescueme.com.modules.dog

import rescueme.com.modules.shelter.ShelterId
import java.util.*

data class Dog(
    val id: DogId,
    val name: DogName,
    val shelterId: ShelterId
) {
    companion object {
        operator fun invoke(id: String, name: String, shelterId: String) =
            Dog(DogId(id), DogName(name), ShelterId(shelterId))
    }
}

data class DogId(val value: UUID) {
    companion object {
        operator fun invoke(value: String) = DogId(UUID.fromString(value))
    }
}

@JvmInline
value class DogName(val name: String)
