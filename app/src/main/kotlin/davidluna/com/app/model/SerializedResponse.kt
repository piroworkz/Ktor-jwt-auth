package davidluna.com.app.model

import kotlinx.serialization.Serializable

@Serializable
data class SerializedResponse<T>(
    val code: SerializedStatusCode,
    val message: String,
    val token: String,
    val body: T,
)