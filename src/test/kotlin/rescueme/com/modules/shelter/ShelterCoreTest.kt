package rescueme.com.modules.shelter

import arrow.core.*
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import rescueme.com.modules.shared.ResourceNotFoundException
import java.util.*

class ShelterCoreTest : FunSpec({

    val shelterRepository = mockk<Repository>()
    val shelter = Shelter(UUID.randomUUID().toString(), "Shelter name", "madrid")

    val context = object : Context {
        override val repository: Repository = shelterRepository
    }
    context("Create new shelter") {

    }

    context("Retrieve all shelters") {
        test("Shelter found") {
            val expected = listOf(shelter)
            coEvery { shelterRepository.getAll() } returns expected
            val actual = context.bindGetAll()
            actual shouldBe expected.right()
        }

        test("Empty shelter list") {
            coEvery { shelterRepository.getAll() } returns emptyList()
            val actual = context.bindGetAll()
            actual shouldBe emptyList<Shelter>().right()
        }

    }

    context("Retrieve shelter by id") {
        test("Id cant be converted to Identifier") {
            val actual = context.bindGetById("fake")
            actual shouldBe ResourceNotFoundException("Shelter with id fake not found").left()
        }

        test("Shelter found") {
            coEvery { shelterRepository.getById(shelter.id) } returns Some(shelter)
            val actual = context.bindGetById(shelter.id.value.toString())
            actual shouldBe Either.Right(shelter.toOption())
        }
    }
})