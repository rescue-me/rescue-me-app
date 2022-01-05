package rescueme.com.modules.shelter

import arrow.core.Either
import rescueme.com.modules.shared.DomainException
import rescueme.com.modules.shared.Has

interface Context : Has.ShelterRepository

fun Context.bindGetAll(): Either<DomainException, List<Shelter>> = TODO()
fun Context.bindPost(): Either<DomainException, Shelter> = TODO()
fun Context.bindGetByProvince(): Either<DomainException, List<Shelter>> = TODO()