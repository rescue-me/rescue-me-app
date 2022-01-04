package rescueme.com.entry_point

import arrow.core.Either
import arrow.core.getOrHandle
import rescueme.com.modules.shared.DomainException

suspend inline fun <reified A : Any> handleResult(block: () -> Either<DomainException, A>): A =
    block().getOrHandle { throw it }

typealias FUN<A, B> = (A) -> B
infix fun <A, B, C> FUN<A, B>.andThen(other: FUN<B, C>): FUN<A, C> = { a: A -> other(this(a)) }