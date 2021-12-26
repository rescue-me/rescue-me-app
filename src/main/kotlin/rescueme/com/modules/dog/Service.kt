package rescueme.com.modules.dog

import rescueme.com.modules.shared.Has

suspend fun <R> R.save(dog: Dog) where R : Has.DogRepository, R : Has.Logger =
    repository.save(dog).also { logger.info("### Saved dog with id ${dog.id}") }