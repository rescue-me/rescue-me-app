package rescueme.com.effects.repositories.mongo

import rescueme.com.modules.dog.Dog
import rescueme.com.modules.dog.DogId
import rescueme.com.modules.dog.DogName
import rescueme.com.modules.dog.Repository
import rescueme.com.modules.shelter.ShelterId
import java.util.*

object DogTestRepository : Repository {
    val shelterID = lazy { UUID.randomUUID() }
    override suspend fun save(dog: Dog) = dog
    override suspend fun getAll(): List<Dog> = listOf(
        Dog(
            DogId(UUID.fromString("ead3f222-1c30-49e4-bfda-5000c582b1d6")),
            DogName("Budy"),
            ShelterId(shelterID.value)
        ),
        Dog(
            DogId(UUID.fromString("5b11084b-ac6c-454f-9f4e-2be7c38a202b")),
            DogName("Kira"),
            ShelterId(shelterID.value)
        )
    )

    override suspend fun getByShelter(uuid: UUID): List<Dog> = getAll()
}