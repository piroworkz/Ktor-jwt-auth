package davidluna.com.app.framework.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteAuthRequest(
    val username: String,
    val password: String
)