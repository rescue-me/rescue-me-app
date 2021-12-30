package rescueme.com.effects.shared

import rescueme.com.modules.dog.Dog

sealed interface HasLive {
    interface DatabaseClient {
        val database: MutableMap<String, Dog>
    }
}