package rescueme.com.entry_point.shelter

import com.google.gson.Gson
import io.kotest.core.spec.style.FunSpec
import io.ktor.http.*
import io.ktor.server.testing.*
import rescueme.com.contains
import rescueme.com.effects.repositories.kafka.NotificationTestRepository
import rescueme.com.effects.repositories.mongo.ShelterTestRepository
import rescueme.com.main
import rescueme.com.modules.shared.NotificationRepository
import rescueme.com.modules.shelter.Context
import rescueme.com.modules.shelter.Repository

class ShelterModuleTest : FunSpec({
    context("Retrieve shelters") {
        test("Should retrieve all shelters") {
            setup {
                handleRequest(HttpMethod.Get, "/shelter").apply {
                    println(response.content)
                    response.contains("ead3f222-1c30-49e4-bfda-5000c582b1d6")
                    response.contains("5b11084b-ac6c-454f-9f4e-2be7c38a202b")
                }
            }
        }
        test("Should retrieve shelter by id") {
            setup {
                handleRequest(HttpMethod.Get, "/shelter/ead3f222-1c30-49e4-bfda-5000c582b1d6").apply {
                    println(response.content)
                    response.contains("ead3f222-1c30-49e4-bfda-5000c582b1d6")
                    response.contains("Budy")
                }
            }
        }
    }

    context("Create new shelter") {
        test("Should create new shelter") {
            setup {
                handleRequest(HttpMethod.Post, "/shelter") {
                    addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    setBody(
                        Gson().toJson(
                            ShelterPayload("Kitty", "guadalajara")
                        )
                    )
                }.apply {
                    println(response.content)
                    response.contains("Kitty")
                    response.contains("GUADALAJARA")
                }
            }
        }
    }

})

private fun setup(block: TestApplicationEngine.() -> TestApplicationCall) {
    withTestApplication({
        main()
        moduleWith(object : Context {
            override val repository: Repository = ShelterTestRepository
            override val notificationRepository: NotificationRepository = NotificationTestRepository
        })
    }) { block() }
}