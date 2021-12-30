package rescueme.com.entry_point

import arrow.core.Either
import arrow.core.getOrHandle
import rescueme.com.modules.shared.DomainException

suspend inline fun <reified A : Any> handleResult(block: () -> Either<DomainException, A>): A =
    block().getOrHandle { throw it }