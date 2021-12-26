package rescueme.com.entry_point.shared

import io.ktor.application.*

fun Application.getProp(property: String, default: String): String = get(property) ?: default
fun Application.getProp(property: String, default: Int): Int = get(property)?.toInt() ?: default


private fun Application.get(property: String): String? = environment.config.propertyOrNull(property)?.getString()