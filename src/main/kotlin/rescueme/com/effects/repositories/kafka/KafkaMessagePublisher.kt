package rescueme.com.effects.repositories.kafka

import com.google.gson.Gson
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import rescueme.com.modules.shared.Message
import rescueme.com.modules.shared.NotificationRepository

@JvmInline
value class Topic(val value: String)

class KafkaMessagePublisher(
    private val producer: KafkaProducer<String, String>,
    private val topic: Topic
) : NotificationRepository {
    override suspend fun publish(message: Message) {
        val gson = Gson()
        producer.send(
            ProducerRecord(topic.value, gson.toJson(message))
        ) { m: RecordMetadata, e: Exception? ->
            when (e) {
                null -> println("Produced record to topic ${m.topic()} partition [${m.partition()}] @ offset ${m.offset()}")
                else -> e.printStackTrace()
            }
        }
    }
}