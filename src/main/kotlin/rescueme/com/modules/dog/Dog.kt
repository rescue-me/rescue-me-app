package rescueme.com.modules.dog

import rescueme.com.modules.shared.AggregateRoot
import java.util.*

data class Dog(
    override val id: UUID = UUID.randomUUID(),
    val name: String
) : AggregateRoot
