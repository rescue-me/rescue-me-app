package rescueme.com.entry_point.dog

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import rescueme.com.effects.logger.ApplicationKtorLogger
import rescueme.com.effects.repositories.mongodb.MongoDBLayer.Companion.getLayer
import rescueme.com.effects.repositories.mongodb.MongoDBUserRepository
import rescueme.com.modules.dog.Context
import rescueme.com.modules.dog.Repository
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
            get {
                call.respond(UserDTO("Budy"))
            }
            post {

            }
        }

    }
}

@Serializable
data class UserDTO(val name: String)