package rescueme.com.modules.users

import arrow.core.None
import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import rescueme.com.modules.shared.BadRequest
import rescueme.com.modules.shared.ResourceNotFoundException
import java.util.*

class UsersCoreTest : FunSpec({

    val uuidString = "5f42a79c-6579-43ff-959d-c887d9738523"
    val uuid = UUID.fromString("5f42a79c-6579-43ff-959d-c887d9738523")
    val user = User.apply(uuidString)

    val context = object : Context {
        override val repository: Repository = mockk()
    }

    context("Retrieving users") {
        test("Should validate and retrieve user ok") {
            coEvery { context.repository.getByUUID(uuid) } returns user.toOption()

            val actual = context.bindGet(uuidString)

            actual shouldBe user.right()
        }

        test("Should return bad request when UUID is wrong") {
            val actual = context.bindGet("fake")
            actual shouldBe BadRequest.left()
        }

        test("Should return Resource not found when user does not exists") {
            coEvery { context.repository.getByUUID(uuid) } returns None

            val actual = context.bindGet(uuidString)

            actual shouldBe ResourceNotFoundException("User with uuid $uuidString not found").left()
        }
    }
})
