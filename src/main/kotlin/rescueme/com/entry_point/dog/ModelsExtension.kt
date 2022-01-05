package rescueme.com.entry_point.dog

import rescueme.com.modules.dog.Dog
import java.util.*

fun DogPayload.toDog(): Dog = Dog.apply(UUID.randomUUID().toString(), name, shelterId)