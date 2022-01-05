package rescueme.com.modules.shelter

interface Repository {
    suspend fun save(dog: Shelter): Shelter
    suspend fun getAll(): List<Shelter>
    suspend fun getByProvince(province: String): List<Shelter>
}