package rescueme.com.effects.repositories.mongodb

import rescueme.com.effects.shared.HasLive
import rescueme.com.modules.dog.Dog
import rescueme.com.modules.dog.Repository
import java.util.*

interface Context : HasLive.DatabaseClient

class MongoDBDogsRepository(ctx: Context) : Repository {

    private val database = ctx.database

    override suspend fun save(dog: Dog) = database.put(dog.id.toString(), dog).let { dog }
    override suspend fun getAll(): List<Dog> = database.map { it.value }
    override suspend fun getByShelter(uuid: UUID): List<Dog> = database.map { it.value }
}