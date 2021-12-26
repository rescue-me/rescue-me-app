package rescueme.com.effects.shared

sealed interface HasLive {
    interface DatabaseClient {
        val database: MutableMap<String, String>
    }
}