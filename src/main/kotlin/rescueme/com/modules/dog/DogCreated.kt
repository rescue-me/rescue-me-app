package rescueme.com.modules.dog

import rescueme.com.modules.shared.Message
import rescueme.com.modules.shelter.ShelterId
import java.time.LocalDateTime


data class DogCreated(
    val id: DogId,
    val name: DogName,
    val shelterId: ShelterId,
    val createdAt: LocalDateTime
) : Message {
    override val subType: String = "dog_created"

    companion object {
        fun apply(dog: Dog): DogCreated =
            DogCreated(dog.id, dog.name, dog.shelterId, LocalDateTime.now())
    }
}