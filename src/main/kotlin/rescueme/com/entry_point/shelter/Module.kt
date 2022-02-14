package rescueme.com.entry_point.shelter

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.core.toOption
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import rescueme.com.entry_point.handleResult
import rescueme.com.entry_point.shared.badRequest
import rescueme.com.modules.shelter.Context
import rescueme.com.modules.shelter.bindGetAll
import rescueme.com.modules.shelter.bindGetById
import rescueme.com.modules.shelter.bindGetByProvince


fun Application.module() {

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
                call.request.queryParameters["province"].toOption().fold(
                    ifEmpty = { badRequest("Filtering needs at least one filter") },
                    ifSome = { call.respond(handleResult { context.bindGetByProvince(it) }) }
                )

            }
        }
    }
}