package rescueme.com.entry_point.dog

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

class DogModuleTest : FunSpec({

    test("Should retrieve all dogs") {
        setup {
            handleRequest(HttpMethod.Get, "/dogs").apply {
                response.contains("ead3f222-1c30-49e4-bfda-5000c582b1d6")
                response.contains("5b11084b-ac6c-454f-9f4e-2be7c38a202b")
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
