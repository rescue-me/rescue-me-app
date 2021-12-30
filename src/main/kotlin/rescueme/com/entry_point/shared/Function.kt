package rescueme.com.entry_point.shared

import io.ktor.http.*
import io.ktor.http.content.*

fun badRequest(message: String) = TextContent(message, ContentType.Text.Plain, HttpStatusCode.BadRequest)