package davidluna.com.app.framework.model

import kotlinx.serialization.Serializable

@Serializable
data class SerializedUser(
    val username: String,
    val password: String,
    val salt: String,
    val role: String
)