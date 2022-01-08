package rescueme.com.modules.shared

sealed class DomainException : RuntimeException()
object BadRequest: DomainException()
data class RepositoryGenericException(override val cause: Throwable? = null) : DomainException()
data class ResourceNotFoundException(override val message: String) : DomainException()
