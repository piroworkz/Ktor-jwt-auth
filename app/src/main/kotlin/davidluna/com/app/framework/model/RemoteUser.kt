package davidluna.com.app.framework.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class RemoteUser(
    @BsonId
    val id: ObjectId = ObjectId(),
    val username: String,
    val password: String,
    val salt: String,
)

