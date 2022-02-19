package rescueme.com.effects.repositories.mongo

import arrow.core.Option
import arrow.core.Some
import rescueme.com.modules.shared.Province
import rescueme.com.modules.shelter.Repository
import rescueme.com.modules.shelter.Shelter
import rescueme.com.modules.shelter.ShelterId
import rescueme.com.modules.shelter.ShelterName
import java.util.*

object ShelterTestRepository : Repository {
    override suspend fun save(shelter: Shelter): Shelter = shelter
    override suspend fun getAll(): List<Shelter> = listOf(
        Shelter(
            ShelterId(UUID.fromString("ead3f222-1c30-49e4-bfda-5000c582b1d6")),
            ShelterName("Budy"),
            Province.MADRID
        ),
        Shelter(
            ShelterId(UUID.fromString("5b11084b-ac6c-454f-9f4e-2be7c38a202b")),
            ShelterName("Kira"),
            Province.GUADALAJARA
        )
    )

    override suspend fun getByProvince(province: String): List<Shelter> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(shelterId: ShelterId): Option<Shelter> = Some(
        Shelter(
            ShelterId(UUID.fromString("ead3f222-1c30-49e4-bfda-5000c582b1d6")),
            ShelterName("Budy"),
            Province.MADRID
        )
    )
}