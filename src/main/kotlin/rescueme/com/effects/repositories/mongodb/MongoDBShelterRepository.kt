package rescueme.com.effects.repositories.mongodb

import arrow.core.Option
import arrow.core.toOption
import rescueme.com.modules.shelter.Repository
import rescueme.com.modules.shelter.Shelter
import rescueme.com.modules.shelter.ShelterId
import java.util.*

class MongoDBShelterRepository : Repository {

    private val database: MutableMap<UUID, Shelter> = mutableMapOf()

    override suspend fun save(shelter: Shelter): Shelter {
        database[shelter.id.value] = shelter
        return shelter
    }

    override suspend fun getAll(): List<Shelter> =
        database.asSequence().map { it.value }.toList()

    override suspend fun getByProvince(province: String): List<Shelter> =
        database.asSequence().filter { it.value.province.name == province }.map { it.value }.toList()

    override suspend fun getById(shelterId: ShelterId): Option<Shelter> =
        database.asSequence()
            .firstOrNull { it.value.id == shelterId }
            ?.value.toOption()
}