package rescueme.com.modules.dog

import arrow.core.left
import arrow.core.right
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import rescueme.com.effects.repositories.kafka.NotificationTestRepository
import rescueme.com.modules.dogId
import rescueme.com.modules.dogName
import rescueme.com.modules.shared.BadRequest
import rescueme.com.modules.shelterId

class DogCoreTest : FunSpec({

    val dogRepository = mockk<Repository>()
    val dog = Dog(dogId.toString(), dogName, shelterId.toString())
    val context = object : Context {
        override val repository: Repository = dogRepository
        override val notificationRepository = NotificationTestRepository
    }

    context("Creating a new dog") {
        test("Should return dog saved when repository saves ok") {
            coEvery { dogRepository.save(dog) } returns dog
            val actual = context.bindPost(dog)
            actual shouldBe dog.right()
        }
    }

    context("Retrieving dogs") {
        test("Should return not empty list") {
            coEvery { dogRepository.getAll() } returns listOf(dog)
            val actual = context.bindGet()
            actual shouldBe listOf(dog).right()
        }

    }

    context("Retrieving dogs by shelter") {
        test("Should return dogs when found") {
            test("Should return not empty list") {
                coEvery { dogRepository.getByShelter(shelterId) } returns listOf(dog)
                val actual = context.bindGetByShelter(shelterId.toString())
                actual shouldBe listOf(dog).right()
            }
        }

        test("Should return empty list when shelter not found") {
            coEvery { dogRepository.getByShelter(shelterId) } returns emptyList()
            val actual = context.bindGetByShelter(shelterId.toString())
            actual shouldBe emptyList<Dog>().right()
        }

        test("Should return DomainException when shelter id is invalid") {
            val actual = context.bindGetByShelter("fake")
            actual shouldBe BadRequest.left()
        }
    }
})