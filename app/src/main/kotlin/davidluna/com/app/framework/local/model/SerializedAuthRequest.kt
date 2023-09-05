package davidluna.com.app.framework.local.model

import davidluna.com.domain.Role
import kotlinx.serialization.Serializable

@Serializable
data class SerializedAuthRequest(
    val username: String,
    val password: String,
    val role: Role = Role.USER
)