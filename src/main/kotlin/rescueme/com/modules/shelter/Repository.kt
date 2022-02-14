package rescueme.com.modules.shelter

import arrow.core.Option

interface Repository {
    suspend fun save(shelter: Shelter): Shelter
    suspend fun getAll(): List<Shelter>
    suspend fun getByProvince(province: String): List<Shelter>
    suspend fun getById(shelterId: ShelterId): Option<Shelter>
}