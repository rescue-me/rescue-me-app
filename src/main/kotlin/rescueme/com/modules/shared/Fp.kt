package rescueme.com.modules.shared

import arrow.core.Either

fun <A> validate(block: () -> A) = try {
    Either.Right(block())
} catch (e: Exception) {
    Either.Left(BadRequest)
}