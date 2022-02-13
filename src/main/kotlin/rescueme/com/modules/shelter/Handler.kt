package rescueme.com.modules.shelter

import arrow.core.Either
import rescueme.com.modules.shared.DomainException
import rescueme.com.modules.shared.Has

interface Context : Has.ShelterRepository

suspend fun Context.bindGetAll(): Either<DomainException, List<Shelter>> = TODO()
suspend fun Context.bindPost(): Either<DomainException, Shelter> = TODO()
suspend fun Context.bindGetByProvince(province: String): Either<DomainException, List<Shelter>> = TODO()
suspend fun Context.getById(shelterId: String): Either<DomainException, Shelter> = TODO()