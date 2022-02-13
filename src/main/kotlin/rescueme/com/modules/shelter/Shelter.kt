package rescueme.com.modules.shelter

import rescueme.com.modules.shared.Province
import java.util.*

data class Shelter(
    val id: ShelterId,
    val shelterName: ShelterName,
    val province: Province
) {
    companion object {
        operator fun invoke(id: String, shelterName: String, provinceName: String): Shelter =
            Shelter(
                ShelterId(id),
                ShelterName(shelterName),
                Province.apply(provinceName)
            )
    }
}

data class ShelterId(val value: UUID) {
    companion object {
        operator fun invoke(value: String) = ShelterId(UUID.fromString(value))
    }
}

@JvmInline
value class ShelterName(val name: String)