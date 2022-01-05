package rescueme.com.modules.dog

import java.util.*

interface Repository {
    suspend fun save(dog: Dog): Dog
    suspend fun getAll(): List<Dog>
    suspend fun getByShelter(uuid: UUID): List<Dog>
}