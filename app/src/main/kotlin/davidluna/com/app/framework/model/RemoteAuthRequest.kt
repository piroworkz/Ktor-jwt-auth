package davidluna.com.app.framework.model

import davidluna.com.domain.Role
import kotlinx.serialization.Serializable

@Serializable
data class RemoteAuthRequest(
    val username: String,
    val password: String,
    val role: Role = Role.USER
)