package rescueme.com.modules.users

import arrow.core.None
import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import rescueme.com.modules.shared.BadRequest
import rescueme.com.modules.shared.NotificationRepository
import rescueme.com.modules.shared.RepositoryGenericException
import rescueme.com.modules.shared.ResourceNotFoundException
import java.util.*

class UsersCoreTest : FunSpec({

    isolationMode = IsolationMode.InstancePerTest

    val uuidString = "5f42a79c-6579-43ff-959d-c887d9738523"
    val uuid = UUID.fromString("5f42a79c-6579-43ff-959d-c887d9738523")
    val user = User.invoke(uuidString)

    val context = object : Context {
        override val repository: Repository = mockk()
        override val notificationRepository: NotificationRepository = mockk()
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

    context("Creating a new user") {
        test("Should create a new user and publish message") {
            val userCreated = UserCreated(user.id)
            coEvery { context.repository.save(user) } returns user.right()
            coEvery { context.notificationRepository.publish(userCreated) } returns Unit

            context.bindPost(uuidString) shouldBe user.right()
        }

        test("Should return left with DomainException"){
            coEvery { context.repository.save(user) } returns RepositoryGenericException().left()

            context.bindPost(uuidString) shouldBe RepositoryGenericException().left()

            coVerify (exactly = 0) { context.notificationRepository.publish(any())  }
        }
    }
})
