package rescueme.com.effects.repositories.mongodb

import io.ktor.application.*
import rescueme.com.effects.repositories.mongodb.Context as LiveDogContext

interface Props {

}

class MongoDBLayer private constructor(props: Props) : LiveDogContext {
    override val database: MutableMap<String, String> = mutableMapOf()

    companion object {
        fun Application.getLayer() = instance(object : Props {
            // get properties
        })

        @Volatile
        private var INSTANCE: MongoDBLayer? = null
        private fun instance(props: Props): MongoDBLayer =
            INSTANCE ?: synchronized(this) { INSTANCE ?: MongoDBLayer(props).also { INSTANCE = it } }
    }
}