package rescueme.com.entry_point.dog

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
import rescueme.com.effects.repositories.mongodb.MongoDBDogsRepository
import rescueme.com.effects.repositories.mongodb.MongoDBLayer.Companion.getLayer
import rescueme.com.entry_point.handleResult
import rescueme.com.entry_point.shared.badRequest
import rescueme.com.modules.dog.*
import rescueme.com.modules.shared.NotificationRepository

typealias BadRequest = TextContent

fun Application.module() {
    moduleWith(object : Context {
        val producerConfig = environment.config.config("kafka.producer")
        override val repository: Repository = MongoDBDogsRepository(getLayer())
        override val notificationRepository: NotificationRepository = KafkaMessagePublisher(
            KafkaProducer(producerProps(producerConfig)),
            Topic("dog_created")
        )
    })
}

fun Application.moduleWith(context: Context) {
    routing {
        route("/dogs") {
            get {
                val result: List<Dog> = handleResult { context.bindGet() }
                call.respond(result)
            }
            get("/shelter/{shelterId}") {
                when (val shelterId = Option.fromNullable(call.parameters["shelterId"])) {
                    is None -> call.respond(badRequest("Shelter name required"))
                    is Some -> call.respond(handleResult { context.bindGetByShelter(shelterId.value) })
                }
            }
            post {
                val result = either<BadRequest, Dog> {
                    Either.catch { call.receive<DogPayload>() }
                        .map { it.toDog() }
                        .mapLeft { badRequest(it.message ?: "Received an invalid Dog") }
                        .bind()
                }
                when (result) {
                    is Either.Left -> call.respond(result.value)
                    is Either.Right -> call.respond(handleResult { context.bindPost(result.value) })
                }
            }
        }

    }
}