package rescueme.com.modules.shelter

import arrow.core.Either
import arrow.core.Option
import rescueme.com.modules.shared.DomainException
import rescueme.com.modules.shared.Has
import rescueme.com.modules.shared.RepositoryGenericException
import rescueme.com.modules.shared.ResourceNotFoundException

interface Context : Has.ShelterRepository, Has.NotificationRepository

suspend fun Context.bindGetAll(): Either<DomainException, List<Shelter>> =
    getAll().mapLeft { RepositoryGenericException() }

suspend fun Context.bindPost(shelter: Shelter): Either<DomainException, Shelter> =
    createShelter(shelter).mapLeft { RepositoryGenericException(it) }

suspend fun Context.bindGetByProvince(province: String): Either<DomainException, List<Shelter>> = TODO()
suspend fun Context.bindGetById(shelterId: String): Either<DomainException, Option<Shelter>> =
    getById(shelterId)
        .mapLeft { ResourceNotFoundException("Shelter with id $shelterId not found") }