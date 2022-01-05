package rescueme.com

import io.ktor.server.testing.*
import org.junit.Assert

fun TestApplicationResponse.contains(s: String) = Assert.assertTrue(
    "Content does not have $s ==> $content",
    content?.contains(s) ?: false)