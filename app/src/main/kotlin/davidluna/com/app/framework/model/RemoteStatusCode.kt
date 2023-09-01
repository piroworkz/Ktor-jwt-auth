package davidluna.com.app.framework.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteStatusCode(
    val code: Int,
    val description: String,
)
