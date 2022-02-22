package rescueme.com.entry_point.shelter

import arrow.core.Either
import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.core.computations.either
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.apache.kafka.clients.producer.KafkaProducer
import rescueme.com.effects.repositories.kafka.KafkaMessagePublisher
import rescueme.com.effects.repositories.kafka.Topic
import rescueme.com.effects.repositories.kafka.producerProps
import rescueme.com.effects.repositories.mongodb.MongoDBShelterRepository
import rescueme.com.entry_point.handleResult
import rescueme.com.entry_point.shared.badRequest
import rescueme.com.modules.shelter.*

typealias BadRequest = TextContent

fun Application.module() {
    val producerConfig = environment.config.config("kafka.producer")
    moduleWith(object : Context {
        override val repository: Repository = MongoDBShelterRepository()
        override val notificationRepository =
            KafkaMessagePublisher(
                KafkaProducer(producerProps(producerConfig)),
                Topic("shelter_created")
            )
    })
}

fun Application.moduleWith(context: Context) {
    routing {
        route("/shelter") {
            get {
                call.respond(handleResult { context.bindGetAll() })
            }
            get("/{shelterId}") {
                when (val shelterId = Option.fromNullable(call.parameters["shelterId"])) {
                    is None -> call.respond(badRequest("Shelter name required"))
                    is Some -> call.respond(handleResult { context.bindGetById(shelterId.value) })
                }
            }
            get("/filter") {
                Option.fromNullable(call.request.queryParameters["province"])
                    .fold(
                        ifEmpty = { badRequest("Filtering needs at least one filter") },
                        ifSome = { call.respond(handleResult { context.bindGetByProvince(it) }) })
            }
            post {
                val result = either<BadRequest, Shelter> {
                    Either.catch { call.receive<ShelterPayload>().toShelter() }
                        .mapLeft { badRequest("Received an invalid shelter") }.bind()
                }
                when (result) {
                    is Either.Left -> call.respond(result.value)
                    is Either.Right -> call.respond(handleResult { context.bindPost(result.value) })
                }
            }
        }
    }
}