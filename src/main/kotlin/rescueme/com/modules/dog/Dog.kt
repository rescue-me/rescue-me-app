package rescueme.com.modules.dog

import arrow.optics.optics
import rescueme.com.modules.shared.AggregateRoot
import java.util.*

@optics
data class Dog(
    override val id: UUID = UUID.randomUUID(),
    val name: String,
    val shelterName: String
) : AggregateRoot {
    companion object
}
