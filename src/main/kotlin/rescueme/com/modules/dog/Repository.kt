package rescueme.com.modules.dog

interface Repository {
    suspend fun save(dog: Dog): Dog
    suspend fun getAll(): List<Dog>
}