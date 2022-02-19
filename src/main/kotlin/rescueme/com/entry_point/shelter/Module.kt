package rescueme.com.entry_point.shelter

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import rescueme.com.effects.repositories.mongodb.MongoDBShelterRepository
import rescueme.com.entry_point.handleResult
import rescueme.com.entry_point.shared.badRequest
import rescueme.com.modules.shelter.*


fun Application.module() {
    moduleWith(object : Context {
        override val repository: Repository = MongoDBShelterRepository()
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
                Option.fromNullable(call.request.queryParameters["province"]).fold(
                    ifEmpty = { badRequest("Filtering needs at least one filter") },
                    ifSome = { call.respond(handleResult { context.bindGetByProvince(it) }) }
                )

            }
        }
    }
}