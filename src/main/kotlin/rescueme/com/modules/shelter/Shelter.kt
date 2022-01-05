package rescueme.com.modules.shelter

import java.util.*

data class ShelterId(val value: UUID) {
    companion object {
        fun apply(value: String) = ShelterId(UUID.fromString(value))
    }
}