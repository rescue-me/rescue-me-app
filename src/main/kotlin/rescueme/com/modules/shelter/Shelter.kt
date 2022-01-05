package rescueme.com.modules.shelter

import rescueme.com.modules.shared.Province
import java.util.*

data class Shelter(
    val id: ShelterId,
    val shelterName: ShelterName,
    val province: Province
) {
    companion object {
        fun apply(id: String, shelterName: String, provinceName: String): Shelter =
            Shelter(
                ShelterId.apply(id),
                ShelterName(shelterName),
                Province.apply(provinceName)
            )
    }
}

data class ShelterId(val value: UUID) {
    companion object {
        fun apply(value: String) = ShelterId(UUID.fromString(value))
    }
}

@JvmInline
value class ShelterName(val name: String)