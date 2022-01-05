package rescueme.com.entry_point.dog

import com.google.gson.Gson
import io.kotest.core.spec.style.FunSpec
import io.ktor.http.*
import io.ktor.server.testing.*
import rescueme.com.contains
import rescueme.com.effects.repositories.kafka.NotificationTestRepository
import rescueme.com.effects.repositories.mongo.DogTestRepository
import rescueme.com.main
import rescueme.com.modules.dog.Context
import rescueme.com.modules.dog.Repository
import rescueme.com.modules.shared.NotificationRepository
import java.util.*

class DogModuleTest : FunSpec({

    context("Retrieve dogs") {
        test("Should retrieve all dogs") {
            setup {
                handleRequest(HttpMethod.Get, "/dogs").apply {
                    println(response.content)
                    response.contains("ead3f222-1c30-49e4-bfda-5000c582b1d6")
                    response.contains("5b11084b-ac6c-454f-9f4e-2be7c38a202b")
                }
            }
        }
        test("Should retrieve dog by shelter id") {
            setup {
                handleRequest(HttpMethod.Get, "/dogs/shelter/${DogTestRepository.shelterID.value}").apply {
                    println(response.content)
                    response.contains("ead3f222-1c30-49e4-bfda-5000c582b1d6")
                    response.contains(DogTestRepository.shelterID.value.toString())
                }
            }
        }
    }

    context("Create new dog") {
        test("Should create new dog") {
            val shelterID = UUID.randomUUID().toString()
            setup {
                handleRequest(HttpMethod.Post, "/dogs") {
                    addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    setBody(
                        Gson().toJson(
                            DogPayload("Budy-test", shelterID)
                        )
                    )
                }.apply {
                    println(response.content)
                    response.contains("Budy-test")
                    response.contains(shelterID)
                }
            }
        }
    }


})

private fun setup(block: TestApplicationEngine.() -> TestApplicationCall) {
    withTestApplication({
        main()
        moduleWith(object : Context {
            override val repository: Repository = DogTestRepository
            override val notificationRepository: NotificationRepository = NotificationTestRepository

        })
    }) { block() }
}
