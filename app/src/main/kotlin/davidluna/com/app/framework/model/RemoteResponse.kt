package davidluna.com.app.framework.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteResponse<T>(
    val code: RemoteStatusCode,
    val message: String,
    val token: String,
    val response: T,
)