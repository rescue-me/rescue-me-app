package rescueme.com.entry_point.dog

import arrow.core.Either
import arrow.core.computations.either
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import rescueme.com.effects.logger.ApplicationKtorLogger
import rescueme.com.effects.repositories.mongodb.MongoDBLayer.Companion.getLayer
import rescueme.com.effects.repositories.mongodb.MongoDBUserRepository
import rescueme.com.entry_point.handleResult
import rescueme.com.modules.dog.Context
import rescueme.com.modules.dog.Dog
import rescueme.com.modules.dog.Repository
import rescueme.com.modules.dog.bindPost
import rescueme.com.modules.shared.Logger

fun Application.module() {
    moduleWith(object : Context {
        override val logger: Logger = ApplicationKtorLogger(log)
        override val repository: Repository = MongoDBUserRepository(getLayer())
    })
}

fun Application.moduleWith(context: Context) {
    routing {
        route("/dogs") {
            post {
                val result = either<BadRequest, Dog> {
                    Either.catch { call.receive<Dog>() }
                        .mapLeft { badRequest(it.message ?: "Received an invalid Dog") }
                        .bind()
                }
                when (result) {
                    is Either.Left -> call.respond(result.value)
                    is Either.Right -> handleResult { context.bindPost(result.value) }
                }

            }
        }

    }
}
typealias BadRequest = TextContent

private fun badRequest(message: String): TextContent =
    TextContent(message, ContentType.Text.Plain, HttpStatusCode.BadRequest)