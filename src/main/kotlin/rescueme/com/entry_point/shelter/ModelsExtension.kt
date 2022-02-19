package rescueme.com.entry_point.shelter

import rescueme.com.modules.shelter.Shelter
import java.util.*

fun ShelterPayload.toShelter(): Shelter =
    Shelter(UUID.randomUUID().toString(), name, province)