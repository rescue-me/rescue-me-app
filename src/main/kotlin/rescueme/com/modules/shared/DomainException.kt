package rescueme.com.modules.shared

sealed class DomainException : RuntimeException()
data class RepositoryGenericException(override val cause: Throwable? = null) : DomainException()
