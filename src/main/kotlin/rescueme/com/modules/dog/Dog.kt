package rescueme.com.modules.dog

import java.util.*

data class Dog(
    val id: DogId,
    val name: DogName,
    val shelterName: ShelterName
) {
    companion object {
        fun apply(id: String, name: String, shelterName: String) =
            Dog(DogId.apply(id), DogName(name), ShelterName(shelterName))
    }
}

data class DogId(val value: UUID) {
    companion object {
        fun apply(value: String) = DogId(UUID.fromString(value))
    }
}

@JvmInline
value class DogName(val name: String)

@JvmInline
value class ShelterName(val name: String)
