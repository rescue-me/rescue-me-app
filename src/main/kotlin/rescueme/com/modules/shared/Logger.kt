package rescueme.com.modules.shared

interface Logger {
    suspend fun info(msg: String)
    suspend fun error(msg: String)
    suspend fun debug(msg: String)
}