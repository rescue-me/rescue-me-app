package rescueme.com.modules.shared

sealed class DomainException : RuntimeException()
data class RepositoryGenericException(override val cause: Throwable? = null) : DomainException()
data class ResourceNotFoundException(override val message: String) : DomainException()
