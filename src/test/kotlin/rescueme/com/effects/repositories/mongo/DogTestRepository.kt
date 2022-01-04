package rescueme.com.effects.repositories.mongo

import rescueme.com.modules.dog.*
import java.util.*

object DogTestRepository : Repository {
    override suspend fun save(dog: Dog) = dog
    override suspend fun getAll(): List<Dog> = listOf(
        Dog(
            DogId(UUID.fromString("ead3f222-1c30-49e4-bfda-5000c582b1d6")),
            DogName("Budy"),
            ShelterName("Frenchie shelter")
        ),
        Dog(
            DogId(UUID.fromString("5b11084b-ac6c-454f-9f4e-2be7c38a202b")),
            DogName("Kira"),
            ShelterName("Labrador shelter")
        )
    )
}