package rescueme.com.modules.dog

import rescueme.com.modules.shared.Has

interface Context : Has.Logger, Has.DogRepository

suspend fun Context.bindPost(dog: Dog) = save(dog)