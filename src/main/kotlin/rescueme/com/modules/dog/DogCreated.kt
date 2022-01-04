package rescueme.com.modules.dog

import rescueme.com.modules.shared.Message
import java.time.LocalDateTime


data class DogCreated(
    val id: DogId,
    val name: DogName,
    val shelterName: ShelterName,
    val createdAt: LocalDateTime
) : Message {
    override val subType: String = "dog_created"

    companion object {
        fun apply(dog: Dog): DogCreated =
            DogCreated(dog.id, dog.name, dog.shelterName, LocalDateTime.now())
    }
}