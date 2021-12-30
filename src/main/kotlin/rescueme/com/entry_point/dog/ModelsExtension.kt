package rescueme.com.entry_point.dog

import rescueme.com.modules.dog.Dog

fun DogPayload.toDog(): Dog = Dog(name = name, shelterName = shelterName)