package rescueme.com

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.main() {
    val env = environment.config.propertyOrNull("ktor.environment")?.getString()
    install(ContentNegotiation) { gson() }
    routing {
        get("/health") {
            call.respondText { "Up" }
        }
        get("/env") {
            call.respondText {
                when (env) {
                    "dev" -> "Development"
                    "prd" -> "Production"
                    else -> "..."
                }
            }
        }
    }
}