package rescueme.com.effects.repositories.kafka

import io.ktor.config.*
import org.apache.kafka.clients.producer.ProducerConfig
import java.util.*

fun producerProps(props: ApplicationConfig) =
    Properties().apply {
        this[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = props.property("bootstrap.servers").getList()
        this[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = props.property("key.serializer").getString()
        this[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = props.property("value.serializer").getString()
    }