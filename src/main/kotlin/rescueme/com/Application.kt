package rescueme.com

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.main() {
    val env = environment.config.propertyOrNull("ktor.environment")?.getString()
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
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