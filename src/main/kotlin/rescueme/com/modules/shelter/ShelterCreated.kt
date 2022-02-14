package rescueme.com.modules.shelter

import rescueme.com.modules.shared.Message
import java.time.LocalDateTime

data class ShelterCreated(
    val shelterId: ShelterId,
    val createdAt: LocalDateTime
) : Message {
    override val subType: String = "shelter_created"

    companion object {
        operator fun invoke(shelter: Shelter): ShelterCreated =
            ShelterCreated(shelter.id, LocalDateTime.now())
    }
}
