package rescueme.com.effects.logger

import rescueme.com.modules.shared.Logger

class ApplicationKtorLogger(private val logger: org.slf4j.Logger) : Logger {
    override suspend fun info(msg: String) = logger.info(msg)
    override suspend fun error(msg: String) = logger.error(msg)
    override suspend fun debug(msg: String) = logger.debug(msg)
}