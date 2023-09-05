package davidluna.com.app.framework.remote.entities

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class MongoUser(
    @BsonId
    val id: ObjectId = ObjectId(),
    val username: String,
    val password: String,
    val salt: String,
    val role: String
)

